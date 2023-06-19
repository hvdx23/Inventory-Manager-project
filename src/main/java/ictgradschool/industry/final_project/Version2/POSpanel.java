package ictgradschool.industry.final_project.Version2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import ictgradschool.industry.final_project.Version2.InventoryItem;
//All buttons & tables for pospanel only in this class.
//declare all buttons
public class POSpanel extends JPanel implements ActionListener {
    private JButton addtocart;
    private JButton removefromcart;
    private JButton checkout;
    private JButton back;
    JTable postable=null;
    JTable inventorytable=null;
    InventoryTableAdaptor inventorytablemodel=null;
    POSTableAdapter posTablemodel=null;

    private DefaultTableModel table;

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
        back=new JButton("Back");
        back.addActionListener(this);
        buttonPanel.add(back);

        //inventory table
        //items to be displayed only if quanotty>0, to be implemeted later

        List<InventoryItem> inventoryItems=inventoryDataProcessor.readInventoryFromFile(filepath);
        List<InventoryItem> filteredItems=inventoryItems.stream().filter(item ->item.getQuantity()>0).collect(Collectors.toList());
        inventorytablemodel=new InventoryTableAdaptor(filteredItems);
        inventorytable=new JTable();
        inventorytable.setModel(inventorytablemodel);
        add(inventorytable,BorderLayout.NORTH);

        //POS table
        posTablemodel=new POSTableAdapter(inventoryItems);
        postable=new JTable();

//


        table = new DefaultTableModel(
                new Object[][]{},   // set the table to be empty
                new String[]{"ID", "Name", "Description", "Price", "Quantity"}  // column names
        );


        postable.setModel(table);
        add(postable,BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == addtocart) {
//            int selectedRow = inventorytable.getSelectedRow();
//            InventoryItem selectedItem = inventorytablemodel.getInventoryItem(selectedRow);
//            table.addRow(new Object[]{selectedItem.getIdentifier(),
//                    selectedItem.getName(),
//                    selectedItem.getDescription(),
//                    selectedItem.getPrice(),
//                    selectedItem.getQuantity()});
//
////            int currentQuantity = selectedItem.getQuantity();
////            if (currentQuantity > 0) {
////                selectedItem.setQuantity(currentQuantity - 1);
////                inventorytablemodel.fireTableDataChanged();
////                if (currentQuantity - 1 == 0) {
////                    int inventoryRow = inventoryItems.indexOf(selectedItem);
////                    inventorytablemodel.removeRow(inventoryRow);
////                }
////            }
//        }
//        if (e.getSource() == removefromcart) {
//            int selectedRow = postable.getSelectedRow();
//            table.removeRow(selectedRow);
//        }
//        if (e.getSource() == checkout) {
//            // Perform the checkout action
//        }
//        if (e.getSource() == back) {
//            ProjectFrame frame = (ProjectFrame) SwingUtilities.getWindowAncestor(this);
//            ManagerPanel managerPanel = new ManagerPanel(filepath);
//            managerPanel.initcomponents();
//            frame.getContentPane().removeAll();
//            frame.getContentPane().add(managerPanel);
//            frame.pack();
//            frame.setVisible(true);
//        }
//    }
//    }
        if (e.getSource()==addtocart){
            int selectedRow=inventorytable.getSelectedRow();
            InventoryItem selectedItem=inventorytablemodel.getInventoryItem(selectedRow);
            table.addRow(new Object[]{selectedItem.getIdentifier(),
                    selectedItem.getName(),
                    selectedItem.getDescription(),
                    selectedItem.getPrice(),
                    selectedItem.getQuantity()});

            int currentQuantity=selectedItem.getQuantity();
            if(currentQuantity>0){
                selectedItem.setQuantity(currentQuantity-1);
                inventorytablemodel.fireTableDataChanged();

            }



        }
        if(e.getSource()==removefromcart){
            int selectedRow=postable.getSelectedRow();
            table.removeRow(selectedRow);

        }
        if (e.getSource()==checkout){

        }
        if (e.getSource()==back){
            ProjectFrame frame = (ProjectFrame) SwingUtilities.getWindowAncestor(this);
            ManagerPanel managerPanel = new ManagerPanel(filepath);
            managerPanel.initcomponents();
            frame.getContentPane().removeAll();
            frame.getContentPane().add(managerPanel);
            managerPanel.setPreferredSize(new Dimension(800, 600));
            frame.pack();
            frame.setVisible(true);


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
