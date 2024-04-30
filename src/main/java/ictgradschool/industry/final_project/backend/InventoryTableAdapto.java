package ictgradschool.industry.final_project.backend;

import javax.swing.table.AbstractTableModel;

public class InventoryTableAdapto extends AbstractTableModel implements InventoryObserverver1 {
    @Override
    public void inventoryChanged(Inventory inventory) {
        fireTableDataChanged();
        //saveInventory();
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
}
