package presentation;

import bll.ClientBLL;
import bll.OrdersBLL;
import bll.ProductBLL;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 *
 * Am view de inceput. Aleg daca sa merg pe client, produs sau order;
 *
 */

public class View extends JFrame
{
    //Toate componentele folosite. Am si modelele aici;
    private JLabel titlu = new JLabel("Alegeti una din aceste tabele:");
    private JButton client = new JButton("Client");
    private JButton product = new JButton("Produs");
    private JButton order = new JButton("Order");

    private ClientBLL clientBLL;
    private ProductBLL productBLL;
    private OrdersBLL ordersBLL;

    public View(ClientBLL clientBLL, ProductBLL productBLL, OrdersBLL ordersBLL)
    {
        //Logica de creeare si de impartire a componentelor gui, nu intru in detaliu;
        this.clientBLL = clientBLL;
        this.productBLL = productBLL;
        this.ordersBLL = ordersBLL;

        titlu.setFont(new Font("Times New Roman", Font.BOLD, 30));
        client.setFont(new Font("Times New Roman", Font.BOLD, 30));
        product.setFont(new Font("Times New Roman", Font.BOLD, 30));
        order.setFont(new Font("Times New Roman", Font.BOLD, 30));
        client.setPreferredSize(new Dimension(350, 100));
        product.setPreferredSize(new Dimension(350, 100));
        order.setPreferredSize(new Dimension(350, 100));

        JPanel c = new JPanel();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        c.setPreferredSize(new Dimension(1000, 750));
        c.setBackground(Color.green);

        JPanel rand0 = new JPanel();
        rand0.setLayout(new FlowLayout());
        rand0.setBackground(Color.blue);

        JPanel rand1 = new JPanel();
        rand1.setLayout(new FlowLayout());
        rand1.setBackground(Color.green);

        rand0.add(titlu);
        rand0.add(Box.createRigidArea(new Dimension(0, 50)));

        rand1.add(client);
        rand1.add(Box.createRigidArea(new Dimension(100, 50)));
        rand1.add(product);
        rand1.add(Box.createRigidArea(new Dimension(100, 50)));
        rand1.add(order);
        rand1.add(Box.createRigidArea(new Dimension(100, 50)));

        c.add(rand0);
        c.add(Box.createRigidArea(new Dimension(0, 50)));
        c.add(rand1);
        c.add(Box.createRigidArea(new Dimension(0, 50)));

        this.setContentPane(c);
        this.pack();
        this.setTitle("Alegere tabela");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //Butoanele pentru alegerea tabelei;
    void alegereClient(ActionListener b)
    {
        client.addActionListener(b);
    }

    void alegereProduct(ActionListener b)
    {
        product.addActionListener(b);
    }

    void alegereOrder(ActionListener b)
    {
        order.addActionListener(b);
    }
}





