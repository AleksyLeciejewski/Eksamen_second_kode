package Items.Armor;

import Items.Item;
import Items.ItemType;

public class HeadArmor extends Armor implements ItemType {
    public String name;

    public HeadArmor(int itemID, String name, double weight, int maxStack, double defense, String ItemType) {
        super(itemID, name, weight, maxStack, defense, ItemType);
    }

    @Override
    public String getItemType(){
        return "Head Armor";
    }
}

