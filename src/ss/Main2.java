package ss;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        runShoppingSystem();
    }

    private static void runShoppingSystem() {
        Store store = initializeStore();
        CouponChecker couponChecker = new CouponChecker();
        ShoppingSystem shoppingSystem = new ShoppingSystem(couponChecker);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Electric Shoppers where we sell all things electric. "
                + "\nWe hope you enjoy your shopping experience! \nIf you spend over $100, you get 10% off!!");

        simulateShoppersAndPurchases(scanner, store, shoppingSystem);

        scanner.close();
    }

    private static Store initializeStore() {
        Store store = new Store();
        store.addItem("laptop", 1000.0, 10);
        store.addItem("phone", 500.0, 15);
        store.addItem("headphones", 100.0, 20);
        store.addItem("charger", 10.00, 12);
        store.addItem("phone case", 7.00, 8);
        store.addItem("ipad", 300.00, 17);
        store.addItem("tv screen", 2500.00, 5);
        return store;
    }

    private static void simulateShoppersAndPurchases(Scanner scanner, Store store, ShoppingSystem shoppingSystem) {
        while (true) {
            String shopperName = getValidShopperName(scanner);

            if (shopperName.equalsIgnoreCase("checkout")) {
                shoppingSystem.simulateCheckout();
                break;
            }

            Shopper shopper = new Shopper(shopperName);

            displayItemsForSale(store);

            simulatePurchases(scanner, store, shopper, shoppingSystem);
        }
    }

    private static void simulatePurchases(Scanner scanner, Store store, Shopper shopper, ShoppingSystem shoppingSystem) {
        while (true) {
            System.out.println("Enter item name to purchase (or 'done' to finish): ");
            String itemName = scanner.nextLine().toLowerCase().trim();

            if (itemName.equalsIgnoreCase("done")) {
                break;
            }

            int quantity = getValidQuantity(scanner);

            ShoppingCart shoppingCart = new ShoppingCart(shopper, store);
            boolean purchaseSuccess = shoppingCart.purchaseItem(itemName, quantity);

            if (purchaseSuccess) {
                System.out.printf("Successfully added to cart %d %s(s). Remaining quantity: %d%n",
                        quantity, itemName, store.getItemQuantities().getOrDefault(itemName, 0));
            } else {
                System.out.println("Failed to purchase item. Please check the item name and quantity.");
            }
        }

        shoppingSystem.addShopper(shopper);
    }

    private static int getValidQuantity(Scanner scanner) {
        int quantity;
        while (true) {
            try {
                System.out.println("Enter quantity to purchase: ");
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
        return quantity;
    }

    private static void displayItemsForSale(Store store) {
        System.out.println("Items for Sale:");
        store.getPricesForAllItems().forEach((itemName, price) -> {
            int quantity = store.getItemQuantities().getOrDefault(itemName, 0);
            System.out.printf("%s - $%.2f - Quantity: %d%n", itemName, price, quantity);
        });
    }

    private static String getValidShopperName(Scanner scanner) {
        while (true) {
            System.out.println("Enter shopper name (or 'checkout' to finish): ");
            String shopperName = scanner.nextLine().toLowerCase();
            if (!shopperName.trim().isEmpty()) {
                return shopperName;
            }
            System.out.println("Shopper name cannot be empty. Please enter a valid name.");
        }
    }
}
