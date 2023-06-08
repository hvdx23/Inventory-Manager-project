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

        System.out.println("Number of rows " + inventory.getRowCount());
        System.out.println("Number of columns " + inventory.getColumnCount());
        for (int i = 0; i < inventory.getRowCount(); i++) {
            Item item = inventory.getItem(i);
            System.out.println("Item " + (i + 1) + ":");
            System.out.println("Identifier: " + item.getIdentifier());
            System.out.println("Name: " + item.getName());
            System.out.println("Description: " + item.getDescription());
            System.out.println("Price: " + item.getPrice());
            System.out.println("Quantity: " + item.getQuantity());
            System.out.println();


            Item newitem = new Item("WITCHER123", "Witcher 4", "Best game ever", 60, 5);
            inventory.addItem(newitem);


        }
    }
}
