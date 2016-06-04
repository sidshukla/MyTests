package com.cisco.telnet.app.request;

import junit.framework.Assert;

import org.junit.Test;

import com.cisco.telnet.app.BaseTelnetAppTest;
import com.cisco.telnet.app.command.CommandEnum;
import com.cisco.telnet.app.session.Session;

public class RequestTest extends BaseTelnetAppTest{

    @Test
    public void testRequestConstructor() {
        
        CommandEnum command = CommandEnum.CD;
        String argument = "temp";
        Session session = new Session(userDirectory);
        String connectionId = "connection1";
        
        Request request = new Request(command, argument, session, connectionId);

        Assert.assertEquals(command, request.getCommand());
        Assert.assertEquals(argument, request.getArgument());
        Assert.assertEquals(session, request.getSession());
        Assert.assertEquals(connectionId, request.getConnectionId());
        
        
    }

}
