package ss;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShoppingTest {
    private Store store;
    private CouponChecker couponChecker;
    private ShoppingSystem shoppingSystem;
    private Shopper shopper;

    @BeforeEach
    public void setUp() {
        store = new Store();
        couponChecker = new CouponChecker();
        shoppingSystem = new ShoppingSystem(couponChecker);
    }

    @Test
    public void testAddItemAndGetPrice() {
        assertTrue(store.getPricesForAllItems().isEmpty());
        store.addItem("Sound System", 1500.00, 5);
        assertFalse(store.getPricesForAllItems().isEmpty());
        assertEquals(1500.00, store.getItemPrice("Sound System"));
    }

    @Test
	public void TestUpdateTotalPurchases() {
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
        assertTrue(shoppingSystem.getPriorityQueue().isEmpty());
        Shopper shopper1 = new Shopper("Mia");
        shoppingSystem.addShopper(shopper1);
        assertFalse(shoppingSystem.getPriorityQueue().isEmpty());
    }

    @Test
    public void testStableSort() {
        ShoppingSystem shoppingSystem = new ShoppingSystem(couponChecker);

        Shopper shopper1 = new Shopper("Cassie");
        shopper1.updateTotalPurchases(50);

        Shopper shopper2 = new Shopper("Mia");
        shopper2.updateTotalPurchases(50);

        Shopper shopper3 = new Shopper("Henry");
        shopper3.updateTotalPurchases(50);

        shoppingSystem.addShopper(shopper1);
        shoppingSystem.addShopper(shopper2);
        shoppingSystem.addShopper(shopper3);

        assertEquals(shopper1, shoppingSystem.getPriorityQueue().dequeue());
        assertEquals(shopper2, shoppingSystem.getPriorityQueue().dequeue());
        assertEquals(shopper3, shoppingSystem.getPriorityQueue().dequeue());
    }

    @Test
    public void testPurchaseItem() {
        store.addItem("Laptop", 1000.0, 10);
        store.addItem("Headphones", 50.0, 20);

        Shopper shopper1 = new Shopper("Alice");
        Shopper shopper2 = new Shopper("Bob");

        shoppingSystem.addShopper(shopper1);
        shoppingSystem.addShopper(shopper2);
        ShoppingCart sc1 = new ShoppingCart(shopper1, store);
        ShoppingCart sc2 = new ShoppingCart(shopper2, store);

        assertTrue(sc1.purchaseItem("Laptop", 2));
        assertTrue(sc1.purchaseItem("Headphones", 3));

        assertTrue(sc2.purchaseItem("Laptop", 1));
        assertTrue(sc2.purchaseItem("Headphones", 5));

        assertEquals(2 * store.getItemPrice("Laptop") + 3 * store.getItemPrice("Headphones"), shopper1.getTotalPurchases());
        assertEquals(1 * store.getItemPrice("Laptop") + 5 * store.getItemPrice("Headphones"), shopper2.getTotalPurchases());
    }

    @Test
    public void testCouponAndCheckout() {
        Shopper shopper1 = new Shopper("Mia");
        shopper1.updateTotalPurchases(200.00);

        Shopper shopper2 = new Shopper("Alex");
        shopper2.updateTotalPurchases(100.00);

        Shopper shopper3 = new Shopper("Sophie");
        shopper3.updateTotalPurchases(150.00);

        shoppingSystem.addShopper(shopper1);
        shoppingSystem.addShopper(shopper2);
        shoppingSystem.addShopper(shopper3);

        shoppingSystem.simulateCheckout();
    }

    @Test
    public void testPurchaseItemWithInvalidQuantity() {
        store.addItem("Laptop", 1000.0, 10);
        ShoppingCart shoppingCart = new ShoppingCart(shopper, store);

        assertFalse(shoppingCart.purchaseItem("Laptop", 0));
        assertFalse(shoppingCart.purchaseItem("Laptop", -1));
    }

    @Test
    public void testEmptyShopperName() {
        assertEquals("Enter a valid name", getErrorMessageForInvalidShopperName(""));
    }

    @Test
    public void testShopperNameWithSpacesOnly() {
        assertEquals("Enter a valid name", getErrorMessageForInvalidShopperName("   "));
    }

    @Test
    public void testPurchaseNonexistentItem() {
        ShoppingCart shoppingCart = new ShoppingCart(shopper, store);
        assertFalse(shoppingCart.purchaseItem("Nonexistent Item", 1));
    }

    @Test
    public void testPurchaseItemExceedingStock() {
        store.addItem("Laptop", 1000.0, 2);
        ShoppingCart shoppingCart = new ShoppingCart(shopper, store);
        assertFalse(shoppingCart.purchaseItem("Laptop", 3));
    }

    private String getErrorMessageForInvalidShopperName(String shopperName) {
        return "Enter a valid name";
    }
}
