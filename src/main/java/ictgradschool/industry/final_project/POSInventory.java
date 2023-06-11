package ictgradschool.industry.final_project;

import javax.swing.table.AbstractTableModel;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class POSInventory extends AbstractTableModel implements InventoryObserver {
    //POS list for adding items to checkout
    private List<Item> POS = new ArrayList<>();

    private List<InventoryObserver> observers=new ArrayList<>();

    private String[] columnNames={"Identifier","Name","Description", "Price", "Quantity"};

    InventoryTableModel inventoryItems=new InventoryTableModel();
    //loads the inventoryItems from data created in InventoryItems class
    List<Item> data=inventoryItems.getInventoryData();





    public void addItemFromInventory(Item item) {
        //index returned is always -1. De bug it
//        int index = inventoryItems.getIndex(item);
//        if(index!=-1){
//            POS.add(item);
//            //reduces the quantity of the item in Inventoryitem datalist in InventoryItems class.
//            inventoryItems.reduceInventoryQuantity(item);
//        }
        String identifier=item.getIdentifier();
        inventoryItems.reduceInventoryQuantity(identifier);
        POS.add(item);
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

//    public Item getItem(int index){
//        if(index>=0 && index<POS.size()){
//            return POS.get(index);
//        }
//        return null;
//    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }

    public void addInventoryItem(InventoryTableModel inventoryItems) {
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
    public void loadFromCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
//                System.out.println("Values: " + Arrays.toString(values)); // Debug output

//                Item item = createInventoryItemFromValues(values);
//                addItem(item);
            }
//            fireTableStructureChanged();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //register observer here after file is loaded

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
