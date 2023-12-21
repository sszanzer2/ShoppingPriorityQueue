package ss;

public class CouponChecker {
    public static final double COUPON_THRESHOLD = 100.0;
    public static final double COUPON_AMOUNT = 0.10;

    public void applyCoupon(Shopper shopper) {
        // Check if the shopper is eligible for a coupon (e.g., total purchases > $100)
        if (shopper.getTotalPurchases() > COUPON_THRESHOLD) {
            System.out.printf("%s total purchases: $%,.2f%n", shopper.getName(), shopper.getTotalPurchases());
            // Adjust the total purchases with a coupon
            shopper.updateTotalPurchases(-shopper.getTotalPurchases() * COUPON_AMOUNT);
            System.out.printf("10 Percent Off Coupon Applied! Total purchases adjusted: $%,.2f%n", shopper.getTotalPurchases());
        }
    }
}
