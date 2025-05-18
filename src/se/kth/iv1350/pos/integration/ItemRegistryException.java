package se.kth.iv1350.pos.integration;

public class ItemRegistryException extends RuntimeException{

    /**
     * Creates a new instance representing the condition
     * described in the specified message
     * @param msg the message that describes the failure.
     */
    public ItemRegistryException(String msg){
        super(msg);
    }
}
