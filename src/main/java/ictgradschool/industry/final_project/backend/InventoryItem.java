package ictgradschool.industry.final_project.backend;

import java.util.ArrayList;

public class InventoryItem {


    private int quantity;

    private int reserved;
    private String name;
    private String id;
    private ArrayList<InventoryItemObserverver1> observers;

    private void notifyObservers(boolean persist) {
        for (InventoryItemObserverver1 iio : observers) {
            iio.inventoryItemChanged(this, persist);
        }
    }
    public int getQuantity() {
        return quantity - reserved;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;

        notifyObservers(true);
    }

    public int getReserved() {
        return reserved;
    }

    public void setReserved(int reserved) {
        this.reserved = reserved;
        notifyObservers(false);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

        notifyObservers(true);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;

        notifyObservers(true);
    }

    public InventoryItem(String id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.observers = new ArrayList<>();
    }

    void addObserver(InventoryItemObserverver1 iio) {
        this.observers.add(iio);
    }

    void removeObserver(InventoryItemObserverver1 iio) {
        this.observers.remove(iio);
    }
}
