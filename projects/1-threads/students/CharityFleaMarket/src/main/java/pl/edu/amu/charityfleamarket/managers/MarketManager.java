package pl.edu.amu.charityfleamarket.managers;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import pl.edu.amu.charityfleamarket.models.Chairman;
import pl.edu.amu.charityfleamarket.models.Donor;
import pl.edu.amu.charityfleamarket.models.Recipient;

/**
 *
 * @author lukasz
 */
public class MarketManager {

    private final ExecutorService executorService = Executors.newCachedThreadPool();

    private final List<Donor> registeredDonors = new LinkedList<>();
    private final List<Recipient> registeredRecipients = new LinkedList<>();

    public static volatile boolean isOpen = true;

    private MarketManager() {

    }

    private static class SingletonHolder {

        private final static MarketManager instance = new MarketManager();
    }

    public static MarketManager getInstance() {
        return SingletonHolder.instance;
    }

    public boolean registerDonor(Donor donor) {
        return registeredDonors.add(donor);
    }

    public boolean deregisterDonor(Donor donor) {
        return registeredDonors.remove(donor);
    }

    public boolean registerRecipient(Recipient recipient) {
        return registeredRecipients.add(recipient);
    }

    public boolean deregisterRecipient(Recipient recipient) {
        return registeredRecipients.remove(recipient);
    }

    public void open() {
        executorService.execute(Chairman.getInstance());

        for (Donor donor : registeredDonors) {
            executorService.execute(donor);
        }

        for (Recipient recipient : registeredRecipients) {
            executorService.execute(recipient);
        }
        executorService.shutdown();
    }

    public void close() {
        isOpen = false;
        executorService.shutdownNow();
    }
}
