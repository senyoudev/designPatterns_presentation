package org.example.model;

public class VerificationStage implements OrderProcessingStage {
    private OrderProcessingStage nextStage;

    public void setNextStage(OrderProcessingStage nextStage) {
        this.nextStage = nextStage;
    }

    @Override
    public void process(Order order) {
        // Perform verification logic
        System.out.println("Verifying order...");
        order.setStatus(OrderStatus.VERIFIED);

        if (nextStage != null) {
            nextStage.process(order);
        }
    }
}

