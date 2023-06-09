package ictgradschool.industry.final_project;

import javax.swing.table.AbstractTableModel;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class POSInventory extends AbstractTableModel implements InventoryObserver {
    private List<Item> POS = new ArrayList<>();
    private List<InventoryObserver> observers=new ArrayList<>();

    private String[] columnNames={"Identifier","Name","Description", "Price", "Quantity"};

    InventoryItems inventoryItems=new InventoryItems();

//    public void addItemFromInventory(Item item){
//        for(int i=0;i<inventoryItems.getRowCount();i++){
//            POS.add(item);
//        }
//
//        fireTableStructureChanged();
//        notifyAll();
//
//    }



    public void addItemFromInventory(Item item) {
        POS.add(item); // Add the item to the POS inventory

        // Reduce the quantity of the item by 1 in the general inventory
        int itemIndex = inventoryItems.getIndex(item); // Get the index of the item in the general inventory
        if (itemIndex >-1 ) {
            Item inventoryItem = inventoryItems.getItem(itemIndex); // Get the corresponding item in the general inventory
            int currentQuantity = inventoryItem.getQuantity(); // Get the current quantity
            if (currentQuantity > 0) {
                inventoryItem.setQuantity(currentQuantity - 1); // Reduce the quantity by 1
                inventoryItems.fireTableCellUpdated(itemIndex, inventoryItems.getColumnCount() - 1); // Notify the table model of the quantity update

            }
        }
    }

    @Override
    public void onInventoryChange() {

    }

    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }

    public void addInventoryItem(InventoryItems inventoryItems) {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Item item : POS) {
            sb.append("Item: ").append(item.getName()).append(System.lineSeparator());
            sb.append("  Identifier: ").append(item.getIdentifier()).append(System.lineSeparator());
            sb.append("  Description: ").append(item.getDescription()).append(System.lineSeparator());
            sb.append("  Price: ").append(item.getPrice()).append(System.lineSeparator());
            sb.append("  Quantity: ").append(item.getQuantity()).append(System.lineSeparator());
        }
        return sb.toString();
    }

    private String createValuesFromInventoryItem(Item item) {
        String[] values = new String[5];
        values[0] = item.getIdentifier();
        values[1] = item.getName();
        values[2] = item.getDescription();
        values[3] = String.valueOf(item.getPrice());
        values[4] = String.valueOf(item.getQuantity());
        return String.join(",", values);
    }

    public void saveToCSV(String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            // Write column names
            bw.write(String.join(",", columnNames));
            bw.newLine();

            // Write data rows
            for (Item item : POS) {
                String[] row = new String[]{createValuesFromInventoryItem(item)};
                bw.write(String.join(",", row));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
