package GUI;

import Items.Item;

import javax.swing.*;
import java.awt.*;

// ItemCellRenderer klassen arver fra JLabel og implementerer ListCellRenderer<Item> interfacet
public class ItemCellRenderer extends JLabel implements ListCellRenderer<Item> {

    // Overrider metoden getListCellRendererComponent for at tilpasse udseendet af hver celle i JList
    @Override
    public Component getListCellRendererComponent(JList<? extends Item> list, Item item, int index, boolean isSelected, boolean cellHasFocus) {
        // Sætter teksten for JLabel til at vise itemets ID, navn, vægt og om det kan stables
        setText(String.format("itemid: %d, Name: %s, Weight: %.2f, Stackable: %b", item.getItemID(), item.getName(), item.getWeight(), item.isStackable()));

        // Ændrer baggrunds- og forgrundsfarverne afhængigt af om cellen er valgt
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        // Sætter JLabel til at være aktiveret eller deaktiveret baseret på JList's tilstand
        setEnabled(list.isEnabled());

        // Sætter JLabel's skrifttype til at matche JList's skrifttype
        setFont(list.getFont());

        // Gør JLabel uigennemsigtig, så baggrundsfarven vises
        setOpaque(true);

        // Returnerer JLabel som komponenten der skal bruges til at rendere cellen
        return this;
    }
}