package Items.Armor;
import Items.Item;
import Items.ItemType;

public class ChestArmor extends Item implements ItemType {
    public String name;

    public ChestArmor(int itemID, String name, double weight, int maxStack, double defense, int itemType){
        super(name, itemID, weight, maxStack, itemType);

    }

    @Override
    public String getItemType(){
        return "Chest Armor";
    }
}
