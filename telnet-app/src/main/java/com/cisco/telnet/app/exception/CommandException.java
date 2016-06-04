package com.cisco.telnet.app.exception;

/**
 * Command Exception class to specify error details during execution of a given command
 * 
 * @author agautam
 *
 */
public class CommandException extends RuntimeException{
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 3375364131929131510L;

    /**
     * Default constructor
     */
    public CommandException(){
        super();
    }
    
    /**
     * Constructor expecting exception detail message
     * @param message
     */
    public CommandException(String message){
        super(message);
    }
    
    /**
     * Constructor expecting exception details message and its cause
     * @param message
     * @param throwable
     */
    public CommandException(String message, Throwable throwable){
        super(message, throwable);
    }

}
