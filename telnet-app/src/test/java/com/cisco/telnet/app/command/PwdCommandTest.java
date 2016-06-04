package com.cisco.telnet.app.command;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.cisco.telnet.app.BaseTelnetAppTest;
import com.cisco.telnet.app.request.Request;
import com.cisco.telnet.app.session.Session;

@RunWith(MockitoJUnitRunner.class)
public class PwdCommandTest extends BaseTelnetAppTest {
    
    @Mock
    private Request request;
    
    @Mock
    private Session session;
    
    private Command command = new PwdCommand();

    @Test
    public void testExecute() {        
        
        when(request.getSession()).thenReturn(session);
        when(session.getCurrentDirectory()).thenReturn(userDirectory);
        
        String output = command.execute(request);
        
        Assert.assertTrue(output.contains(userDirectory));
        verify(request).getSession();
        verify(session).getCurrentDirectory();       

    }

}
