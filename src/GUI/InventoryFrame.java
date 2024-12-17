package GUI;

import Inventory.Inventory;
import Items.Item;
import Items.ItemFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryFrame extends JFrame {

    JButton addItemButton = new JButton("Add item");
    JButton removeItemButton = new JButton("Remove Item");
    JButton addSlotButton = new JButton("Add Slot");
    JButton sortItemsButton = new JButton("Sort Items");
    Inventory inventory = new Inventory(32, 0.0);
    DefaultListModel<Item> listModel = new DefaultListModel<>();
    JList<Item> itemList = new JList<>(listModel);

    public InventoryFrame() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.gray);
        buttonPanel.setBounds(0, 0, 900, 100);
        buttonPanel.setLayout(null);
        buttonPanel.add(addItemButton);
        buttonPanel.add(removeItemButton);
        buttonPanel.add(addSlotButton);
        buttonPanel.add(sortItemsButton);

        addItemButton.setBounds(150, 25, 100, 50);
        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItem();
            }
        });
        removeItemButton.setBounds(400, 25, 100, 50);
        removeItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeItem();
            }

        });

        addSlotButton.setBounds(650, 25, 100, 50);
        addSlotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventory.addSlots();
                JOptionPane.showMessageDialog(null, "Slot has been added!");
            }
        });

        sortItemsButton.setBounds(800, 25, 100, 50);
        sortItemsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventory.sortInventory();
                listModel.clear();
                for (Item item : inventory.getInventoryList()) {
                    listModel.addElement(item);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(itemList);
        scrollPane.setBounds(0, 100, 900, 500);
        itemList.setCellRenderer(new ItemCellRenderer());


        this.setTitle("Inventory");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900, 600);
        this.setResizable(false);
        this.setLayout(null);
        this.add(buttonPanel);
        this.add(scrollPane);
        this.setLocationRelativeTo(null); // Center the frame

        ImageIcon icon = new ImageIcon("src/GUI/logoNoBG.png");
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(Color.lightGray);
        this.setVisible(true);
    }

    // Method to add an item to the inventory
    private void addItem() {
        Item randomItem = ItemFactory.getRandomItem(); // Get a random item
        inventory.addItem(randomItem);
        System.out.println("Random item added to inventory: " + randomItem.getName());
    }

    private void removeItem() {
        try {
            int slot = Integer.parseInt(JOptionPane.showInputDialog("Slot to be deleted:"));
            inventory.removeItemBySlot(slot);
            JOptionPane.showMessageDialog(null, "Item has been deleted!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid slot.");
        }
    }

    public static void main(String[] args) {
        new InventoryFrame();
    }
}
