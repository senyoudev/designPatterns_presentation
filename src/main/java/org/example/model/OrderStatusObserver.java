package org.example.model;

public interface OrderStatusObserver {
    void update(OrderStatus status);
}