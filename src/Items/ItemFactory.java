package Items;

import Items.Armor.ChestArmor;
import Items.Armor.HeadArmor;
import Items.Armor.LegsArmor;
import Items.Armor.ShoulderArmor;
import Items.Consumable.Consumable;
import Items.Weapon.OneHandItem;
import Items.Weapon.Shields;
import Items.Weapon.TwoHandsItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemFactory {
    private static final List<Item> items = new ArrayList<>();


    static {
        items.add(new OneHandItem("Short copper sword", 2.5, 1, 10.0, "one handed", false));
        items.add(new TwoHandsItem("Long copper sword", 5, 15, 1, "two handed", false));
        items.add(new Consumable("Healing pot", 0.2, 5, "Healing", 10, "healing", true));
        items.add(new Consumable("Mana pot", 0.2, 5, "Mana regen", 1, "mana", true));
        items.add(new ChestArmor("Leather armor", 5.2, 1, 8,"chestplate", false));
        items.add(new ShoulderArmor("Leather straps", 1.5, 1, 5,"shoulder pads", false));
        items.add(new LegsArmor("Leather pants", 1.8, 1, 6,"Pants", false));
        items.add(new Shields("Oak shield", 5.5, 1, 5.0, "Shield", false));
        items.add(new HeadArmor("Leather hood", 2.1, 1, 4,"leather hood", false));
    }

    public static Item getRandomItem() {
        Random random = new Random();
        return items.get(random.nextInt(items.size()));
    }
}