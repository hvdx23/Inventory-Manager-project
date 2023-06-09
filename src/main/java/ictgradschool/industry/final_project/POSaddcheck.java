package ictgradschool.industry.final_project;

import java.util.Arrays;

public class POSaddcheck {
    public static void main(String[] args) {
        InventoryItems inventory = new InventoryItems();
        POSInventory posInventory=new POSInventory();
        inventory.loadFromCSV("test.csv");
       //All Items added to inventory
//        for (int i = 0; i < inventory.getRowCount(); i++) {
//            Item item=inventory.getItem(i);
//            posInventory.addItemFromInventory(item);
////            System.out.println("id: "+ item.getIdentifier()+ " name: "+ item.getName()+ " desc: "+ item.getDescription()+ " price: "+ item.getPrice()+ " Qty: "+ item.getQuantity());
////            System.out.println("Values: " + Arrays.toString(posInventory));
//
//
//
//            //add item from inventory to POS
//        }
        //Adding only if the quantity is greater than 0 & subtracting from inventory
        for (int i = 0; i < inventory.getRowCount(); i++) {
            if (inventory.getItem(i).getQuantity()>0){
                Item item=inventory.getItem(i);
                posInventory.addItemFromInventory(item);
                inventory.reduceInventoryQuantity(item);
//                inventory.getItem(i).setQuantity(inventory.getItem(i).getQuantity()-1);
            }

//            Item item=inventory.getItem(i);
//            posInventory.addItemFromInventory(item);
////            System.out.println("id: "+ item.getIdentifier()+ " name: "+ item.getName()+ " desc: "+ item.getDescription()+ " price: "+ item.getPrice()+ " Qty: "+ item.getQuantity());
//            System.out.println("Values: " + Arrays.toString(posInventory));



            //add item from inventory to POS
        }
        System.out.println("contents of inventory: ");
        System.out.println(inventory);
        System.out.println("contents of POS: ");
        System.out.println(posInventory);


        String filePath = "./test/inventory.csv";
        inventory.saveToCSV(filePath);
        String filePath1 = "./test/pos.csv";
        posInventory.saveToCSV(filePath1);
    }
}



public class POSaddcheck {
    public static void main(String[] args) {


    }
}
