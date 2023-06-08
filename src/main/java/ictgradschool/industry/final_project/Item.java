package ictgradschool.industry.final_project;

public class Item {
    private String Identifier;
    private  String Name;

    private  String Description;

    private  double Price;

    private  int Quantity;

    public Item(String identifier, String name, String description, double price, int quantity) {
        this.Identifier = identifier;
        this.Name = name;
        this.Description = description;
        this.Price = price;
        this.Quantity = quantity;
    }

    public  String getIdentifier() {
        return this.Identifier;
    }

    public String getName() {
        return this.Name;
    }

    public String getDescription() {
        return this.Description;
    }

    public int getQuantity() {
        return this.Quantity;
    }

    public double getPrice() {
        return this.Price;
    }

    public void setIdentifier(String Identifier){
        this.Identifier= Identifier;
    }

    public void setName(String Name){
        this.Name= Name;
    }

    public void setDescription(String Description){
        this.Description= Description;
    }

    public void setPrice(double Price){
        this.Price= Price;
    }

    public void setQuantity(int Quantity){
        this.Quantity= Quantity;
    }

}
