package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class OrderProcessor {
    private List<OrderStatusObserver> observers;
    private OrderProcessingStage processingChain;


    public OrderProcessor() {
        this.observers = new ArrayList<>();
        setupProcessingChain();
    }

    public void addObserver(OrderStatusObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(OrderStatusObserver observer) {
        observers.remove(observer);
    }

    private void setupProcessingChain() {
        // Create the processing chain
        VerificationStage verificationStage = new VerificationStage();
        PaymentStage paymentStage = new PaymentStage();

        // Set the order of processing
        verificationStage.setNextStage(paymentStage);

        // Set the processing chain
        processingChain = verificationStage;
    }

    public void processOrder(Order order) {
        // Start the processing chain
        processingChain.process(order);

        // Notify observers about the final order status
        notifyObservers(order.getStatus());
    }
    public void placeOrder(OrderCommand orderCommand) {
        // The invoker (OrderProcessor) executes the command
        orderCommand.execute();
    }

    private void notifyObservers(OrderStatus status) {
        for (OrderStatusObserver observer : observers) {
            observer.update(status);
        }
    }
}
