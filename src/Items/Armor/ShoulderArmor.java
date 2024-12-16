package Items.Armor;

import Items.Item;
import Items.ItemType;

public class ShoulderArmor extends Armor implements ItemType {
    public String name;

    public ShoulderArmor(int itemID, String name, double weight, int maxStack, double defense, String ItemType){
        super(itemID, name, weight, maxStack, defense, ItemType);

    }


    @Override
    public String getItemType(){
        return "Shoulder Armor";
    }
}
