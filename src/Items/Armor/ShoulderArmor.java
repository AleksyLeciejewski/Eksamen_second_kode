package Items.Armor;

import Items.Item;
import Items.ItemType;

public class ShoulderArmor extends Armor implements ItemType {
    public String name;

    public ShoulderArmor(int itemID, String name, double weight, int maxStack, double defense, String ItemType, boolean isStackable){
        super(itemID, name, weight, maxStack, defense, ItemType, isStackable);

    }


    @Override
    public String getItemType(){
        return "Shoulder Armor";
    }
}
