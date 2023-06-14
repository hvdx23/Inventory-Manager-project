package ictgradschool.industry.final_project.Version2;

import ictgradschool.industry.final_project.Version1.Item;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class InventoryDataProcessor {

    public void createFile(String fileName) {
        File file = new File(fileName);


        if (file.exists()) {
//            JOptionPane.showMessageDialog(this, "File already exists");
            return;
        }
        try {

            if (!fileName.toLowerCase().endsWith(".csv")) {
                fileName += ".csv";
            }
            file.createNewFile();
        }
        catch (IOException exc) {
            exc.printStackTrace();
        }
    }
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
    public void saveInventoryToFile(String fileName,List<Item> inventoryItems,String[] columnNames){
        //write to file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            // Write column names
            bw.write(String.join(",", columnNames));
            bw.newLine();

            // Write data rows
            for (Item item : inventoryItems) {
                String row = createValuesFromInventoryItem(item);
                bw.write(row);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void addInventoryItem(String fileName, Item item){
        List<Item> inventoryList=readInventoryFromFile(fileName);
        inventoryList.add(item);
        saveInventoryToFile(fileName,inventoryList,new String[]{"Identifier","Name","Description","Price","Quantity"});

    }

    public  void deleteInventoryItem(String fileName,Item item){
        List<Item> inventoryList=readInventoryFromFile(fileName);
        inventoryList.remove(item);
        saveInventoryToFile(fileName,inventoryList,new String[]{"Identifier","Name","Description","Price","Quantity"});
    }

    public void updateInventoryItem(String fileName,Item item){
        List<Item> inventoryList=readInventoryFromFile(fileName);
        for(Item i:inventoryList){
            if(i.getIdentifier().equals(item.getIdentifier())){
                i.setName(item.getName());
                i.setDescription(item.getDescription());
                i.setPrice(item.getPrice());
                i.setQuantity(item.getQuantity());
            }
        }
        saveInventoryToFile(fileName,inventoryList,new String[]{"Identifier","Name","Description","Price","Quantity"});
    }

    public void searchInventoryItem(String fileName,String search){
        List<Item> inventoryList=readInventoryFromFile(fileName);
        for(Item i:inventoryList){
            if(i.getIdentifier().equals(search)){
                System.out.println(i);
            }
        }
    }

    public void sortInventoryItem(String fileName){
        List<Item> inventoryList=readInventoryFromFile(fileName);
        inventoryList.sort((o1, o2) -> {
            if(o1.getPrice()>o2.getPrice()){
                return 1;
            }
            else if(o1.getPrice()<o2.getPrice()){
                return -1;
            }
            else{
                return 0;
            }
        });
    }

    public void posAddItem(String fileName,Item item){
        List<Item> inventoryList=readInventoryFromFile(fileName);
        for(Item i:inventoryList){
            if(i.getIdentifier().equals(item.getIdentifier())){
                i.setQuantity(i.getQuantity()-1);
            }
        }
    }

    public void posDeleteItem(String fileName,Item item){
        List<Item> inventoryList=readInventoryFromFile(fileName);
        for(Item i:inventoryList){
            if(i.getIdentifier().equals(item.getIdentifier())){
                i.setQuantity(i.getQuantity()+1);
            }
        }
    }

    public void checkout(String fileName,List<Item> items){
        List<Item> inventoryList=readInventoryFromFile(fileName);
        for(Item i:items){
            for(Item j:inventoryList){
                if(i.getIdentifier().equals(j.getIdentifier())){
                    j.setQuantity(j.getQuantity()-1);
                }
            }
        }
        saveInventoryToFile(fileName,inventoryList,new String[]{"Identifier", "Name", "Description", "Price", "Quantity"});
    }


}
