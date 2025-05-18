package se.kth.iv1350.pos.model;

/**
 * Represents a cash register in a pos, it holds a balance which is how
 * much cash it has and it receives the payment from a customer.
 */
public class CashRegister {
    private double balance;
    
    /**
     * Adds the payment amount to the cash register, being the amount that the customer paid.
     * @param payment the amount the customer paid for the entire sale.
     */
    public void addPayment(Payment payment){
        this.balance += payment.getAmountPaid();
    }
}
