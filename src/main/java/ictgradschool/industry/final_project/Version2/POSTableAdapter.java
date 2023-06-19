//package ictgradschool.industry.final_project.Version2;
//
//import ictgradschool.industry.final_project.Version1.Item;
//
//import javax.swing.table.AbstractTableModel;
//import java.util.List;
//
//public class POSTableAdapter extends AbstractTableModel implements InventoryItemObserver {
//
//    private String[] columnNames={"Identifier","Name","Description", "Price", "Quantity"};
//
//
//    List<InventoryItem> inventoryItems;
//
//
//    @Override
//    public int getRowCount() {
//        return 0;
//    }
//
//    @Override
//    public int getColumnCount() {
//        return 0;
//    }
//
//    @Override
//    public Object getValueAt(int rowIndex, int columnIndex) {
//        return null;
//    }
//
//    //POS table getItem method
//    public static Object getObject(int rowIndex, int columnIndex, List<InventoryItem> inventoryItems) {
//        InventoryItem item = inventoryItems.get(rowIndex);
//        switch (columnIndex) {
//            case 0:
//                return item.getIdentifier();
//            case 1:
//                return item.getName();
//            case 2:
//                return item.getDescription();
//            case 3:
//                return item.getPrice();
//            case 4:
//                return item.getQuantity();
//            default:
//                return null;
//
//        }
//    }
//}


//postable new method

package ictgradschool.industry.final_project.Version2;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class POSTableAdapter extends AbstractTableModel implements InventoryItemObserver {

    private String[] columnNames = {"Identifier", "Name", "Description", "Price", "Quantity"};

    List<InventoryItem> inventoryItems;
    List<InventoryItem> posItems;

    public POSTableAdapter(List<InventoryItem> inventoryItems) {
        this.inventoryItems = inventoryItems;
        this.posItems = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return posItems.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return getObject(rowIndex, columnIndex, posItems);
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void addInventoryData(InventoryItem data) {
        posItems.add(data);
        fireTableDataChanged();
    }

    public InventoryItem getInventoryItem(int row) {
        return posItems.get(row);
    }

    public List<InventoryItem> getInventoryItems() {
        return posItems;
    }

    public static Object getObject(int rowIndex, int columnIndex, List<InventoryItem> inventoryItems) {
        InventoryItem item = inventoryItems.get(rowIndex);
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
}
