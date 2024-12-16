package Items.Weapon;

import Items.Item;
import Items.ItemType;

public class TwoHandsItem extends Weapon implements ItemType {
    public String name;


    public TwoHandsItem(int itemID, String name, double weight, double damage, int maxStack){
        super(name, itemID, damage, maxStack, weight);
    }

    @Override
    public String getItemType() {
        return "Two Hands";
    }
}
