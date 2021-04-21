package start;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import bll.ClientBLL;
import bll.OrdersBLL;
import bll.ProductBLL;
import model.Client;
import model.Product;
import model.Orders;
import presentation.*;


/**
 *
 * Clasa main. Aici creez view, model si controller. Modelul este reprezentat de BLL. Acolo validam si avem acces
 * la metodele din AbstractDao; Dupa, folosim modelul la view, si dupa pentru controler folosim si model si view.
 * Doar apelam aceste instante de clase, restul logicii fiind impartita peste tot;
 *
 */

public class Start {
    protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

    public static void main(String[] args) throws SQLException {

        //Model:
        ClientBLL clientBll = new ClientBLL();
        ProductBLL productBll = new ProductBLL();
        OrdersBLL ordersBll = new OrdersBLL();

        //View:
        View view = new View(clientBll, productBll, ordersBll);
        ViewClient viewclient = new ViewClient(clientBll, productBll, ordersBll);
        ViewProduct viewprodus = new ViewProduct(clientBll, productBll, ordersBll);
        ViewOrder vieworder = new ViewOrder(clientBll, productBll, ordersBll);
        ViewTable viewtable = new ViewTable(clientBll, productBll, ordersBll);

        //Controller:
        Controller controller = new Controller(view, viewclient, viewprodus, vieworder, viewtable,
                clientBll, productBll, ordersBll);
    }
}

