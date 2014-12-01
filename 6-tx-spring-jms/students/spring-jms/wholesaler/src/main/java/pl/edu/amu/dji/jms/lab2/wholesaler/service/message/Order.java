/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.amu.dji.jms.lab2.wholesaler.service.message;

/**
 *
 * @author lukasz
 */
public class Order {

    private int quantity;
    private String retailerID;

    public Order() {
    }

    public Order(int quantity, String retailerID) {
        this.quantity = quantity;
        this.retailerID = retailerID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getRetailerID() {
        return retailerID;
    }

    public void setRetailerID(String retailerID) {
        this.retailerID = retailerID;
    }
}
