package org.example.model;

import java.util.List;

public interface ProductDAO {
    Product getProductById(int productId);
    List<Product> getAllProducts();
    void addProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(int productId);
}
