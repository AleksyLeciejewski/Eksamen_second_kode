package Items.Consumable;

public class Potion extends Consumable {
    public Potion(String name, double weight, int maxStack, String effect, int duration, String itemType, boolean isStackable) {
        super(name, weight, maxStack, effect, duration, itemType, isStackable);
    }

    public void applyEffect(){
        System.out.println("Applying effect: " + this.getEffect() + " for " + this.getDuration() + " seconds");
    }

    public void drink(){
        System.out.println("Drinking potion: " + this.getName());
        applyEffect();
    }
//
//    public static Potion createHealthPotion(String name, int itemID, int duration){
//        return new Potion("Healing Items.Potion", itemID, 0.5, true, 4, "Healing", 5);
//    }

    @Override
    public boolean isStackable() {
        return super.isStackable();
    }
}
