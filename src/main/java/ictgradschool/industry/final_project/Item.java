package ictgradschool.industry.final_project;

public class Item {
    private final String Identifier;
    private final String Name;

    private final String Description;

    private final double Price;

    private final int Quantity;

    public Item(String identifier, String name, String description, double price, int quantity) {
        this.Identifier = identifier;
        this.Name = name;
        this.Description = description;
        this.Price = price;
        this.Quantity = quantity;
    }
}
