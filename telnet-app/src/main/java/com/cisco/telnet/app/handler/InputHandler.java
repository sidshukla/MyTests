package com.cisco.telnet.app.handler;

import org.springframework.integration.Message;

/**
 * Interface to define input handler for coming requests from client.
 * 
 * @author agautam
 *
 */
public interface InputHandler {
    
    /**
     * Method to handle incoming user requests
     * 
     * @param message
     * @return command output
     */
    String handle(Message<String> message);

}
