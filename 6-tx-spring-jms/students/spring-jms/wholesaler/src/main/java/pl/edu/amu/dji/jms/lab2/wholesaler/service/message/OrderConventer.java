/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.amu.dji.jms.lab2.wholesaler.service.message;

import com.google.common.base.Preconditions;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

/**
 *
 * @author lukasz
 */
@Component("orderConventer")
public class OrderConventer implements MessageConverter {

    public Message toMessage(Object o, Session sn) throws JMSException, MessageConversionException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object fromMessage(Message msg) throws JMSException, MessageConversionException {
        Preconditions.checkArgument(msg instanceof MapMessage);

        MapMessage mapMessage = (MapMessage) msg;
        int quantity = mapMessage.getInt("quantity");
        String retailerID = mapMessage.getString("retailerID");

        Preconditions.checkState(quantity > 0);
        Preconditions.checkState(StringUtils.isNotEmpty(retailerID));

        return new Order(quantity, retailerID);
    }
}
