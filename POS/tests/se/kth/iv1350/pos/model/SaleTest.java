package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.integration.ItemDTO;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SaleTest {
    private ItemDTO itemToScan;
    private int quantityOfZero;
    private int quantityOfOne;
    private int quantityOfTwo;
    private int quantityOfTen;
    private Payment paymentForSale;

    @BeforeEach
    public void setUp() {
        itemToScan = new ItemDTO(1, "Normal Youghurt, nothing special",
        "Normal Youghurt", 10, 6);
        quantityOfZero = 0;
        quantityOfOne = 1;
        quantityOfTwo = 2;

        quantityOfTen = 10;
        paymentForSale = new Payment(100);
    }

    @AfterEach
    public void tearDown() {
        itemToScan = null;
    }

    @Test
    public void testAddZeroOfAnItem(){
        Sale instanceOfSale = new Sale();
        instanceOfSale.addItem(itemToScan, quantityOfZero);
        SaleDTO instanceOfSaleDTO = instanceOfSale.getCurrentSaleState();
        int expResult = 0;
        int result = instanceOfSaleDTO.getBoughtItems().size();
        assertEquals(expResult, result, "Item was not added correctly.");
    }

    @Test
    public void testAddOneOfAnItem(){
        Sale instanceOfSale = new Sale();
        instanceOfSale.addItem(itemToScan, quantityOfOne);
        SaleDTO instanceOfSaleDTO = instanceOfSale.getCurrentSaleState();
        int expResult = 1;
        int result = instanceOfSaleDTO.getBoughtItems().size();
        assertEquals(expResult, result, "Items was not added correctly.");

    }
    
    @Test
    public void testAddTwoOfAnItem(){
        Sale instanceOfSale = new Sale();
        instanceOfSale.addItem(itemToScan, quantityOfTwo);
        SaleDTO instanceOfSaleDTO = instanceOfSale.getCurrentSaleState();
        int expResult = 2;
        int result = instanceOfSaleDTO.getBoughtItems().size();
        assertEquals(expResult, result, "Items was not added correctly.");
    }

    @Test
    public void testCalculateChangeEqualToZero() {
        Sale instance = new Sale();
        instance.addItem(itemToScan, quantityOfTen);
        instance.calculateChange(paymentForSale);
        SaleDTO instanceOfSaleDTO = instance.getCurrentSaleState();
        double expResult = 0;
        double result = instanceOfSaleDTO.getAmountOfChange();
        assertEquals(expResult, result, "Change was not amounted to zero.");
    }
}