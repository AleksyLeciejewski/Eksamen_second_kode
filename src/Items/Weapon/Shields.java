package Items.Weapon;

import Items.Item;
import Items.ItemType;

public class Shields extends Weapon implements ItemType {
    public String name;

    public Shields(int itemID, String name, double weight, int maxStack, double damage, String itemType,  boolean isStackable){
        super(name, itemID, weight, damage, itemType, maxStack, isStackable);
    }

    @Override
    public String getItemType(){
        return "One-handed";
    }
}
