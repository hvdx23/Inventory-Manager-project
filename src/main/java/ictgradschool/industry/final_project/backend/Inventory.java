package ictgradschool.industry.final_project.backend;

import java.util.ArrayList;

public class Inventory implements InventoryItemObserverver1 {

    private boolean isInitialised;
    private ArrayList<InventoryObserverver1> observers;
    private ArrayList<InventoryItem> items;
    private String filename;

    public Inventory() {
        this.isInitialised = false;
        this.observers = new ArrayList<>();
    }

    private void notifyObservers() {
        for (InventoryObserverver1 io : observers) {
            io.inventoryChanged(this);
        }
    }

    public void removeItem(InventoryItem ii) {
        this.items.remove(ii);
        ii.removeObserver(this);

        saveFile();

        notifyObservers();
    }

    public void addItem(String id, String name, int quantity) {
        // Add an item to the inventory
        InventoryItem item = new InventoryItem(id, name, quantity);
        this.addItem(item);
    }

    public void addItem(InventoryItem item) {
        // Add an item to the inventory

        this.items.add(item);
        item.addObserver(this);

        notifyObservers();
    }

    public boolean loadFile(String filename) {
        // Try to read in each line from the file, adding inventory items
        this.filename = filename;
        this.isInitialised = true;

        return this.isInitialised;
    }

    public void saveFile() {
        // Open the file
        // Save each InventoryItem we hold
    }

    void addObserver(InventoryObserverver1 io) {
        this.observers.add(io);
    }

    void removeObserver(InventoryObserverver1 io) {
        this.observers.remove(io);
    }

    @Override
    public void inventoryItemChanged(InventoryItem item, boolean persist) {
        if (this.isInitialised && persist) {
            this.saveFile();
        }

        notifyObservers();
    }
    // TODO: This should support providing a list of items, getting items at an index and so on, so the adaptors can function
}
