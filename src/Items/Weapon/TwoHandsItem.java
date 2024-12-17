package Items.Weapon;

import Items.Item;
import Items.ItemType;

public class TwoHandsItem extends Weapon implements ItemType {
    public String name;


    public TwoHandsItem(int itemID, String name, double weight, double damage, int maxStack, String itemType){
        super(name, itemID, damage, weight, itemType, maxStack);
    }

    @Override
    public String getItemType() {
        return "Two Hands";
    }
}
