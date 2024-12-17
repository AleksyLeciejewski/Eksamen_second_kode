package Inventory;

import Database.DatabaseConnection;
import Items.Armor.Armor;
import Items.Consumable.Consumable;
import Items.Item;
import Items.Weapon.Weapon;

import javax.swing.*;
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

    public void removeItemBySlot() {
        String input = JOptionPane.showInputDialog("Enter the slot number to be deleted:");
        if (input == null) {
            return; // User cancelled the input
        }

        int slot;
        try {
            slot = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid slot number.");
            return;
        }

        if (slot < 1 || slot > inventoryList.size()) {
            JOptionPane.showMessageDialog(null, "The slot number is out of bounds. Try again.");
            return;
        }

        Item itemToRemove = inventoryList.get(slot - 1);
        if (itemToRemove == null) {
            JOptionPane.showMessageDialog(null, "Slot is empty. No item to remove.");
            return;
        }

        String sql = "DELETE FROM InventoryMain WHERE itemID = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, itemToRemove.getItemID());
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                inventoryList.remove(slot - 1);
                JOptionPane.showMessageDialog(null, "Item '" + itemToRemove.getName() + "' has been deleted from slot " + slot + ".");
            } else {
                JOptionPane.showMessageDialog(null, "Could not find the item in the database.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error deleting item: " + e.getMessage());
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



    public void sortInventory() {
        String[] options = {"Name", "Weight", "Item Type"};
        int choice = JOptionPane.showOptionDialog(null,
                "Choose sorting option",
                "Sort Inventory",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);

        try {
            switch (choice) {
                case 0:
                    Bubblesort.sort(inventoryList, Comparator.comparing(Item::getName));
                    break;
                case 1:
                    Bubblesort.sort(inventoryList, Comparator.comparingDouble(Item::getWeight));
                    break;
                case 2:
                    Bubblesort.sort(inventoryList, Comparator.comparing(Item::getItemType));
                    break;
                default:
                    throw new IllegalArgumentException("Invalid choice, please try again.");
            }
            JOptionPane.showMessageDialog(null, "Inventory sorted!");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Unexpected error during sorting: " + e.getMessage());
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

    public int addSlots() {
        JOptionPane.showMessageDialog(null, "You have chosen to add additional slot spaces to your inventory");
        String input = JOptionPane.showInputDialog("How many slots would you like to expand your inventory with?: ");

        int moreSlots;
        try {
            moreSlots = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
            return maxSlots;
        }

        if (moreSlots <= 0) {
            JOptionPane.showMessageDialog(null, "You cannot add 0 slots");
            return maxSlots;
        }

        this.maxSlots += moreSlots;

        JOptionPane.showMessageDialog(null, "You have now expanded your inventory with " + moreSlots + " slots");
        JOptionPane.showMessageDialog(null, "Your new inventory capacity is now: " + maxSlots + "!");

        return maxSlots;
    }
}