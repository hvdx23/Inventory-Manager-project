package ictgradschool.industry.final_project.Version2;

import ictgradschool.industry.final_project.Version1.Item;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class InventoryTableAdaptor extends AbstractTableModel {
    List<Item> inventoryItems;
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
