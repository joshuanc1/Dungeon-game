package org.example.Hunter;

public class Item {

    //Item Parameters

    private int itemID;
    private String name;
    private int stock;
    private String description;
    private int typeID;


    //Getters & Setters


    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    //Constructor

    public Item (int itemID, String name, int stock, String description, int typeID) {
        this.itemID = itemID;
        this.name = name;
        this.stock = stock;
        this.description = description;
        this.typeID = typeID;
    }

    public Item (){};
}
