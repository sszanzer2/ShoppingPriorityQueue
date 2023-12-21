package ss;

// ShoppingSystem class manages shoppers using a priority queue
public class ShoppingSystem implements ShopperCheckout {
    private PriorityQueue<Shopper> priorityQueue;

    public ShoppingSystem() {
        priorityQueue = new PriorityQueue<>();
    }

    // Add a shopper to the priority queue
    @Override
    public void addShopper(Shopper shopper) {
        priorityQueue.enqueue(shopper);
    }

    @Override
    public void simulateCheckout() {
        while (!priorityQueue.isEmpty()) {
            Shopper shopper = priorityQueue.dequeue(); // Use dequeue method to get the next shopper
            CouponChecker couponChecker=new CouponChecker();
            // Apply the coupon after the shopper is done with purchases
            couponChecker.applyCoupon(shopper);

            System.out.printf("Checking out: %s (Insertion Order: %d) - Total Purchase Amount: $%,.2f%n",
                    shopper.getName(), shopper.getInsertionOrder(), shopper.getTotalPurchases());
        }
    }

    @Override
    public PriorityQueue<Shopper> getPriorityQueue() {
        return priorityQueue;
    }
}
