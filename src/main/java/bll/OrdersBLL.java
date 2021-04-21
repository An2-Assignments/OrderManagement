package bll;

import bll.validators.DesiredQuantityValidator;
import bll.validators.Validator;
import dao.OrdersDAO;
import model.Orders;
import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *
 * Pentru a valida fieldurile din orders, inainte sa se execute operatii pe baza de date.
 * La fel ca si client si products, doar ca cu mai putine operatii (doar insert si find)
 *
 */

public class OrdersBLL {
    private List<Validator<Orders>> validators;
    OrdersDAO operatiiSql;

    //Analog cu client; (pentru toate metodele de mai jos)
    public OrdersBLL() {
        operatiiSql = new OrdersDAO();
        validators = new ArrayList<Validator<Orders>>();
        validators.add(new DesiredQuantityValidator());
    }

    //Find:
    public Orders findOrdersByIdBLL(int id) {
        Orders or= operatiiSql.findOrdersById(id);

        if (or == null) {
            throw new NoSuchElementException("The order with id =" + id + " was not found!");
        }
        System.out.println("Am gasit: " + or.toString());
        return or;
    }

    //Find all:
    public List<Orders> findAllOrdersByIdBLL() {
        List<Orders> or = operatiiSql.findAllOrdersById();
        if (or == null) {
            throw new NoSuchElementException("The orders were not found!");
        }
        System.out.println("Am gasit orderurile:");
        for(Orders o: or)
        {
            System.out.println(o.toString());
        }
        System.out.println("");
        return or;
    }

    //Insert:
    public int insertOrdersBLL(Orders orders) {
        for (Validator<Orders> o : validators) {
            o.validate(orders);
        }
        System.out.println("Am inserat: " + orders.toString());
        return operatiiSql.insertOrders(orders);
    }
}
