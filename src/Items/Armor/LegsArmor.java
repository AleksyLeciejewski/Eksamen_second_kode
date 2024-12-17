package Items.Armor;

import Items.ItemType;

public class LegsArmor extends Armor implements Items.ItemType {
    public String name;


    public LegsArmor(int itemID, String name, double weight, int maxStack, double defense, String ItemType, boolean isStackable){
        super(itemID, name, weight, maxStack, defense, ItemType, isStackable);
    }

    @Override
    public String getItemType(){
        return "LegsArmor";
    }
}
