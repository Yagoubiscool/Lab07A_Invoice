import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InvoiceGUI extends JFrame {
    private Invoice invoice;

    // GUI Components
    private JTextField titleField;
    private JTextArea addressArea;
    private JTextField productNameField;
    private JTextField unitPriceField;
    private JTextField quantityField;
    private JTextArea outputArea;

    public InvoiceGUI() {
        // Set up frame
        setTitle("Lab 07A: Invoice Application");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Initialize empty invoice (will be configured upon adding first item or initializing)
        invoice = new Invoice("Sales Invoice", "123 Main Street\nCincinnati, OH 45236");

        // --- Top Panel: Invoice Metadata ---
        JPanel topPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        topPanel.setBorder(BorderFactory.createTitledBorder("Invoice Details"));

        topPanel.add(new JLabel("Invoice Title:"));
        titleField = new JTextField("Sample Invoice");
        topPanel.add(titleField);

        topPanel.add(new JLabel("Customer Address:"));
        addressArea = new JTextArea("John Doe\n456 Oak Road\nMilford, OH 45150", 2, 20);
        topPanel.add(new JScrollPane(addressArea));

        JButton updateHeaderButton = new JButton("Set Invoice Header");
        topPanel.add(updateHeaderButton);
        add(topPanel, BorderLayout.NORTH);

        // --- Center Panel: Add Line Items ---
        JPanel centerPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        centerPanel.setBorder(BorderFactory.createTitledBorder("Add Line Item"));

        centerPanel.add(new JLabel("Product Name:"));
        productNameField = new JTextField();
        centerPanel.add(productNameField);

        centerPanel.add(new JLabel("Unit Price ($):"));
        unitPriceField = new JTextField();
        centerPanel.add(unitPriceField);

        centerPanel.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        centerPanel.add(quantityField);

        JButton addItemButton = new JButton("Add Line Item");
        centerPanel.add(addItemButton);

        JButton generateButton = new JButton("Generate Invoice");
        centerPanel.add(generateButton);

        // Group the center inputs and buttons in a container panel
        JPanel inputWrapper = new JPanel(new BorderLayout());
        inputWrapper.add(centerPanel, BorderLayout.CENTER);
        add(inputWrapper, BorderLayout.CENTER);

        // --- Bottom Panel: Output Display ---
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Invoice Output"));
        add(scrollPane, BorderLayout.SOUTH);

        // --- Action Listeners ---
        updateHeaderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                invoice.setTitle(titleField.getText());
                invoice.setCustomerAddress(addressArea.getText());
                JOptionPane.showMessageDialog(InvoiceGUI.INSTANCE, "Invoice header updated successfully!");
            }
        });

        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Update header info just in case
                    invoice.setTitle(titleField.getText());
                    invoice.setCustomerAddress(addressArea.getText());

                    String name = productNameField.getText();
                    double price = Double.parseDouble(unitPriceField.getText());
                    int qty = Integer.parseInt(quantityField.getText());

                    Product product = new Product(name, price);
                    LineItem item = new LineItem(product, qty);
                    invoice.addLineItem(item);

                    JOptionPane.showMessageDialog(InvoiceGUI.INSTANCE, "Line item added: " + name);

                    // Clear input fields
                    productNameField.setText("");
                    unitPriceField.setText("");
                    quantityField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(InvoiceGUI.INSTANCE, "Please enter valid numbers for unit price and quantity.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update header values before rendering
                invoice.setTitle(titleField.getText());
                invoice.setCustomerAddress(addressArea.getText());

                outputArea.setText(invoice.formatInvoice());
            }
        });
    }

    private static InvoiceGUI INSTANCE;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                INSTANCE = new InvoiceGUI();
                INSTANCE.setVisible(true);
            }
        });
    }
}