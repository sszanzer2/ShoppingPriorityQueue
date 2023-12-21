package ss;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Store store = new Store();

        // Add items to the store
        store.addItem("laptop", 1000.0, 10);
        store.addItem("phone", 500.0, 15);
        store.addItem("headphones", 100.0, 20);
        store.addItem("charger", 10.00, 12);
        store.addItem("phone case", 7.00, 8);
        store.addItem("ipad", 300.00, 17);
        store.addItem("tv screen", 2500.00, 5);

        // Create a scanner for user input
        Scanner scanner = new Scanner(System.in);

     // Create a ShoppingSystem instance outside the loop
        ShoppingSystem shoppingSystem = new ShoppingSystem();

        System.out.println("Welcome to Electric Shoppers where we sell all things electric. "
        		+ "\nWe hope you enjoy your shopping experience! \nIf you spend over $100 you get 10% off!!");

        // Simulate shoppers and purchases interactively
        while (true) {
            System.out.println("Enter shopper name (or 'checkout' to finish): ");
            String shopperName = scanner.nextLine().toLowerCase();

            // Input validation: Check if the shopper name is not empty
            if (shopperName.trim().isEmpty()) {
                System.out.println("Shopper name cannot be empty. Please enter a valid name.");
                continue; // Repeat the loop to get a valid input
            }

            if (shopperName.equalsIgnoreCase("checkout")) {
                // Simulate checkout after all shoppers have been added
                shoppingSystem.simulateCheckout();
                break;
            }

            // Create a new shopper with the given name
            Shopper shopper = new Shopper(shopperName);

            System.out.println("Items for Sale:");
            store.getAllItemPrices().forEach((itemName, price) -> {
                int quantity = store.getItemQuantities().getOrDefault(itemName, 0);
                System.out.printf("%s - $%.2f - Quantity: %d%n", itemName, price, quantity);
            });

            // Allow the user to simulate purchases for the shopper
            while (true) {
                System.out.println("Enter item name to purchase (or 'done' to finish): ");
                String itemName = scanner.nextLine().toLowerCase().trim();

                if (itemName.equalsIgnoreCase("done")) {
                    break;
                }

                System.out.println("Enter quantity to purchase: ");
                int quantity;
                while (true) {
                    try {
                        quantity = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character
                        if (quantity > 0) {
                            break;
                        } else {
                            System.out.println("Invalid input. Quantity must be greater than 0. Please enter again: ");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid integer for quantity: ");
                        scanner.nextLine(); // Consume the invalid input
                    }
                }
                ShoppingCart shoppingCart = new ShoppingCart(shopper, store);
                // Simulate the purchase
                boolean purchaseSuccess = shoppingCart.purchaseItem(itemName, quantity);

                if (purchaseSuccess) {
                    // Display a summary after each successful purchase
                    System.out.printf("Successfully added to cart %d %s(s). Remaining quantity: %d%n",
                            quantity, itemName, store.getItemQuantities().getOrDefault(itemName, 0));
                } else {
                    // Display a message if the purchase was unsuccessful
                    System.out.println("Failed to purchase item. Please check the item name and quantity.");
                }
            }

            // Add the shopper to the shopping system only when ready to check out
            shoppingSystem.addShopper(shopper);
        }

        // Close the scanner
        scanner.close();

    }
}
