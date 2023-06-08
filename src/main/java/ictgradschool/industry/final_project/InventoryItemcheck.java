package ictgradschool.industry.final_project;
import ictgradschool.industry.final_project.Item;
import ictgradschool.industry.final_project.InventoryItems;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class InventoryItemcheck {
    public static void main(String[] args) {
//        Item inventory=new Item(Identifier,Name,Description,Price,Quantity);
        InventoryItems inventory = new InventoryItems();
        inventory.loadFromCSV("test.csv");

        System.out.println("Number of rows: " + inventory.getRowCount());
        System.out.println("Number of columns: " + inventory.getColumnCount());

        Item newItem = new Item("WITCHER123", "Witcher 4", "Best game ever", 60, 5);
        inventory.addItem(newItem);
        Item newItem1 = new Item("RAGNAROK", "God of war ragnarok", "Best game ever-period", 60, 5);
        inventory.addItem(newItem1);
        for (int i = 0; i < inventory.getRowCount(); i++) {
            Item item = inventory.getItem(i);
            System.out.println("Item " + (i) + ":");
            System.out.println("Identifier: " + item.getIdentifier());
            System.out.println("Name: " + item.getName());
            System.out.println("Description: " + item.getDescription());
            System.out.println("Price: " + item.getPrice());
            System.out.println("Quantity: " + item.getQuantity());
            System.out.println();
        }

//        Item item= inventory.getItem(0);
//        if (item != null) {
//            System.out.println("Item Details:");
//            System.out.println("Identifier: " + item.getIdentifier());
//            System.out.println("Name: " + item.getName());
//            System.out.println("Description: " + item.getDescription());
//            System.out.println("Price: " + item.getPrice());
//            System.out.println("Quantity: " + item.getQuantity());
//        } else {
//            System.out.println("Item not found in inventory.");
//        }
//
//        Item item1 = inventory.getItem(1);
//        if (item1 != null) {
//            System.out.println("Item Details:");
//            System.out.println("Identifier: " + item1.getIdentifier());
//            System.out.println("Name: " + item1.getName());
//            System.out.println("Description: " + item1.getDescription());
//            System.out.println("Price: " + item1.getPrice());
//            System.out.println("Quantity: " + item1.getQuantity());
//        } else {
//            System.out.println("Item not found in inventory.");
//        }

//        InventoryItems inventorysave= new InventoryItems();
        String filePath = "testsaveinvne.csv";
        inventory.saveToCSV(filePath);

    }
}
//GODXXX7890,God of war,Game of the year,60,22,SPDMN765489,Spider man,enhanced,69,3,MILSMRLS23,Miles morales,Additional,40,23,DTH45STRND,Death Stranding,directors cut,75,34
//        WITCHER123,Witcher 4,Best game ever,60.0,5
//        RAGNAROK,God of war ragnarok,Best game ever-period,60.0,5
