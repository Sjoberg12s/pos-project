package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.model.SaleObserver;
import se.kth.iv1350.pos.model.SaleDTO;

/**
 * Prints the total income from all sales made since the program started.
 */
public class TotalRevenueView implements SaleObserver {
    double totalRevenueOfAllSales;

    /**
     * Creates a new instance, with the total Revenue
     * set to zero.
     */
    public TotalRevenueView() {
        totalRevenueOfAllSales = 0;
    }

    @Override
    public void newSale(SaleDTO paidSale) {
        addNewSale(paidSale);
        printCurrentState(totalRevenueOfAllSales);
    }

    private void addNewSale(SaleDTO paidSale) {
        totalRevenueOfAllSales += paidSale.getRunningTotal();
    }

    private void printCurrentState(double totalRevenueOfAllSales) {
        System.out.println("#### Total amount from all sales ####");
        System.out.printf("%.2f SEK%n", totalRevenueOfAllSales);
        System.out.println("#####################################");
    }
}
