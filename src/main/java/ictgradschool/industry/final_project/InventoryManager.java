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
    private String filePath;

    public InventoryManager(String filePath){
        this.filePath=filePath;
        setTitle("Inventory Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        pack();

    }



    private void initComponents(){
        inventoryItems = new InventoryItems();

        table=new JTable(new DefaultTableModel());
        JScrollPane scrollPane = new JScrollPane(table);

        addButton = new JButton("Add Item");

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(addButton, BorderLayout.SOUTH);

        inventoryItems.loadFromCSV(filePath);

        table.setModel(new DefaultTableModel(inventoryItems.getData(), inventoryItems.getColumnNames()));
        setVisible(true);


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

                inventoryItems.saveToCSV(filePath);
            }
        });

    }

}
