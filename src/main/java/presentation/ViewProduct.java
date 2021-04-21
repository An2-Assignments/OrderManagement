package presentation;

import bll.ClientBLL;
import bll.OrdersBLL;
import bll.ProductBLL;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 *
 * Analog cu client, acelasi numar de operatii, getteri asemanatori;
 *
 */

public class ViewProduct extends JFrame
{
    //Analog cu client, nu am ce adauga;
    private JLabel titlu = new JLabel("Alegeti o operatie de efectuat:");

    private JLabel insertProduct1 = new JLabel("Pentru insert: ");
    private JTextField name = new JTextField("Name");
    private JTextField Quantity = new JTextField("Quantity");
    private JTextField Price = new JTextField("Price");
    private JButton insertProduct = new JButton("Insert Product");

    private JLabel updateProduct1 = new JLabel("Pentru update: ");
    private JTextField id1 = new JTextField("Id");
    private JTextField name1 = new JTextField("Name");
    private JTextField Quantity1 = new JTextField("Quantity");
    private JTextField Price1 = new JTextField("Price");
    private JButton updateProduct = new JButton("Edit Product");

    private JLabel deleteProduct1 = new JLabel("Pentru delete: ");
    private JTextField id2 = new JTextField("Id");
    private JButton deleteProduct = new JButton("Delete Product");

    private JLabel viewAllProduct1 = new JLabel("Pentru view: "); //Nimic altceva
    private JButton viewAllProduct = new JButton("View all products");

    private JButton back = new JButton("Back");

    private ClientBLL clientBLL;
    private ProductBLL productBLL;
    private OrdersBLL ordersBLL;

    public ViewProduct(ClientBLL clientBLL, ProductBLL productBLL, OrdersBLL ordersBLL)
    {
        this.clientBLL = clientBLL;
        this.productBLL = productBLL;
        this.ordersBLL = ordersBLL;

        titlu.setFont(new Font("Times New Roman", Font.BOLD, 20));
        name.setFont(new Font("Times New Roman", Font.BOLD, 20));
        Quantity.setFont(new Font("Times New Roman", Font.BOLD, 20));
        Price.setFont(new Font("Times New Roman", Font.BOLD, 20));
        id1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        name1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        Quantity1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        Price1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        id2.setFont(new Font("Times New Roman", Font.BOLD, 20));

        insertProduct1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        insertProduct.setFont(new Font("Times New Roman", Font.BOLD, 20));
        updateProduct1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        updateProduct.setFont(new Font("Times New Roman", Font.BOLD, 20));
        deleteProduct1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        deleteProduct.setFont(new Font("Times New Roman", Font.BOLD, 20));
        viewAllProduct1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        viewAllProduct.setFont(new Font("Times New Roman", Font.BOLD, 20));
        back.setFont(new Font("Times New Roman", Font.BOLD, 30));

        name.setPreferredSize(new Dimension(200, 60));
        Quantity.setPreferredSize(new Dimension(200, 60));
        Price.setPreferredSize(new Dimension(200, 60));
        id1.setPreferredSize(new Dimension(200, 60));
        name1.setPreferredSize(new Dimension(200, 60));
        Quantity1.setPreferredSize(new Dimension(200, 60));
        Price1.setPreferredSize(new Dimension(200, 60));
        id2.setPreferredSize(new Dimension(200, 60));

        insertProduct1.setPreferredSize(new Dimension(200, 60));
        insertProduct.setPreferredSize(new Dimension(200, 60));
        updateProduct1.setPreferredSize(new Dimension(200, 60));
        updateProduct.setPreferredSize(new Dimension(200, 60));
        deleteProduct1.setPreferredSize(new Dimension(200, 60));
        deleteProduct.setPreferredSize(new Dimension(200, 60));
        viewAllProduct1.setPreferredSize(new Dimension(200, 60));
        viewAllProduct.setPreferredSize(new Dimension(200, 60));
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

        JPanel rand2 = new JPanel();
        rand2.setLayout(new FlowLayout());
        rand2.setBackground(Color.red);

        JPanel rand3 = new JPanel();
        rand3.setLayout(new FlowLayout());
        rand3.setBackground(Color.red);

        JPanel rand4 = new JPanel();
        rand4.setLayout(new FlowLayout());
        rand4.setBackground(Color.red);

        rand0.add(titlu);
        rand0.add(Box.createRigidArea(new Dimension(0, 50)));

        rand1.add(insertProduct1);
        rand1.add(Box.createRigidArea(new Dimension(10, 0)));
        rand1.add(name);
        rand1.add(Box.createRigidArea(new Dimension(10, 0)));
        rand1.add(Quantity);
        rand1.add(Box.createRigidArea(new Dimension(10, 0)));
        rand1.add(Price);
        rand1.add(Box.createRigidArea(new Dimension(10, 0)));
        rand1.add(insertProduct);
        rand1.add(Box.createRigidArea(new Dimension(10, 0)));

        rand2.add(updateProduct1);
        rand2.add(Box.createRigidArea(new Dimension(10, 0)));
        rand2.add(id1);
        rand2.add(Box.createRigidArea(new Dimension(10, 0)));
        rand2.add(name1);
        rand2.add(Box.createRigidArea(new Dimension(10, 0)));
        rand2.add(Quantity1);
        rand2.add(Box.createRigidArea(new Dimension(10, 0)));
        rand2.add(Price1);
        rand2.add(Box.createRigidArea(new Dimension(10, 0)));
        rand2.add(updateProduct);
        rand2.add(Box.createRigidArea(new Dimension(10, 0)));

        rand3.add(deleteProduct1);
        rand3.add(Box.createRigidArea(new Dimension(30, 0)));
        rand3.add(id2);
        rand3.add(Box.createRigidArea(new Dimension(30, 0)));
        rand3.add(deleteProduct);

        rand4.add(viewAllProduct1);
        rand4.add(Box.createRigidArea(new Dimension(30, 0)));
        rand4.add(viewAllProduct);
        rand4.add(Box.createRigidArea(new Dimension(30, 0)));
        rand4.add(back);
        rand4.add(Box.createRigidArea(new Dimension(30, 0)));

        c.add(rand0);
        c.add(Box.createRigidArea(new Dimension(0, 20)));
        c.add(rand1);
        c.add(Box.createRigidArea(new Dimension(0, 20)));
        c.add(rand2);
        c.add(Box.createRigidArea(new Dimension(0, 20)));
        c.add(rand3);
        c.add(Box.createRigidArea(new Dimension(0, 20)));
        c.add(rand4);
        c.add(Box.createRigidArea(new Dimension(0, 20)));

        this.setContentPane(c);
        this.pack();
        this.setTitle("Tabela produs");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //Operatiile, inapoi, getteri;
    void addProduct(ActionListener b)
    {
        insertProduct.addActionListener(b);
    }

    void updateProduct(ActionListener b)
    {
        updateProduct.addActionListener(b);
    }

    void deleteProduct(ActionListener b)
    {
        deleteProduct.addActionListener(b);
    }

    void viewAllProducts(ActionListener b)
    {
        viewAllProduct.addActionListener(b);
    }

    void inapoi(ActionListener b)
    {
        back.addActionListener(b);
    }

    //De aici getteri:
    public String getNameProduct() {
        return name.getText();
    }

    public String getQuantity() {
        return Quantity.getText();
    }

    public String getPrice() {
        return Price.getText();
    }

    public String getId1() {
        return id1.getText();
    }

    public String getName1() {
        return name1.getText();
    }

    public String getQuantity1() {
        return Quantity1.getText();
    }

    public String getPrice1() {
        return Price1.getText();
    }

    public String getId2() {
        return id2.getText();
    }
}