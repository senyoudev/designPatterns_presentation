package org.example.model;

import org.example.dao.ProductDAO;

import java.util.List;

public class RegularOrderFactory implements OrderFactory {
    private ProductDAO productDAO;

    public RegularOrderFactory(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public Order createOrder(List<Product> products) {
        return new RegularOrder(products);
    }
}