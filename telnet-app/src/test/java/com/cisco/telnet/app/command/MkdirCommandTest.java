package com.cisco.telnet.app.command;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.io.File;

import junit.framework.Assert;

import org.junit.After;
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
public class MkdirCommandTest extends BaseTelnetAppTest {

  private static final String NO_ARGUMENT_ERROR_MESSAGE = "need directory name to be created";
  private static final String SUCCESS_MESSAGE = "directory created successfully";
  private static final String FAILURE_MESSAGE = "not able to create directory";


  private Command mkdirCommand;

  @Mock
  private Request request;

  @Mock
  private Session session;

  @Before
  public void setup() throws Exception {

    mkdirCommand = new MkdirCommand();
    Whitebox.setInternalState(mkdirCommand, "commandMkdrNeedDirectoryNameOutput",
        NO_ARGUMENT_ERROR_MESSAGE);
    Whitebox.setInternalState(mkdirCommand, "commandMkdrSuccessOutputFormat", SUCCESS_MESSAGE);
    Whitebox.setInternalState(mkdirCommand, "commandMkdrFailureOutputFormat", FAILURE_MESSAGE);
    
    new File(CREATED_DIRECTORY_PATH).delete();
  }
  
  @After
  public void clean() throws Exception {

    new File(CREATED_DIRECTORY_PATH).delete();
    
  }

  @Test
  public void testMakeDirectoryWithNoArguments() {

    when(request.getArgument()).thenReturn("");

    String exceptionMessage = "";
    try {
      mkdirCommand.execute(request);
    } catch (CommandException ce) {
      exceptionMessage = ce.getMessage();
    }

    Assert.assertEquals(NO_ARGUMENT_ERROR_MESSAGE, exceptionMessage);

    verify(request).getArgument();
    verifyZeroInteractions(session);

  }

  @Test
  public void testMakeDirectorySuccessfully() {

    when(request.getArgument()).thenReturn(DIRECTORY_TO_BE_CREATED);

    when(request.getSession()).thenReturn(session);
    when(session.getCurrentDirectory()).thenReturn(userDirectory);

    String output = mkdirCommand.execute(request);

    Assert.assertTrue(new File(CREATED_DIRECTORY_PATH).exists());
    Assert.assertTrue(output.contains(SUCCESS_MESSAGE));

    verify(request, atLeastOnce()).getArgument();
    verify(request, atLeastOnce()).getSession();
    verify(session, atLeastOnce()).getCurrentDirectory();

  }
  
  @Test
  public void testMakeDirectoryFailure() {

    when(request.getArgument()).thenReturn(DIRECTORY_TO_BE_CREATED);

    when(request.getSession()).thenReturn(session);
    when(session.getCurrentDirectory()).thenReturn(userDirectory);

    //creating directory in advance
    boolean success = (new File(CREATED_DIRECTORY_PATH)).mkdir();
    
    if(success){
      
      
      String exceptionMessage = null;
      try{
      mkdirCommand.execute(request);
      }catch(CommandException e){
        exceptionMessage = e.getMessage();
      }
      
      Assert.assertTrue(exceptionMessage.contains(FAILURE_MESSAGE));

      verify(request, atLeastOnce()).getArgument();
      verify(request, atLeastOnce()).getSession();
      verify(session, atLeastOnce()).getCurrentDirectory();
      
    }else{
      Assert.fail("not able to create directory to test failure scenario");
    }
    
  }

}
