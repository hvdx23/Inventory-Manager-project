package ictgradschool.industry.final_project;

import java.util.Arrays;

public class POSaddcheck {
    public static void main(String[] args) {
        InventoryItems inventory = new InventoryItems();
        POSInventory posInventory=new POSInventory();
        inventory.loadFromCSV("test.csv");
        for (int i = 0; i < inventory.getRowCount(); i++) {
            Item item=inventory.getItem(i);
            posInventory.addItemFromInventory(item);
//            System.out.println("id: "+ item.getIdentifier()+ " name: "+ item.getName()+ " desc: "+ item.getDescription()+ " price: "+ item.getPrice()+ " Qty: "+ item.getQuantity());
//            System.out.println("Values: " + Arrays.toString(posInventory));



            //add item from inventory to POS
        }
        System.out.println("contents of inventory: ");
        System.out.println(inventory);
        System.out.println("contents of POS: ");
        System.out.println(posInventory);
    }
}