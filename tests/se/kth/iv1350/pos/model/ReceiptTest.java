package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.integration.ItemDTO;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

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
    public void testGetQuantityOfAnItem(){
        Receipt receiptToTest = new Receipt();
        List<ItemDTO> boughtItems = new ArrayList<>();
        boughtItems.add(itemToAddToSale);
        boughtItems.add(itemToAddToSale);
        boughtItems.add(itemToAddToSale);

        int quantityOfItemToAddToSale = receiptToTest.getQuantityOfAnItem(itemToAddToSale, boughtItems);
        assertEquals(3, quantityOfItemToAddToSale, "Quantity of item1 should be 3");
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