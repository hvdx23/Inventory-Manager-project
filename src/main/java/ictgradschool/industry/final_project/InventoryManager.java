package ictgradschool.industry.final_project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryManager extends JTable{
    private InventoryItems inventoryItems;
    private JTable table;

    private JButton addButton;



    private void initComponents(){
        inventoryItems = new InventoryItems();

        table=new JTable(new DefaultTableModel());
        JScrollPane scrollPane = new JScrollPane(table);

        addButton = new JButton("Add Item");

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(addButton, BorderLayout.SOUTH);

        inventoryItems.loadFromCSV("inventory.csv");

        table.setModel(new DefaultTableModel(inventoryItems.getData(), inventoryItems.getColumnNames()));


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String identifier = JOptionPane.showInputDialog(null, "Enter identifier:");
                String name = JOptionPane.showInputDialog(null, "Enter name:");
                String description = JOptionPane.showInputDialog(null, "Enter description:");
                String price = JOptionPane.showInputDialog(null, "Enter price:");
                String quantity = JOptionPane.showInputDialog(null, "Enter quantity:");

                String[] newItem = {identifier, name, description, price, quantity};
                inventoryItems.addItem(newItem);

                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                tableModel.addRow(newItem);

                inventoryItems.saveToCSV("inventory.csv");
            }
        });

    }

}

//
//import javax.swing.*;
//        import javax.swing.table.DefaultTableModel;
//        import java.awt.*;
//        import java.awt.event.ActionEvent;
//        import java.awt.event.ActionListener;
//
//public class InventoryManager extends JFrame {
//    private InventoryItems inventoryItems;
//    private JTable table;
//    private JButton addButton;
//
//    public InventoryManager() {
//        setTitle("Inventory Manager");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        initComponents();
//        pack();
//        setLocationRelativeTo(null);
//    }
//
//    private void initComponents() {
//        // Create an instance of InventoryItems
//        inventoryItems = new InventoryItems();
//
//        // Create a JTable with a DefaultTableModel
//        table = new JTable(new DefaultTableModel());
//        JScrollPane scrollPane = new JScrollPane(table);
//
//        // Create a JButton for adding new items
//        addButton = new JButton("Add Item");
//
//        // Add the components to the frame
//        setLayout(new BorderLayout());
//        add(scrollPane, BorderLayout.CENTER);
//        add(addButton, BorderLayout.SOUTH);
//
//        // Load data from a CSV file
//        inventoryItems.loadFromCSV("inventory.csv");
//
//        // Set the data model of the table
//        table.setModel(new DefaultTableModel(inventoryItems.getData(), inventoryItems.getColumnNames()));
//
//        // Add an ActionListener to the Add button
//        addButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Open a dialog to get item details
//                String identifier = JOptionPane.showInputDialog(null, "Enter identifier:");
//                String name = JOptionPane.showInputDialog(null, "Enter name:");
//                String description = JOptionPane.showInputDialog(null, "Enter description:");
//                String price = JOptionPane.showInputDialog(null, "Enter price:");
//                String quantity = JOptionPane.showInputDialog(null, "Enter quantity:");
//
//                // Add the new item to the inventory
//                String[] newItem = {identifier, name, description, price, quantity};
//                inventoryItems.addItem(newItem);
//
//                // Update the table model
//                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
//                tableModel.addRow(newItem);
//
//                // Save the updated data to the CSV file
//                inventoryItems.saveToCSV("inventory.csv");
//            }
//        });
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new InventoryManager().setVisible(true);
//            }
//        });
//    }
//}
