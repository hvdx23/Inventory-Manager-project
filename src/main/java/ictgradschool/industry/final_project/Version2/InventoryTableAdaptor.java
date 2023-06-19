package ictgradschool.industry.final_project.Version2;



import javax.swing.table.AbstractTableModel;
import java.util.Iterator;
import java.util.List;

//get methods from version 1 tableadaptor
public class InventoryTableAdaptor extends AbstractTableModel implements InventoryItemObserver {
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

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void setInventoryData(List<InventoryItem> data) {
        this.inventoryItems = data;
        fireTableDataChanged();
    }

    public InventoryItem getmatchingItem(String identifier){
        for (InventoryItem item:inventoryItems){
            if (item.getIdentifier().equals(identifier)){
                return item;
            }
        }
        return null;
    }

    public void addInventoryData(InventoryItem data) {
        this.inventoryItems.add(data);
        fireTableDataChanged();
    }

    public void deleteInventoryData(InventoryItem data) {
//        this.inventoryItems.remove(data);
        Iterator<InventoryItem> inventoryItemIterator=inventoryItems.iterator();
        while (inventoryItemIterator.hasNext()){
            InventoryItem item=inventoryItemIterator.next();
            if (item.getIdentifier().equals(data.getIdentifier())){
                inventoryItemIterator.remove();
                break;
            }
        }
        fireTableDataChanged();
    }

    public InventoryItem getInventoryItem(int row) {
        return inventoryItems.get(row);
    }

    public List<InventoryItem> getInventoryItems() {
        return inventoryItems;
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

    public void removeRow(int row) {
        if (row >= 0 && row < inventoryItems.size()) {
            inventoryItems.remove(row);
            fireTableRowsDeleted(row, row);
        }
    }
}
