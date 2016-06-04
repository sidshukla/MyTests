package com.cisco.telnet.app.session;

/**
 * Interface to define session storing class.
 * 
 * @author agautam
 *
 */
public interface SessionStore {
    
    /**
     * Method to update session information for a given connection
     * @param key
     * @param session
     */
    void updateSession(String key, Session session);
    
    /**
     * Method to return the existing session object for the given connection
     * @param key
     * @return
     */
    Session getSession(String key);

}
