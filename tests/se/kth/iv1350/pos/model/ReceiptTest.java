package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.integration.ItemDTO;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReceiptTest {
    private ItemDTO itemToAddToSale;
    private int quantityOfItemToAdd;
    private Payment paidAmt;

    @BeforeEach
    public void setUp() {
        itemToAddToSale = new ItemDTO(12345, "Sugary cereal", 
        "Cereal", 30, 6);
        quantityOfItemToAdd = 1;
        paidAmt = new Payment(50);
    }

    @AfterEach
    public void tearDown() {
        itemToAddToSale = null;
        paidAmt = null;
    }

    @Test
    public void testCreateReceiptString() {
        Sale instanceSale = new Sale();
        instanceSale.addItem(itemToAddToSale, quantityOfItemToAdd);
        instanceSale.calculateChange(paidAmt);
        SaleDTO instanceSaleDTO = instanceSale.getCurrentSaleState();
        instanceSale.addSaleInfoToReceipt(instanceSaleDTO);
        
        Receipt receiptOfSale = instanceSale.getReceipt();
        String expResult = 
        "-----------------------Begin Receipt-----------------------\n" +
        "Time of sale: " + receiptOfSale.getSaleDate() + " " + receiptOfSale.getSaleTime() + "\n\n" +
        "Cereal      1 x 30,00      30,00 SEK\n" +
        "Total:     30,00 SEK" +
        "\nVAT:       1,80 SEK" +
        "\n\nCash:      50,00 SEK" +
        "\nChange:      20,00 SEK" +
        "\n-----------------------End Receipt-----------------------";
        String result = receiptOfSale.createReceiptString();

        assertEquals(expResult, result, "Wrong printout.");
    }
}