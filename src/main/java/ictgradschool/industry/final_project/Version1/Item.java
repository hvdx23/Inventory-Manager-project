package ictgradschool.industry.final_project.Version1;

import java.util.Objects;

public class Item {
    private String Identifier;
    private  String Name;

    private  String Description;

    private  double Price;

    private  int Quantity;

    public Item(String Identifier, String Name, String Description, double Price, int Quantity) {
        this.Identifier = Identifier;
        this.Name = Name;
        this.Description = Description;
        this.Price = Price;
        this.Quantity = Quantity;
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
//        notifyObservers();
    }

    public void setName(String Name){
        this.Name= Name;
//        notifyObservers();
    }

    public void setDescription(String Description){
        this.Description= Description;
//        notifyObservers();
    }

    public void setPrice(double Price){
        this.Price= Price;
//        notifyObservers();
    }

    public void setQuantity(int Quantity){
        this.Quantity= Quantity;
//        notifyObservers();
    }

    //Obj code for getindex method
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Item other = (Item) obj;
        return Objects.equals(Identifier, other.Identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Identifier);
    }

}
