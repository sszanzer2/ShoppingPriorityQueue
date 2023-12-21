package ss;

public class Main {
    public static void main(String[] args) {
        Store store = new Store();

        // Add items to the store
        store.addItem("Laptop", 1000.0, 10);
        store.addItem("Phone", 500.0, 15);
        store.addItem("Headphones", 100.0, 20);
        store.addItem("Charger", 10.00, 12);
        store.addItem("Phone Cases", 7.00, 8);
        store.addItem("Ipad", 300.00, 17);
        store.addItem("TV Screen", 2500.00, 5);

        // Create shoppers
        Shopper shopper1 = new Shopper("Mia");
        Shopper shopper2 = new Shopper("Ava");
        Shopper shopper3 = new Shopper("Leala");

        ShoppingCart sc1 = new ShoppingCart(shopper1, store);
        ShoppingCart sc2 = new ShoppingCart(shopper2, store);
        ShoppingCart sc3 = new ShoppingCart(shopper3, store);
        // Shoppers purchase items
        sc1.purchaseItem("Laptop", 2);
        sc1.purchaseItem("Phone", 1);

        sc2.purchaseItem( "Headphones", 5);
        sc2.purchaseItem("Charger", 1);

        sc3.purchaseItem("TV Screen", 2);
        sc3.purchaseItem( "Ipad", 3);

        // Create the shopping system
        ShoppingSystem shoppingSystem = new ShoppingSystem();

        // Add shoppers to the priority queue
        shoppingSystem.addShopper(shopper1);
        shoppingSystem.addShopper(shopper2);
        shoppingSystem.addShopper(shopper3);

        // Simulate the checkout process
        shoppingSystem.simulateCheckout();
    }
}
