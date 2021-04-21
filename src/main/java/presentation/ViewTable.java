package presentation;

import bll.ClientBLL;
import bll.OrdersBLL;
import bll.ProductBLL;
import model.Client;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.*;
import java.util.List;

/**
 *
 * Pentru a putea face JTable, ca sa afisam tabelele in java, avem nevoie de un view nou. Aici
 * setam, in functie de ce buton a apelat (metoda de findAll), un JTable, ce seamana cu afisarea din
 * sql. Este un tabel cu liniile generate, inserate, updatate, sterse, etc...;
 *
 */

public class ViewTable extends JFrame
{
    //Logica de mapare gui: (la fields si constructor;)
    private JLabel titlu = new JLabel("Tabela aleasa:");
    private JButton back = new JButton("Back");

    private ClientBLL clientBLL;
    private ProductBLL productBLL;
    private OrdersBLL ordersBLL;

    private JTable j = new JTable();

    public ViewTable(ClientBLL clientBLL, ProductBLL productBLL, OrdersBLL ordersBLL) {
        this.clientBLL = clientBLL;
        this.productBLL = productBLL;
        this.ordersBLL = ordersBLL;

        titlu.setFont(new Font("Times New Roman", Font.BOLD, 50));
        back.setFont(new Font("Times New Roman", Font.BOLD, 50));
        back.setPreferredSize(new Dimension(350, 100));

        j.setPreferredSize(new Dimension(900, 550));

        JPanel c = new JPanel();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        c.setPreferredSize(new Dimension(1000, 750));
        c.setBackground(Color.green);

        JPanel rand1 = new JPanel();
        rand1.setLayout(new FlowLayout());
        rand1.setBackground(Color.green);

        rand1.add(titlu);
        rand1.add(Box.createRigidArea(new Dimension(0, 50)));
        rand1.add(j); //neinitializat momenta;
        rand1.add(Box.createRigidArea(new Dimension(100, 50)));
        rand1.add(back);

        c.add(rand1);
        c.add(Box.createRigidArea(new Dimension(0, 50)));

        this.setContentPane(c);
        this.pack();
        this.setTitle("Tabela prezentata");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //Avem butonul de inapoi, si pe langa avem butonul unde setam JTable;
    //Avem nevoie de o matrice data de metoda abstracta dao findAll, unde gasim
    //toate liniile tabelei (la acel moment);
    //Cu acele date, fac o tabela, si o afisez in view (si o setez la fiecare buton de findAll (la oricare tabela))
    void inapoiTable(ActionListener b)
    {
        back.addActionListener(b);
    }

    void setTable(String[][] mat, String[] col)
    {
        DefaultTableModel data = new DefaultTableModel(mat, col);
        j.setModel(data);
    }
}
