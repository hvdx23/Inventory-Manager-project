package ictgradschool.industry.final_project.backend;

import ictgradschool.industry.final_project.Item;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class InventoryDataProcessor {
    //Read inventory from file
    public List<Item> readInventoryFromFile(String fileName){
        List<Item> inventoryList=new LinkedList<>();
        BufferedReader br=null;
        try  {
             br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
//                System.out.println("Values: " + Arrays.toString(values)); // Debug output

                Item item = createInventoryItemFromValues(values);
                inventoryList.add(item);
            }
//            fireTableStructureChanged();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            //CLOSE BUfferedReader here using try catch
            if(br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return inventoryList;
    }

    //save file to  csv using buffered reader

    public void writeinventorytoFile(String fileName,List<Item> inventoryItems){


    }
    private Item createInventoryItemFromValues(String[] values) {
        String identifier = values[0];
        String name = values[1];
        String description = values[2];
        double price = Double.parseDouble(values[3]);
        int quantity = Integer.parseInt(values[4]);
        return new Item(identifier, name, description, price, quantity);
    }

    private String createValuesFromInventoryItem(Item item) {
        String[] values = new String[5];
        values[0] = item.getIdentifier();
        values[1] = item.getName();
        values[2] = item.getDescription();
        values[3] = String.valueOf(item.getPrice());
        values[4] = String.valueOf(item.getQuantity());
        return String.join(",", values);
    }
}
