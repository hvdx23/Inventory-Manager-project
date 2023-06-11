package ictgradschool.industry.final_project;

import ictgradschool.industry.final_project.backend.InventoryDataProcessor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class InventoryManagerPanel extends JPanel{
    private InventoryTableModel inventoryTableModel;
    private JTable table;

    private JButton addButton;
    private String filePath;

    public InventoryManagerPanel(String filePath){
        //panel for the inventory manager
        this.filePath=filePath;
//        frame.setTitle("Inventory Manager");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
//        pack();


    }



    private void initComponents(){
        inventoryTableModel = new InventoryTableModel();

        table=new JTable(new DefaultTableModel());
        JScrollPane scrollPane = new JScrollPane(table);

        addButton = new JButton("Add Item");

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(addButton, BorderLayout.SOUTH);

//        inventoryItems.loadFromCSV(filePath);
        List<Item> inventoryList =new InventoryDataProcessor().readInventoryFromFile(filePath);
        inventoryTableModel.setInventoryData(inventoryList);

//        table.setModel(new DefaultTableModel(inventoryItems.getData(), inventoryItems.getColumnNames()));
        table.setModel(inventoryTableModel);

        setVisible(true);


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String identifier = JOptionPane.showInputDialog(null, "Enter identifier:");
                String name = JOptionPane.showInputDialog(null, "Enter name:");
                String description = JOptionPane.showInputDialog(null, "Enter description:");
                String price = JOptionPane.showInputDialog(null, "Enter price:");
                String quantity = JOptionPane.showInputDialog(null, "Enter quantity:");

//                Item newItem;
//                newItem = {identifier, name, description, price, quantity};
//                inventoryItems.addItem(newItem);
//
//                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
//                tableModel.addRow(newItem);

                inventoryTableModel.saveToCSV(filePath);
            }
        });

    }

}
