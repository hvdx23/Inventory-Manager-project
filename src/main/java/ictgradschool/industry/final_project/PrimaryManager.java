package ictgradschool.industry.final_project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PrimaryManager extends rootManagerFrame{

    private JButton inv;
    private JButton pos;

    private JButton back;

    private JButton close;
    private String filePath;

    public PrimaryManager(){

    }




    public PrimaryManager(String filePath) {


            JFrame frame = new JFrame("Product Manager");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setBounds(100, 100, 800, 800);

            JPanel contentpane = new JPanel(new FlowLayout(FlowLayout.CENTER));

            pos = new JButton("Point of sale");
//        Open.setPreferredSize(new Dimension(100, 30));
            contentpane.add(pos);
            pos.addActionListener(this);

            inv = new JButton("Inventory Manager");
//        Create.setPreferredSize(new Dimension(100, 30));
            contentpane.add(inv);
            inv.addActionListener(this);

            close = new JButton("Close");
            contentpane.add(close);
            close.addActionListener(this);


            frame.setContentPane(contentpane);
            frame.setVisible(true);



    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == pos) {
            PointOfSale pos = new PointOfSale();
            pos.setVisible(true);
            this.setVisible(false);
        }
        if (e.getSource() == inv) {
//            InventoryManager inv = new InventoryManager();
            inv.setVisible(true);
            this.setVisible(false);
        }
        if (e.getSource() == close) {
            System.exit(0);
        }


    }




}
