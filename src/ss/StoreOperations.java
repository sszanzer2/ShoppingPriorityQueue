package ss;

public interface StoreOperations {
    void addItem(String itemName, double price, int quantity);
    boolean purchaseItem(Shopper shopper, String itemName, int quantity);
    double getItemPrice(String itemName);
}

