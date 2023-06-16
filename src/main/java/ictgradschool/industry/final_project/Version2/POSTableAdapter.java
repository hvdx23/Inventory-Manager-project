//package ictgradschool.industry.final_project.Version2;
//
//import ictgradschool.industry.final_project.Version1.Item;
//
//import javax.swing.table.AbstractTableModel;
//import java.util.List;
//
//public class POSTableAdapter extends AbstractTableModel implements POSObserver{
//    private final List<posItem> posItems;
//    private List<Item> inventoryItems;
//    private List<Item> cartItems;
//
//    public POSTableAdapter(List<InventoryItem> posItems) {
//        this.posItems = posItems;
//
//    }
//
//    @Override
//    public int getRowCount() {
//        return posItems.size();
//    }
//
//    @Override
//    public int getColumnCount() {
//        if (columnNames != null) {
//            return columnNames.length;
//        }
//        return 0;
//    }
//
//    @Override
//    public Object getValueAt(int rowIndex, int columnIndex) {
//        return getObject(rowIndex, columnIndex, inventoryItems);
//    }
//
//    @Override
//    public String getColumnName(int column) {
//        return columnNames[column];
//    }
//
//    public void setInventoryData(List<InventoryItem> data) {
//        this.posItems = data;
//        fireTableDataChanged();
//    }
//
////    public InventoryItem getmatchingItem(String identifier){
////        for (InventoryItem item:inventoryItems){
////            if (item.getIdentifier().equals(identifier)){
////                return item;
////            }
////        }
////        return null;
////    }
//
//    public void addInventoryData(InventoryItem data) {
//        this.inventoryItems.add(data);
//        fireTableDataChanged();
//    }
//
//    public void deleteInventoryData(InventoryItem data) {
//        this.inventoryItems.remove(data);
//        fireTableDataChanged();
//    }
//
//    public InventoryItem getInventoryItem(int row) {
//        return posItems.get(row);
//    }
//
//
//    public static Object getObject(int rowIndex, int columnIndex, List<InventoryItem> inventoryItems) {
//        posItem item = inventoryItems.get(rowIndex);
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
////    @Override
////    public int getRowCount() {
////        return 0;
////    }
////
////    @Override
////    public int getColumnCount() {
////        return 0;
////    }
////
////    @Override
////    public Object getValueAt(int rowIndex, int columnIndex) {
////        return null;
////    }
//}
