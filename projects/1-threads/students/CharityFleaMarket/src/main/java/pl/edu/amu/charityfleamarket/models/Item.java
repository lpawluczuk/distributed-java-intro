package pl.edu.amu.charityfleamarket.models;

/**
 *
 * @author Łukasz Pawluczuk
 */
public class Item extends Sprite {

    public Item(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return getName();
    }

}
