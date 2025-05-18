package se.kth.iv1350.pos.model;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.pos.integration.DiscountDTO;
import se.kth.iv1350.pos.integration.ItemDTO;

/**
 * This represents a sale made by one single customer and payed with one payment
 * by the customer.
 */
public class Sale {
    private Receipt receipt;
    private double runningTotal;
    private double totalVAT;
    private List<ItemDTO> boughtItems;
    private double amountOfChange;
    private static final double VAT_DIVISOR = 100;
    private List<SaleObserver> saleObservers = new ArrayList<>();

    /**
     * Creates a new instance and a receipt for that instance
     */
    public Sale(){
        receipt = new Receipt();
        this.boughtItems = new ArrayList<>();
    }

    /**
     * Adds the scanned item or items to the sale according to the quantity, and updates
     * the running total after item or items are added.
     * @param scannedItem the item that was scanned.
     * @param quantity the quantity of that item.
     */
    public void addItem(ItemDTO scannedItem, int quantity){
        if(scannedItem != null){
            for (int i = 0; i < quantity; i++){
            this.boughtItems.add(scannedItem);
            }
        }
        updateRunningTotals();
    }

    private void updateRunningTotals(){
        double newRunningTotal = 0;
        double newRunningVAT = 0;
        for (ItemDTO currentItem: boughtItems){
            double priceOfCurrentItem = currentItem.getPrice();
            double perecentVAT = currentItem.getVAT() / VAT_DIVISOR;
            newRunningTotal += priceOfCurrentItem;
            newRunningVAT += priceOfCurrentItem * perecentVAT;
        }
        this.runningTotal = newRunningTotal;
        this.totalVAT = newRunningVAT;
    }

    /**
     * Creates a snapshot of the current sale state and returns it.
     * @return a SaleDTO object.
     */
    public SaleDTO getCurrentSaleState(){
        SaleDTO currentSaleInfo = new SaleDTO(this.runningTotal, this.totalVAT, this.boughtItems, this.amountOfChange);
        return currentSaleInfo;
    }

    /**
     * Applies a discount to the current sale.
     * @param amountToBeReduced the specififed amount that will be reduced from the total price.
     */
    public void applyDiscount(DiscountDTO amountToBeReduced){
        this.runningTotal = this.runningTotal - amountToBeReduced.getDiscountAmount();
    }

    /**
     * Calculates the amount of change that the customer is supposed to get.
     * @param payment holds the amount paid by the customer.
     */
    public void calculateChange(Payment payment){
        amountOfChange = payment.getAmountPaid() - runningTotal;
        notifyObservers();
    }

    private void notifyObservers() {
        for (SaleObserver obs : saleObservers) {
            obs.newSale(getCurrentSaleState());
        }
    }

    /**
     * All the specified observers will be notified when this sale has been paid for.
     * @param observers the observers to notify.
     */
    public void addSaleObservers(List<SaleObserver> observers) {
        saleObservers.addAll(observers);
    }

    /**
     * Adds the current sale information to the receipt created by the sale.
     * @param currentSaleInfo holds the information on the current sale, such as bought items etc.
     */
    public void addSaleInfoToReceipt(SaleDTO currentSaleInfo){
        receipt.addSaleInfo(currentSaleInfo);
    }
    
    /**
     * Retrieves the receipt for the current ongoing sale.
     * @return the receipt.
     */
    public Receipt getReceipt() {
        return receipt;
    }


}
