package Items.Weapon;

import Items.Item;
import Items.ItemType;

public class OneHandItem extends Weapon implements ItemType {
    private String name;

    public OneHandItem(int itemID, String name, double weight, int maxStack, double damage, String itemType){
        super(name, itemID, weight, damage, itemType, maxStack);
    }


    @Override
    public String getItemType() {
        return "one-hand";
    }
}
