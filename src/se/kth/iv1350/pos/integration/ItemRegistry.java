package se.kth.iv1350.pos.integration;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.pos.model.SaleDTO;

/**
 * ItemRegistry is the item database which is supposed to hold all the information about an item
 * that exists in the inventory.
 */
public class ItemRegistry {
    private SaleDTO saleInformation;
    private List<ItemDTO> itemsInInventory;
    /**
     * Creates a new instance, that is used to get information about items and adds items to the inventory.
     */
    public ItemRegistry(){
        this.itemsInInventory = new ArrayList<>();
        ItemDTO bigWheel = new ItemDTO(12345, "BigWheel Oatmeal 500g, whole grain oats, high fiber, gluten free", "BigWheel Oatmeal", 29.90, 6);
        this.itemsInInventory.add(bigWheel);
        ItemDTO youGoGo = new ItemDTO(54321, "YouGoGo Blueberry 240g, low sugar youghurt, blueberry flavour", "YouGoGo Blueberry", 14.90, 6);
        this.itemsInInventory.add(youGoGo);

    }
    /**
     * Finds the correct item in the item register according to the item identifier, 
     * if not found then null is returned. The item database is identfied as being 
     * turned off if the item identifier 55555 is used.
     * @param identifier the item identifier as a integer.
     * @return the item that corresponds to the identifier
     * @throws ItemNotFoundException If an item is not found in the database.
     * @throws ItemRegistryException If there is a failure to access the item database.
     */
    public ItemDTO findItem(int identifier) throws ItemNotFoundException{
        if (identifier == 55555) {
            throw new ItemRegistryException("Failed to access the item database.");
        }
        ItemDTO scannedItem = null;
        for (ItemDTO e: itemsInInventory){
            if (identifier == e.getIdentifier()) {
                scannedItem = e;
                return scannedItem;
            }
        }
        throw new ItemNotFoundException(identifier);
    }

    /**
     * Stores the sale information in the item registry to be able to update the inventory.
     * @param currentSaleInfo current sale information, such as bought items etc.
     */
    public void addSaleInformation(SaleDTO currentSaleInfo){
        this.saleInformation = currentSaleInfo;
    }

}
