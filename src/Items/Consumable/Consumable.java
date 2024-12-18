package Items.Consumable;

import Items.IsStackable;
import Items.Item;

public class Consumable extends Item implements IsStackable {

    public Consumable(String name, double weight, int maxStack, String effect, int duration, String itemType, boolean isStackable){
        super(name, weight, maxStack, itemType, isStackable);
        this.duration = duration;
        this.effect = effect;
    }

    String effect;
    int duration;

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    @Override
    public boolean isStackable() {
        return true;
    }
}
