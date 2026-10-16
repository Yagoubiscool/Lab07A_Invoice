import java.util.ArrayList;

public class Invoice {
    private String title;
    private String customerAddress;
    private ArrayList<LineItem> lineItems;

    // Constructor
    public Invoice(String title, String customerAddress) {
        this.title = title;
        this.customerAddress = customerAddress;
        this.lineItems = new ArrayList<>();
    }

    // Add a line item to the invoice
    public void addLineItem(LineItem item) {
        this.lineItems.add(item);
    }

    // Calculate total amount due for all line items
    public double calculateTotalDue() {
        double totalDue = 0.0;
        for (LineItem item : lineItems) {
            totalDue += item.calculateTotal();
        }
        return totalDue;
    }

    // Format the invoice using text line dividers[cite: 1]
    public String formatInvoice() {
        StringBuilder sb = new StringBuilder();
        sb.append("=========================================\n");
        sb.append("          ").append(title).append("\n");
        sb.append("=========================================\n");
        sb.append("Customer Address:\n").append(customerAddress).append("\n");
        sb.append("-----------------------------------------\n");
        sb.append(String.format("%-20s %-5s %-10s %-10s\n", "Item", "Qty", "Price", "Total"));
        sb.append("-----------------------------------------\n");

        for (LineItem item : lineItems) {
            sb.append(String.format("%-20s %-5d $%-9.2f $%-9.2f\n",
                    item.getProduct().getName(),
                    item.getQuantity(),
                    item.getProduct().getUnitPrice(),
                    item.calculateTotal()));
        }

        sb.append("-----------------------------------------\n");
        sb.append(String.format("TOTAL AMOUNT DUE: $%.2f\n", calculateTotalDue()));
        sb.append("=========================================\n");

        return sb.toString();
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public ArrayList<LineItem> getLineItems() {
        return lineItems;
    }
}