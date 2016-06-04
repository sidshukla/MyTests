package com.cisco.sample.handler;

import static junit.framework.Assert.assertEquals;

import java.io.File;

import org.junit.Before;
import org.junit.Test;


public class CommandHandlerTest {
	String currentTestDirctory = "C:/testDirectory";
	
	File testDirectory = null;
	File childDirectory = null;
	
	private CommandHandler testCommandHandler = null;
	
	@Before
	public void setup(){
		testCommandHandler = new CommandHandler(currentTestDirctory);
		
		testDirectory = new File(currentTestDirctory);
		testDirectory.mkdir();
		testDirectory.deleteOnExit();
		
		File childDirectory = new File(currentTestDirctory+"/test");
		childDirectory.mkdir();
		testDirectory.deleteOnExit();
	}
	
	@Test
	public void testWorkingDirectory(){
		String actualOutput = testCommandHandler.processCommand(CommandsEnum.PWD, null);
		assertEquals("Current directory is : C:/testDirectory", actualOutput);
	}
	
	@Test
	public void testListDirectory(){
		String actualOutput = testCommandHandler.processCommand(CommandsEnum.LS, null);
		assertEquals("test", actualOutput.trim());
	}
	
	@Test
	public void testChangeDirectory(){
		String actualOutput = testCommandHandler.processCommand(CommandsEnum.CD, "test");
		assertEquals("Changed the current working directory to test", actualOutput.trim());
	}
	
	@Test
	public void testChangeDirectoryNegative(){
		String actualOutput = testCommandHandler.processCommand(CommandsEnum.CD, "test1");
		assertEquals("Directory test1 does not exist", actualOutput.trim());
	}
}
