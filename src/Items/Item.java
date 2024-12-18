package Items;

import java.util.Scanner;

public abstract class Item implements IsStackable{

  public Item(String name, double weight, int maxStack, String itemType, boolean isStackable){
    this.itemID = itemID;
    this.name = name;
    this.weight = weight;
    this.maxStack = maxStack;
    this.ItemType = itemType;
    this.isStackable = isStackable();
  }

    private String ItemType;
    private int itemID;
    private String name;
    private double weight;
    private boolean isStackable;
    private int maxStack;

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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public boolean isStackable() {
        return isStackable;
    }

    public void setStackable(boolean stackable) {
        isStackable = stackable;
    }

    public int getMaxStack() {
        return maxStack;
    }

    public void setMaxStack(int maxStack) {
        this.maxStack = maxStack;
    }


    //Forslag: set boolean = false som standard, og override til true i subklasserne, hvis nødvendigt


    @Override
    public String toString() {
        return String.format("Name: %s, Weight: %.2f, Max Stack: %d, Item Type: %s",
                name, weight, maxStack, ItemType);
    }


    public String getItemType() {
        return ItemType;
    }
}


