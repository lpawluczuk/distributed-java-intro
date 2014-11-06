package pl.edu.amu.dji.jms.lab2.retailer.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import org.springframework.jms.core.MessageCreator;

public class BuyService implements MessageListener {

    private JmsTemplate jmsTemplate;

    private Double maxPrice;

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    @Override
    public void onMessage(Message message) {
        try {
            MapMessage mapMessage = (MapMessage) message;
            double price = mapMessage.getDouble("price");
            if (maxPrice.compareTo(price) == 1) {
                jmsTemplate.send(mapMessage.getJMSReplyTo(), new MessageCreator() {

                    public Message createMessage(Session sn) throws JMSException {
                        MapMessage msg = sn.createMapMessage();
                        msg.setString("retailerID", getClass().getName());
                        msg.setDouble("quantity", 3.0);
                        return msg;
                    }
                });
            }
        } catch (JMSException ex) {
            Logger.getLogger(BuyService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
