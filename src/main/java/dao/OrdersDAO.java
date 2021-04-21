package dao;
import model.Orders;
import java.util.List;

/**
 *
 * Analog cu client, doar apelam metode mostenite, de la java reflection
 *
 */

public class OrdersDAO extends AbstractDao<Orders>{
    public OrdersDAO()
    {
    }

    //Find:
    public Orders findOrdersById(int id)
    {
        return findById(id);
    }

    //Find all:
    public List<Orders> findAllOrdersById()
    {
        return findAllById();
    }

    //Insert:
    public int insertOrders(Orders orders)
    {
        return insert(orders);
    }
}
