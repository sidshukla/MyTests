package com.cisco.telnet.app;

import java.io.File;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cisco.telnet.app.request.RequestGateway;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/telnetApp-context.xml" })
public class TelnetAppIT extends BaseTelnetAppTest {
  
  @Value("${command.listing.invalid.argument.output.format}")
  private String commandListingInvalidArgumentOutputFormat;
  
  @Value("${command.change.directory.no.change.output}")
  private String changeDirectoryNoChangeOutput;
  
  @Value("${command.mkdir.need.directory.name.output}")
  private  String commandMkdrNeedDirectoryNameOutput;
  

  @Autowired
  private RequestGateway requestGateway;
  
  @Test
  public void testCommandPwd() {

    String result = requestGateway.execute("pwd");    
    Assert.assertTrue(result.contains(userDirectory));
    
  }
  
  @Test
  public void testListingCommand() {
    
    String result = requestGateway.execute("ls");   
   
    Assert.assertTrue(result.contains("pom.xml"));
    Assert.assertTrue(result.contains("src"));        
  }
  
  @Test
  public void testCdWithNoDirectoryGiven() {
      
    String result = requestGateway.execute("cd");   
      
     Assert.assertTrue(result.contains(changeDirectoryNoChangeOutput));
      
  }
  
  @Test
  public void testChangeDirectoryToCurrentDirectoryItself() {
      
    String result = requestGateway.execute("cd .");   
      
     Assert.assertTrue(result.contains(changeDirectoryNoChangeOutput));
      
  }
  
  @Test
  public void testToChangeDirectoryToNextDirectory() {
    
    String newDirectoryPath = userDirectory + File.separator + "src";
      
    String result = requestGateway.execute("cd src");   
      
     Assert.assertTrue(result.contains(newDirectoryPath));
      
  }
  
  @Test
  public void testToChangeDirectoryToParentDirectory() {
    
    requestGateway.execute("cd src");   
      
    String result = requestGateway.execute("cd ..");   
      
     Assert.assertTrue(result.contains(userDirectory));
      
  }
  
  @Test
  public void testToChangeDirectoryWithAbsolutePath() {
    
    String newDirectoryPath = userDirectory + File.separator + "src";
    
    String result = requestGateway.execute("cd " + newDirectoryPath);         
      
     Assert.assertTrue(result.contains(newDirectoryPath));
     
     //changing directory back to original
     requestGateway.execute("cd .."); 
     
      
  }
  
  @Test
  public void testMakeDirectoryWithNoArguments() {
    
    String result = requestGateway.execute("mkdir");       
    
    Assert.assertTrue(result.contains(commandMkdrNeedDirectoryNameOutput));
    
  }
  
  @Test
  public void testMakeDirectorySuccessfully() {
    
    //making sure directory does not exist already
    new File(CREATED_DIRECTORY_PATH).delete();
    
    String result = requestGateway.execute("mkdir " + DIRECTORY_TO_BE_CREATED);       
    
    Assert.assertTrue(new File(CREATED_DIRECTORY_PATH).exists());
    
    Assert.assertTrue(result.contains(DIRECTORY_TO_BE_CREATED));
    
    //cleaning up the file
    new File(CREATED_DIRECTORY_PATH).delete();
    
  }
  
}
