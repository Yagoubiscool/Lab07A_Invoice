public class LineItem {
    private Product product;
    private int quantity;

    // Constructor
    public LineItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    // Calculate total for this line item (Unit Price * Quantity)
    public double calculateTotal() {
        return product.getUnitPrice() * quantity;
    }

    // Getters and Setters
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return quantity + "x " + product.getName() + " @ $" + product.getUnitPrice() + " = $" + calculateTotal();
    }
}