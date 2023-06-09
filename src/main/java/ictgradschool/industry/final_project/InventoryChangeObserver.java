package ictgradschool.industry.final_project;

public class InventoryChangeObserver implements InventoryObserver {
    private InventoryItems inventory;

    public InventoryChangeObserver(InventoryItems inventory) {
        this.inventory = inventory;
    }
    @Override
    public void onInventoryChange() {

        String filePath = "testsave.csv";
        //csv to be saved at filepath
        inventory.saveToCSV(filePath);
        //savetoCSV() here when inventory changes
//        System.out.println("Inventory changed");
//        System.out.println("======================================================================");
    }
}
