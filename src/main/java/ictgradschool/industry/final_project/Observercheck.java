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

        SaleInventory saleInventory = new SaleInventory();
        inventory.loadFromCSV("test.csv");
        InventoryObserver observer = new InventoryChangeObserver(inventory);
        inventory.registerObserver(observer);
        System.out.println(inventory);

//        for (int i = 0; i < inventory.getRowCount(); i++) {
//            Item item = inventory.getItem(i);
////            System.out.println("Item " + (i) + ":");
////            System.out.println("id: "+ item.getIdentifier()+ " name: "+ item.getName()+ " desc: "+ item.getDescription()+ " price: "+ item.getPrice()+ " Qty: "+ item.getQuantity());
//////            System.out.println("Identifier: " + item.getIdentifier());
////            System.out.println("Name: " + item.getName());
////            System.out.println("Description: " + item.getDescription());
////            System.out.println("Price: " + item.getPrice());
////            System.out.println("Quantity: " + item.getQuantity());
////            System.out.println();
////            item.toString();
//
//        }
//          inventory.setValueAt(100.0,2,3);


//        inventory.setValueAt("Game of the year-period",0,2);


//        System.out.println("======================================================================");


        Item newItem = new Item("WITCHER2345", "Witcher 3", "Best game ever", 60, 5);
        inventory.addItem(newItem);

        System.out.println(inventory);


    }



    }
