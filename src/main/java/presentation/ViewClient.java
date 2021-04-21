package presentation;

import bll.ClientBLL;
import bll.OrdersBLL;
import bll.ProductBLL;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 *
 * Avem un view pentru client: Aici avem operatiile sale, si aici putem introduce ce date sa inseram, actualizam,
 * sterge etc...; Pe langa, avem getteri la datele date in acest view;
 * Este la fel ca si product si order si dpdv al gui, deci nu intru in detaliu la toate 3, pentru ca as explica
 * aceleasi lucruri;
 *
 */

public class ViewClient extends JFrame
{
    //Componentele folosite;
    private JLabel titlu = new JLabel("Alegeti o operatie de efectuat:");

    private JLabel insertClient1 = new JLabel("Pentru insert: ");
    private JTextField name = new JTextField("Name");
    private JTextField address = new JTextField("Address");
    private JTextField email = new JTextField("Email");
    private JTextField age = new JTextField("Age");
    private JButton insertClient = new JButton("Insert client");

    private JLabel updateClient1 = new JLabel("Pentru update: ");
    private JTextField id1 = new JTextField("Id");
    private JTextField name1 = new JTextField("Name");
    private JTextField address1 = new JTextField("Address");
    private JTextField email1 = new JTextField("Email");
    private JTextField age1 = new JTextField("Age");
    private JButton updateClient = new JButton("Edit client");

    private JLabel deleteClient1 = new JLabel("Pentru delete: ");
    private JTextField id2 = new JTextField("Id");
    private JButton deleteClient = new JButton("Delete client");

    private JLabel viewAllClient1 = new JLabel("Pentru view: ");
    private JButton viewAllClient = new JButton("View all clients");

    private JButton back = new JButton("Back");

    private ClientBLL clientBLL;
    private ProductBLL productBLL;
    private OrdersBLL ordersBLL;

    //In constructor, pun toate componentele impreuna;
    public ViewClient(ClientBLL clientBLL, ProductBLL productBLL, OrdersBLL ordersBLL)
    {
        this.clientBLL = clientBLL;
        this.productBLL = productBLL;
        this.ordersBLL = ordersBLL;

        titlu.setFont(new Font("Times New Roman", Font.BOLD, 20));
        name.setFont(new Font("Times New Roman", Font.BOLD, 20));
        address.setFont(new Font("Times New Roman", Font.BOLD, 20));
        email.setFont(new Font("Times New Roman", Font.BOLD, 20));
        age.setFont(new Font("Times New Roman", Font.BOLD, 20));
        id1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        name1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        address1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        email1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        age1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        id2.setFont(new Font("Times New Roman", Font.BOLD, 20));
        insertClient1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        insertClient.setFont(new Font("Times New Roman", Font.BOLD, 20));
        updateClient1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        updateClient.setFont(new Font("Times New Roman", Font.BOLD, 20));
        deleteClient1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        deleteClient.setFont(new Font("Times New Roman", Font.BOLD, 20));
        viewAllClient1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        viewAllClient.setFont(new Font("Times New Roman", Font.BOLD, 20));
        back.setFont(new Font("Times New Roman", Font.BOLD, 30));

        name.setPreferredSize(new Dimension(200, 60));
        address.setPreferredSize(new Dimension(200, 60));
        email.setPreferredSize(new Dimension(200, 60));
        age.setPreferredSize(new Dimension(200, 60));
        id1.setPreferredSize(new Dimension(200, 60));
        name1.setPreferredSize(new Dimension(200, 60));
        address1.setPreferredSize(new Dimension(200, 60));
        email1.setPreferredSize(new Dimension(200, 60));
        age1.setPreferredSize(new Dimension(200, 60));
        id2.setPreferredSize(new Dimension(200, 60));

        insertClient1.setPreferredSize(new Dimension(200, 60));
        insertClient.setPreferredSize(new Dimension(200, 60));
        updateClient1.setPreferredSize(new Dimension(200, 60));
        updateClient.setPreferredSize(new Dimension(200, 60));
        deleteClient1.setPreferredSize(new Dimension(200, 60));
        deleteClient.setPreferredSize(new Dimension(200, 60));
        viewAllClient1.setPreferredSize(new Dimension(200, 60));
        viewAllClient.setPreferredSize(new Dimension(200, 60));
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

        rand1.add(insertClient1);
        rand1.add(Box.createRigidArea(new Dimension(10, 0)));
        rand1.add(name);
        rand1.add(Box.createRigidArea(new Dimension(10, 0)));
        rand1.add(address);
        rand1.add(Box.createRigidArea(new Dimension(10, 0)));
        rand1.add(email);
        rand1.add(Box.createRigidArea(new Dimension(10, 0)));
        rand1.add(age);
        rand1.add(Box.createRigidArea(new Dimension(10, 0)));
        rand1.add(insertClient);
        rand1.add(Box.createRigidArea(new Dimension(10, 0)));

        rand2.add(updateClient1);
        rand2.add(Box.createRigidArea(new Dimension(10, 0)));
        rand2.add(id1);
        rand2.add(Box.createRigidArea(new Dimension(10, 0)));
        rand2.add(name1);
        rand2.add(Box.createRigidArea(new Dimension(10, 0)));
        rand2.add(address1);
        rand2.add(Box.createRigidArea(new Dimension(10, 0)));
        rand2.add(email1);
        rand2.add(Box.createRigidArea(new Dimension(10, 0)));
        rand2.add(age1);
        rand2.add(Box.createRigidArea(new Dimension(10, 0)));
        rand2.add(updateClient);
        rand2.add(Box.createRigidArea(new Dimension(10, 0)));

        rand3.add(deleteClient1);
        rand3.add(Box.createRigidArea(new Dimension(30, 0)));
        rand3.add(id2);
        rand3.add(Box.createRigidArea(new Dimension(30, 0)));
        rand3.add(deleteClient);

        rand4.add(viewAllClient1);
        rand4.add(Box.createRigidArea(new Dimension(30, 0)));
        rand4.add(viewAllClient);
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
        this.setTitle("Tabela client");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //Butoanele pentru operatii, pentru inapoi si pentru getteri (de setteri nu avem nevoie;)
    void addClient(ActionListener b)
    {
        insertClient.addActionListener(b);
    }

    void updateClient(ActionListener b)
    {
        updateClient.addActionListener(b);
    }

    void deleteClient(ActionListener b)
    {
        deleteClient.addActionListener(b);
    }

    void viewAllClients(ActionListener b)
    {
        viewAllClient.addActionListener(b);
    }

    void inapoi(ActionListener b)
    {
        back.addActionListener(b);
    }

    //getteri:
    public String getNameClient() {
        return name.getText();
    }

    public String getAddress() {
        return address.getText();
    }

    public String getEmail() {
        return email.getText();
    }

    public String getAge() {
        return age.getText();
    }

    public String getId1() {
        return id1.getText();
    }

    public String getName1() {
        return name1.getText();
    }

    public String getAddress1() {
        return address1.getText();
    }

    public String getEmail1() {
        return email1.getText();
    }

    public String getAge1() {
        return age1.getText();
    }

    public String getId2() {
        return id2.getText();
    }
}
