package Items.Armor;
import Items.Item;
import Items.ItemType;

public class ChestArmor extends Armor implements ItemType {
    public String name;

    public ChestArmor(int itemID, String name, double weight, int maxStack, double defense, String ItemType){
        super(itemID, name, weight, maxStack, defense, ItemType);

    }

    @Override
    public String getItemType(){
        return "Chest Armor";
    }
}
