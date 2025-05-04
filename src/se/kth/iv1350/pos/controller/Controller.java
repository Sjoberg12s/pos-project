package se.kth.iv1350.pos.controller;

import se.kth.iv1350.pos.integration.RegistryCreator;
import se.kth.iv1350.pos.integration.DiscountRegistry;
import se.kth.iv1350.pos.integration.ItemRegistry;
import se.kth.iv1350.pos.integration.AccountRegistry;
import se.kth.iv1350.pos.integration.Printer;
import se.kth.iv1350.pos.model.CashRegister;
import se.kth.iv1350.pos.model.Sale;
import se.kth.iv1350.pos.integration.ItemDTO;
import se.kth.iv1350.pos.integration.DiscountDTO;
import se.kth.iv1350.pos.model.SaleDTO;
import se.kth.iv1350.pos.model.Payment;
import se.kth.iv1350.pos.model.Receipt;
/** 
 * This is the application controller. All calls to the model pass through this class
 * which then delegates control to the other layers.
*/
public class Controller {
    private ItemRegistry itemRegistry;
    private DiscountRegistry discountRegistry;
    private AccountRegistry accountRegistry;
    private CashRegister cashRegister;
    private Sale sale;
    private Printer printer;
    /**
     * Creates a new instance, a controller that delegates work to other classes
     * and acts as a layer between view and model/integration.
     */
    public Controller(RegistryCreator creator, Printer printer){
        itemRegistry = creator.getItemRegistry();
        discountRegistry = creator.getDiscountRegistry();
        accountRegistry = creator.getAccountRegistry();
        cashRegister = new CashRegister();
        this.printer = printer;
    }

    /**
     * Starts a new sale. This method myst be called first before doing anything else during
     * the sale.
     */
    public void startSale(){
        this.sale = new Sale();
    }
    /**
     * Retrieves the item from the item registry and adds it to the current sale.
     * @param identifier item identifier.
     * @param quantity quantity of the.
     * @return the item that was scanned.
     */
    public ItemDTO scanItem(int identifier, int quantity){
        ItemDTO scannedItem = itemRegistry.findItem(identifier);
        sale.addItem(scannedItem, quantity);
        return scannedItem;
    }

    /**
     * Retrieves the current running total and sends it back to display information about
     * the total cost.
     * @return the current running total.
     */
    public double retrieveRunningTotal(){
        SaleDTO currentSale = sale.getCurrentSaleState();
        double totalCost = currentSale.getRunningTotal();
        return totalCost;
    }

    /**
     * Retrieves the current running total and sends it back to display information about
     * the total cost.
     * @return the current running total.
     */
    public double retrieveRunningVAT(){
        SaleDTO currentSale = sale.getCurrentSaleState();
        double totalVAT = currentSale.getTotalVAT();
        return totalVAT;
    }


    /**
     * Ends the current sale and returns the total cost.
     * @return the total cost of the sale.
     */
    public double endSale(){
        double totalCost = retrieveRunningTotal();
        return totalCost;
    }

    /**
     * Retrieves the discount from the discount registry and applies it to the current sale.
     * @param identification customer identification.
     * @return the discount that the customer is elegible for.
     */
    public DiscountDTO discountRequest(int idenfication){
        SaleDTO currentSaleInfo = sale.getCurrentSaleState();
        DiscountDTO amountToBeReduced = discountRegistry.findDiscount(idenfication, currentSaleInfo);
        
        return null;
    }

    /**
     * The amount paid gets entered and it sends sale information to different registries and 
     * then prints out the receipt for the current sale.
     * @param amount the amount that the customer paid for the goods.
     */
    public void enterAmountPaid(int amount){
        Payment payment = new Payment(amount);
        sale.calculateChange(payment);
        SaleDTO currentSaleInfo = sale.getCurrentSaleState();
        cashRegister.addPayment(payment);
        accountRegistry.addSaleInformation(payment);
        itemRegistry.addSaleInformation(currentSaleInfo);
        sale.addSaleInfoToReceipt(currentSaleInfo);
        Receipt receipt = sale.getReceipt();
        printer.printReceipt(receipt);
    }
}
