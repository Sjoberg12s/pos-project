package se.kth.iv1350.pos.integration;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.pos.model.Receipt;

public class Printer {
    /**
     * Creates a new instance, is used to print out the receipt.
     */
    public Printer(){

    }

    /**
     * Prints out the current receipt for the customer.
     * @param receipt current receipt for the ongoing sale.
     */
    public void printReceipt(Receipt receipt){
        String receiptToPrint = receipt.createReceiptString();
        System.out.println(receiptToPrint);
    }

}
