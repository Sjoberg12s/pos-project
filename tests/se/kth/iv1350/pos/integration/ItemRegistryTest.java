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
    public void testFindItem() {
        ItemRegistry instance = new ItemRegistry();
        ItemDTO expResult = itemWithIdentifier12345;
        ItemDTO result = instance.findItem(itemWithIdentifier12345.getIdentifier());
        assertEquals(expResult, result, "Item in inventory was not found");
    }

    @Test
    public void testFindItemNotFound() {
        ItemRegistry instance = new ItemRegistry();
        ItemDTO expResult = null;
        ItemDTO result = instance.findItem(99999);
        assertEquals(expResult, result, "Should return null when item is not found");
    }
}