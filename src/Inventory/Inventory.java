package Inventory;

import Database.DatabaseConnection;
import Items.Armor.*;
import Items.Consumable.Consumable;
import Items.Item;
import Items.Weapon.Weapon;

import javax.swing.*;
import java.sql.*;
import java.util.*;

public class Inventory {
    private int availableSlots;
    private int maxSlots = 32;
    private double totalWeight;
    private ArrayList<Item> inventoryList;
    private int maxWeight = 50;
    boolean weightCapacity = maxWeight >= totalWeight;
    private Map<String, Integer> itemTypeMap = new HashMap<>();
    private List<Item> items;
    private int nextItemID;

    public List<Item> getInventoryList() {
        return inventoryList;
    }

    public Map<String, Integer> getItemTypeMap() {
        return itemTypeMap;
    }

    public Inventory(int maxSlots, double totalWeight) {
        this.maxSlots = maxSlots;
        this.availableSlots = maxSlots;
        this.inventoryList = new ArrayList<>();
        boolean maxWeight = this.weightCapacity;
        this.itemTypeMap = new HashMap<>();
        this.items = new ArrayList<>();
        this.nextItemID = 1;
        itemTypeMap.put("shortsword", 1);
        itemTypeMap.put("Armor", 2);
        itemTypeMap.put("Consumable", 3);
        itemTypeMap.put("one handed", 4);
        itemTypeMap.put("two handed", 5);
        itemTypeMap.put("healing", 6);
        itemTypeMap.put("mana", 7);
        itemTypeMap.put("chestplate", 8);
        itemTypeMap.put("shoulder pads", 9);
        itemTypeMap.put("Pants", 10);
        itemTypeMap.put("Shield", 11);
        itemTypeMap.put("leather hood", 12);
        itemTypeMap.put("Two Hands", 13);
        itemTypeMap.put("LegsArmor", 14);
        itemTypeMap.put("One-handed", 15); // Ensure this is added
        itemTypeMap.put("Chest Armor", 16);
        itemTypeMap.put("Head Armor", 17);
        itemTypeMap.put("one-hand", 18);
        itemTypeMap.put("Shoulder Armor", 19);
    }


    public int getNextItemID() {
        String sql = "SELECT MAX(itemid) AS maxItemID FROM InventoryMain";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            if (resultSet.next()) {
                return resultSet.getInt("maxItemID") + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1; // Return 1 if there is an error or no items in the database
    }

    //addItem skal kaldes ved hver add item. Hver metode skal referere til sin respektive tabel.
    public void addItem(Item item) {
        int nextItemID = getNextItemID();
        item.setItemID(nextItemID);

        String sql = "INSERT INTO InventoryMain (itemid, itemname, weight, maxStack, itemType) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            Integer itemTypeValue = itemTypeMap.get(item.getItemType());
            if (itemTypeValue == null) {
                throw new IllegalArgumentException("Unknown item type: " + item.getItemType());
            }

            preparedStatement.setInt(1, item.getItemID());
            preparedStatement.setString(2, item.getName());
            preparedStatement.setDouble(3, item.getWeight());
            preparedStatement.setInt(4, item.getMaxStack());
            preparedStatement.setInt(5, itemTypeValue);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new item has been added to your inventory successfully!");
                inventoryList.add(item); // Add item to inventoryList
                availableSlots -= 1;
                totalWeight += item.getWeight();
            } else {
                System.out.println("Your inventory has no available slots remaining, please remove or sell an item");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addWeapon(Weapon weapon) {

        if (totalWeight + weapon.getWeight() > maxWeight) {
            System.out.println("You're over encumbered, please remove items to free up weight");
            return;
        }
        if (availableSlots <= 0) {
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
            //  preparedStatement.setInt(3, weapon.getItemType());

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

        if (totalWeight + armor.getWeight() > maxWeight) {
            System.out.println("You're over encumbered, please remove items to free up weight");
            return;
        }
        if (availableSlots <= 0) {
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
            //   preparedStatement.setInt(3, armor.getItemType());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new armor has been added to your inventory succesfully!");

            } else {
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

        if (totalWeight + consumable.getWeight() > maxWeight) {
            System.out.println("You're over encumbered, please remove items to free up weight");
            return;
        }
        if (availableSlots <= 0) {
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
                System.out.println("A new consumable has been added to your inventory succesfully!");
                availableSlots -= 1;
            } else {
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

        String sql = "DELETE FROM InventoryMain WHERE itemid = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, itemToRemove.getItemID());
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                inventoryList.remove(slot - 1);
                availableSlots += 1;
                totalWeight -= itemToRemove.getWeight();
                JOptionPane.showMessageDialog(null, "Item '" + itemToRemove.getName() + "' has been deleted from slot " + slot + ".");
            } else {
                JOptionPane.showMessageDialog(null, "Could not find the item in the database.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error deleting item: " + e.getMessage());
        }
    }


    public void showInventory() {
        String sql = "SELECT itemid, itemname, maxStack, weight, itemtype FROM InventoryMain";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int itemID = resultSet.getInt("itemID");
                String name = resultSet.getString("itemname");
                int maxStack = resultSet.getInt("maxStack");
                double weight = resultSet.getDouble("weight");
                String itemType = resultSet.getString("itemType");

                System.out.printf("itemid %d, Items.Item: %s, Max Stack: %d, Weight: %.2f, Is Stackable: %b%n", itemID, name, maxStack, weight, itemType);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error upon loading inventory");
        }
    }


    public void sortInventory() {
        String[] options = {"Name", "Item ID", "Item Type"};
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
                    inventoryList.sort(Comparator.comparing(Item::getName));
                    break;
                case 1:
                    inventoryList.sort(Comparator.comparingDouble(Item::getItemID));
                    break;
                case 2:
                    inventoryList.sort(Comparator.comparing(Item::getItemType));
                    break;
                default:
                    throw new IllegalArgumentException("Invalid choice, please try again.");
            }
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Unexpected error during sorting: " + e.getMessage());
        }
    }


    public double calcTotalWeight() {
        double totalWeight = 0;
        for (Item item : inventoryList) {
            totalWeight += item.getWeight();
        }
        return totalWeight;
    }

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

    public void clearDatabase() {
        String[] tables = {"InventoryMain", "WeaponsMain", "ArmorMain", "ConsumableMain"};
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            // Disable foreign key checks
            statement.execute("SET foreign_key_checks = 0");

            // Truncate each table
            for (String table : tables) {
                String sql = "TRUNCATE TABLE " + table;
                int rowsAffected = statement.executeUpdate(sql);
                System.out.println("Cleared " + rowsAffected + " rows from " + table);
            }

            // Re-enable foreign key checks
            statement.execute("SET foreign_key_checks = 1");

            System.out.println("Database cleared successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error clearing the database.");
        }
    }
}