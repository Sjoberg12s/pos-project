package se.kth.iv1350.pos.polymlogger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class FileLogger implements Logger{
    private PrintWriter logStream;

    /**
     * Creates a new instance and also creates a new log file.
     * An existing log file will be deleted.
     */
    public FileLogger(){
        try{
            this.logStream = new PrintWriter(new FileWriter("log.txt"), true);
        } catch(IOException ioe) {
            System.out.println("CAN NOT LOG.");
            ioe.printStackTrace();
        }
    }

    /**
     * The specified string is logged to a log file.
     * @param message the string that will be printed to the log file.
     */
    @Override
    public void log(String message){
        logStream.println(message);
    }
}
