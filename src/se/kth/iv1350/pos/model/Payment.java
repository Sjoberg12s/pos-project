package se.kth.iv1350.pos.model;

public class Payment {
    private double amountPaid;

    /**
     * Creates a new instance, the amount that the customer paid for the entire sale.
     * @param amountPaid the total amount the customer paid for the sale.
     */
    public Payment(double amountPaid){
        this.amountPaid = amountPaid;
    }

    /**
     * Retrieves the amount paid for the current sale.
     * @return the amount paid by the customer.
     */
    public double getAmountPaid() {
        return amountPaid;
    }
}
