package ictgradschool.industry.final_project.Version2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import ictgradschool.industry.final_project.Version2.InventoryItem;
//All buttons & tables for pospanel only in this class.
//declare all buttons
public class POSpanel extends JPanel implements ActionListener {
    private JButton addtocart;
    private JButton removefromcart;
    private JButton checkout;
    JTable postable=null;
    JTable inventorytable=null;
    InventoryTableAdaptor inventorytablemodel=null;
    POSTableAdapter posTablemodel=null;

    private String filepath;
    private InventoryDataProcessor inventoryDataProcessor=new InventoryDataProcessor();


//    private JTable postable;



    public POSpanel(String filepath) {
        this.filepath=filepath;
    }


    //multiple panels for displaying tables & buttons.//3 panels

    public void initcomponents(){
        JPanel buttonPanel=new JPanel();
        setBackground(Color.WHITE);
        // All buttons & textfields for pos panel
        setLayout(new BorderLayout());

        inventorytable=new JTable();
        List<InventoryItem> inventoryItems=inventoryDataProcessor.readInventoryFromFile(filepath);

        addtocart=new JButton("Add to cart");
        addtocart.addActionListener(this);
        buttonPanel.add(addtocart);
        removefromcart=new JButton("Remove from cart");
        removefromcart.addActionListener(this);
        buttonPanel.add(removefromcart);
        checkout =new JButton("Checkout");
        checkout.addActionListener(this);
        buttonPanel.add(checkout);
        add(buttonPanel,BorderLayout.CENTER);
//        postable=new JTable();
//        add(postable);
        inventorytablemodel=new InventoryTableAdaptor(inventoryItems);
        inventorytable.setModel(inventorytablemodel);
        add(inventorytable,BorderLayout.NORTH);
        posTablemodel=new POSTableAdapter();
        postable=new JTable();
        postable.setModel(posTablemodel);
        add(postable,BorderLayout.SOUTH);




    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addtocart){



        }
        if(e.getSource()==removefromcart){

        }
        if (e.getSource()==checkout){

        }


    }

    private void openPOSPanel(POSpanel frame,String filepath){



//        POSpanel pospanel=new POSpanel();
//
//        this.frame.getContentPane().removeAll();
////        frame.getContentPane().removeAll();
//        this.frame.getContentPane().add(managerPanel);
//        this.frame.pack();
//        this.frame.setVisible(true);

    }
}
