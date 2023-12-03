package org.example.model;

import java.util.Objects;

public class Product {
    private int productId;
    private String name;
    private double price;

    public Product(String name, double price) {
        this.productId = 1;
        this.name = name;
        this.price = price;
    }


    public Product(int productId,String name, double price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }


    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId == product.productId && Double.compare(price, product.price) == 0 && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, name, price);
    }

    @Override
    public String toString() {
        return "Product{" + "productId=" + productId + ", name='" + name + '\'' + ", price=" + price + '}';
    }
}
