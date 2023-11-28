package org.example.model;

import java.util.List;

public interface Order {
    double getTotalAmount();
    void addProduct(Product product);
    OrderStatus getStatus();

    void setStatus(OrderStatus status);
}
