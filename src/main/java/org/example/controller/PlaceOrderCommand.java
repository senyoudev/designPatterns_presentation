package org.example.controller;

import org.example.controller.OrderProcessor;
import org.example.model.Order;
import org.example.model.OrderCommand;

public class PlaceOrderCommand implements OrderCommand {
    private OrderProcessor orderProcessor;
    private Order order;

    public PlaceOrderCommand(OrderProcessor orderProcessor, Order order) {
        this.orderProcessor = orderProcessor;
        this.order = order;
    }

    @Override
    public void execute() {
        System.out.println("Executing PlaceOrderCommand...");

        orderProcessor.processOrder(order);

        System.out.println("PlaceOrderCommand executed successfully.");
    }
}

