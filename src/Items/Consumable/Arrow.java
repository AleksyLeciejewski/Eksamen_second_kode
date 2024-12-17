package Items.Consumable;

public class Arrow extends Consumable {
    public Arrow(String name, int itemID, double weight, int maxStack, String effect, int duration, String itemType) {
        super(name, itemID, weight, maxStack, effect, duration, itemType);
    }

    public void damageWithEffect(){
        System.out.println("Dealing damage with effect: " + this.getEffect() + " for " + this.getDuration() + " seconds");
    }

    public void damageWithoutEffect(){
        System.out.println("Dealing damage without effect: " + this.getName());
    }

    public void shoot(){
        System.out.println("Shooting arrow: " + this.getName());
        if(this.getEffect() != null){
            damageWithEffect();
        }else{
            damageWithoutEffect();
        }
    }

    @Override
    public boolean isStackable() {
        return super.isStackable();
    }
}
