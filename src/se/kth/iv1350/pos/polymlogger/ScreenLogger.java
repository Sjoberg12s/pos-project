package se.kth.iv1350.pos.polymlogger;

public class ScreenLogger implements Logger {

    /**
     * The specified string is logged to the console.
     * @param message the string that will be printed to the console.
     */
    @Override
    public void log(String message){
        System.out.println(message);
    }
}
