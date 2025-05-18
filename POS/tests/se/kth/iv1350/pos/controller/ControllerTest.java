package se.kth.iv1350.pos.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.pos.integration.ItemDTO;
import se.kth.iv1350.pos.integration.ItemNotFoundException;
import se.kth.iv1350.pos.integration.ItemRegistry;
import se.kth.iv1350.pos.integration.RegistryCreator;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

public class ControllerTest {
    private RegistryCreator regCreator;
    private ItemRegistry itemReg;
    private int nonExistingId;
    private int existingId;

    @BeforeEach
    public void setUp() {
        regCreator = new RegistryCreator();
        itemReg = regCreator.getItemRegistry();
        nonExistingId = 99999;
        existingId = 12345;
    }

    @AfterEach
    public void tearDown() {
        regCreator = null;
    }

    @Test
    public void testScanItemThatDoesNotExist() throws IOException {
        Controller contr = new Controller(regCreator, null);
        contr.startSale();
        try {
            contr.scanItem(nonExistingId, 1);
            fail("Expected ItemNotFoundException was not thrown");
        } catch (OperationFailedException exc) {
            fail("Unexpected OperationFailedException: " + exc.getMessage());
            exc.printStackTrace();
        } catch (ItemNotFoundException exc){
            assertTrue(exc.getMessage().contains(String.valueOf(nonExistingId)),
            "Wrong exception message, does not contain specified item identifier: " + exc.getMessage());
        }
    }

    @Test
    public void testScanItemThatDoesExist() throws IOException {
        Controller contr = new Controller(regCreator, null);
        contr.startSale();

        try {
            ItemDTO scannedItem = contr.scanItem(existingId, 1);
            assertEquals(existingId, scannedItem.getIdentifier(), "Scanned item ID does not match.");
        } catch (OperationFailedException | ItemNotFoundException exc) {
            fail("Exception should not be thrown for existing item: " + exc.getMessage());
        }
    }

    @Test
    public void testScanItemWhenDatabaseFailure() throws IOException, ItemNotFoundException {
        Controller contr = new Controller(regCreator, null);
        contr.startSale();
        int databaseFailureId = 55555;

        try {
            ItemDTO scannedItem = contr.scanItem(databaseFailureId, 1);
            fail("Database was running, expected OperationFailedException");
        } catch (OperationFailedException exc) {
            assertTrue(exc.getMessage().contains("Could not connect to the item database"),
                   "Wrong exception message"); 
        }
    }

    @Test
    public void testScanItemWhenDatabaseRunning() throws IOException, ItemNotFoundException {
        Controller contr = new Controller(regCreator, null);
        contr.startSale();

        try {
            ItemDTO scannedItem = contr.scanItem(existingId, 1);
            assertEquals(scannedItem.getIdentifier(), existingId, "Database did not find the item");
        } catch (OperationFailedException exc) {
            fail("Exception should not be thrown for working database: " + exc.getMessage());
        }
    }


}