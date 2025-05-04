package se.kth.iv1350.pos.integration;

import se.kth.iv1350.pos.model.SaleDTO;


/**
 * DicountRegistry is the discount database which is supposed to hold information
 * about which discount a customer is going to get.
 */
public class DiscountRegistry {
    /**
     * Creates a new instance, that is used to get the discount for a customer.
     */
    public DiscountRegistry(){

    }
    /**
     * Finds a suitable discount from the customers identification and current sale information
     * and returns it.
     * @param idenfication customers identifcation number.
     * @param currentSaleInfo the current sale information.
     * @return a discount.
     */
    public DiscountDTO findDiscount(int idenfication, SaleDTO currentSaleInfo){
        //Fix a way to store discounts here.
        int discountAmount = 0;
        DiscountDTO amountToBeReduced = new DiscountDTO(discountAmount);
        return amountToBeReduced;
    }
}
