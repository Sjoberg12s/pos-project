package se.kth.iv1350.pos.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemRegistryTest {
    private ItemDTO itemWithIdentifier12345;

    @BeforeEach
    public void setUp() {
       itemWithIdentifier12345 = new ItemDTO(12345, null, null, 0, 0);
    }

    @AfterEach
    public void tearDown() {
        itemWithIdentifier12345 = null;
    }
    @Test
    public void testFindItem() {
        ItemRegistry instance = new ItemRegistry();
        ItemDTO expResult = null;
        ItemDTO result = instance.findItem(itemWithIdentifier12345.getIdentifier());
        assertEquals(expResult, result, "Item in inventory was found");


    }
}