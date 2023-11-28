package org.example.model;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class OrderManagementUI extends JFrame {

    private OrderProcessor orderProcessor;
    private Order currentOrder;
    private List<Product> defaultProducts;

    private JTextArea orderStatusTextArea;
    private JComboBox<Product> productComboBox;
    private JButton addToOrderButton;
    private JButton placeOrderButton;

    public OrderManagementUI(OrderProcessor orderProcessor) {
        this.orderProcessor = orderProcessor;
        this.currentOrder = new RegularOrder(new ArrayList<>());

        // Initialize default products
        this.defaultProducts = getDefaultProducts();

        setTitle("Order Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        orderStatusTextArea = new JTextArea();
        orderStatusTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(orderStatusTextArea);
        add(scrollPane, BorderLayout.CENTER);

        productComboBox = new JComboBox<>(defaultProducts.toArray(new Product[0]));

        addToOrderButton = new JButton("Add to Order");
        addToOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected product from the combo box
                Product selectedProduct = (Product) productComboBox.getSelectedItem();

                // Add the selected product to the current order
                currentOrder.addProduct(selectedProduct);

                // Update order status
                updateOrderStatus(OrderStatus.valueOf("Added product to order: " + selectedProduct.getName()));
            }
        });

        placeOrderButton = new JButton("Place Order");
        placeOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Place the order
                placeOrder();
            }
        });

        JPanel orderPanel = new JPanel(new FlowLayout());
        orderPanel.add(new JLabel("Select Product:"));
        orderPanel.add(productComboBox);
        orderPanel.add(addToOrderButton);
        orderPanel.add(placeOrderButton);

        add(orderPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private List<Product> getDefaultProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Product 1", 10.0));
        products.add(new Product("Product 2", 15.0));
        // Add more default products as needed
        return products;
    }

    private void placeOrder() {
        // Create a command to place the order
        OrderCommand placeOrderCommand = new PlaceOrderCommand(orderProcessor, currentOrder);

        // Execute the command
        orderProcessor.placeOrder(placeOrderCommand);

        // Reset the current order for the next one
        currentOrder = new RegularOrder(new ArrayList<>());
    }



    public void updateOrderStatus(OrderStatus status) {
        orderStatusTextArea.append(status.toString() + "\n");
    }

    public static void main(String[] args) {
        // Create an OrderProcessor instance
        OrderProcessor orderProcessor = new OrderProcessor();

        // Create the UI
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                OrderManagementUI orderManagementUI = new OrderManagementUI(orderProcessor);
                orderProcessor.addObserver(orderManagementUI::updateOrderStatus);
            }
        });
    }
}
