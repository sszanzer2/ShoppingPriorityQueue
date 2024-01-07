package ss;

import java.util.HashMap;
import java.util.Map;

// Store class represents a store with items, prices, and quantities
public class Store implements StoreOperations {
    private Map<String, Double> itemPrices; // Map to store item prices
    private Map<String, Integer> itemQuantities; // Map to store item quantities

    public Store() {
        itemPrices = new HashMap<>();
        itemQuantities = new HashMap<>();
    }

    @Override
    // Add an item to the store with a specified price and quantity
    public void addItem(String itemName, double price, int quantity) {
        // Format the price to have two decimal places
        double formattedPrice = formatPrice(price);
        // Add item price to the map
        getPricesForAllItems().put(itemName, formattedPrice);
        // Add item quantity to the map
        itemQuantities.put(itemName, quantity);
    }

    @Override
    // Simulate a shopper purchasing an item from the store
    public boolean purchaseItem(Shopper shopper, String itemName, int quantity) {
        // Check if the item exists and has a quantity
        if (!hasItem(itemName) || !hasQuantity(itemName, quantity)) {
            return false;
        }

        double itemPrice = getItemPrice(itemName);
        updateShopperAndStore(shopper, itemName, quantity, itemPrice);

        return true;
    }

    //Method to update shopper's total purchases and store's item quantity
    private void updateShopperAndStore(Shopper shopper, String itemName, int quantity, double itemPrice) {
        double purchaseAmount = itemPrice * quantity;
        shopper.updateTotalPurchases(purchaseAmount);

        // Update the quantity of the item
        int availableQuantity = itemQuantities.get(itemName);
        itemQuantities.put(itemName, availableQuantity - quantity);
    }

    @Override
    // Get the price of a specific item
    public double getItemPrice(String itemName) {
        return  getPricesForAllItems().getOrDefault(itemName, 0.0);
    }

    //Method to check if the store has a specific item
    private boolean hasItem(String itemName) {
        if (!getPricesForAllItems().containsKey(itemName)) {
            System.out.println("Item not found: " + itemName);
            return false;
        }
        return true;
    }

    // Method to check if the store has enough quantity of a specific item
    private boolean hasQuantity(String itemName, int quantity) {
        int availableQuantity = itemQuantities.getOrDefault(itemName, 0);
        if (quantity > availableQuantity) {
            System.out.println("Not enough quantity available for item: " + itemName);
            return false;
        }
        return true;
    }

    // Method to format a price to two decimal places
    public double formatPrice(double price) {
        return Double.parseDouble(String.format("%.2f", price));
    }

    // Getter for all item prices
    public Map<String, Double> getPricesForAllItems() {
        return itemPrices;
    }

    // Getter for all item quantities
    public Map<String, Integer> getItemQuantities() {
        return itemQuantities;
    }
}
