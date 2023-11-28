package org.example.model;

import java.util.List;

import java.util.List;

public class RegularOrder implements Order {
    private List<Product> products;
    private double totalAmount;

    private OrderStatus status = OrderStatus.PENDING;

    public RegularOrder(List<Product> products) {
        this.products = products;
        calculateTotalAmount();
    }

    private void calculateTotalAmount() {
        // You can add any specific logic for regular order total calculation here.
        // For simplicity, this example sums up the prices of all products.
        totalAmount = products.stream().mapToDouble(Product::getPrice).sum();
    }

    @Override
    public double getTotalAmount() {
        return totalAmount;
    }

    @Override
    public OrderStatus getStatus() {
        return status;
    }
    @Override
    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void addProduct(Product product) {
        products.add(product);
        calculateTotalAmount();
    }

}

