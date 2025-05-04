package se.kth.iv1350.pos.integration;

/**
 * This represents a discount for a sale, specifies how much
 * will be drawn of from the current total cost.
 */
public class DiscountDTO {
    private int discountAmount;

    /**
     * Creates a new instance, a discount that indicates how much that will be reduced from
     * the total price of a sale.
     */
    public DiscountDTO(int discountAmount){
        this.discountAmount = discountAmount;
    }

    /**
     * Retrieevs the discount amount from the current sale.
     * @return the discount amount.
     */
    public int getDiscountAmount(){
        return this.discountAmount;
    }
}
