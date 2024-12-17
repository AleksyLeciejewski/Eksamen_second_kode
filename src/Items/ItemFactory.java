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
        items.add(new OneHandItem(1, "Short copper sword", 2.5, 1, 5.2, "one handed"));
        items.add(new TwoHandsItem(2, "Long copper sword", 5, 1, 8, "two handed"));
        items.add(new Consumable("Healing pot", 3, 0.2, 5, "Healing", 10, "healing"));
        items.add(new Consumable("Mana pot", 4, 0.2, 5, "Mana regen", 1, "mana"));
        items.add(new ChestArmor(5, "Leather armor", 5.2, 1, 55, "chestplate"));
        items.add(new ShoulderArmor(6, "Leather straps", 1.5, 1, 22, "shoulder pads"));
        items.add(new LegsArmor(7, "Leather pants", 1.8, 1, 36, "Pants"));
        items.add(new Shields(8, "Oak shield", 5.5, 1, 14, "Shield"));
        items.add(new HeadArmor(9, "Leather hood", 2.1, 1, 33, "leather hood"));
    }

    public static Item getRandomItem() {
        Random random = new Random();
        return items.get(random.nextInt(items.size()));
    }
}

