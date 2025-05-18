package se.kth.iv1350.pos.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemRegistryTest {
    private ItemDTO itemWithIdentifier12345;

    @BeforeEach
    public void setUp() {
        itemWithIdentifier12345 = new ItemDTO(12345,
         "BigWheel Oatmeal 500g, whole grain oats, high fiber, gluten free",
          "BigWheel Oatmeal", 29.90, 6);
    }

    @AfterEach
    public void tearDown() {
        itemWithIdentifier12345 = null;
    }
    @Test
    public void testFindItem() throws ItemNotFoundException {
        ItemRegistry instance = new ItemRegistry();
        ItemDTO expResult = itemWithIdentifier12345;
        ItemDTO result = instance.findItem(itemWithIdentifier12345.getIdentifier());
        assertEquals(expResult, result, "Item in inventory was not found");
    }

   @Test
    public void testFindItemThatDoesNotExist() {
        ItemRegistry instance = new ItemRegistry();
        try {
            instance.findItem(99999);
            fail("Could find a non-existing item");
        }   catch (ItemNotFoundException exc) {
            assertTrue(exc.getMessage().contains("99999"),
                   "Wrong exception message, does not contain specified item identifier");
        }
    }

    @Test
    public void testFindItemThatDoesExist() {
        ItemRegistry instance = new ItemRegistry();
        int existingId = 12345;
        try {
            instance.findItem(itemWithIdentifier12345.getIdentifier());
            assertEquals(itemWithIdentifier12345.getIdentifier(), existingId, "Scanned item ID does not match.");
        }   catch (ItemNotFoundException exc) {
                fail("Exception should not be thrown for existing item: " + exc.getMessage());
        }
    }

    @Test
    public void testFindItemDatabaseFailure() throws ItemNotFoundException{
        ItemRegistry instance = new ItemRegistry();
        int databaseFailureId = 55555;
        try {
            instance.findItem(databaseFailureId);
            fail("Database was running, expected ItemRegistryException");
        } catch (ItemRegistryException exc) {
            assertTrue(exc.getMessage().contains("Failed to access the item database"),
                   "Wrong exception message"); 
        }
    }

    @Test
    public void testFindItemDatabaseRunning() throws ItemNotFoundException{
        ItemRegistry instance = new ItemRegistry();
        int existingId = 12345;
        try {
            ItemDTO foundItem = instance.findItem(existingId);
            assertEquals(foundItem.getIdentifier(), existingId, "Database did not find item");
        } catch (ItemRegistryException exc) {
            fail("Exception should not be thrown for working database: " + exc.getMessage());
        }
    }

}