package Items.Armor;
import Items.Item;
import Items.ItemType;

public class ChestArmor extends Armor implements ItemType {
    public String name;

    public ChestArmor( String name, double weight, int maxStack, double defense, String ItemType, boolean isStackable){
        super( name, weight, maxStack, defense, ItemType, isStackable);

    }

    @Override
    public String getItemType(){
        return "Chest Armor";
    }
}
