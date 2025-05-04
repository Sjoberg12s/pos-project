package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.integration.ItemDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Represents one receipt, which proves the payment of one sale.
 */
public class Receipt {
    private LocalTime saleTime;
    private LocalDate saleDate;
    private List<ItemDTO> boughtItems;
    private double totalPrice;
    private double totalVAT;
    private double amountOfChange;
    private double amountPaid;

    /**
     * Creates a new instance and saves the time and date of the receipt
     */
    public Receipt(){
        this.boughtItems = new ArrayList<>();
        setDateOfSale();
        setTimeOfSale();
    }

    private void setTimeOfSale(){
        this.saleTime = LocalTime.now();
    }

    private void setDateOfSale(){
        this.saleDate = LocalDate.now();
    }

    /**
     * Retrieves the date of the sale.
     * @return the date of the sale.
     */
    public LocalDate getSaleDate() {
        return saleDate;
    }

    /**
     * Retrieves the time of the sale.
     * @return the time of the sale.
     */
    public LocalTime getSaleTime() {
        return saleTime;
    }

    /**
     * Retrieves the amount of change for the sale.
     * @return the amount of change.
     */
    public double getAmountOfChange() {
        return amountOfChange;
    }

    /**
     * Retrieves the amount paid for the sale.
     * @return the amount paid.
     */
    public double getAmountPaid() {
        return amountPaid;
    }

    /**
     * Retrieves the list of bought items for the sale.
     * @return the list of bought items.
     */
    public List<ItemDTO> getBoughtItems() {
        return boughtItems;
    }

    /**
     * Retrieves the total price for the entire sale.
     * @return the total price for the entire sale.
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Retrieves the total VAT for the entire sale.
     * @return the total VAT for the entire sale.
     */
    public double getTotalVAT() {
        return totalVAT;
    }

    public int getQuantityOfAnItem(ItemDTO itemToFindQuantityOf, List<ItemDTO> bougthItemsToCheck){
        int count = 0;
        for (ItemDTO currentItem: bougthItemsToCheck){

            if (currentItem.getIdentifier() == itemToFindQuantityOf.getIdentifier()){
                count++;
            }
        }
        return count;
    }

    /**
     * Adds the sale information to the receipt.
     * @param currentSaleInfo holds the current sale information.
     */
    public void addSaleInfo(SaleDTO currentSaleInfo){
        this.boughtItems = currentSaleInfo.getBoughtItems();
        this.amountOfChange = currentSaleInfo.getAmountOfChange();
        this.totalPrice = currentSaleInfo.getRunningTotal();
        this.amountPaid = this.totalPrice + this.amountOfChange;
        this.totalVAT = currentSaleInfo.getTotalVAT();
    }

    /**
     * Creates a receipt that can be printed using the attributes saved in the receipt
     * @return the receipt as a printable string.
     */
    public String createReceiptString() {
        StringBuilder receiptBuilder = new StringBuilder();
    
        receiptBuilder.append("-----------------------Begin Receipt-----------------------\n");
        receiptBuilder.append("Time of sale: ").append(getSaleDate()).append(" ").append(getSaleTime()).append("\n\n");
    
        List<ItemDTO> uniqueBoughtItems = getUniqueItems(getBoughtItems());
        for (ItemDTO currentItem : uniqueBoughtItems) {
            int quantity = getQuantityOfAnItem(currentItem, getBoughtItems());
            double totalItemPrice = quantity * currentItem.getPrice();
            receiptBuilder.append(String.format(Locale.forLanguageTag("sv-SE"),
                    "%s      %d x %.2f      %.2f SEK\n",
                    currentItem.getName(), quantity, currentItem.getPrice(), totalItemPrice));
        }
    
        receiptBuilder.append(String.format(Locale.forLanguageTag("sv-SE"), "Total:     %.2f SEK", getTotalPrice()));
        receiptBuilder.append(String.format(Locale.forLanguageTag("sv-SE"), "\nVAT:       %.2f SEK", getTotalVAT()));
        receiptBuilder.append(String.format(Locale.forLanguageTag("sv-SE"), "\n\nCash:      %.2f SEK", getAmountPaid()));
        receiptBuilder.append(String.format(Locale.forLanguageTag("sv-SE"), "\nChange:      %.2f SEK", getAmountOfChange()));
        receiptBuilder.append("\n-----------------------End Receipt-----------------------");
    
        return receiptBuilder.toString();
    }
    
    private List<ItemDTO> getUniqueItems(List<ItemDTO> boughtItems){
        List<ItemDTO> uniqueItemsFromBoughtItems = new ArrayList<>();
        boolean matchFound = false;
        for (ItemDTO currentItem: boughtItems){
            if (uniqueItemsFromBoughtItems.isEmpty()) {
                uniqueItemsFromBoughtItems.add(currentItem);
            } else{
                for (ItemDTO currentUniqueItem: uniqueItemsFromBoughtItems){
                    if (currentItem.getIdentifier() == currentUniqueItem.getIdentifier()) {
                        matchFound = true;
                        break;
                    }
                }
                if (matchFound != true) {
                    uniqueItemsFromBoughtItems.add(currentItem);
                }
                matchFound = false;
            }
        }
        return uniqueItemsFromBoughtItems;
    }
}
