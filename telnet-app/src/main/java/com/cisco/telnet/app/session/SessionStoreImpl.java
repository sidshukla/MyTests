package com.cisco.telnet.app.session;

import java.util.concurrent.ExecutionException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * Implementation for session store interface. This class is responsible for
 * maintaining the state between different requests coming from the users
 * 
 * @author agautam
 * 
 */
@Component
public class SessionStoreImpl implements SessionStore {

    /**
     * Session store cache specification config format
     */
    private static final String CACHE_SPEC_FORMAT = "maximumSize=50,expireAfterWrite=%ss";

    /**
     * Connection expiration time for clients
     */
    @Value("${app.connection.expire.millis}")
    private int connectionExpireTime;

    /**
     * System property from which to get the default directory where the fresh
     * connection from a client will be landing
     */
    @Value("${app.user.home.directory}")
    private String defaultDirectory;

    /**
     * Cache provided by google guava library which automatically takes care of
     * removing entries from cache if entries not accessed for expiration
     * interval from last accessed.
     * 
     * This map also takes care that if key does not exist already then it will
     * be added with value as calculated ie. default user directory path
     */
    private LoadingCache<String, Session> sessions;

    /**
     * Initialization method for Session store
     * 
     * @throws Exception
     */
    @PostConstruct
    public void init() throws Exception {
        sessions = CacheBuilder.from(getCacheSpecs()).build(new CacheLoader<String, Session>() {
            public Session load(String key) {
                return new Session(System.getProperty(defaultDirectory));
            }
        });
    }

    /**
     * Method to update session information for a given connection
     * 
     * @param key
     * @param session
     */
    public void updateSession(String key, Session session) {
        sessions.put(key, session);
    }

    /**
     * Method to return the existing session object for the given connection
     * 
     * @param key
     * @return
     */
    public Session getSession(String key) {

        Session session = null;

        try {
            // this returns default created value in case key is not existing
            // already
            session = sessions.get(key);

        } catch (ExecutionException e) {
            // nothing required to do as no logic in creating default
            // value for non existing keys
        }

        return session;
    }

    /**
     * Method to create cache specs
     * @return
     */
    private String getCacheSpecs() {
        return String.format(CACHE_SPEC_FORMAT, (connectionExpireTime / 1000));
    }

}
