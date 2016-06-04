package com.cisco.telnet.app.command;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cisco.telnet.app.BaseTelnetAppTest;
import com.cisco.telnet.app.exception.CommandException;
import com.cisco.telnet.app.request.Request;
import com.cisco.telnet.app.session.Session;
import com.cisco.telnet.app.session.SessionStore;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:testAppContext-CdCommand.xml" })
public class CdCommandTest extends BaseTelnetAppTest {

    @Mock
    private SessionStore sessionStore;

    @Value("${command.change.directory.failure.output.format}")
    private String changeDirectoryFailureOutputFormat;

    @Value("${command.change.directory.already.root.output}")
    private String changeDirectoryAlreadyRootOutput;
    
    @Value("${command.change.directory.no.change.output}")
    private String changeDirectoryNoChangeOutput;

    private static final String currentDirectoryArg = ".";
    private static final String parentDirectoryArg = "..";
    
    @Mock
    private Request request;
    
    @Mock
    private Session session;

    @Autowired
    @Qualifier("cdCommand")
    @InjectMocks
    private Command cdCommand;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testToChangeDirectoryWithNoDirectoryGiven() {
        when(request.getArgument()).thenReturn(""); // no directory argument
        when(request.getSession()).thenReturn(session);
        when(session.getCurrentDirectory()).thenReturn(userDirectory);
        
        String output = cdCommand.execute(request);
        
        String expected = getOutputString("", changeDirectoryNoChangeOutput);
        
        Assert.assertEquals(expected, output);
        verify(request).getArgument();
        
    }
    
    @Test
    public void testToChangeDirectoryToCurrentItself() {
        when(request.getArgument()).thenReturn(currentDirectoryArg); 
        when(request.getSession()).thenReturn(session);
        when(session.getCurrentDirectory()).thenReturn(userDirectory);
        
        String output = cdCommand.execute(request);
        
        String expected = getOutputString("", changeDirectoryNoChangeOutput);
        
        Assert.assertEquals(expected, output);
        verify(request, atLeastOnce()).getArgument();
        
    }
    
    @Test
    public void testToChangeDirectoryToParentDirectory() {
        
        String currentDirectory = userDirectory + File.separator + "src";
        Session newSession = new Session(userDirectory);
        
        when(request.getArgument()).thenReturn(parentDirectoryArg); 
        when(request.getSession()).thenReturn(session);
        when(request.getConnectionId()).thenReturn(connectionId);
        when(session.getCurrentDirectory()).thenReturn(currentDirectory);
        doNothing().when(sessionStore).updateSession(connectionId, newSession);
        
        String output = cdCommand.execute(request);
        
        Assert.assertTrue(output.contains(userDirectory));
        verify(request, atLeastOnce()).getArgument();
        verify(session).getCurrentDirectory();
        verify(sessionStore).updateSession(connectionId, newSession);
        
    }
    
    @Test
    public void testToChangeDirectoryWithAbsolutePath() {
        
        String absoluteDirectoryPath = userDirectory + File.separator + "src";
        Session newSession = new Session(absoluteDirectoryPath);
        
        when(request.getArgument()).thenReturn(absoluteDirectoryPath); 
        when(request.getSession()).thenReturn(session);
        when(request.getConnectionId()).thenReturn(connectionId);
        when(session.getCurrentDirectory()).thenReturn(userDirectory);
        doNothing().when(sessionStore).updateSession(connectionId, newSession);
        
        String output = cdCommand.execute(request);
        
        Assert.assertTrue(output.contains(userDirectory));
        verify(request, atLeastOnce()).getArgument();
        verify(session).getCurrentDirectory();
        verify(sessionStore).updateSession(connectionId, newSession);
        
    }
    
    @Test
    public void testToChangeDirectoryToNextDirectory() {
        
        String newDirectoryPath = userDirectory + File.separator + "src";
        Session newSession = new Session(newDirectoryPath);
        
        when(request.getArgument()).thenReturn("src"); 
        when(request.getSession()).thenReturn(session);
        when(request.getConnectionId()).thenReturn(connectionId);
        when(session.getCurrentDirectory()).thenReturn(userDirectory);
        doNothing().when(sessionStore).updateSession(connectionId, newSession);
        
        String output = cdCommand.execute(request);
        
        Assert.assertTrue(output.contains(newDirectoryPath));
        verify(request, atLeastOnce()).getArgument();
        verify(session).getCurrentDirectory();
        verify(sessionStore).updateSession(connectionId, newSession);
        
    }
    
    @Test
    public void testToChangeDirectoryToParentWhenAlreadyAtRoot() {        
        
        when(request.getArgument()).thenReturn(parentDirectoryArg); 
        when(request.getSession()).thenReturn(session);
        when(request.getConnectionId()).thenReturn(connectionId);
        when(session.getCurrentDirectory()).thenReturn(getRootDirectory()); // setting current directory to root
        
        String exceptionMessage = null;
        try{
            cdCommand.execute(request);
        }catch(CommandException ce){
            exceptionMessage = ce.getMessage();
        }
        
        Assert.assertEquals(changeDirectoryAlreadyRootOutput, exceptionMessage);
        verify(request, atLeastOnce()).getArgument();
        verify(session).getCurrentDirectory();
        
    }
    
    @Test
    public void testToChangeDirectoryFilePath() {        
        
        String requestedPath = userDirectory + File.pathSeparator + "pom.xml";
        
        when(request.getArgument()).thenReturn(requestedPath); 
        when(request.getSession()).thenReturn(session);
        when(request.getConnectionId()).thenReturn(connectionId);
        when(session.getCurrentDirectory()).thenReturn(userDirectory); // setting current directory to root
        
        
        String expectedMessage = String.format(changeDirectoryFailureOutputFormat, requestedPath);
        String exceptionMessage = null;
        try{
            cdCommand.execute(request);
        }catch(CommandException ce){
            exceptionMessage = ce.getMessage();
        }
        
        Assert.assertEquals(expectedMessage, exceptionMessage);
        verify(request, atLeastOnce()).getArgument();
        verify(session).getCurrentDirectory();
        
    }
    
    @Test
    public void testToChangeDirectoryToInvalidPath() {        
        
        String requestedPath = userDirectory + File.pathSeparator + "abc";
        
        when(request.getArgument()).thenReturn(requestedPath); 
        when(request.getSession()).thenReturn(session);
        when(request.getConnectionId()).thenReturn(connectionId);
        when(session.getCurrentDirectory()).thenReturn(userDirectory); // setting current directory to root
        
        
        String expectedMessage = String.format(changeDirectoryFailureOutputFormat, requestedPath);
        String exceptionMessage = null;
        try{
            cdCommand.execute(request);
        }catch(CommandException ce){
            exceptionMessage = ce.getMessage();
        }
        
        Assert.assertEquals(expectedMessage, exceptionMessage);
        verify(request, atLeastOnce()).getArgument();
        verify(session).getCurrentDirectory();
        
    }
    
    private String getRootDirectory(){
        String directory = System.getProperty("user.home");
        File f = null;
        while(true){
            f = new File(directory);
            if(f.getParent() != null){
                directory = f.getParent();
                
            }else{
                break;
            }
        }
        return f.getPath();
    }
    
    

}
