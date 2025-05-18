package se.kth.iv1350.pos.view;

import java.io.IOException;

import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.controller.OperationFailedException;
import se.kth.iv1350.pos.integration.ItemDTO;
import se.kth.iv1350.pos.integration.ItemNotFoundException;
import se.kth.iv1350.pos.model.TotalRevenueFileOutput;
import se.kth.iv1350.pos.util.LogHandler;
/**
 * This is a placeholder for the real view. It contains a hardcoded execution with calls to all
 * system operations in the controller.
 */
public class View {
    private Controller contr;
    private ErrorMessageHandler errorMsgHandler;
    private LogHandler logger;

    /**
     * Creates a new instance, that uses the specified controller for all calls to other layers.
     * 
     * @param contr The controller to use for all calls to other layers.
     */
    public View(Controller contr) throws IOException{
        this.contr = contr;
        contr.addSaleObserver(new TotalRevenueView());
        contr.addSaleObserver(new TotalRevenueFileOutput());
        errorMsgHandler = new ErrorMessageHandler();
        logger = new LogHandler();
    }

    /**
     * Runs the fake execution of the progra, it is a simulation of how
     * a pos works.
     */
    public void runFakeExecution(){
        contr.startSale();
        double totalCost = 0;
        double totalVAT = 0;
        ItemDTO scannedItem = null;
        System.out.println("A new sale has been started.");

        System.out.println("Add 1 item with item id 12346:");
        try {
            scannedItem = contr.scanItem(12346, 1);
            printItemInformation(scannedItem);
            totalCost = contr.retrieveRunningTotal();
            totalVAT = contr.retrieveRunningVAT();
            printRunningTotal(totalCost);
            printRunningVAT(totalVAT);
        } catch (ItemNotFoundException exc) {
            errorMsgHandler.showErrorMessage(exc.getMessage());
        } catch (OperationFailedException exc) {
            writeToLogAndView(exc.getMessage(), exc);
        }

        try {
            scannedItem = contr.scanItem(55555, 1);
            printItemInformation(scannedItem);
            totalCost = contr.retrieveRunningTotal();
            totalVAT = contr.retrieveRunningVAT();
            printRunningTotal(totalCost);
            printRunningVAT(totalVAT);
        } catch (ItemNotFoundException exc) {
            errorMsgHandler.showErrorMessage(exc.getMessage());
        } catch (OperationFailedException exc) {
            writeToLogAndView(exc.getMessage(), exc);
        }

        System.out.println("\nAdd 1 item with item id 12345:");
        try {
            scannedItem = contr.scanItem(12345, 1);
            totalCost = contr.retrieveRunningTotal();
            totalVAT = contr.retrieveRunningVAT();
            printItemInformation(scannedItem);
            printRunningTotal(totalCost);
            printRunningVAT(totalVAT);
        } catch (ItemNotFoundException exc) {
            errorMsgHandler.showErrorMessage(exc.getMessage());
        } catch (OperationFailedException exc) {
            writeToLogAndView(exc.getMessage(), exc);
        }

        System.out.println("Add 1 item with item id 12345:");
        try {
            scannedItem = contr.scanItem(12345, 1);
            totalCost = contr.retrieveRunningTotal();
            totalVAT = contr.retrieveRunningVAT();
            printItemInformation(scannedItem);
            printRunningTotal(totalCost);
            printRunningVAT(totalVAT);
        } catch (ItemNotFoundException exc) {
            errorMsgHandler.showErrorMessage(exc.getMessage());
        } catch (OperationFailedException exc) {
            writeToLogAndView(exc.getMessage(), exc);
        }

        System.out.println("Add 1 item with item id 54321:");
        try {
            scannedItem = contr.scanItem(54321, 1);
            totalCost = contr.retrieveRunningTotal();
            totalVAT = contr.retrieveRunningVAT();
            printItemInformation(scannedItem);
            printRunningTotal(totalCost);
            printRunningVAT(totalVAT);
        } catch (ItemNotFoundException exc) {
            errorMsgHandler.showErrorMessage(exc.getMessage());
        } catch (OperationFailedException exc) {
            writeToLogAndView(exc.getMessage(), exc);
        }

        totalCost = contr.endSale(); //Ends the sale and returns the complete sale.
        printEndSale(totalCost); //Prints the total price for the end of sale.

        contr.enterAmountPaid(100);

        contr.startSale();
        System.out.println("A new sale has been started.");

        System.out.println("Add 1 item with item id 12345:");
        try {
            scannedItem = contr.scanItem(12345, 1);
            totalCost = contr.retrieveRunningTotal();
            totalVAT = contr.retrieveRunningVAT();
            printItemInformation(scannedItem);
            printRunningTotal(totalCost);
            printRunningVAT(totalVAT);
        } catch (ItemNotFoundException exc) {
            errorMsgHandler.showErrorMessage(exc.getMessage());
        } catch (OperationFailedException exc) {
            writeToLogAndView(exc.getMessage(), exc);
        }

        System.out.println("Add 1 item with item id 12345:");
        try {
            scannedItem = contr.scanItem(12345, 1);
            totalCost = contr.retrieveRunningTotal();
            totalVAT = contr.retrieveRunningVAT();
            printItemInformation(scannedItem);
            printRunningTotal(totalCost);
            printRunningVAT(totalVAT);
        } catch (ItemNotFoundException exc) {
            errorMsgHandler.showErrorMessage(exc.getMessage());
        } catch (OperationFailedException exc) {
            writeToLogAndView(exc.getMessage(), exc);
        }

        System.out.println("Add 1 item with item id 54321:");
        try {
            scannedItem = contr.scanItem(54321, 1);
            totalCost = contr.retrieveRunningTotal();
            totalVAT = contr.retrieveRunningVAT();
            printItemInformation(scannedItem);
            printRunningTotal(totalCost);
            printRunningVAT(totalVAT);
        } catch (ItemNotFoundException exc) {
            errorMsgHandler.showErrorMessage(exc.getMessage());
        } catch (OperationFailedException exc) {
            writeToLogAndView(exc.getMessage(), exc);
        }

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
     * Prints the running total for the current sale.
     * @param totalCost the current total cost of the sale.
     */
    public void printRunningTotal(double totalCost){
        System.out.printf("Total cost (incl VAT): %.2f SEK%n\n", totalCost);
    }

    /**
     * Prints the running VAT for the current sale.
     * @param totalVAT the current total VAT of the sale.
     */
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

    private void writeToLogAndView(String viewMsg, Exception exc) {
        errorMsgHandler.showErrorMessage(viewMsg);
        logger.logException(exc);
    }

}
