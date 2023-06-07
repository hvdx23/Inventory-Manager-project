package ictgradschool.industry.final_project;
import javax.swing.table.AbstractTableModel;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryItems extends AbstractTableModel {
    private List<String[]> data = new ArrayList<>();

    private String[] columnNames={"Identifier","Name","Description", "price", "quantity"};

    public void loadFromCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (firstLine) {
                    columnNames = values;
                    firstLine = false;
                } else {
                    data.add(values);
                }
            }
            fireTableStructureChanged();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveToCSV(String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            // Write column names
            bw.write(String.join(",", columnNames));
            bw.newLine();

            // Write data rows
            for (String[] row : data) {
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

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex < data.size() && columnIndex < getColumnCount()) {
            return data.get(rowIndex)[columnIndex];
        }
        return null;
    }

    public int getData() {
        return data.size();
    }

    public int getColumnNames() {
        return columnNames.length;
    }

    public void addItem(String[] newItem) {
        data.add(newItem);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }
}
