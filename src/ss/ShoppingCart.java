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
    	 try {
             // Check if the quantity is valid
             validateQuantity(quantity);
	        // Check if the store has the item in sufficient quantity
	        if (store.getItemQuantities().containsKey(itemName) && store.getItemQuantities().get(itemName) >= quantity) {
	            double itemPrice = store.getItemPrice(itemName);
	            double purchaseAmount = itemPrice * quantity;
	
	            // Update the shopper's total purchases
	            shopper.updateTotalPurchases(purchaseAmount);
	
	            // Update the quantity of the item in the store
	            store.getItemQuantities().put(itemName, store.getItemQuantities().get(itemName) - quantity);
	
	            return true;
	        } 
        } catch (Exception e) {
            // Log the exception or handle it as needed
            System.out.println("Error during purchase: " + e.getMessage());
            return false;
        }
		return false;
    }
    
    private void validateQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Invalid quantity: " + quantity);
        }
    }
}
