package org.example.model;

public interface OrderProcessingStage {
    void process(Order order);
    void setNextStage(OrderProcessingStage orderProcessingStage);
}
