package se.kth.iv1350.pos.model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Logs the total income from all sales made since the program started.
 */
public class TotalRevenueFileOutput implements SaleObserver{
    double totalRevenueOfAllSales;

    /**
     * Creates a new instance, with the total Revenue
     * set to zero.
     */
    public TotalRevenueFileOutput() throws IOException{
        totalRevenueOfAllSales = 0;
    }

    @Override
    public void newSale(SaleDTO paidSale) {
        addNewSale(paidSale);
        logCurrentState(totalRevenueOfAllSales);
    }

    private void addNewSale(SaleDTO paidSale) {
        totalRevenueOfAllSales += paidSale.getRunningTotal();
    }

    private void logCurrentState(double totalRevenueOfAllSales) {
        try (PrintWriter logStream = new PrintWriter(new FileWriter("sale-income-log.txt", false))) {
            logStream.printf("Total income from sales: %.2f SEK%n", totalRevenueOfAllSales);
        } catch (IOException ioe) {
            System.out.println("CAN NOT LOG.");
            ioe.printStackTrace();
        }
    }
}
