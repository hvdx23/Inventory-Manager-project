package ictgradschool.industry.final_project.Version2;

//import ictgradschool.industry.final_project.Version1.Item;

import javax.swing.table.AbstractTableModel;
import java.util.List;

//get methods from version 1 tableadaptor
public class InventoryTableAdaptor extends AbstractTableModel {
    private String[] columnNames={"Identifier","Name","Description", "Price", "Quantity"};
    List<InventoryItem> inventoryItems;

    public InventoryTableAdaptor(List<InventoryItem> inventoryItems) {
        this.inventoryItems = inventoryItems;
    }

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

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return getObject(rowIndex, columnIndex, inventoryItems);
    }

    private Object getObject(int rowIndex, int columnIndex, List<InventoryItem> inventoryItems) {
        return null;
    }

//    public static Object getObject(int rowIndex, int columnIndex, List<Item> inventoryItems) {
//        Item item = inventoryItems.get(rowIndex);
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
}
