package se.kth.iv1350.pos.controller;

/**
 * Thrown when trying to perform an operation in the system, the message
 * can be whatever caused it.
 */
public class OperationFailedException extends Exception{

    /**
     * Creates a new instance representing the condition
     * described in the specified message and what caused it.
     * @param msg the specified message.
     * @param cause what caused the exception.
     */
    public OperationFailedException(String msg, Exception cause){
        super(msg, cause);
    }
}
