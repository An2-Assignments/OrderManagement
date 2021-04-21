package presentation;

import bll.ClientBLL;
import bll.OrdersBLL;
import bll.ProductBLL;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 *
 * Analog cu client, sunt mai putine operatii, aproape aceiasi getteri; Nu intru in detalii
 *
 */

public class ViewOrder extends JFrame
{
    //Analog cu client;
    private JLabel titlu = new JLabel("Alegeti o operatie de efectuat:");

    private JLabel insertOrder1 = new JLabel("Pentru insert: ");
    private JTextField idClient = new JTextField("Id Client");
    private JTextField idProdus = new JTextField("Id Produs");
    private JTextField desiredQuantity = new JTextField("Desired Quantity");
    private JButton insertOrder = new JButton("Insert order");

    private JLabel viewAllOrder1 = new JLabel("Pentru view: ");
    private JButton viewAllOrder = new JButton("View all orders");

    private JButton back = new JButton("Back");

    private ClientBLL clientBLL;
    private ProductBLL productBLL;
    private OrdersBLL ordersBLL;

    public ViewOrder(ClientBLL clientBLL, ProductBLL productBLL, OrdersBLL ordersBLL)
    {
        this.clientBLL = clientBLL;
        this.productBLL = productBLL;
        this.ordersBLL = ordersBLL;

        titlu.setFont(new Font("Times New Roman", Font.BOLD, 20));
        idClient.setFont(new Font("Times New Roman", Font.BOLD, 20));
        idProdus.setFont(new Font("Times New Roman", Font.BOLD, 20));
        desiredQuantity.setFont(new Font("Times New Roman", Font.BOLD, 20));
        insertOrder1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        insertOrder.setFont(new Font("Times New Roman", Font.BOLD, 20));
        viewAllOrder1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        viewAllOrder.setFont(new Font("Times New Roman", Font.BOLD, 20));
        back.setFont(new Font("Times New Roman", Font.BOLD, 30));

        idClient.setPreferredSize(new Dimension(200, 60));
        idProdus.setPreferredSize(new Dimension(200, 60));
        desiredQuantity.setPreferredSize(new Dimension(200, 60));

        insertOrder1.setPreferredSize(new Dimension(200, 60));
        insertOrder.setPreferredSize(new Dimension(200, 60));
        viewAllOrder1.setPreferredSize(new Dimension(200, 60));
        viewAllOrder.setPreferredSize(new Dimension(200, 60));
        back.setPreferredSize(new Dimension(200, 60));

        JPanel c = new JPanel();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        c.setPreferredSize(new Dimension(1500, 900));
        c.setBackground(Color.green);

        JPanel rand0 = new JPanel();
        rand0.setLayout(new FlowLayout());
        rand0.setBackground(Color.red);

        JPanel rand1 = new JPanel();
        rand1.setLayout(new FlowLayout());
        rand1.setBackground(Color.red);

        JPanel rand4 = new JPanel();
        rand4.setLayout(new FlowLayout());
        rand4.setBackground(Color.red);

        rand0.add(titlu);
        rand0.add(Box.createRigidArea(new Dimension(0, 50)));

        rand1.add(insertOrder1);
        rand1.add(Box.createRigidArea(new Dimension(10, 0)));
        rand1.add(idClient);
        rand1.add(Box.createRigidArea(new Dimension(10, 0)));
        rand1.add(idProdus);
        rand1.add(Box.createRigidArea(new Dimension(10, 0)));
        rand1.add(desiredQuantity);
        rand1.add(Box.createRigidArea(new Dimension(10, 0)));
        rand1.add(insertOrder);
        rand1.add(Box.createRigidArea(new Dimension(10, 0)));

        rand4.add(viewAllOrder1);
        rand4.add(Box.createRigidArea(new Dimension(30, 0)));
        rand4.add(viewAllOrder);
        rand4.add(Box.createRigidArea(new Dimension(30, 0)));
        rand4.add(back);
        rand4.add(Box.createRigidArea(new Dimension(30, 0)));

        c.add(rand0);
        c.add(Box.createRigidArea(new Dimension(0, 20)));
        c.add(rand1);
        c.add(Box.createRigidArea(new Dimension(0, 20)));
        c.add(rand4);
        c.add(Box.createRigidArea(new Dimension(0, 20)));

        this.setContentPane(c);
        this.pack();
        this.setTitle("Tabela order");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //Operatiile si getterii si butonul de inapoi (toate string, urmeaza sa fie convertite cand este nevoie de asta):
    void addOrder(ActionListener b)
    {
        insertOrder.addActionListener(b);
    }

    void viewAllOrders(ActionListener b)
    {
        viewAllOrder.addActionListener(b);
    }

    void inapoi(ActionListener b)
    {
        back.addActionListener(b);
    }

    public String getIdClient() {
        return idClient.getText();
    }

    public String getIdProdus() {
        return idProdus.getText();
    }

    public String getDesiredQuantity() {
        return desiredQuantity.getText();
    }
}
