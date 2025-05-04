package se.kth.iv1350.pos.startup;

import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.integration.RegistryCreator;
import se.kth.iv1350.pos.integration.Printer;
import se.kth.iv1350.pos.view.View;

/**
 * Starts the entire application. Contains the main method used to start the application
 */
public class Main {
    /**
    * The main method used to start the application
    * 
    * @param args The application does not take any command line parameters.
    */
    public static void main(String[] args) {
        RegistryCreator creator = new RegistryCreator();
        Printer printer = new Printer();
        Controller contr = new Controller(creator, printer);
        View view = new View(contr);
        view.runFakeExecution();
    }
}
