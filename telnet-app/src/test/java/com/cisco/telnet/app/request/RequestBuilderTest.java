package com.cisco.telnet.app.request;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cisco.telnet.app.BaseTelnetAppTest;
import com.cisco.telnet.app.command.CommandEnum;
import com.cisco.telnet.app.exception.CommandException;
import com.cisco.telnet.app.session.Session;
import com.cisco.telnet.app.session.SessionStore;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:testAppContext-RequestBuilder.xml" })
public class RequestBuilderTest extends BaseTelnetAppTest {
        
    @Value("${app.request.builder.not.able.to.process.command.output.format}")
    private  String requestBuilderNotAbleToProcessCommandFormat;
    
    @Value("${app.request.builder.command.not.found.output.format}")
    private  String requestBuilderCommandNotFoundOutputFormat;

    @Mock
    private SessionStore sessionStore;

    @Autowired
    @InjectMocks
    private RequestBuilder requestBuilder;
    
    @Before
    public void setup() {
            MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testBuildWithConnectionIdNull() {

        String input = "cd temp";

        String message = getCommandExceptionMessage(input, null);

        Assert.assertEquals(String.format(requestBuilderNotAbleToProcessCommandFormat, input), message);
        
        when(sessionStore.getSession("")).thenReturn(new Session("2"));
        verifyZeroInteractions(sessionStore);

    }

    @Test
    public void testBuildWithConnectionIdBlank() {
        String input = "cd temp";

        String message = getCommandExceptionMessage(input, "");

        Assert.assertEquals(String.format(requestBuilderNotAbleToProcessCommandFormat, input), message);
        
        verifyZeroInteractions(sessionStore);
    }

    @Test
    public void testBuildWithInvalidCommand() {
        String input = "ca temp";

        String message = getCommandExceptionMessage(input, connectionId);

        Assert.assertEquals(String.format(requestBuilderCommandNotFoundOutputFormat, "ca"), message);
        
        verifyZeroInteractions(sessionStore);
    }
    
    @Test
    public void testBuildWithInputNull() {
        
        Assert.assertNull(requestBuilder.build(null, connectionId));
        
        verifyZeroInteractions(sessionStore);
    }
    
    @Test
    public void testBuildWithInputBlank() {
        
        Assert.assertNull(requestBuilder.build("", connectionId));
        
        verifyZeroInteractions(sessionStore);
    }
    
    @Test
    public void testBuildWithCdCommandInput() {
        
        String input = "cd temp";
        
        when(sessionStore.getSession(connectionId)).thenReturn(new Session(userDirectory));
        
        Request request = requestBuilder.build(input, connectionId);
        
        Assert.assertNotNull(request);
        Assert.assertEquals(CommandEnum.CD, request.getCommand());
        Assert.assertEquals("temp", request.getArgument());
        Assert.assertEquals(userDirectory, request.getSession().getCurrentDirectory());
        Assert.assertEquals(connectionId, request.getConnectionId());
        
        verify(sessionStore).getSession(connectionId);
    }
    
    @Test
    public void testBuildWithPwdCommandInput() {
        
        String input = "pwd";
        
        when(sessionStore.getSession(connectionId)).thenReturn(new Session(userDirectory));
        
        Request request = requestBuilder.build(input, connectionId);
        
        Assert.assertNotNull(request);
        Assert.assertEquals(CommandEnum.PWD, request.getCommand());
        Assert.assertEquals("", request.getArgument());
        Assert.assertEquals(userDirectory, request.getSession().getCurrentDirectory());
        Assert.assertEquals(connectionId, request.getConnectionId());
        
        verify(sessionStore).getSession(connectionId);
    }
    
    @Test
    public void testBuildWithMkdrCommandInput() {
        
        String input = "mkdir temp";
        
        when(sessionStore.getSession(connectionId)).thenReturn(new Session(userDirectory));
        
        Request request = requestBuilder.build(input, connectionId);
        
        Assert.assertNotNull(request);
        Assert.assertEquals(CommandEnum.MKDIR, request.getCommand());
        Assert.assertEquals("temp", request.getArgument());
        Assert.assertEquals(userDirectory, request.getSession().getCurrentDirectory());
        Assert.assertEquals(connectionId, request.getConnectionId());
        
        verify(sessionStore).getSession(connectionId);
    }
    
    @Test
    public void testBuildWithLsCommandInput() {
        
        String input = "ls temp";
        
        when(sessionStore.getSession(connectionId)).thenReturn(new Session(userDirectory));
        
        Request request = requestBuilder.build(input, connectionId);
        
        Assert.assertNotNull(request);
        Assert.assertEquals(CommandEnum.LS, request.getCommand());
        Assert.assertEquals("temp", request.getArgument());
        Assert.assertEquals(userDirectory, request.getSession().getCurrentDirectory());
        Assert.assertEquals(connectionId, request.getConnectionId());
        
        verify(sessionStore).getSession(connectionId);
    }
    
    

    private String getCommandExceptionMessage(String input, String connection) {

        String commandExceptionMessage = null;

        try {
            requestBuilder.build(input, connection);
        } catch (CommandException ce) {
            commandExceptionMessage = ce.getMessage();
        }

        return commandExceptionMessage;

    }

}
