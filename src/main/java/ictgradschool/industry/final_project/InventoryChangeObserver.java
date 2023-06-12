package ictgradschool.industry.final_project;

public class InventoryChangeObserver implements InventoryObserver {
    private InventoryTableModel inventory;

    public InventoryChangeObserver(InventoryTableModel inventory) {
        this.inventory = inventory;
    }
    @Override
    public void onInventoryChange() {

//        String filePath = "testsave.csv";
//        //csv to be saved at filepath
//        inventory.saveToCSV(filePath);
        //savetoCSV() here when inventory changes
//        System.out.println("Inventory changed");
//        System.out.println("======================================================================");
    }
}
