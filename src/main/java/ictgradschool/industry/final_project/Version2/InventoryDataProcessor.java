package ictgradschool.industry.final_project.Version2;



import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class InventoryDataProcessor {

    public void createFile(String fileName) {
        File file = new File(fileName);


        if (file.exists()) {
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
    public List<InventoryItem> readInventoryFromFile(String fileName){
        List<InventoryItem> inventoryList=new LinkedList<>();
        BufferedReader br=null;
        try  {
            br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                InventoryItem item = createInventoryItemFromValues(values);
                inventoryList.add(item);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {

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
    public void saveInventoryToFile(String fileName,List<InventoryItem> inventoryItems){
        //write to file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            // Write column names


            // Write data rows
            for (InventoryItem item : inventoryItems) {
                String row = createValuesFromInventoryItem(item);
                bw.write(row);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private InventoryItem createInventoryItemFromValues(String[] values) {
        String identifier = values[0];
        String name = values[1];
        String description = values[2];
        double price = Double.parseDouble(values[3]);
        int quantity = Integer.parseInt(values[4]);
        return new InventoryItem(identifier, name, description, price, quantity);
    }

    private String createValuesFromInventoryItem(InventoryItem item) {
        String[] values = new String[5];
        values[0] = item.getIdentifier();
        values[1] = item.getName();
        values[2] = item.getDescription();
        values[3] = String.valueOf(item.getPrice());
        values[4] = String.valueOf(item.getQuantity());
        return String.join(",", values);
    }

    public void addInventoryItem(String fileName, InventoryItem item){
        List<InventoryItem> inventoryList=readInventoryFromFile(fileName);
        inventoryList.add(item);
        saveInventoryToFile(fileName,inventoryList);

    }

    public  void deleteInventoryItem(String fileName,InventoryItem item){
        List<InventoryItem> inventoryList=readInventoryFromFile(fileName);
        inventoryList.remove(item);
        saveInventoryToFile(fileName,inventoryList);
    }

    public void updateInventoryItem(String fileName,InventoryItem item){
        List<InventoryItem> inventoryList=readInventoryFromFile(fileName);
        for(InventoryItem i:inventoryList){
            if(i.getIdentifier().equals(item.getIdentifier())){
                i.setName(item.getName());
                i.setDescription(item.getDescription());
                i.setPrice(item.getPrice());
                i.setQuantity(item.getQuantity());
            }
        }
        saveInventoryToFile(fileName,inventoryList);
    }

    public void searchInventoryItem(String fileName,String search){
        List<InventoryItem> inventoryList=readInventoryFromFile(fileName);
        for(InventoryItem i:inventoryList){
            if(i.getIdentifier().equals(search)){
                System.out.println(i);
            }
        }
    }

    public void sortInventoryItem(String fileName){
        List<InventoryItem> inventoryList=readInventoryFromFile(fileName);
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

    public void posAddItem(String fileName,InventoryItem item){
        List<InventoryItem> inventoryList=readInventoryFromFile(fileName);
        for(InventoryItem i:inventoryList){
            if(i.getIdentifier().equals(item.getIdentifier())){
                i.setQuantity(i.getQuantity()-1);
            }
        }
    }

    public void posDeleteItem(String fileName,InventoryItem item){
        List<InventoryItem> inventoryList=readInventoryFromFile(fileName);
        for(InventoryItem i:inventoryList){
            if(i.getIdentifier().equals(item.getIdentifier())){
                i.setQuantity(i.getQuantity()+1);
            }
        }
    }

    public void checkout(String fileName,List<InventoryItem> items){
        List<InventoryItem> inventoryList=readInventoryFromFile(fileName);
        for(InventoryItem i:items){
            for(InventoryItem j:inventoryList){
                if(i.getIdentifier().equals(j.getIdentifier())){
                    j.setQuantity(j.getQuantity()-1);
                }
            }
        }
        saveInventoryToFile(fileName,inventoryList);
    }


}
