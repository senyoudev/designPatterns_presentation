package org.example.model;

import org.example.dao.ProductDAO;

import java.util.List;

public class ExpressOrderFactory implements OrderFactory {
    private ProductDAO productDAO;

    public ExpressOrderFactory(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public Order createOrder(List<Product> products) {
        return new ExpressOrder(products);
    }
}

