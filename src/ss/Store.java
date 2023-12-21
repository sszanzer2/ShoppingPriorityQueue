package ss;

import java.util.HashMap;
import java.util.Map;

//Store class represents a store with items, prices, and quantities
public class Store implements StoreOperations{
    private Map<String, Double> itemPrices;
    private Map<String, Integer> itemQuantities;

    public Store() {
        itemPrices = new HashMap<>();
        itemQuantities = new HashMap<>();
    }

    // Add an item to the store with a specified price and quantity
    @Override
    public void addItem(String itemName, double price, int quantity) {
    	// Format the price to have two decimal places
        double Price = Double.parseDouble(String.format("%.2f", price));
        
        getAllItemPrices().put(itemName, Price);
        itemQuantities.put(itemName, quantity);
    }

    // Simulate a shopper purchasing an item from the store
    @Override
    public boolean purchaseItem(Shopper shopper, String itemName, int quantity) {
        if (getAllItemPrices().containsKey(itemName) && itemQuantities.containsKey(itemName)) {
            double itemPrice = getAllItemPrices().get(itemName);
            int availableQuantity = itemQuantities.get(itemName);

            if (quantity <= availableQuantity) {
                double purchaseAmount = itemPrice * quantity;
                shopper.updateTotalPurchases(purchaseAmount);
                
                // Update the quantity of the item
                itemQuantities.put(itemName, availableQuantity - quantity);
               
                return true;
            } else {
                System.out.println("Not enough quantity available for item: " + itemName);
            }
        } else {
            System.out.println("Item not found: " + itemName);
        }

        return false;
    }
    // method to get the(1) item price
    @Override
    public double getItemPrice(String itemName) {
        return getAllItemPrices().getOrDefault(itemName, 0.0);
    }

	public Map<String, Double> getAllItemPrices() {
		return itemPrices;
	}
    
	public Map<String, Integer> getItemQuantities() {
	    return itemQuantities;
	}
}
