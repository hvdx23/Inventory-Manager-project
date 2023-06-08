package ictgradschool.industry.final_project;
import javax.swing.table.AbstractTableModel;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InventoryItems extends AbstractTableModel {
    private List<Item> data = new ArrayList<>();

    private String[] columnNames={"Identifier","Name","Description", "Price", "Quantity"};

//    public void loadFromCSV(String filePath) {
//        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            boolean firstLine = true;
//            while ((line = br.readLine()) != null) {
//                String[] values = line.split(",");
//                if (firstLine) {
//                    columnNames = values;
//                    firstLine = false;
//                } else {
//                    Item item=createInventoryItemFromValues(values);
//                    addItem(item);
//                }
//            }
//            fireTableStructureChanged();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//


    //loadFromCSV working for following code
//    public void loadFromCSV(String filePath) {
//        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            boolean firstLine = true;
//            while ((line = br.readLine()) != null) {
//                String[] values = line.split(",");
//                System.out.println("Values: " + Arrays.toString(values)); // Debug output
//                if (firstLine) {
//                    columnNames = values;
//                    firstLine = false;
//                } else {
//                    Item item = createInventoryItemFromValues(values);
//                    addItem(item);
//                }
//            }
//            fireTableStructureChanged();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    public void loadFromCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                System.out.println("Values: " + Arrays.toString(values)); // Debug output
                if (firstLine) {
                    columnNames = values;
                    firstLine = false;
                } else {
                    Item item = createInventoryItemFromValues(values);
                    addItem(item);
                }
            }
            fireTableStructureChanged();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //savetoCSVdefault Code
    public void saveToCSV(String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            // Write column names
            bw.write(String.join(",", columnNames));
            bw.newLine();

            // Write data rows
            for (Item item : data) {
                String[] row = {item.getIdentifier(), item.getName(), item.getDescription(), String.valueOf(item.getPrice()), String.valueOf(item.getQuantity())};
                bw.write(String.join(",", row));
                bw.newLine();
            }
            fireTableStructureChanged();
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

    private String[] createValuesFromInventoryItem(Item item) {
        String[] values = new String[5];
        values[0] = item.getIdentifier();
        values[1] = item.getName();
        values[2] = item.getDescription();
        values[3] = String.valueOf(item.getPrice());
        values[4] = String.valueOf(item.getQuantity());
        return values;
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
    }

    public Item getItem(int index){
        if(index>=0 && index<data.size()){
            return data.get(index);
        }
        return null;
    }
}
