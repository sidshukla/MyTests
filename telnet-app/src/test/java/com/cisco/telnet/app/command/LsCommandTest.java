package com.cisco.telnet.app.command;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.reflect.Whitebox;

import com.cisco.telnet.app.BaseTelnetAppTest;
import com.cisco.telnet.app.exception.CommandException;
import com.cisco.telnet.app.request.Request;
import com.cisco.telnet.app.session.Session;

@RunWith(MockitoJUnitRunner.class)
public class LsCommandTest  extends BaseTelnetAppTest {
  
  private static final String INVALID_ARGUMENT_MESSSAGE = "invalid input argument for listing command";
  private Command lsCommand;
  
  @Mock
  private Request request;
  
  @Mock
  private Session session;
  
  
  @Before
  public void setup() throws Exception {
    lsCommand = new LsCommand();
    Whitebox.setInternalState(lsCommand, "commandListingInvalidArgumentOutputFormat", INVALID_ARGUMENT_MESSSAGE);     
  }

  @Test
  public void testLsWithArguments() {
    String argument = "temp";
    when(request.getArgument()).thenReturn(argument);
    
    String exceptionMessage = "";
    try{
    lsCommand.execute(request);
    }catch(CommandException ce){
      exceptionMessage = ce.getMessage();
    }
    
    Assert.assertEquals(INVALID_ARGUMENT_MESSSAGE, exceptionMessage);
    
    verify(request, times(2)).getArgument();
    verifyZeroInteractions(session);
    
  }
  
  @Test
  public void testListingCommand() {
    String argument = "";
    when(request.getArgument()).thenReturn(argument);
    when(request.getSession()).thenReturn(session);
    when(session.getCurrentDirectory()).thenReturn(userDirectory);
    
    String output = lsCommand.execute(request);
   
    Assert.assertTrue(output.contains("pom.xml"));
    Assert.assertTrue(output.contains("src"));
    
    verify(request).getArgument();
    verify(request, atLeastOnce()).getSession();
    verify(session, atLeastOnce()).getCurrentDirectory();
    
  }

}
