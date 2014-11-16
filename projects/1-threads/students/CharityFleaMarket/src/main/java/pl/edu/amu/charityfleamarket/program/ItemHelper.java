package pl.edu.amu.charityfleamarket.program;

/**
 *
 * @author lukasz
 */
public class ItemHelper {

    private static long number = 0L;

    public static synchronized String getUniqueItemName() {
        number++;
        return "item" + number;
    }
}
