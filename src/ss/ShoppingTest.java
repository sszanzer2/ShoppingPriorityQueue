package ss;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ShoppingTest {

	@Test
	public void TestAddItemAndGetPrice() {
		Store store = new Store();
		assertTrue(store.getAllItemPrices().isEmpty());
		store.addItem("Sound System", 1500.00, 5);
		assertTrue(!store.getAllItemPrices().isEmpty());
		assertEquals(1500.00, store.getItemPrice("Sound System"));
	}
	
	@Test
	public void TestUpdateTotalPurchases() {
		Store store = new Store();
		store.addItem("Sound System", 1500.00, 5);
		store.addItem("Airpods", 500.00, 7);
		Shopper shopper1 = new Shopper("Cassie");
		ShoppingCart sc = new ShoppingCart(shopper1, store);
		sc.purchaseItem("sound system", 1);
		sc.purchaseItem("airpods", 1);
		shopper1.updateTotalPurchases(1500.00);
		shopper1.updateTotalPurchases(500.00);
		assertEquals(2000.00, shopper1.getTotalPurchases());
	}
	
	@Test
	public void testAddShopper() {
	    ShoppingSystem ss = new ShoppingSystem();
	    assertTrue(ss.getPriorityQueue().isEmpty()); 
	    Shopper shopper1 = new Shopper("Mia");
	    ss.addShopper(shopper1);
	    assertFalse(ss.getPriorityQueue().isEmpty());
	}

    @Test
    public void testStableSort() {
        ShoppingSystem shoppingSystem = new ShoppingSystem();

        // Create shoppers with the same total purchase values but different insertion orders
        Shopper shopper1 = new Shopper("Cassie");
        shopper1.updateTotalPurchases(50);

        Shopper shopper2 = new Shopper("Mia");
        shopper2.updateTotalPurchases(50);

        Shopper shopper3 = new Shopper("Henry");
        shopper3.updateTotalPurchases(50);

        // Add shoppers to the shopping system
        shoppingSystem.addShopper(shopper1);
        shoppingSystem.addShopper(shopper2);
        shoppingSystem.addShopper(shopper3);

        // Verify that shoppers with the same total purchase values are checked out based on their insertion order
        assertEquals(shopper1, shoppingSystem.getPriorityQueue().dequeue());
        assertEquals(shopper2, shoppingSystem.getPriorityQueue().dequeue());
        assertEquals(shopper3, shoppingSystem.getPriorityQueue().dequeue());
    }
    
    

    @Test
    public void testPurchaseItem() {
        Store store = new Store();
        ShoppingSystem shoppingSystem = new ShoppingSystem();

        // Add items to the store
        store.addItem("Laptop", 1000.0, 10);
        store.addItem("Headphones", 50.0, 20);

        // Create shoppers
        Shopper shopper1 = new Shopper("Alice");
        Shopper shopper2 = new Shopper("Bob");

        // Add shoppers to the shopping system
        shoppingSystem.addShopper(shopper1);
        shoppingSystem.addShopper(shopper2);
        ShoppingCart sc1 = new ShoppingCart(shopper1, store);
        ShoppingCart sc2 = new ShoppingCart(shopper2, store);
        // Simulate item purchases for shopper1
        assertTrue(sc1.purchaseItem("Laptop", 2));
        assertTrue(sc1.purchaseItem("Headphones", 3));

        // Simulate item purchases for shopper2
        assertTrue(sc2.purchaseItem("Laptop", 1));
        assertTrue(sc2.purchaseItem("Headphones", 5));

        // Verify that the total purchase value of each shopper is correctly updated
        assertEquals(2 * store.getItemPrice("Laptop") + 3 * store.getItemPrice("Headphones"), shopper1.getTotalPurchases());
        assertEquals(1 * store.getItemPrice("Laptop") + 5 * store.getItemPrice("Headphones"), shopper2.getTotalPurchases());
    }
    
    @Test
    public void testCouponAndCheckout() {
        // Create a shopping system
        ShoppingSystem shoppingSystem = new ShoppingSystem();

        // Create shoppers with different total purchase values for testing
        Shopper shopper1 = new Shopper("Mia");
        shopper1.updateTotalPurchases(200.00);

        Shopper shopper2 = new Shopper("Alex");
        shopper2.updateTotalPurchases(100.00);

        Shopper shopper3 = new Shopper("Sophie");
        shopper3.updateTotalPurchases(150.00);

        // Add shoppers to the priority queue
        shoppingSystem.addShopper(shopper1);
        shoppingSystem.addShopper(shopper2);
        shoppingSystem.addShopper(shopper3);

        // Simulate coupon distribution and checkout process
        shoppingSystem.simulateCheckout();
    }
}




