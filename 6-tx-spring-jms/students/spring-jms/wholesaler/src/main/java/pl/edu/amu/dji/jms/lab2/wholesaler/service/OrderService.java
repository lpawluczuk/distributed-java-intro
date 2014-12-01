package pl.edu.amu.dji.jms.lab2.wholesaler.service;

import com.google.common.base.Preconditions;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.amu.dji.jms.lab2.wholesaler.service.message.Order;

@Service("orderService")
public class OrderService {

    @Transactional
    public void order(Order order) {
        System.out.println("Urdered quantity " + order.getQuantity() + " by " + order.getRetailerID());
    }
}
