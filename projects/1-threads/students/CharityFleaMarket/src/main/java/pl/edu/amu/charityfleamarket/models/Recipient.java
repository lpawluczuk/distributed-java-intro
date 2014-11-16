package pl.edu.amu.charityfleamarket.models;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.apache.commons.lang.StringUtils;
import pl.edu.amu.charityfleamarket.managers.MarketManager;

/**
 *
 * @author lukasz
 */
public class Recipient extends Sprite implements Runnable {

    private final List<Item> wonItems = new LinkedList<>();
    private boolean isWinner = false;

    public Recipient(String name) {
        super(name);
    }

    public void notifyWinner(Item item) {
        wonItems.add(item);
        isWinner = true;
        System.out.println(getName() + " won " + item.getName() + ".");
    }

    @Override
    public void run() {
        while (MarketManager.isOpen) {
            try {
                if (Chairman.getInstance().registerRecipient(this) && isWinner) {
                    Thread.sleep(new Random().nextInt(25001) + 5000);
                } else {
                    Thread.sleep(5000);
                }
            } catch (InterruptedException e) {
                if (wonItems.isEmpty()) {
                    System.out.println(getName() + " says good bye leaving with no items.");
                } else {
                    System.out.println(getName() + " says good bye leaving with items " + StringUtils.join(wonItems, ",") + ".");
                }
            }
        }
    }
}
