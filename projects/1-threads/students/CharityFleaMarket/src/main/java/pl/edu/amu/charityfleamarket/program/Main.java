package pl.edu.amu.charityfleamarket.program;

import pl.edu.amu.charityfleamarket.managers.MarketManager;
import pl.edu.amu.charityfleamarket.models.Donor;
import pl.edu.amu.charityfleamarket.models.Recipient;

/**
 *
 * @author lukasz
 */
public class Main {

    public static void main(String[] args) {
        MarketManager manager = MarketManager.getInstance();
        manager.registerDonor(new Donor("Maciek"));
        manager.registerDonor(new Donor("Janek"));
        manager.registerDonor(new Donor("Łukasz"));
        manager.registerDonor(new Donor("Paweł"));
        manager.registerRecipient(new Recipient("Kupiec1"));
        manager.registerRecipient(new Recipient("Kupiec2"));
        manager.registerRecipient(new Recipient("Kupiec3"));
        manager.registerRecipient(new Recipient("Kupiec4"));
        manager.registerRecipient(new Recipient("Kupiec5"));
        manager.open();


    }
}
