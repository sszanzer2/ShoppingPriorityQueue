package ss;

public interface StoreOperations {
    void addItem(String itemName, double price, int quantity);
    boolean purchaseItem(Shopper shopper, String itemName, int quantity);
	Object getItemQuantities();
	double getItemPrice(String itemName);
}

