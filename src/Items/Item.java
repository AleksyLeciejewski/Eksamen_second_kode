package Items;

import java.util.Scanner;

public abstract class Item {

  public Item(String name, int itemID, double weight){
    this.itemID = itemID;
    this.name = name;
    this.weight = weight;

  }

  private int itemID;
  private String name;
  private double weight;

  Scanner brugerinput = new Scanner(System.in);

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

//    public boolean isStackable() {
//        if(isStackable == false){
//            setMaxStack(1);
//        }
//        return false;
//    }



    //Forslag: set boolean = false som standard, og override til true i subklasserne, hvis nødvendigt


    public String toString() {
        return String.format("name: %s, weight: %f" );
    }

    }



