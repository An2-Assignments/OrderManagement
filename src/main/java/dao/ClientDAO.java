package dao;
import model.Client;

import java.util.List;

/**
 *
 * Pentru extinderea AbstractDao, prima data cu clientul. Folosim metodele generice,
 * facute cu ajutorul java reflection. Doar apelam niste metode cu parametrii dati.
 *
 */

public class ClientDAO extends AbstractDao<Client>
{
    public ClientDAO()
    {

    }

    //Operatiile pe rand: pentru ca mostenim AbstracDao, doar apelam metodele, acum pe tipurile tabelelor;
    //Find:
    public Client findClientById(int id)
    {
        return findById(id);
    }

    //Find all:
    public List<Client> findAllClientsById()
    {
        return findAllById();
    }

    //Insert:
    public int insertClient(Client client)
    {
        return insert(client);
    }

    //Update:
    public int updateClient(Client client)
    {
        return update(client);
    }

    //Delete:
    public int deleteClientById(int id) { return deleteById(id); }
}
