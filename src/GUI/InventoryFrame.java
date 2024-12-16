package GUI;

import Items.Inventory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryFrame extends JFrame {

    JButton shopkeeperButton = new JButton("Shopkeeper");
    JButton removeItemButton = new JButton("Remove Item");
    JButton addSlotButton = new JButton("Add Slot");
    Inventory inventory = new Inventory(32, 0.0); // Create an instance of Items.Inventory

    public InventoryFrame() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.gray);
        buttonPanel.setBounds(0, 0, 900, 100);
        buttonPanel.setLayout(null);
        buttonPanel.add(shopkeeperButton);
        buttonPanel.add(removeItemButton);
        buttonPanel.add(addSlotButton);
        shopkeeperButton.setBounds(150, 25, 100, 50);
        shopkeeperButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new ShopkeeperFrame();
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

        this.setTitle("Inventory");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900, 600);
        this.setResizable(false);
        this.setLayout(null);
        this.add(buttonPanel);
        this.setLocationRelativeTo(null); // Center the frame

        ImageIcon icon = new ImageIcon("src/GUI/logoNoBG.png");
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(Color.lightGray);
        this.setVisible(true);
    }

    // Method to add an item to the inventory
//    private void addItem() {
//        Item randomItem = ItemFactory.getRandomItem(); // Get a random item
//        inventory.addItem(randomItem);
//        System.out.println("Random item added to inventory: " + randomItem.getName());
//    }

    private void removeItem() {
        try {
            int slot = Integer.parseInt(JOptionPane.showInputDialog("Slot to be deleted:"));
            inventory.removeItemBySlot(slot);
            JOptionPane.showMessageDialog(null, "Item has been deleted!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid slot.");
        }
    }
}
