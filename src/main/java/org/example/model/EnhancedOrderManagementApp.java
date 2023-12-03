package org.example.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EnhancedOrderManagementApp {
    private JFrame frame;
    private DefaultListModel<Product> productListModel;
    private JList<Product> productList;
    private JButton addToOrderButton;
    private DefaultListModel<Product> orderListModel;
    private JList<Product> orderList;
    private JButton placeOrderButton;

    private List<Product> availableProducts;
    private OrderProcessor orderProcessor;

    public EnhancedOrderManagementApp() {
        availableProducts = createSampleProducts();
        orderProcessor = new OrderProcessor();
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Enhanced Order Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        productListModel = new DefaultListModel<>();
        for (Product product : availableProducts) {
            productListModel.addElement(product);
        }
        productList = new JList<>(productListModel);

        orderListModel = new DefaultListModel<>();
        orderList = new JList<>(orderListModel);

        addToOrderButton = new JButton("Add to Order");
        addToOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product selectedProduct = productList.getSelectedValue();
                if (selectedProduct != null) {
                    orderListModel.addElement(selectedProduct);
                }
            }
        });

        placeOrderButton = new JButton("Place Order");
        placeOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Product> selectedProducts = new ArrayList<>();
                for (int i = 0; i < orderListModel.getSize(); i++) {
                    selectedProducts.add(orderListModel.getElementAt(i));
                }
                Order order = new RegularOrder(selectedProducts);

                // Execute the order processing
                OrderCommand placeOrderCommand = new PlaceOrderCommand(orderProcessor, order);
                orderProcessor.placeOrder(placeOrderCommand);
            }
        });

        JPanel panel = new JPanel(new BorderLayout());

        JPanel productListPanel = new JPanel(new BorderLayout());
        productListPanel.add(new JLabel("Available Products"), BorderLayout.NORTH);
        productListPanel.add(new JScrollPane(productList), BorderLayout.CENTER);
        productListPanel.add(addToOrderButton, BorderLayout.SOUTH);

        JPanel orderListPanel = new JPanel(new BorderLayout());
        orderListPanel.add(new JLabel("Selected Products"), BorderLayout.NORTH);
        orderListPanel.add(new JScrollPane(orderList), BorderLayout.CENTER);
        orderListPanel.add(placeOrderButton, BorderLayout.SOUTH);

        panel.add(productListPanel, BorderLayout.WEST);
        panel.add(orderListPanel, BorderLayout.EAST);

        frame.getContentPane().add(panel);

        // Observer Pattern: Register the order status observer
        orderProcessor.addObserver(new OrderStatusObserver() {
            @Override
            public void update(OrderStatus status) {
                JOptionPane.showMessageDialog(frame, "Order Status Update: " + status, "Order Status", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private List<Product> createSampleProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Product 1", 10.0));
        products.add(new Product("Product 2", 15.0));
        products.add(new Product("Product 3", 20.0));
        return products;
    }

    public void show() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EnhancedOrderManagementApp().show();
            }
        });
    }
}
