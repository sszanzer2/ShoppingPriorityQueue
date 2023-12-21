package ss;

public interface ShopperCheckout {
    void addShopper(Shopper shopper);
    void simulateCheckout();
    PriorityQueue<Shopper> getPriorityQueue();
}
