package se.kth.iv1350.pos.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemDTOTest {
    private ItemDTO itemWithIdentifierTwo;

    @BeforeEach
    public void setUp() {
        itemWithIdentifierTwo = new ItemDTO(2, null, null, 0, 0);
    }

    @AfterEach
    public void tearDown() {
        itemWithIdentifierTwo = null;
    }

    @Test
    public void testNotEqualIdentifiers() {
        int amountOfOther = 1;
        ItemDTO other = new ItemDTO(amountOfOther, null, null, 0, 0);
        boolean expResult = false;
        boolean result = itemWithIdentifierTwo.equalIdentifiers(other);
        assertEquals(expResult, result, "Item identifiers are equal.");
    }

    @Test
    public void testEqualIdentifiers() {
        int amountOfOther = 2;
        ItemDTO other = new ItemDTO(amountOfOther, null, null, 0, 0);
        boolean expResult = true;
        boolean result = itemWithIdentifierTwo.equalIdentifiers(other);
        assertEquals(expResult, result, "Item identifiers are not equal.");
    }
}
