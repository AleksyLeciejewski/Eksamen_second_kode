package Items.Weapon;

import Items.Item;
import Items.ItemType;

public class TwoHandsItem extends Weapon implements ItemType {
    public String name;


    public TwoHandsItem( String name, double weight, double damage, int maxStack, String itemType, boolean isStackable){
        super(name, damage, weight, itemType, maxStack, isStackable);
    }

    @Override
    public String getItemType() {
        return "Two Hands";
    }
}
