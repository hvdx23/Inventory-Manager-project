package ictgradschool.industry.final_project;
import javax.swing.table.AbstractTableModel;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

public class InventoryItems extends AbstractTableModel {
    private List<Item> data = new ArrayList<>();
    private List<InventoryObserver> observers=new ArrayList<>();

    private String[] columnNames={"Identifier","Name","Description", "Price", "Quantity"};



//load from CSV working
    public void loadFromCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
//                System.out.println("Values: " + Arrays.toString(values)); // Debug output

                    Item item = createInventoryItemFromValues(values);
                    addItem(item);
            }
//            fireTableStructureChanged();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //register observer here after file is loaded

    }

    //savetoCSV working

    public void saveToCSV(String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            // Write column names
            bw.write(String.join(",", columnNames));
            bw.newLine();

            // Write data rows
            for (Item item : data) {
                String[] row = new String[]{createValuesFromInventoryItem(item)};
                bw.write(String.join(",", row));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        if (columnNames != null) {
            return columnNames.length;
        }
        return 0;
    }

    private Item createInventoryItemFromValues(String[] values) {
        String identifier = values[0];
        String name = values[1];
        String description = values[2];
        double price = Double.parseDouble(values[3]);
        int quantity = Integer.parseInt(values[4]);
        return new Item(identifier, name, description, price, quantity);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex < data.size() && columnIndex < getColumnCount()) {
            Item item = data.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return item.getIdentifier();
                case 1:
                    return item.getName();
                case 2:
                    return item.getDescription();
                case 3:
                    return item.getPrice();
                case 4:
                    return item.getQuantity();
                default:
                    return null;

            }
        }
        return null;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        if (rowIndex >= 0 && rowIndex < data.size() && columnIndex < getColumnCount()) {
            Item item = data.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    item.setIdentifier((String) value);
                    break;
                case 1:
                    item.setName((String) value);
                    break;
                case 2:
                    item.setDescription((String) value);
                    break;
                case 3:
                    item.setPrice((double) value);
                    break;
                case 4:
                    item.setQuantity((int) value);
                    break;
            }
            fireTableCellUpdated(rowIndex, columnIndex);
            notifyObservers();
        }
    }


    //new code for saveCSV
    private String createValuesFromInventoryItem(Item item) {
        String[] values = new String[5];
        values[0] = item.getIdentifier();
        values[1] = item.getName();
        values[2] = item.getDescription();
        values[3] = String.valueOf(item.getPrice());
        values[4] = String.valueOf(item.getQuantity());
        return String.join(",", values);
    }

    public int getData() {
        return data.size();
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    public void addItem(Item newItem) {
        data.add(newItem);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
        notifyObservers();
    }

    public Item getItem(int index){
        if(index>=0 && index<data.size()){
            return data.get(index);
        }
        return null;
    }

//    @Override
//    public void onInventoryChange() {
//        System.out.println("Inventory changed");
//        String filePath= "observerchanged.csv";
//        saveToCSV(filePath);
//
//    }

    public void registerObserver(InventoryObserver observer){
        observers.add(observer);
    }

    public void removeObserver(InventoryObserver observer){
        observers.remove(observer);
    }

    public void notifyObservers(){
        for(InventoryObserver observer: observers){
            observer.onInventoryChange();
        }
    }

    public List<InventoryObserver> getObservers(){
        return observers;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getRowCount(); i++) {
            Item item = getItem(i);
            sb.append("Item ").append(i).append(":").append(System.lineSeparator());
            sb.append("id: ").append(item.getIdentifier()).append(" name: ").append(item.getName()).append(" desc: ").append(item.getDescription())
                    .append(" price: ").append(item.getPrice()).append(" Qty: ").append(item.getQuantity()).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
