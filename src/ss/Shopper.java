package ss;

//Shopper class represents customers with names and total purchase values
public class Shopper implements Comparable<Shopper> {
    private static int insertionCounter = 0;
    private String shopperName;
    private double totalPurchases;
    private int insertionOrder;

    // Constructor initializes a Shopper with a name and sets initial purchase values
    public Shopper(String name) {
        this.shopperName = name;
        this.totalPurchases = 0;
        this.insertionOrder = insertionCounter++;
    }

    public String getName() {
        return shopperName;
    }

    public double getTotalPurchases() {
        return totalPurchases;
    }
    
    public int getInsertionOrder() {
        return insertionOrder;
    }

    // Method to update total purchases when a shopper makes additional purchases
    public void updateTotalPurchases(double additionalPurchases) {
        totalPurchases += additionalPurchases;
    }
    
    // Compare shoppers based on total purchase values and insertion order for stability
    @Override
    public int compareTo(Shopper other) {
        int priorityComparison = Double.compare(other.totalPurchases, this.totalPurchases);

        if (priorityComparison != 0) {
            return priorityComparison;
        } else {
            return Integer.compare(this.insertionOrder, other.insertionOrder);
        }
    }
}
