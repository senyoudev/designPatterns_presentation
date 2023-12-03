package org.example.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class InteractiveOrderManagementApp {
    private JFrame frame;
    private DefaultListModel<Product> productListModel;
    private JList<Product> productList;
    private JButton addToOrderButton;
    private DefaultListModel<Product> orderListModel;
    private JList<Product> orderList;
    private JButton placeOrderButton;
    private JButton verifyButton;
    private JButton payButton;
    private JButton clearOrderButton;
    private JLabel totalLabel;

    private List<Product> availableProducts;
    private OrderProcessor orderProcessor;
    private Order currentOrder;

    private boolean orderVerified = false;

    public InteractiveOrderManagementApp() {
        availableProducts = createSampleProducts();
        orderProcessor = new OrderProcessor();
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Interactive Order Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);

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
                currentOrder = new RegularOrder(selectedProducts);
                JOptionPane.showMessageDialog(frame, "Order Placed!", "Order Status", JOptionPane.INFORMATION_MESSAGE);
                updateTotalLabel();
            }
        });

        verifyButton = new JButton("Verify Order");
        verifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentOrder != null) {
                    double total = currentOrder.getTotalAmount();
                    orderVerified = true;
                    JOptionPane.showMessageDialog(frame, "Order Total: $" + total, "Order Verification", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Please place an order first.", "Order Status", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        payButton = new JButton("Make Payment");
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (orderVerified && currentOrder != null) {
                    // Design Pattern 4: Chain of Responsibility Pattern
                    // Create stages for order processing
                    OrderProcessingStage paymentStage = new PaymentStage();

                    // Process the order through the payment stage
                    paymentStage.process(currentOrder);
                    JOptionPane.showMessageDialog(frame, "Payment Verified!", "Payment Status", JOptionPane.INFORMATION_MESSAGE);
                    clearOrder();
                } else {
                    JOptionPane.showMessageDialog(frame, "Please place an order and verify it first.", "Order Status", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        clearOrderButton = new JButton("Clear Order");
        clearOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearOrder();
            }
        });

        totalLabel = new JLabel("Total: $0.00");

        JPanel panel = new JPanel(new BorderLayout());

        JPanel productListPanel = new JPanel(new BorderLayout());
        productListPanel.add(new JLabel("Available Products"), BorderLayout.NORTH);
        productListPanel.add(new JScrollPane(productList), BorderLayout.CENTER);
        productListPanel.add(addToOrderButton, BorderLayout.SOUTH);

        JPanel orderListPanel = new JPanel(new BorderLayout());
        orderListPanel.add(new JLabel("Selected Products"), BorderLayout.NORTH);
        orderListPanel.add(new JScrollPane(orderList), BorderLayout.CENTER);
        orderListPanel.add(placeOrderButton, BorderLayout.SOUTH);

        JPanel processingPanel = new JPanel(new FlowLayout());
        processingPanel.add(verifyButton);
        processingPanel.add(payButton);
        processingPanel.add(clearOrderButton);

        panel.add(productListPanel, BorderLayout.WEST);
        panel.add(orderListPanel, BorderLayout.EAST);
        panel.add(processingPanel, BorderLayout.SOUTH);
        panel.add(totalLabel, BorderLayout.CENTER);

        frame.getContentPane().add(panel);
    }

    private List<Product> createSampleProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Product 1", 10.0));
        products.add(new Product("Product 2", 15.0));
        products.add(new Product("Product 3", 20.0));
        return products;
    }

    private void updateTotalLabel() {
        if (currentOrder != null) {
            double total = currentOrder.getTotalAmount();
            totalLabel.setText("Total: $" + String.format("%.2f", total));
        }
    }

    private void clearOrder() {
        orderListModel.clear();
        currentOrder = null;
        totalLabel.setText("Total: $0.00");
        JOptionPane.showMessageDialog(frame, "Order Cleared!", "Order Status", JOptionPane.INFORMATION_MESSAGE);
    }

    public void show() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InteractiveOrderManagementApp().show();
            }
        });
    }
}

