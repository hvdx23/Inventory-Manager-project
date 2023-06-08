package ictgradschool.industry.final_project;

import javax.swing.table.AbstractTableModel;

public class SaleInventory extends AbstractTableModel implements InventoryObserver {
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
}
