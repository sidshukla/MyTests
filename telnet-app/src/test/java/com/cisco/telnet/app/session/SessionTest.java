package com.cisco.telnet.app.session;

import junit.framework.Assert;

import org.junit.Test;

import com.cisco.telnet.app.BaseTelnetAppTest;

public class SessionTest extends BaseTelnetAppTest {

    @Test
    public void testSessionConstructor() {
        
        String currentDirectory = "/current/directory";
        Session session = new Session(currentDirectory);
        
        Assert.assertEquals(currentDirectory, session.getCurrentDirectory());
        
    }

}
