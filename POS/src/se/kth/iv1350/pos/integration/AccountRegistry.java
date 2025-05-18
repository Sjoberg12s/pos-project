package se.kth.iv1350.pos.integration;

import se.kth.iv1350.pos.model.Payment;

/**
 * AccountRegistry is an accounting registry that holds accounting details about
 * different POS from customers.
 */
public class AccountRegistry {
    private Payment accountingInformation;
    /**
     * Creates a new instance, that is used to receive accounting information
     * from a customer.
     */
    public AccountRegistry(){

    }
    
    /**
     * Stores the sale information, meaning the accounting details in the accounting registry.
     * @param payment payment information to be stored for accounting.
     */
    public void addSaleInformation(Payment payment){
        this.accountingInformation = payment;
    }
}
