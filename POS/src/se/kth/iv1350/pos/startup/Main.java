package se.kth.iv1350.pos.startup;

import java.io.IOException;

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
    * @throws IOException throws IOException for the constructor of Controller because
    * of LogHandler in Controller. 
    */
    public static void main(String[] args) throws IOException {
        RegistryCreator creator = new RegistryCreator();
        Printer printer = new Printer();
        Controller contr = new Controller(creator, printer);
        View view = new View(contr);
        view.runFakeExecution();
    }
}
