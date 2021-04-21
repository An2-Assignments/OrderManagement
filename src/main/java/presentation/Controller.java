package presentation;

import bll.ClientBLL;
import bll.OrdersBLL;
import bll.ProductBLL;
import model.Client;
import model.Orders;
import model.Product;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * Avem controllerul din MVC. Aici controlam toate viewurile, si cand se apasa butoanele.
 * Folosim, in clasele cu butoane, toate operatiile descrise la alte clase;
 * Doar ca aici, pentru ca apasam butoane, apelam metodele direct;
 *
 */

public class Controller
{
    //Avem fieldurile pentru viewuri si modelele, salvate in BLL (vin cu validari apelarile de metode BLL)
    private View view;
    private ViewClient viewclient;
    private ViewProduct viewproduct;
    private ViewOrder vieworder;
    private ViewTable viewtable;

    private ClientBLL clientBLL;
    private ProductBLL productBLL;
    private OrdersBLL ordersBLL;

    //Pentru scrierea in fisier:
    private static FileWriter F;
    private int scriereFisier = 0;

    public Controller(View view, ViewClient viewclient,
                      ViewProduct viewproduct, ViewOrder vieworder, ViewTable viewtable,
                      ClientBLL clientBLL, ProductBLL productBLL, OrdersBLL ordersBLL)
    {
        this.view = view;
        this.viewclient = viewclient;
        this.viewproduct = viewproduct;
        this.vieworder = vieworder;
        this.viewtable = viewtable;

        //Primul view , si dupa putem merge printre ele;
        view.setVisible(true);

        this.clientBLL = clientBLL;
        this.productBLL = productBLL;
        this.ordersBLL = ordersBLL;

        //Butoanele, impartite pe categorii:
        view.alegereClient(new alegereClientListener());
        view.alegereProduct(new alegereProductListener());
        view.alegereOrder(new alegereOrderListener());

        viewclient.addClient(new addClientListener());
        viewclient.updateClient(new updateClientListener());
        viewclient.deleteClient(new deleteClientListener());
        viewclient.viewAllClients(new viewAllClientsListener());
        viewclient.inapoi(new inapoiClientListener());

        viewproduct.addProduct(new addProductListener());
        viewproduct.updateProduct(new updateProductListener());
        viewproduct.deleteProduct(new deleteProductListener());
        viewproduct.viewAllProducts(new viewAllProductsListener());
        viewproduct.inapoi(new inapoiProductListener());

        vieworder.addOrder(new addOrderListener());
        vieworder.viewAllOrders(new viewAllOrdersListener());
        vieworder.inapoi(new inapoiOrderListener());

        viewtable.inapoiTable(new inapoiTableListener());
    }

    //Toate clasele butoanelor:
    //Avem intai cele 3 butoane ce ne duc la tabele, dupa care avem
    //butoanele ce se ocupa de executarea operatiilor, dupa care avem butoane
    //ce se ocupa de mersul inapoi, la alte viewuri.
    //Nu voi explica fiecare clasa;
    class alegereClientListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            //Se face vizibila doar una din doua, aleasa;
            viewclient.setVisible(true);
            view.setVisible(false);
        }
    }

    class alegereProductListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            viewproduct.setVisible(true);
            view.setVisible(false);
        }
    }

    class alegereOrderListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            vieworder.setVisible(true);
            view.setVisible(false);
        }
    }

    class addClientListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                //Luam din view datele, si le testam in try catch, pentru mesaj de eroare sau mesaj de succes;
                //Facem asta pentru fiecare operatie;
                String name = viewclient.getNameClient();
                String address = viewclient.getAddress();
                String email = viewclient.getEmail();
                int age = Integer.parseInt(viewclient.getAge());
                Client client = new Client(name, address, email, age);
                clientBLL.insertClientBLL(client); //
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(viewclient, ex.getMessage(),
                        "Eroare", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(viewclient, "Inserarea clientului a fost facuta cu succes.",
                    "Succes", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    class updateClientListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                int id = Integer.parseInt(viewclient.getId1());
                String name = viewclient.getName1();
                String address = viewclient.getAddress1();
                String email = viewclient.getEmail1();
                int age = Integer.parseInt(viewclient.getAge1());

                Client client = new Client(id, name, address, email, age);
                clientBLL.findClientByIdBLL(id);
                clientBLL.updateClientBLL(client);

                JOptionPane.showMessageDialog(viewclient, "Updatarea clientului a fost facuta cu succes.",
                        "Succes", JOptionPane.INFORMATION_MESSAGE);
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(viewclient, ex.getMessage(),
                        "Eroare", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    class deleteClientListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                int id = Integer.parseInt(viewclient.getId2());
                clientBLL.findClientByIdBLL(id);
                clientBLL.deleteClientByIdBLL(id);

                JOptionPane.showMessageDialog(viewclient, "Stergerea clientului a fost facuta cu succes.",
                        "Succes", JOptionPane.INFORMATION_MESSAGE);
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(viewclient, ex.getMessage(),
                        "Eroare", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    class viewAllClientsListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                clientBLL.findAllClientsByIdBLL();

                //Pentru viewAll, am salvat matricea ce contine datele din Client, pentru a le putea afisa cu JTable;
                //Nu am folosit reflection aici;
                List<Client> rez = clientBLL.findAllClientsByIdBLL();
                String mat[][] = new String[rez.size()][4];
                int contor1 = 0;

                String col[] = new String[4];
                col[0] = "Name";
                col[1] = "Address";
                col[2] = "Email";
                col[3] = "Age";

                for(Client c: rez)
                {
                    int contor2 = 0;
                    mat[contor1][contor2] = c.getName();
                    contor2++;
                    mat[contor1][contor2] = c.getAddress();
                    contor2++;
                    mat[contor1][contor2] = c.getEmail();
                    contor2++;
                    mat[contor1][contor2] = Integer.toString(c.getAge());
                    contor2++;
                    contor1++;
                }
                viewtable.setTable(mat, col);

                viewclient.setVisible(false);
                viewtable.setVisible(true);
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(viewclient, ex.getMessage(),
                        "Eroare", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    //Un buton de inapoi, iar cu vizibilitate;
    class inapoiClientListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            view.setVisible(true);
            viewclient.setVisible(false);
        }
    }

    //La fel ca la client, si la product si la orders:
    class addProductListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                String name = viewproduct.getNameProduct();
                int quantity = Integer.parseInt(viewproduct.getQuantity());
                int price = Integer.parseInt(viewproduct.getPrice());

                Product product = new Product(quantity, name, price);
                productBLL.insertProductBLL(product);

                JOptionPane.showMessageDialog(viewproduct, "Inserarea produsului a fost facuta cu succes.",
                        "Succes", JOptionPane.INFORMATION_MESSAGE);
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(viewproduct, ex.getMessage(),
                        "Eroare", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    class updateProductListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                int id = Integer.parseInt(viewproduct.getId1());
                String name = viewproduct.getName1();
                int quantity = Integer.parseInt(viewproduct.getQuantity1());
                int price = Integer.parseInt(viewproduct.getPrice1());

                Product product = new Product(id, quantity, name, price);
                productBLL.findProductByIdBLL(id);
                productBLL.updateProductBLL(product);

                JOptionPane.showMessageDialog(viewproduct, "Updatarea produsului a fost facuta cu succes.",
                        "Succes", JOptionPane.INFORMATION_MESSAGE);
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(viewproduct, ex.getMessage(),
                        "Eroare", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    class deleteProductListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                int id = Integer.parseInt(viewproduct.getId2());
                productBLL.findProductByIdBLL(id);
                productBLL.deleteProductByIdBLL(id);
                JOptionPane.showMessageDialog(viewproduct, "Stergerea produsului a fost facuta cu succes.",
                        "Succes", JOptionPane.INFORMATION_MESSAGE);
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(viewproduct, ex.getMessage(),
                        "Eroare", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    class viewAllProductsListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                //Analog client;
                productBLL.findAllProductsByIdBLL();

                List<Product> rez = productBLL.findAllProductsByIdBLL();
                String mat[][] = new String[rez.size()][3];
                int contor1 = 0;

                String col[] = new String[3];
                col[0] = "Quantity";
                col[1] = "Name";
                col[2] = "Price";

                for(Product p: rez)
                {
                    int contor2 = 0;
                    mat[contor1][contor2] = Integer.toString(p.getQuantity());
                    contor2++;
                    mat[contor1][contor2] = p.getName();
                    contor2++;
                    mat[contor1][contor2] = Integer.toString(p.getPrice());
                    contor2++;
                    contor1++;
                }
                viewtable.setTable(mat, col);

                viewtable.setVisible(true);
                viewproduct.setVisible(false);
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(viewproduct, ex.getMessage(),
                        "Eroare", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    class inapoiProductListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            view.setVisible(true);
            viewproduct.setVisible(false);
        }
    }

    class addOrderListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                int idc = Integer.parseInt(vieworder.getIdClient());
                int idp = Integer.parseInt(vieworder.getIdProdus());
                int desiredQ = Integer.parseInt(vieworder.getDesiredQuantity());

                clientBLL.findClientByIdBLL(idc);
                Product nouaCantitate = productBLL.findProductByIdBLL(idp);

                //Aici facem logica de decrementare a cantitatii de produse;
                //Daca este under stock, exista un mesaj special;
                int QActual = nouaCantitate.getQuantity();
                int QNou = QActual - desiredQ;
                if(QNou >= 0)
                {
                    Orders orders = new Orders(idc, idp, desiredQ);
                    ordersBLL.insertOrdersBLL(orders);

                    Client cli = clientBLL.findClientByIdBLL(orders.getIdClient());
                    Product pro = productBLL.findProductByIdBLL(orders.getIdProdus());

                    String idOr = Integer.toString(orders.getIdOrders());
                    String idCl = Integer.toString(orders.getIdClient());
                    String idPr = Integer.toString(orders.getIdProdus());
                    String DQuant = Integer.toString(orders.getDesiredQuantity());

                    scriereFisier++;
                    F = new FileWriter("Bill" + scriereFisier + ".txt");

                    //Scriem un bill generat, dupa inserarea in aceasta tabela;
                    //Scriu in fisier; (nu in PDF)
                    try {
                        F.write("The client: " + cli.toString() + "\nThe product: " +
                                pro.toString() + "\nThe quantity: " + DQuant +"\nDone.");
                    } catch (IOException exc) {
                        System.out.println("An error occurred when writing the text.");
                        exc.printStackTrace();
                    }

                    F.close();
                    nouaCantitate.setQuantity(QNou);
                    productBLL.updateProductBLL(nouaCantitate);
                }
                else
                {
                    JOptionPane.showMessageDialog(vieworder, "Produsul este under stocked, nu poate fi cumparat.",
                            "Eroare", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                JOptionPane.showMessageDialog(vieworder, "Inserarea orderului a fost facuta cu succes.",
                        "Succes", JOptionPane.INFORMATION_MESSAGE);
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(vieworder, ex.getMessage(),
                        "Eroare", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    class viewAllOrdersListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                ordersBLL.findAllOrdersByIdBLL();

                List<Orders> rez = ordersBLL.findAllOrdersByIdBLL();
                String mat[][] = new String[rez.size()][3];
                int contor1 = 0;

                String col[] = new String[3];
                col[0] = "IdClient";
                col[1] = "IdProdus";
                col[2] = "DesiredQuantity";

                for(Orders o: rez)
                {
                    int contor2 = 0;
                    mat[contor1][contor2] = Integer.toString(o.getIdClient());
                    contor2++;
                    mat[contor1][contor2] = Integer.toString(o.getIdProdus());
                    contor2++;
                    mat[contor1][contor2] = Integer.toString(o.getDesiredQuantity());
                    contor2++;
                    contor1++;
                }
                viewtable.setTable(mat, col);

                viewtable.setVisible(true);
                vieworder.setVisible(false);
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(vieworder, ex.getMessage(),
                        "Eroare", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    class inapoiOrderListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            view.setVisible(true);
            vieworder.setVisible(false);
        }
    }

    class inapoiTableListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            view.setVisible(true);
            viewtable.setVisible(false);
        }
    }
}
