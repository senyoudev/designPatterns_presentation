package org.example.model;

public class PaymentStage implements OrderProcessingStage {
    private OrderProcessingStage nextStage;

    public void setNextStage(OrderProcessingStage nextStage) {
        this.nextStage = nextStage;
    }

    @Override
    public void process(Order order) {
        // Perform payment logic
        System.out.println("Processing payment...");
        order.setStatus(OrderStatus.COMPLETED);

        if (nextStage != null) {
            nextStage.process(order);
        }
    }
}
