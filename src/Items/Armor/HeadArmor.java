package Items.Armor;

import Items.Item;
import Items.ItemType;

public class HeadArmor extends Armor implements ItemType {
    public String name;

    public HeadArmor( String name, double weight, int maxStack, double defense, String ItemType, boolean isStackable) {
        super( name, weight, maxStack, defense, ItemType, isStackable);
    }

    @Override
    public String getItemType(){
        return "Head Armor";
    }
}

