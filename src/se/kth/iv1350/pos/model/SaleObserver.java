package se.kth.iv1350.pos.model;

/**
 * A listener interface for receiving notificiations about ended sales.
 * The class that is interested in such notifications implements this interface, and
 * the object created with that class is registered with
 * {@link se.kth.iv1350.pos.controller.Controller#addSaleObserver(SaleObserver)}. When
 * a sale is ended, that object's {@link #newSale newSale} method is invoked.
 */
public interface SaleObserver {

    /**
     * Invoked when a sale has been paid.
     * @param paidSale the sale that has been paid by a customer.
     */
    void newSale(SaleDTO paidSale);
}
