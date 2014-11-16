package pl.edu.amu.charityfleamarket.models;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.edu.amu.charityfleamarket.managers.MarketManager;

/**
 *
 * @author lukasz
 */
public class Chairman implements Runnable {

    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    private final BlockingQueue<Item> registeredItemsQueue = new ArrayBlockingQueue<>(10);
    private volatile BlockingQueue<Recipient> registeredRecipients = new ArrayBlockingQueue<>(10);

    private boolean isAuctionOpen = false;

    private Chairman() {
    }

    private static class SingletonHolder {

        private final static Chairman instance = new Chairman();
    }

    public static Chairman getInstance() {
        return SingletonHolder.instance;
    }

    public boolean registerItem(Item item) throws InterruptedException {
        return registeredItemsQueue.offer(item, 5, TimeUnit.SECONDS);
    }

    private Item takeItem() throws InterruptedException {
        return registeredItemsQueue.poll(5, TimeUnit.SECONDS);
    }

    public boolean registerRecipient(Recipient recipient) throws InterruptedException {
        if (isAuctionOpen && registeredRecipients.offer(recipient, 5, TimeUnit.SECONDS)) {
            System.out.println("Registering " + recipient.getName());

            lock.lock();
            try {
                while (isAuctionOpen) {
                    condition.await();
                }
                if (isAuctionOpen) {
                    throw new IllegalStateException("There is some error with auction!");
                }
            } finally {
                lock.unlock();
            }
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        while (MarketManager.isOpen) {

            try {
                Item item = takeItem();
                if (item == null) {
                    System.out.println("No auctions within 5 seconds. Closing the market.");
                    MarketManager.getInstance().close();
                    break;
                }
//                System.out.println("Chairman -> Aukcja: " + item);
                registeredRecipients.clear();
                isAuctionOpen = true;
                Thread.sleep(5000L);
                if (registeredRecipients.isEmpty()) {
                    isAuctionOpen = false;
                    System.out.println("There is no winner for " + item.getName() + ".");
                } else {
                    lock.lock();
                    try {
                        Recipient recipient = (Recipient) registeredRecipients.toArray()[new Random().nextInt(registeredRecipients.size())];
                        recipient.notifyWinner(item);
                        System.out.println("Winner for auction " + item.getName() + " is " + recipient.getName() + ".");
                        isAuctionOpen = false;
                        condition.signal();
                    } finally {
                        lock.unlock();
                    }
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Chairman.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Chairman says good bye. ");
    }
}
