package ictgradschool.industry.final_project;
import ictgradschool.industry.final_project.Item;
import ictgradschool.industry.final_project.InventoryItems;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class Observercheck {
    public static void main(String[] args) {

        // observer pattern implementation
        InventoryItems inventory = new InventoryItems();
        InventoryObserver observer=new InventoryChangeObserver(inventory);
        inventory.registerObserver(observer);
        SaleInventory saleInventory = new SaleInventory();
        inventory.loadFromCSV("testobs.csv");
        for (int i = 0; i < inventory.getRowCount(); i++) {
            Item item = inventory.getItem(i);
            System.out.println("Item " + (i) + ":");
            System.out.println("id: "+ item.getIdentifier()+ " name: "+ item.getName()+ " desc: "+ item.getDescription()+ " price: "+ item.getPrice()+ " Qty: "+ item.getQuantity());
////            System.out.println("Identifier: " + item.getIdentifier());
//            System.out.println("Name: " + item.getName());
//            System.out.println("Description: " + item.getDescription());
//            System.out.println("Price: " + item.getPrice());
//            System.out.println("Quantity: " + item.getQuantity());
//            System.out.println();
        }
//        System.out.println("======================================================================");



        Item newItem = new Item("WITCHER123", "Witcher 4", "Best game ever", 60, 5);
        inventory.addItem(newItem);

//        for (int j = 0; j < inventory.getRowCount(); j++) {
//            Item item = inventory.getItem(j);
//            System.out.println("Item " + (j) + ":");
//            System.out.println("id: "+ item.getIdentifier()+ " name: "+ item.getName()+ " desc: "+ item.getDescription()+ " price: "+ item.getPrice()+ " Qty: "+ item.getQuantity());
//
//
//        }

//        List<InventoryObserver> observers = inventory.getObservers();
//
//        for(InventoryObserver observer1:observers){
//            System.out.println(observer);
//        }






    }
}
