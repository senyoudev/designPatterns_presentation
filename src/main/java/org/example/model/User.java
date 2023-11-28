package org.example.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class User implements OrderStatusObserver {
    private static final Logger logger = LoggerFactory.getLogger(User.class);

    private String name;
    private List<OrderStatus> orderStatusHistory;

    public User(String name) {
        this.name = name;
        this.orderStatusHistory = new ArrayList<>();
    }

    @Override
    public void update(OrderStatus status) {
        String message = String.format("User %s received an update: Order Status - %s", name, status);
        logger.info(message);
        orderStatusHistory.add(status);
    }

    public List<OrderStatus> getOrderStatusHistory() {
        return orderStatusHistory;
    }
}
