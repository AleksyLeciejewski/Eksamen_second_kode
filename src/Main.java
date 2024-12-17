import Inventory.Inventory;

import static Items.ItemFactory.getRandomItem;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

         Inventory inventory = new Inventory(32, 0);

         boolean run = true;


//while(run){
    getRandomItem();

    inventory.showInventory();

}





    }
