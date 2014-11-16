package pl.edu.amu.charityfleamarket.models;

import java.util.Random;
import pl.edu.amu.charityfleamarket.managers.MarketManager;
import pl.edu.amu.charityfleamarket.program.ItemHelper;

/**
 *
 * @author lukasz
 */
public class Donor extends Sprite implements Runnable {

    public Donor(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (MarketManager.isOpen) {
            Item item = new Item(ItemHelper.getUniqueItemName());
//            System.out.println(this.toString() + " -> " + item.toString());

            try {
                Chairman.getInstance().registerItem(item);
                Thread.sleep(new Random().nextInt(25001) + 5000);
            } catch (InterruptedException e) {
                System.out.println(getName() + " says good bye.");
            }
        }
    }

    @Override
    public String toString() {
        return "Donor{" + getName() + '}';
    }

}
