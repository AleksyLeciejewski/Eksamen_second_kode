package Items.Armor;

import Items.Item;
import Items.ItemType;

public class ShoulderArmor extends Item implements ItemType {
    public String name;

    public ShoulderArmor(int itemID, String name, double weight, int maxStack, boolean isStackable, double defense){
        super(name, itemID, weight, isStackable, maxStack);
        this.name = name;
    }


    @Override
    public String getItemType(){
        return "Shoulder Armor";
    }
}
