package se.kth.iv1350.pos.model;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.pos.integration.ItemDTO;

/**
 * This represents a snapshot of the Sale class where different 
 * attributes can be retrieved from the sale.
 */
public class SaleDTO {
    private double runningTotal;
    private double totalVAT;
    private List<ItemDTO> boughtItems;
    private double amountOfChange;

    /**
     * Creates a new instance, a snapshot of the current sale.
     * @param runningTotal
     * @param totalVAT
     * @param boughtItems
     * @param amountOfChange
     */
    public SaleDTO(double runningTotal, double totalVAT, List<ItemDTO> boughtItems, double amountOfChange){
        this.boughtItems = new ArrayList<>();
        this.runningTotal = runningTotal;
        this.totalVAT = totalVAT;
        this.boughtItems = boughtItems;
        this.amountOfChange = amountOfChange;
    }
    /**
     * Retrieves the running total of the sale.
     * @return the running total.
     */
    public double getRunningTotal() {
        return runningTotal;
    }

    /**
     * Retrieves the total VAT for the entire sale.
     * @return the total VAT.
     */
    public double getTotalVAT() {
        return totalVAT;
    }
    /**
     * Retrieves the amount of change for the sale.
     * @return the amount of change.
     */
    public double getAmountOfChange() {
        return amountOfChange;
    }
    /**
     * Retrieves the list of bought items.
     * @return a list of bought items.
     */
    public List<ItemDTO> getBoughtItems() {
        return boughtItems;
    }
}
