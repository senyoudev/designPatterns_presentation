package org.example.model;

import java.util.List;

public interface OrderFactory {
    Order createOrder(List<Product> products);
}