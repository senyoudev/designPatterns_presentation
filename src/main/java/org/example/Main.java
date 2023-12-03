package org.example;


import org.example.model.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        OrderProcessor orderProcessor = new OrderProcessor();

        // Create an instance of ProductDAO
        ProductDAO productDAO = new PostgresProductDAO();

        // Create a product and add it to the database
        Product newProduct = new Product("New Product", 20.0);
        productDAO.addProduct(newProduct);

        // Retrieve all products from the database
        List<Product> allProducts = productDAO.getAllProducts();

        // Design Pattern 1: Factory Method Pattern
        // Create an order using a factory method
        OrderFactory orderFactory = new RegularOrderFactory(productDAO);
        Order order = orderFactory.createOrder(allProducts);

        // Design Pattern 2: Observer Pattern
        // Create an observer to display order status
        OrderStatusObserver orderStatusObserver = new OrderStatusObserver() {
            @Override
            public void update(OrderStatus status) {
                System.out.println("Order Status Update: " + status);
            }
        };

        // Register the observer with the order processor
        orderProcessor.addObserver(orderStatusObserver);

        // Design Pattern 3: Command Pattern
        // Create a command to place the order
        OrderCommand placeOrderCommand = new PlaceOrderCommand(orderProcessor, order);

        // Execute the command
        orderProcessor.placeOrder(placeOrderCommand);

        // Design Pattern 4: Chain of Responsibility Pattern
        // Create stages for order processing
        OrderProcessingStage verificationStage = new VerificationStage();
        OrderProcessingStage paymentStage = new PaymentStage();

        // Set up the chain of responsibility
        verificationStage.setNextStage(paymentStage);

        // Process the order through the chain
        verificationStage.process(order);

        // Design Pattern 5: Singleton Pattern
        // Access a singleton database connection manager
        DatabaseConnectionManager connectionManager = DatabaseConnectionManager.getInstance();
        Connection connection = connectionManager.getConnection();

        // Design Pattern 6: Data Access Object (DAO) Pattern
        // Access data from the database using a DAO
        List<Product> retrievedProducts = productDAO.getAllProducts();

        // Print the retrieved products
        retrievedProducts.stream().forEach(System.out::println);


    }

    private static Order createExampleOrder(List<Product> products) {
        // For example, create an order with the first product from the list
        Order exampleOrder = new RegularOrder(new ArrayList<>());
        if (!products.isEmpty()) {
            exampleOrder.addProduct(products.get(0));
        }
        return exampleOrder;
    }
}
