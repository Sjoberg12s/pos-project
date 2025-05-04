package se.kth.iv1350.pos.integration;
/**
 * This creates all of the different registries that are going to be used in the 
 * application.
 */
public class RegistryCreator {
    private ItemRegistry itemRegistry;
    private DiscountRegistry discountRegistry;
    private AccountRegistry accountRegistry;
    /**
     * Creates a new instance, that creates all the different registries.
     */
    public RegistryCreator(){
        this.itemRegistry = new ItemRegistry();
        this.discountRegistry = new DiscountRegistry();
        this.accountRegistry = new AccountRegistry();
    }

    /**
     * Gets the account registry of the current instance.
     * @return current account registry.
     */
    public AccountRegistry getAccountRegistry() {
        return this.accountRegistry;
    }

    /**
     * Gets the discount registry of the current instance.
     * @return current discount registry
     */
    public DiscountRegistry getDiscountRegistry() {
        return this.discountRegistry;
    }

    /**
     * Gets the item registry of the current instance.
     * @return current item registry.
     */
    public ItemRegistry getItemRegistry() {
        return this.itemRegistry;
    }
    
}
