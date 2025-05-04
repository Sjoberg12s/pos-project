package se.kth.iv1350.pos.integration;

/**
 * A class that acts like a item in the store and is only used for get methods to retrieve
 * stuff from that item.
 */
public class ItemDTO {
    private int identifier;
    private String itemDescription;
    private String name;
    private double price;
    private double VAT;

    /**
     * Creates a new instance, an item that exists in the inventory database.
     * @param identifier the item identifier code.
     * @param itemDescription a description of the item.
     * @param name the name of the item.
     * @param price the price of the item.
     * @param VAT the VAT of the item.
     */
    public ItemDTO(int identifier, String itemDescription, String name, double price, double VAT){
        this.identifier = identifier;
        this.itemDescription = itemDescription;
        this.name = name;
        this.price = price;
        this.VAT = VAT;
    }

    /**
     * Retrieves the item identifier.
     * @return the item identifier.
     */
    public int getIdentifier() {
        return identifier;
    }

    /**
     * Retrieves the item description.
     * @return item description.
     */
    public String getItemDescription() {
        return itemDescription;
    }
    /**
     * Retrieves the name of the item.
     * @return the name of the item.
     */
    public String getName() {
        return name;
    }
    /**
     * Retrieves the price of the item.
     * @return the price of the item.
     */
    public double getPrice() {
        return price;
    }
    /**
     * Retrieves the VAT of the item
     * @return the VAT of the item
     */
    public double getVAT() {
        return VAT;
    }

    /**
     * Takes two different items and checks if their identifiers are equal or not
     * @param firstItem
     * @param secondItem
     * @return true if they are equal and false if they are not equal.
     */
    public boolean equalIdentifiers(ItemDTO other){
        if (this.getIdentifier() == other.getIdentifier()) {
            return true;
        }else{
            return false;
        }
    }

    /**
     * Overrides the equals for ItemDTO so it checks the content
     * and not the reference.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ItemDTO itemDTO = (ItemDTO) obj;
        return identifier == itemDTO.identifier &&
            Double.compare(itemDTO.price, price) == 0 &&
            Double.compare(itemDTO.VAT, VAT) == 0 &&
            name.equals(itemDTO.name) &&
            itemDescription.equals(itemDTO.itemDescription);
    }
}
