package ictgradschool.industry.final_project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PointOfSale extends JFrame {

    private InventoryItems inventoryItems;
    private JTable table;

    public PointOfSale(InventoryItems inventoryItems){
        this.inventoryItems=inventoryItems;
        initComponents();
    }

    public PointOfSale() {

    }

    private void initComponents() {
//        table.setModel(new DefaultTableModel(inventoryItems.getData(), inventoryItems.getColumnNames()));
        table.setModel(new DefaultTableModel(new Object[][]{}, inventoryItems.getColumnNames()));

    }

}
