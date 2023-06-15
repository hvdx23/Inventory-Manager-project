package ictgradschool.industry.final_project.Version1;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;


//inventory table for the inventory(can be reused for the POS inventory)
public class InventoryTableModel extends AbstractTableModel {
    private List<Item> inventoryItems = new ArrayList<>();
    private List<InventoryObserver> observers=new ArrayList<>();

    private final String[] columnNames={"Identifier","Name","Description", "Price", "Quantity"};



//load from CSV working

    public void setInventoryData(List<Item> data) {
        this.inventoryItems = data;
        fireTableDataChanged();
    }

    public void addInventoryData(List<Item> data) {
        this.inventoryItems.addAll(data);
        fireTableDataChanged();
    }


//    public void loadFromCSV(String filePath) {
//        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] values = line.split(",");
////                System.out.println("Values: " + Arrays.toString(values)); // Debug output
//
//                    Item item = createInventoryItemFromValues(values);
//                    addItem(item);
//            }
////            fireTableStructureChanged();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        //register observer here after file is loaded
//
//    }

    //savetoCSV working

//    public void saveToCSV(String filePath) {
//        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
//            // Write column names
//            bw.write(String.join(",", columnNames));
//            bw.newLine();
//
//            // Write data rows
//            for (Item item : data) {
//                String[] row = new String[]{createValuesFromInventoryItem(item)};
//                bw.write(String.join(",", row));
//                bw.newLine();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    @Override
    public int getRowCount() {
        return inventoryItems.size();
    }

    @Override
    public int getColumnCount() {
        if (columnNames != null) {
            return columnNames.length;
        }
        return 0;
    }
//    @Override
    public int getIndex(Item item){
        return inventoryItems.indexOf(inventoryItems);
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
        if (rowIndex < inventoryItems.size() && columnIndex < getColumnCount()) {
            return getObject(rowIndex, columnIndex, inventoryItems);
        }
        return null;
    }

    private Object getObject(int rowIndex, int columnIndex, List<Item> inventoryItems) {
        return switch (columnIndex) {
            case 0 -> inventoryItems.get(rowIndex).getIdentifier();
            case 1 -> inventoryItems.get(rowIndex).getName();
            case 2 -> inventoryItems.get(rowIndex).getDescription();
            case 3 -> inventoryItems.get(rowIndex).getPrice();
            case 4 -> inventoryItems.get(rowIndex).getQuantity();
            default -> null;
        };

    }


    //OLD WORKING CODE FOR SET VALUE AT()
//    @Override
//    public void setValueAt(Object value, int rowIndex, int columnIndex) {
//        if (rowIndex >= 0 && rowIndex < data.size() && columnIndex < getColumnCount()) {
//            Item item = data.get(rowIndex);
//            switch (columnIndex) {
//                case 0:
//                    item.setIdentifier((String) value);
//                    break;
//                case 1:
//                    item.setName((String) value);
//                    break;
//                case 2:
//                    item.setDescription((String) value);
//                    break;
//                case 3:
//                    item.setPrice((double) value);
//                    break;
//                case 4:
//                    item.setQuantity((int) value);
//                    break;
//            }
//            fireTableCellUpdated(rowIndex, columnIndex);
//            notifyObservers();
//        }
//    }

    //New cod efor setvalue with POS inventory addition and subtraction
    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        if (rowIndex >= 0 && rowIndex < inventoryItems.size() && columnIndex < getColumnCount()) {
            Item item = inventoryItems.get(rowIndex);
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
                    int newQuantity = (int) value;
                    int currentQuantity = item.getQuantity();
                    int quantityDifference = newQuantity - currentQuantity;
                    item.setQuantity(newQuantity);
                    fireTableCellUpdated(rowIndex, columnIndex);
                    if (quantityDifference < 0) {
                        // If the quantity has decreased, notify observers of the inventory change
                        notifyObservers();
                    }
                    break;
            }
        }
    }

    public List<Item> getInventoryData(){
        //gives the data list with only greater than one quantity to POSInventory class
        List<Item> inventoryData=new ArrayList<>();
        for(Item item: inventoryItems){
            if(item.getQuantity()>0){
                inventoryData.add(item);
            }
        }
        return inventoryData;
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
        return inventoryItems.size();
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    public void addItem(Item newItem) {
        inventoryItems.add(newItem);
        fireTableRowsInserted(inventoryItems.size() - 1, inventoryItems.size() - 1);
        notifyObservers();
    }

    public Item getItem(int index){
        if(index>=0 && index< inventoryItems.size()){
            return inventoryItems.get(index);
        }
        return null;
    }

    public void reduceInventoryQuantity(String identifier){
        for (Item item : inventoryItems) {
            if (item.getIdentifier().equals(identifier)) {
                int currentQuantity = item.getQuantity();
                int newQuantity = currentQuantity - 1;
                item.setQuantity(newQuantity);
                break; // Exit the loop once the item is found and updated
            }
        }
        //notify observer for changes in the list
        notifyObservers();

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
