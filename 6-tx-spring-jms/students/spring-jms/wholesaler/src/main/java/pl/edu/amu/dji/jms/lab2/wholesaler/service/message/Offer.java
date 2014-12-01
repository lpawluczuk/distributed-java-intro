/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.amu.dji.jms.lab2.wholesaler.service.message;

import javax.jms.Destination;

/**
 *
 * @author lukasz
 */
public class Offer {

    private Double price;
    private Destination replyTo;

    public Offer() {
    }

    public Offer(Double price, Destination replyTo) {
        this.price = price;
        this.replyTo = replyTo;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Destination getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(Destination replyTo) {
        this.replyTo = replyTo;
    }
}
