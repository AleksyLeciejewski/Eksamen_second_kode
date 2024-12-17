package GUI;

import Items.Item;

import javax.swing.*;
import java.awt.*;

public class ItemCellRenderer extends JLabel implements ListCellRenderer<Item> {

    @Override
    public Component getListCellRendererComponent(JList<? extends Item> list, Item item, int index, boolean isSelected, boolean cellHasFocus) {
        setText(String.format("Name: %s, Weight: %.2f, Stackable: %b", item.getName(), item.getWeight(), item.isStackable()));
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        setEnabled(list.isEnabled());
        setFont(list.getFont());
        setOpaque(true);
        return this;
    }
}