package ss;

public class ShoppingCart {
    private Shopper shopper;
    private Store store;

    public ShoppingCart(Shopper shopper, Store store) {
        this.shopper = shopper;
        this.store = store;
    }
    // Method to simulate a shopper purchasing an item from the store
    public boolean purchaseItem(String itemName, int quantity) {
        // Check if the store has the item in sufficient quantity
        if (store.getItemQuantities().containsKey(itemName) && store.getItemQuantities().get(itemName) >= quantity) {
            double itemPrice = store.getItemPrice(itemName);
            double purchaseAmount = itemPrice * quantity;

            // Update the shopper's total purchases
            shopper.updateTotalPurchases(purchaseAmount);

            // Update the quantity of the item in the store
            store.getItemQuantities().put(itemName, store.getItemQuantities().get(itemName) - quantity);

            return true;
        } else {
            System.out.println("Item not available in sufficient quantity: " + itemName);
            return false;
        }
    }
}
