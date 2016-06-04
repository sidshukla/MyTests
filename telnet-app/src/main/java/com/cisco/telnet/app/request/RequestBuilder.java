package com.cisco.telnet.app.request;

/**
 * Request builder class that is responsible for parsing the input string and
 * creating a Request object from it
 * 
 * @author agautam
 * 
 */
public interface RequestBuilder {

    /**
     * Method to build request object from a given user input
     * @param input
     * @param connectionId
     * @return Request object
     */
    Request build(String input, String connectionId);

}
