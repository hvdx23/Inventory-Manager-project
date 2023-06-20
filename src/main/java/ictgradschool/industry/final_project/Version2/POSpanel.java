package ictgradschool.industry.final_project.Version2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.HashMap;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ictgradschool.industry.final_project.Version2.InventoryItem;
//All buttons & tables for pospanel only in this class.
//declare all buttons
public class POSpanel extends JPanel implements ActionListener {
    private JButton addtocart;
    private List<InventoryItem> inventoryItems;
    private JButton removefromcart;
    private JButton checkout;
    private JButton back;
    JTable postable=null;
    JTable inventorytable=null;
    InventoryTableAdaptor inventorytablemodel=null;
    POSTableAdapter posTablemodel=null;

    JScrollPane scrollPane=null;
    JScrollPane scrollPane1=null;

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

        inventoryItems=inventoryDataProcessor.readInventoryFromFile(filepath);
        List<InventoryItem> filteredItems=inventoryItems.stream().filter(item ->item.getQuantity()>0).collect(Collectors.toList());
        inventorytablemodel=new InventoryTableAdaptor(filteredItems);
        inventorytable=new JTable();
        inventorytable.setModel(inventorytablemodel);
        scrollPane=new JScrollPane(inventorytable);
        add(scrollPane,BorderLayout.NORTH);

        //POS table
        posTablemodel=new POSTableAdapter(inventoryItems);
        postable=new JTable();
//        postable.setModel(posTablemodel);


//


        table = new DefaultTableModel(
                new Object[][]{},   // set the table to be empty
                new String[]{"ID", "Name", "Description", "Price", "Quantity"}  // column names
        );


        postable.setModel(table);
        scrollPane1=new JScrollPane(postable);
        add(scrollPane1,BorderLayout.SOUTH);
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
//        if (e.getSource()==addtocart){
//            int selectedRow=inventorytable.getSelectedRow();
//            InventoryItem selectedItem=inventorytablemodel.getInventoryItem(selectedRow);
//            table.addRow(new Object[]{selectedItem.getIdentifier(),
//                    selectedItem.getName(),
//                    selectedItem.getDescription(),
//                    selectedItem.getPrice(),
//                    1});
//
//            int currentQuantity=selectedItem.getQuantity();
//            if(currentQuantity>0){
//                selectedItem.setQuantity(currentQuantity-1);
//                inventorytablemodel.fireTableDataChanged();
//
//                if (currentQuantity - 1 == 0) {
//                    int inventoryRow = inventoryItems.indexOf(selectedItem);
//                    inventorytablemodel.removeRow(inventoryRow);
//                }
//
//            }
        if (e.getSource() == addtocart) {
            int selectedRow = inventorytable.getSelectedRow();
            InventoryItem selectedItem = inventorytablemodel.getInventoryItem(selectedRow);

            if (selectedItem.getQuantity() > 0) {
                selectedItem.setQuantity(selectedItem.getQuantity() - 1);
                inventorytablemodel.fireTableDataChanged();

                if (selectedItem.getQuantity() == 0) {
                    int inventoryRow = inventoryItems.indexOf(selectedItem);
                    inventorytablemodel.removeRow(inventoryRow);
                }

                table.addRow(new Object[]{
                        selectedItem.getIdentifier(),
                        selectedItem.getName(),
                        selectedItem.getDescription(),
                        selectedItem.getPrice(),
                        1
                });
            }
        }

        if(e.getSource()==removefromcart){
            int selectedRow=postable.getSelectedRow();
//            table.removeRow(selectedRow);
            if (selectedRow !=-1){
                Object [] rowData=new Object[table.getColumnCount()];
                for(int i=0;i<table.getColumnCount();i++){
                    rowData[i]=table.getValueAt(selectedRow,i);
                }
                table.removeRow(selectedRow);

                String itemID=(String) rowData[0];
                InventoryItem selectedItem=inventoryItems.stream().filter(item -> item.getIdentifier().equals(itemID)).findFirst().orElse(null);
                if (selectedItem !=null){
                    selectedItem.setQuantity(selectedItem.getQuantity()+1);
                    inventorytablemodel.fireTableDataChanged();
                    if (selectedItem.getQuantity()==1){
                        inventorytablemodel.addInventoryData(selectedItem);
                    }
                }
                reloadInventoryTable();

            }
            reloadInventoryTable();
        }
        if (e.getSource()==checkout){
            try{
                saveCheckoutDetails();
                inventoryDataProcessor.saveInventoryToFile(filepath, inventorytablemodel.getInventoryItems());
            }catch(IOException ex){
                ex.printStackTrace();
            }

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
    public void reloadInventoryTable() {
        inventoryItems = inventoryItems.stream()
                .filter(item -> item.getQuantity() > 0)
                .collect(Collectors.toList());
        inventorytablemodel.setInventoryItems(inventoryItems);
        inventorytablemodel.fireTableDataChanged();
    }

//    private void saveCheckoutDetails()throws IOException{
//        String fileName=JOptionPane.showInputDialog("Enter the file name");
//        if (fileName!=null){
//            fileName=fileName.trim();
//            if(!fileName.isEmpty()){
//                try(PrintWriter writer=new PrintWriter(new FileWriter(fileName+".txt"))){
//                    writer.println("-----------------------------------");
//                    for(int i=0;i<table.getRowCount();i++){
//                        String productname=(String) table.getValueAt(i,1);
//                        int quantity=(int) table.getValueAt(i,4);
//                        double price=(double) table.getValueAt(i,3);
//                        double total=quantity*price;
//
//                        writer.printf("%-3d %-20s $%.2f%n",quantity,productname,price);
//                        if(price<0){
//                            writer.printf("     (%.2f)%n", -price);
//                        }
//                        writer.printf("  Total: $%.2f%n", total);
//
//                    }
//                    writer.println("====================================");
//                    double grandtotal=calculateGrandTotal();
//                    writer.printf("  TOTAL                   $%.2f%n", grandtotal);
//                    writer.println("-------------------------------------");
//                }
//            }else{
//                System.out.println("Error");
//            }
//        }
//    }



// ...save file to src folder-workign version

    private void saveCheckoutDetails() throws IOException {
        String fileName = JOptionPane.showInputDialog("Enter the file name");
        if (fileName != null) {
            fileName = fileName.trim();
            if (!fileName.isEmpty()) {
                try (PrintWriter writer = new PrintWriter(new FileWriter(fileName + ".txt"))) {
                    writer.println("--------------------------------");

                    // Create a HashMap to store the total quantity and price for each product
                    Map<String, Double> productTotals = new HashMap<>();

                    for (int i = 0; i < table.getRowCount(); i++) {
                        String productName = (String) table.getValueAt(i, 1);
                        int quantity = (int) table.getValueAt(i, 4);
                        double price = (double) table.getValueAt(i, 3);
                        double total = quantity * price;

                        // Check if the product is already in the HashMap
                        if (productTotals.containsKey(productName)) {
                            double currentTotal = productTotals.get(productName);
                            productTotals.put(productName, currentTotal + total);
                        } else {
                            productTotals.put(productName, total);
                        }
                    }

                    // Print the individual product totals
                    for (Map.Entry<String, Double> entry : productTotals.entrySet()) {
                        String productName = entry.getKey();
                        double total = entry.getValue();
                        int quantity=getQuantityForProduct(productName);
                        if (total != quantity * getProductPrice(productName)) {
                            writer.printf("%-3d %-20s    Total: $%.2f%n", quantity, productName, total);
                        }else {
                            writer.printf("%-3d %-20s    $%.2f    Total: $%.2f%n", quantity, productName, total / quantity, total);
                        }


                    }

                    writer.println("================================");

                    // Calculate the grand total
                    double grandTotal = calculateGrandTotal();
                    writer.printf("   TOTAL                 $%.2f%n", grandTotal);
                    writer.println("--------------------------------");
                }
            } else {
                System.out.println("Error");
            }
        }
    }





    private double calculateGrandTotal() {
        double grandTotal = 0.0;
        for (int i = 0; i < table.getRowCount(); i++) {
            int quantity =Integer.parseInt(table.getValueAt(i, 4).toString());
            double price = (double) table.getValueAt(i, 3);
            grandTotal += quantity * price;
        }
        return grandTotal;
    }
    private int getQuantityForProduct(String productName) {
        int quantity = 0;
        for (int i = 0; i < table.getRowCount(); i++) {
            String name = (String) table.getValueAt(i, 1);
            if (name.equals(productName)) {
                quantity += (int) table.getValueAt(i, 4);
            }
        }
        return quantity;
    }
    private double getProductPrice(String productName) {
        double price = 0.0;
        for (int i = 0; i < table.getRowCount(); i++) {
            String name = (String) table.getValueAt(i, 1);
            if (name.equals(productName)) {
                price = (double) table.getValueAt(i, 3);
                break;
            }
        }
        return price;
    }


}


