package GUI;

import Inventory.Inventory;
import Items.Item;
import Items.ItemFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class InventoryFrame extends JFrame {

    // Opret knapper til forskellige handlinger
    JButton addItemButton = new JButton("Add item");
    JButton removeItemButton = new JButton("Remove Item");
    JButton addSlotButton = new JButton("Add Slot");
    JButton sortItemsButton = new JButton("Sort Items");

    // Opret et Inventory objekt med 32 slots og 0.0 vægt
    Inventory inventory = new Inventory(32, 0.0);

    // Opret en DefaultListModel til at holde Item objekter
    DefaultListModel<Item> listModel = new DefaultListModel<>();

    // Opret en JList til at vise Item objekter
    JList<Item> itemList = new JList<>(listModel);

    public InventoryFrame() {
        // Ryd databasen ved opstart
        inventory.clearDatabase();

        // Opret et panel til knapperne
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.gray);
        buttonPanel.setBounds(0, 0, 900, 100);
        buttonPanel.setLayout(null);
        buttonPanel.add(addItemButton);
        buttonPanel.add(removeItemButton);
        buttonPanel.add(addSlotButton);
        buttonPanel.add(sortItemsButton);

        // Beregn knapstørrelser og afstand
        int buttonWidth = 100;
        int buttonHeight = 50;
        int buttonY = 25;
        int spacing = (900 - 4 * buttonWidth) / 5; // Beregn afstand mellem knapperne

        // Sæt knappernes positioner
        addItemButton.setBounds(spacing, buttonY, buttonWidth, buttonHeight);
        removeItemButton.setBounds(2 * spacing + buttonWidth, buttonY, buttonWidth, buttonHeight);
        addSlotButton.setBounds(3 * spacing + 2 * buttonWidth, buttonY, buttonWidth, buttonHeight);
        sortItemsButton.setBounds(4 * spacing + 3 * buttonWidth, buttonY, buttonWidth, buttonHeight);

        // Tilføj action listeners til knapperne
        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItem(); // Tilføj et item
            }
        });
        removeItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventory.removeItemBySlot(); // Fjern et item fra en slot
            }
        });
        addSlotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventory.addSlots(); // Tilføj en slot
                JOptionPane.showMessageDialog(null, "Slot has been added!"); // Vis besked
            }
        });
        sortItemsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventory.sortInventory(); // Sorter items
                listModel.clear();
                List<Item> sortedItems = inventory.getInventoryList();
                if (sortedItems != null) {
                    for (Item item : sortedItems) {
                        listModel.addElement(item); // Tilføj sorterede items til listen
                    }
                }
            }
        });

        // Opret en JScrollPane til item listen
        JScrollPane scrollPane = new JScrollPane(itemList);
        scrollPane.setBounds(0, 100, 900, 500);
        itemList.setCellRenderer(new ItemCellRenderer()); // Sæt custom renderer

        // Sæt vinduets egenskaber
        this.setTitle("Inventory");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900, 600);
        this.setResizable(false);
        this.setLayout(null);
        this.add(buttonPanel);
        this.add(scrollPane);
        this.setLocationRelativeTo(null);

        // Sæt ikon og baggrundsfarve
        ImageIcon icon = new ImageIcon("src/GUI/logoNoBG.png");
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(Color.lightGray);
        this.setVisible(true);
    }

    // Metode til at tilføje et item
    private void addItem() {
        Item randomItem = ItemFactory.getRandomItem();
        randomItem.setItemID(inventory.getNextItemID());
        inventory.addItem(randomItem);
        listModel.addElement(randomItem);
    }

    // Main metode til at starte applikationen
    public static void main(String[] args) {
        new InventoryFrame(); // Start applikationen
    }
}