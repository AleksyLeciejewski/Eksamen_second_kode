package Items.Armor;

import Items.Item;
import Items.ItemType;

public class ShoulderArmor extends Armor implements ItemType {
    public String name;

    public ShoulderArmor( String name, double weight, int maxStack, double defense, String ItemType, boolean isStackable){
        super( name, weight, maxStack, defense, ItemType, isStackable);

    }


    @Override
    public String getItemType(){
        return "Shoulder Armor";
    }
}
