package dao;
import connection.ConnectionFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * In aceasta clasa folosim metoda de Reflection, pentru a putea folosi mai multe tabele ce se folosesc de aceasta
 * clasa. Aici avem operatii facute generic, folosind java reflection, pentru fielduri mai ales, pentru a obtine
 * metadate si pentru a le folosi sa invocam setteri si alte metode anume;
 *
 * @param <T> Este clasa in sine, data generic putem primi orice tabela anume;
 */

public class AbstractDao<T>
{
    //Pentru erori, si pentru clasa (acel type)
    protected static final Logger LOGGER = Logger.getLogger(AbstractDao.class.getName());
    private final Class<T> type;

    //Luam clasa:
    @SuppressWarnings("unchecked")
    public AbstractDao()
    {
        this.type = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0] ;
    }

    //Query creeat pentru select; Se face cu StringBuilder, pentru a concatena mai usor sirul;
    private String createSelectQuery(String field)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " = ?");
        //Returnam querieul final, ce a primit numele fieldului cautat;
        return sb.toString();
    }

    //FindById: aici facem si conexiunea, dupa care cu result set facem sa gasim obiectul si sa il identificam dupa
    //calitatile sale;
    public T findById(int id)
    {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String idTabela = type.getSimpleName();
        //Pentru a avea numele fieldului tabelei, deci clasei;
        String idTabelaRezultat = idTabela.toLowerCase(Locale.ENGLISH);
        String idNou = "id" + idTabelaRezultat;
        String query = createSelectQuery(idNou);
        try
        {
            //Se fac conectiunile, se face statementul, si se pune parametru idul la statement;
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            //Dupa executare query, returnam primul obiect gasit in lista calculata la metoda de dupa;

            return createObjects(resultSet).get(0);
        }
        catch(SQLException e)
        {
            LOGGER.log(Level.WARNING, type.getName() + "DAO : findId" + e.getMessage());
        }
        finally
        {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        //Daca nu am gasit;
        return null;
    }

    //Se creeaza o lista de obiecte din result set, de obiecte ce reprezinta instante de clase ce repr tabele;
    private List<T> createObjects(ResultSet resultSet)
    {
        //Avem lista de obiecte:
        List<T> list = new ArrayList<T>();
        try
        {
            //Mergem pe coloane in lina resultului:
            while(resultSet.next())
            {
                //Instantiem acel T, dupa care mergem din field in field;
                T instance = type.newInstance();
                for(Field field: type.getDeclaredFields())
                {
                    //Gasim din result set ce ne trebuie, folosind numele fieldurilor
                    Object value = resultSet.getObject(field.getName());
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);

                    //Dupa apelam metode de setteri pt a creea acele obiecte;
                    Method method = propertyDescriptor.getWriteMethod();
                    //Invocam acea metoda de set;
                    method.invoke(instance, value);
                }
                //Am adaugat acel obiect instantiat ca alt element al listei;
                list.add(instance);
            }
        }
        catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }

        //Returnam lista finala:
        return list;
    }

    //La fel ca la select normal, dar selectam toate elementele tabelei, toti clientii;
    private String createSelectAllQuery()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * "); //all
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        return sb.toString();
    }

    //Analog cu mai sus; Dar acum returnat tot setul, nu doar primul element;
    public List<T> findAllById()
    {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAllQuery();
        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        }
        catch(SQLException e)
        {
            LOGGER.log(Level.WARNING, type.getName() + "DAO : findId" + e.getMessage());
        }
        finally
        {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    //Pentru insert: dam argumentele necesare: adica
    //le gasim mai jos din fielduri, si aici le dam pentru a face query la insert;
    private String createInsertQuery(String[] fields) {
        StringBuilder sb = new StringBuilder();
        sb.append(" INSERT INTO ");
        sb.append(type.getSimpleName() + "(");

        int contor = 0;
        for(int i=0; i<fields.length; i++) {
            contor++;
            if(i == fields.length - 1) {
                sb.append(fields[i] + ") VALUES (");
                continue;
            }
            sb.append(fields[i] + ", ");
        }
        for(int i=0; i<contor; i++)
        {
            if(i == contor - 1)
            {
                sb.append("?)");
                continue;
            }
            sb.append("?, ");
        }
        return sb.toString();
    }

    //Inseram in tabela (nu validam inca aici)
    public int insert(T Inserare) {
        String query;
        //Avem un sir de lungimea nr de fielduri avute
        String[] FieldNames = new String[Inserare.getClass().getDeclaredFields().length];

        int contor = 0;
        //Facem fieldurile accesibile chiar daca private, si salvam numele fieldurilor;
        for (Field field : Inserare.getClass().getDeclaredFields())
        {
            field.setAccessible(true);
            FieldNames[contor] = field.getName();
            contor++;
        }

        //Avem queryul
        query = createInsertQuery(FieldNames);
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);

            //Avem si conexiunea, deci dupa ce facem iar accesibile fieldurile, incepem sa setam parametrii queryului;
            int counter = 1;
            for (Field field : Inserare.getClass().getDeclaredFields())
            {
                field.setAccessible(true);

                Object fieldActual = field.get(Inserare);
                statement.setObject(counter, fieldActual);
                counter++;
            }

            //Dupa inseram;
            statement.executeUpdate();
            return 0;
        } catch (SQLException | IllegalAccessException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO : insert" + e.getMessage());
        } finally {
            //Aici oprim conexiunile;
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        //Pentru daca nu am reusit:
        return -1;
    }

    //Asemanator cu update: tot ce avem extra este un id;
    //Acum avem nevoie si de idul obiectului cautat, nu doar de obiectul de inserat;
    //De asta avem 3 tipuri de constructori;
    private String createUpdateQuery(String[] fields) {
        //Facem ca mai sus queryul:
        StringBuilder sb = new StringBuilder();
        sb.append(" UPDATE ");
        sb.append(type.getSimpleName() + " ");

        String id = new String();
        for(int i=0; i<fields.length; i++) {
            if(i == 0)
            {
                id = fields[i];
                sb.append(" SET ");
            }
            if(i == fields.length - 1) {
                sb.append(fields[i] + " = ? WHERE " + id + " = ?");
                continue;
            }
            sb.append(fields[i] + " = ? , ");
        }
        return sb.toString();
    }

    //In update, aceeasi metoda, punem pe rand parametrii in statement;
    //Avem grija la acel id, si in rest este bine pus; (deci analog insert)
    public int update(T Updatare) {
        String query;
        String[] FieldNames = new String[Updatare.getClass().getDeclaredFields().length];

        int contor = 0;
        for (Field field : Updatare.getClass().getDeclaredFields())
        {
            field.setAccessible(true);
            FieldNames[contor] = field.getName();
            contor++;
        }

        query = createUpdateQuery(FieldNames);
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);

            int counter = 1;
            for (Field field : Updatare.getClass().getDeclaredFields())
            {
                if(counter == 1)
                {
                    field.setAccessible(true);

                    Object fieldActual = field.get(Updatare);
                    statement.setObject(Updatare.getClass().getDeclaredFields().length+1, fieldActual);
                    statement.setObject(counter, fieldActual);
                    counter++;
                    continue;
                }
                field.setAccessible(true);
                Object fieldActual = field.get(Updatare);
                statement.setObject(counter, fieldActual);
                counter++;
            }

            statement.executeUpdate();
            return 0;
        } catch (SQLException | IllegalAccessException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO : update" + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return -1;
    }

    //Un delete simplu, ne uitam doar la id; (trebuie gasit)
    private String createDeleteQuery(String field)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE "); //atat;
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " = ?");
        return sb.toString();
    }

    //Pentru stergere: iar avem nevoie de idul cautat pt query;
    public int deleteById(int id)
    {
        Connection connection = null;
        PreparedStatement statement = null;
        String idTabela = type.getSimpleName();
        String idTabelaRezultat = idTabela.toLowerCase(Locale.ENGLISH);
        String idNou = "id" + idTabelaRezultat;
        String query = createDeleteQuery(idNou);
        try
        {
            //Dupa facem conexiunea, si setam parametrul
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            //Nu avem nevoie de result set, pentru ca noi stergem elementul, deci dam execute;
            statement.executeUpdate();

            //Am terminat cu succes, sau la -1 am dat fail;
            return 0;
        }
        catch(SQLException e)
        {
            LOGGER.log(Level.WARNING, type.getName() + "DAO : deleteId" + e.getMessage());
        }
        finally
        {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return -1;
    }
}
