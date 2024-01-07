package ss;

// ShoppingSystem class manages shoppers using a priority queue
public class ShoppingSystem implements ShopperCheckout {
    private PriorityQueue<Shopper> priorityQueue;
    private CouponChecker couponChecker;

    public ShoppingSystem(CouponChecker couponChecker) {
        this.priorityQueue = new PriorityQueue<>();
        this.couponChecker = couponChecker;
    }

    // Add a shopper to the priority queue
    @Override
    public void addShopper(Shopper shopper) {
        priorityQueue.enqueue(shopper);
    }

    @Override
    public void simulateCheckout() {
        while (!priorityQueue.isEmpty()) {
            Shopper shopper = priorityQueue.dequeue();
            applyCoupon(shopper);
            printCheckoutInformation(shopper);
        }
    }

    private void applyCoupon(Shopper shopper) {
        couponChecker.printingAndApplyCoupon(shopper);
    }

    private void printCheckoutInformation(Shopper shopper) {
        System.out.printf("Checking out: %s (Insertion Order: %d) - Total Purchase Amount: $%,.2f%n",
                shopper.getName(), shopper.getInsertionOrder(), shopper.getTotalPurchases());
    }

    @Override
    public PriorityQueue<Shopper> getPriorityQueue() {
        return priorityQueue;
    }
}
