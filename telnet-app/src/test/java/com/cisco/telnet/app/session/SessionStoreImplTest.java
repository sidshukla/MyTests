package com.cisco.telnet.app.session;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.reflect.Whitebox;

import com.cisco.telnet.app.BaseTelnetAppTest;

@RunWith(MockitoJUnitRunner.class)
public class SessionStoreImplTest extends BaseTelnetAppTest {

    private static final int CONNECTION_EXPIRE_TIME = 2000;
    private SessionStore sessionStore;
    

    @Before
    public void setup() throws Exception {
        sessionStore = new SessionStoreImpl();
        
        Whitebox.setInternalState(sessionStore, "connectionExpireTime", CONNECTION_EXPIRE_TIME);
        Whitebox.setInternalState(sessionStore, "defaultDirectory", userDirectorySystemProperty);

        Whitebox.invokeMethod(sessionStore, "init");
    }

    @Test
    public void testSetup() throws Exception {

        Assert.assertEquals(CONNECTION_EXPIRE_TIME, Whitebox.getInternalState(sessionStore, "connectionExpireTime"));
        Assert.assertEquals(userDirectorySystemProperty, Whitebox.getInternalState(sessionStore, "defaultDirectory"));
        Assert.assertNotNull(Whitebox.getInternalState(sessionStore, "sessions"));
        
    }

    @Test
    public void testGetSession() throws Exception {

        Assert.assertEquals(userDirectory, sessionStore.getSession("key1").getCurrentDirectory());
        
    }
    
    @Test
    public void testUpdateSession() throws Exception {
        
        String changedDirectoryValue = userDirectory + "/temp";
        
        sessionStore.updateSession("key1", new Session(userDirectory));       

        Assert.assertEquals(userDirectory, sessionStore.getSession("key1").getCurrentDirectory());
        
        sessionStore.updateSession("key1", new Session(changedDirectoryValue));
        
        Assert.assertEquals(changedDirectoryValue, sessionStore.getSession("key1").getCurrentDirectory());
        
    }
    
    @Test
    public void testSessionExpireInStore() throws Exception {
        
        String changedDirectoryValue = userDirectory + "/temp";
        
        sessionStore.updateSession("key1", new Session(changedDirectoryValue));       
        
        Thread.sleep(CONNECTION_EXPIRE_TIME + 10);
               
        Assert.assertEquals(userDirectory, sessionStore.getSession("key1").getCurrentDirectory());
        
    }

}
