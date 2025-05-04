package se.kth.iv1350.pos.model;

public class CashRegister {
    private double balance;
    
    /**
     * Adds the payment amount to the cash register, being the amount that the customer paid.
     * @param payment the amount the customer paid for the entire sale.
     */
    public void addPayment(Payment payment){
        balance += payment.getAmountPaid();
    }
}
