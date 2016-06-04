package com.cisco.telnet.app.handler;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import org.springframework.integration.Message;
import org.springframework.integration.MessageHeaders;

import com.cisco.telnet.app.BaseTelnetAppTest;
import com.cisco.telnet.app.command.Command;
import com.cisco.telnet.app.command.CommandEnum;
import com.cisco.telnet.app.exception.CommandException;
import com.cisco.telnet.app.request.Request;
import com.cisco.telnet.app.request.RequestBuilder;

@RunWith(PowerMockRunner.class)
@PrepareForTest({MessageHeaders.class, Request.class})
public class InputHandlerImplTest extends BaseTelnetAppTest {   

    @Mock
    private RequestBuilder requestBuilder;
    
    @Mock
    private Message<String> message;
    
    @Mock
    private MessageHeaders messageHeaders;
    
    @Mock
    private Request request;
    
    @Mock
    private Command command;

    @Mock
    private Map<CommandEnum, Command> commandMap;
    
    @InjectMocks
    InputHandler inputHandler = new InputHandlerImpl();

    @Test
    public void testHandlerForEmptyInput() {
        
        when(requestBuilder.build(anyString(), anyString())).thenReturn(null);
        when(message.getPayload()).thenReturn("");
        when(message.getHeaders()).thenReturn(messageHeaders);
        when(messageHeaders.getCorrelationId()).thenReturn(connectionId);
        
        Assert.assertEquals("", inputHandler.handle(message));
        
        verify(requestBuilder).build("", connectionId);
        verify(message).getPayload();
        verify(message).getHeaders();
        
        verifyZeroInteractions(commandMap);
        
    }
    
    @Test
    public void testValidRequestInput() {
        
        String output = "test output abc";
        
        Whitebox.setInternalState(inputHandler, "commandMap", commandMap);
        
        when(requestBuilder.build(anyString(), anyString())).thenReturn(request);
        when(message.getPayload()).thenReturn("");
        when(message.getHeaders()).thenReturn(messageHeaders);
        when(messageHeaders.getCorrelationId()).thenReturn(connectionId);
        when(request.getCommand()).thenReturn(CommandEnum.CD);
        when(commandMap.get(any(CommandEnum.class))).thenReturn(command);
        when(command.execute(request)).thenReturn(output);
        
        
        Assert.assertEquals(output, inputHandler.handle(message));
        
        verify(requestBuilder).build("", connectionId);
        verify(message).getPayload();
        verify(message).getHeaders();
        verify(messageHeaders).getCorrelationId();
        verify(request).getCommand();
        verify(commandMap).get(any(CommandEnum.class));
        verify(command).execute(request);
        
        
    }
    
    @Test
    public void testCommandExceptionDuringHandling() {
        String exceptionMessage = "test error";
        
        Whitebox.setInternalState(inputHandler, "commandMap", commandMap);
        
        when(requestBuilder.build(anyString(), anyString())).thenReturn(request);
        when(message.getPayload()).thenReturn("");
        when(message.getHeaders()).thenReturn(messageHeaders);
        when(messageHeaders.getCorrelationId()).thenReturn(connectionId);
        when(request.getCommand()).thenReturn(CommandEnum.CD);
        when(commandMap.get(any(CommandEnum.class))).thenReturn(command);
        when(command.execute(request)).thenThrow(new CommandException(exceptionMessage));
        
        
        Assert.assertTrue(inputHandler.handle(message).endsWith(exceptionMessage));
        
        verify(requestBuilder).build("", connectionId);
        verify(message).getPayload();
        verify(message).getHeaders();
        verify(messageHeaders).getCorrelationId();
        verify(request).getCommand();
        verify(commandMap).get(any(CommandEnum.class));
        verify(command).execute(request);
        
        
    }

}
