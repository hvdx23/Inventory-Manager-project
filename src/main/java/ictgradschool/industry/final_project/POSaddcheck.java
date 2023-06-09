package ictgradschool.industry.final_project;

import java.util.Arrays;
import java.util.List;

public class POSaddcheck {
    public static void main(String[] args) {
        Item Item1=new Item("GODXXX7890", "God of War", "Game of the year", 60.0, 0);
        Item Item2=new Item("SPDMN765489", "Spiderman", "Enhanced", 69.0, 3);
        Item Item3=new Item("MILSMRLS23", "Miles Morales", "Additional", 40.0, 2);
        Item Item4=new Item("DTH45STRND", "Death Stranding", "Director's Cut", 75.0, 0);
        Item Item5=new Item("WITCHER123", "Witcher 4", "Best game ever", 60.0, 5);
        Item Item6=new Item("RAGNAROK", "God of War Ragnarok", "Best game ever - period", 60.0, 5);

        InventoryItems inventoryItems = new InventoryItems();
        inventoryItems.addItem(Item1);
        inventoryItems.addItem(Item2);
        inventoryItems.addItem(Item3);
        inventoryItems.addItem(Item4);
        inventoryItems.addItem(Item5);
        inventoryItems.addItem(Item6);



    }
}
