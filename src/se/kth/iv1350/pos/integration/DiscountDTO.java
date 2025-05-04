package se.kth.iv1350.pos.integration;

public class DiscountDTO {
    private int discountAmount;

    /**
     * Creates a new instance, a discount that indicates how much that will be reduced from
     * the total price of a sale.
     */
    public DiscountDTO(int discountAmount){
        this.discountAmount = discountAmount;
    }

    public int getDiscountAmount(){
        return this.discountAmount;
    }
}
