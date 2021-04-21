package bll;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import bll.validators.EmailValidator;
import bll.validators.ClientAgeValidator;
import bll.validators.Validator;
import dao.ClientDAO;
import model.Client;
import model.Orders;
import model.Product;

/**
 *
 * Pentru a valida clientul. Inainte sa se efectueze operatiile, se valideaza corectitudinea datelor.
 * Daca nu sunt corecte, se arunca o exceptie ce se vede in interfata.
 *
 */

public class ClientBLL {
    //Lista de validatori;
    private List<Validator<Client>> validators;
    ClientDAO operatiiSql;

    //Voi initializa validatorii in constructor;
    public ClientBLL() {
        operatiiSql = new ClientDAO();
        validators = new ArrayList<Validator<Client>>();
        validators.add(new EmailValidator());
        validators.add(new ClientAgeValidator());
    }

    //Find:
    public Client findClientByIdBLL(int id) {
        Client cl = operatiiSql.findClientById(id);
        //Daca nu gaseste un client, returneaza null si arunca exceptie
        if (cl == null) {
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        System.out.println("Am gasit: " + cl.toString());
        return cl;
    }

    //Find all:
    public List<Client> findAllClientsByIdBLL() {
        List<Client> cl = operatiiSql.findAllClientsById();

        //La fel si aici, doar ca returneaza un sir de clienti;
        if (cl == null) {
            throw new NoSuchElementException("The clients were not found!");
        }
        System.out.println("Am gasit clientii:");
        for(Client c: cl)
        {
            System.out.println(c.toString());
        }
        System.out.println("");
        return cl;
    }

    //Insert:
    public int insertClientBLL(Client client) {
        for (Validator<Client> c : validators) {
            c.validate(client); //Inainte de inserare este nevoie de validare;
        }
        System.out.println("Am inserat: " + client.toString());
        return operatiiSql.insertClient(client);
    }

    //Update:
    public int updateClientBLL(Client client) {
        for (Validator<Client> c : validators) {
            c.validate(client); //Seamana cu inserarea
        }
        System.out.println("Am updatat: " + client.toString());
        return operatiiSql.updateClient(client);
    }

    public void deleteClientByIdBLL(int id) {
        int cl = operatiiSql.deleteClientById(id);

        //0 succes, -1 eroare;
        if (cl == -1) {
            throw new NoSuchElementException("The client with id =" + id + " was not found to be deleted!");
        }
        System.out.println("Am sters clientul.");
    }
}