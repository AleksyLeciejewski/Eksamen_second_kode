package Items.Armor;

import Items.Item;
import Items.Reforge;

import java.util.Random;
import java.util.Scanner;

public class Armor extends Item implements Reforge {

    Scanner brugerInput = new Scanner(System.in);
    Random reforgeSetter = new Random(); //random generator initialiseres

    public Armor(int itemID, String name, double weight, int maxStack, double defense, String ItemType, boolean isStackable){
        super(name, itemID, weight, maxStack, ItemType, isStackable);
        this.defense = defense;
   }
    double defense;

    public double getDefense() {
        return defense;
    }

    public void setDefense(double defense) {
        this.defense = defense;
    }

    @Override
    public boolean isStackable(){
//        if(!isStackable()){
//            setMaxStack(1);

        return false;}

    @Override
    public void ReforgeStats() {
    int valg = brugerInput.nextInt();
        System.out.println("You have chosen to reforge the stats on your armor");
        System.out.println("Select the stats you'd like to reforge");

            switch (valg) {

                case 1: //defense
                    System.out.println("You have chosen to reforge the defense attribute on your armor");
                    System.out.println("This transaction will cost you XX gold.  Do you want to continue?"); //Lod vær' med at implementere brugerinput prompts for at det kan implementeres i gui
                 double newDefense = getDefense() + reforgeSetter.nextDouble() * 5 + 1; //Påsætter en begrænsning for værdierne.
                setDefense(newDefense);

                     System.out.println("Your armor now has: " + newDefense + " defense");

                break;

                case 2:
                    System.out.println("You have chosen to reforge the wieght of your armor");
                    System.out.println("This transaction will cost you XX gold. Do you want to continue?"); //Lod vær' med at implementere brugerinput prompts for at det kan implementeres i gui

                double newWeight = getWeight() + reforgeSetter.nextDouble() * 15 + 3; //Påsætter en begrænsning for værdierne.
                setWeight(newWeight);

                    System.out.println("Your armor now weighs: " + newWeight);

                break;
        }
    }
}
