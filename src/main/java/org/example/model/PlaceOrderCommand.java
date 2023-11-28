package org.example.model;

public class PlaceOrderCommand implements OrderCommand {
    private OrderProcessor orderProcessor;
    private Order order;

    public PlaceOrderCommand(OrderProcessor orderProcessor, Order order) {
        this.orderProcessor = orderProcessor;
        this.order = order;
    }

    @Override
    public void execute() {
        // Perform any pre-processing logic if needed
        System.out.println("Executing PlaceOrderCommand...");

        // Delegate the order processing to the OrderProcessor
        orderProcessor.processOrder(order);

        // Perform any post-processing logic if needed
        System.out.println("PlaceOrderCommand executed successfully.");
    }
}

