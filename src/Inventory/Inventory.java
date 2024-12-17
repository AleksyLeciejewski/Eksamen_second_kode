package Inventory;

import Database.DatabaseConnection;
import Items.Armor.Armor;
import Items.Consumable.Consumable;
import Items.Item;
import Items.Weapon.Weapon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Inventory {
    Scanner brugerInput = new Scanner(System.in);
    private int availableSlots;
    private int maxSlots = 32;
    private double totalWeight;
    private ArrayList<Item> inventoryList;
    private int maxWeight = 50;
    boolean weightCapacity = maxWeight >= totalWeight;
    public ArrayList<Item> getInventoryList() {
        return inventoryList;
    }

    public Inventory(int maxSlots, double totalWeight){
    this.maxSlots = maxSlots;
    this.availableSlots = maxSlots;
    this.inventoryList = new ArrayList<>();
    boolean maxWeight = this.weightCapacity;
    }

//addItem skal kaldes ved hver add item. Hver metode skal referere til sin respektive tabel.
    public void addItem(Item item) {
        String sql = "INSERT INTO InventoryMain (name, weight, maxStack, itemType) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Indsætter værdier i placeholders i SQL-sætningen.
            preparedStatement.setString(2, item.getName());
            preparedStatement.setInt(1, item.getItemID());
            preparedStatement.setInt(4, item.getMaxStack());
            preparedStatement.setDouble(3, item.getWeight());
            preparedStatement.setInt(5, item.getItemType());


            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                        System.out.println("A new item has been added to your inventory succesfully!");
            } else {
                System.out.println("Your inventory has no available slots remaining, please remove or sell an item");
            }
        } catch (SQLException e) {
            // Håndterer SQL-relaterede fejl.
            e.printStackTrace();
        }
        availableSlots -= 1;
        totalWeight += item.getWeight();
    }

    public void addWeapon(Weapon weapon) {

        if(totalWeight + weapon.getWeight() > maxWeight){
            System.out.println("You're over encumbered, please remove items to free up weight");
        return;
        }
        if (availableSlots <= 0){
            System.out.println("Your inventory has no available slots remaining, please remove or sell an item");
        return;
        }

        addItem(weapon);
        String sql = "INSERT INTO WeaponsMain (weaponId, damage, itemType) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Indsætter værdier i placeholders i SQL-sætningen.
            preparedStatement.setInt(1, weapon.getItemID());
            preparedStatement.setDouble(2, weapon.getDamage());
            preparedStatement.setInt(3, weapon.getItemType());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                        System.out.println("A new weapon has been added to your inventory succesfully!");
            } else {
                System.out.println("There was an error while adding the weapon to your inventory");
            }

        } catch (SQLException e) {
            // Håndterer SQL-relaterede fejl.
            e.printStackTrace();
        }
        availableSlots -= 1;
        totalWeight += weapon.getWeight();
    }

    public void addArmor(Armor armor) {

        if(totalWeight + armor.getWeight() > maxWeight){
            System.out.println("You're over encumbered, please remove items to free up weight");
            return;
        }
        if (availableSlots <= 0){
            System.out.println("Your inventory has no available slots remaining, please remove or sell an item");
            return;
        }

        addItem(armor);
        String sql = "INSERT INTO ArmorMain (armorId, defense, itemType) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Indsætter værdier i placeholders i SQL-sætningen.
            preparedStatement.setInt(1, armor.getItemID());
            preparedStatement.setDouble(2, armor.getDefense());
            preparedStatement.setInt(3, armor.getItemType());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                    System.out.println("A new armor has been added to your inventory succesfully!");

            } else{
                System.out.println("There was an error while adding the armor to your inventory");
            }
        } catch (SQLException e) {
            // Håndterer SQL-relaterede fejl.
            e.printStackTrace();
        }
        availableSlots -= 1;
        totalWeight += armor.getWeight();
    }

    public void addConsumable(Consumable consumable) throws SQLException {

        if(totalWeight + consumable.getWeight() > maxWeight){
            System.out.println("You're over encumbered, please remove items to free up weight");
            return;
        }
        if (availableSlots <= 0){
            System.out.println("Your inventory has no available slots remaining, please remove or sell an item");
            return;
        }

        addItem(consumable);
        String sql = "INSERT INTO ConsumableMain (consumableId, consumableEffect, consumableDuration, itemType) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Indsætter værdier i placeholders i SQL-sætningen.
            preparedStatement.setInt(1, consumable.getItemID());
            preparedStatement.setInt(4, Integer.parseInt(consumable.getItemType()));
            preparedStatement.setInt(3, consumable.getDuration());
            preparedStatement.setString(2, consumable.getEffect());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new consumable has been added to your inventory succesfully!");                    availableSlots -= 1;
            } else{
                System.out.println("There was an error while adding the armor to your inventory");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        availableSlots -= 1;
        totalWeight += consumable.getWeight();
    }

    public void removeItemBySlot(int slot) {

        if (slot < 1 || slot > inventoryList.size()) {
            System.out.println("The slot space is out of bounds. Try again.");
            return;
        }

        Item itemToRemove = inventoryList.get(slot - 1);
        if (itemToRemove == null) {
            System.out.println("Slot spot is empty. No item to remove.");
            return;
        }

        String sql = "DELETE FROM InventoryMain WHERE itemID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, itemToRemove.getItemID());
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {

                System.out.printf("Genstanden '%s' er blevet slettet fra slot %d.%n",
                        itemToRemove.getName(), slot);


                inventoryList.remove(slot - 1);
            } else {

                System.out.printf("Kunne ikke finde genstanden '%s' i databasen.%n",
                        itemToRemove.getName());
            }

        } catch (SQLException e) {

            System.err.printf("Fejl ved sletning af slot %d: %s%n", slot, e.getMessage());
        }
    }


    public void showInventory() {
        String sql = "SELECT name, maxStack, weight, isStackable FROM InventoryMain";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("itemname");
                int maxStack = resultSet.getInt("maxStack");
                double weight = resultSet.getDouble("weight");
                String itemType = resultSet.getString("itemType");

                System.out.printf("Items.Item: %s, Max Stack: %d, Weight: %.2f, Is Stackable: %b%n", name, maxStack, weight, itemType);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error upon loading inventory");
        }
    }



    public void sortInventory(int choice) {
        try {

            switch (choice) {
                case 1:
                    Bubblesort.sort(inventoryList, Comparator.comparing(Item::getName));
                    break;

                case 2:
                    Bubblesort.sort(inventoryList, Comparator.comparingDouble(Item::getWeight));
                    break;

                case 3:
                    Bubblesort.sort(inventoryList, Comparator.comparing(Item::getItemType));
                    break;

                default:
                    throw new IllegalArgumentException("Ugyldigt valg, prøv igen.");
            }
            System.out.println("Inventory sorteret!");

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            System.out.println("Sorting fejl");
        } catch (Exception e) {
            System.err.println("Uventet fejl ved sortering: " + e.getMessage());
        }
    }



    public double calcTotalWeight(){
        double totalWeight = 0;
        for (Item item : inventoryList) {
            totalWeight += item.getWeight();
        }
    return totalWeight;
    }
/*
    public int addSlots(int moreSlots){

    if(moreSlots <= 0){
            System.out.println("Du kan ikke tilføje 0 slots");
    return maxSlots;
    }

    this.maxSlots += moreSlots;

        System.out.println("Du har nu udvidet din inventory med " + moreSlots + "pladser");
        System.out.println("Din nuværende kapacitet er nu opgraderet til: " + maxSlots + "!");
        System.out.println("Dit inventory er fyldt! Slet eller sælg items for at frigøre plads");

    return maxSlots;
    }

 */

    public int addSlots(){

        System.out.println("You have chosen to add additional slot spaces to your inventory");
        System.out.println("How many slots would you like to expand your inventory with?: ");

    int moreSlots = brugerInput.nextInt();

        if(moreSlots <= 0){
            System.out.println("You cannot add 0 slots");
        return maxSlots;
        }

    this.maxSlots += moreSlots;

        System.out.println("You have now expanded your inventory with " + moreSlots + " slots");
        System.out.println("Your new inventory capacity is now: " + maxSlots + "!");

    return maxSlots;
    }
}