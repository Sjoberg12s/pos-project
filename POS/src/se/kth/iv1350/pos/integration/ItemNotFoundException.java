package se.kth.iv1350.pos.integration;

/**
 * Thrown when trying to find an item in the database that 
 * does not exist.
 */
public class ItemNotFoundException extends Exception {
    private int itemIdentifierThatCanNotBeFound;

    /**
     * Creates a new instance representing that the item with 
     * a specific identifier could not be found.
     * @param itemIdentifierThatCanNotBeFound the identifier that 
     * could not be found.
     */
    public ItemNotFoundException(int itemIdentifierThatCanNotBeFound){
        super("Item with identifier " + 
        itemIdentifierThatCanNotBeFound + 
        " does not exist in the inventory catalog");
        this.itemIdentifierThatCanNotBeFound = itemIdentifierThatCanNotBeFound;
    }

    /**
     * Retrieves the item that can not be found.
     * @return the item that can not be found.
     */
    public int getItemIdentifierThatCanNotBeFound() {
        return itemIdentifierThatCanNotBeFound;
    }
}
