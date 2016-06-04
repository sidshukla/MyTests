package com.cisco.telnet.app.request;

/**
 * Interface that allows to send commands to running server port
 * 
 * @author agautam
 *
 */
public interface RequestGateway {

    /**
     * Method to execute text entered on console ]
     * 
     * @param text
     * @return
     */
	public String execute(String text);

}