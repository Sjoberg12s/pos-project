package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.integration.ItemDTO;
/**
 * This is a placeholder for the real view. It contains a hardcoded execution with calls to all
 * system operations in the controller.
 */
public class View {
    private Controller contr;

    /**
     * Creates a new instance, that uses the specified controller for all calls to other layers.
     * 
     * @param contr The controller to use for all calls to other layers.
     */
    public View(Controller contr){
        this.contr = contr;
    }

    public void runFakeExecution(){
        contr.startSale();
        System.out.println("A new sale has been started.");

        System.out.println("Add 1 item with item id 12345:");
        ItemDTO scannedItem = contr.scanItem(12345, 1);
        printItemInformation(scannedItem);
        double totalCost = contr.retrieveRunningTotal();
        double totalVAT = contr.retrieveRunningVAT();
        printRunningTotal(totalCost);
        printRunningVAT(totalVAT);


        System.out.println("Add 1 item with item id 12345:");
        scannedItem = contr.scanItem(12345, 1);
        totalCost = contr.retrieveRunningTotal();
        totalVAT = contr.retrieveRunningVAT();
        printItemInformation(scannedItem);
        printRunningTotal(totalCost);
        printRunningVAT(totalVAT);

        System.out.println("Add 1 item with item id 54321:");
        scannedItem = contr.scanItem(54321, 1);
        totalCost = contr.retrieveRunningTotal();
        totalVAT = contr.retrieveRunningVAT();
        printItemInformation(scannedItem);
        printRunningTotal(totalCost);
        printRunningVAT(totalVAT);

        totalCost = contr.endSale(); //Ends the sale and returns the complete sale.
        printEndSale(totalCost); //Prints the total price for the end of sale.

        contr.enterAmountPaid(100);


    }

    /**
     * Prints the item information in the view for the scanned item.
     * @param scannedItem the scanned item and its information.
     */
    public void printItemInformation(ItemDTO scannedItem){
        System.out.println("Item ID: " + scannedItem.getIdentifier());
        System.out.println("Item name: " + scannedItem.getName());
        System.out.printf("Item cost: %.2f SEK%n", scannedItem.getPrice());
        System.out.println("VAT: " + scannedItem.getVAT() + "%");
        System.out.println("Item description: " + scannedItem.getItemDescription() + "\n");
    }

    /**
     * Prints the running total for the current sale
     * @param currentSale the current sale that contains the running total, items etc.
     */
    public void printRunningTotal(double totalCost){
        System.out.printf("Total cost (incl VAT): %.2f SEK%n\n", totalCost);
    }

    public void printRunningVAT(double totalVAT){
        System.out.printf("Total VAT: %.2f SEK%n\n", totalVAT);
    }

    /**
     * Prints the running total after a "End sale" message to indicate to the view that 
     * the sale has ended and here is the total cost.
     * @param totalCost total cost for the entire sale.
     */
    public void printEndSale(double totalCost){
        System.out.println("End sale:");
        printRunningTotal(totalCost);
    }

}
