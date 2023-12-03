package org.example.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class OrderManagementApp {
    private JFrame frame;
    private DefaultListModel<Product> productListModel;
    private JList<Product> productList;
    private JButton addToOrderButton;
    private DefaultListModel<Product> orderListModel;
    private JList<Product> orderList;
    private JButton placeOrderButton;

    private List<Product> availableProducts;

    public OrderManagementApp() {
        availableProducts = createSampleProducts(); // Replace this with your logic to fetch products from the database
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Order Management");
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
                // Implement your logic to process the order
                List<Product> selectedProducts = new ArrayList<>();
                for (int i = 0; i < orderListModel.getSize(); i++) {
                    selectedProducts.add(orderListModel.getElementAt(i));
                }
                // Call a method to process the order using the selectedProducts
                processOrder(selectedProducts);
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
    }

    private void processOrder(List<Product> selectedProducts) {
        // Implement your logic to process the order with the selected products
        StringBuilder message = new StringBuilder("Order Placed with Products:\n");
        for (Product product : selectedProducts) {
            message.append(product.getName()).append("\n");
        }
        JOptionPane.showMessageDialog(frame, message.toString(), "Order Placed", JOptionPane.INFORMATION_MESSAGE);
        // Optionally, clear the orderListModel or perform other actions after placing the order
        orderListModel.clear();
    }

    private List<Product> createSampleProducts() {
        ProductDAO productDAO = new PostgresProductDAO();
        List<Product> products = productDAO.getAllProducts();
        return products;
    }

    public void show() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new OrderManagementApp().show();
            }
        });
    }
}
