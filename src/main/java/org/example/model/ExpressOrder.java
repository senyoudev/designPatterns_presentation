package org.example.model;

import java.util.List;


public class ExpressOrder implements Order {
    private List<Product> products;
    private double totalAmount;

    private OrderStatus status = OrderStatus.PENDING;

    public ExpressOrder(List<Product> products) {
        this.products = products;
        calculateTotalAmount();
    }

    private void calculateTotalAmount() {
        double expressFee = 5.0; // Example express shipping fee
        totalAmount = products.stream().mapToDouble(Product::getPrice).sum() + expressFee;
    }
    public void addProduct(Product product) {
        products.add(product);
        calculateTotalAmount();
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

}
