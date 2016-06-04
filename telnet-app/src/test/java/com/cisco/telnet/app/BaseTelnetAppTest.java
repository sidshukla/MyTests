package com.cisco.telnet.app;

import java.io.File;

import com.cisco.telnet.app.command.Command;

public class BaseTelnetAppTest {

  protected static final String userDirectorySystemProperty = "user.dir";
  protected static final String userDirectory = System.getProperty(userDirectorySystemProperty);
  protected static final String connectionId = "connection1";
  protected static final String DIRECTORY_TO_BE_CREATED = "temp";
  protected static final String CREATED_DIRECTORY_PATH = new StringBuilder().append(userDirectory)
      .append(File.separator).append(DIRECTORY_TO_BE_CREATED).toString();

  protected String getOutputString(String currentDirectory, String output) {

    return String.format(Command.COMMAND_RESULT_OUTPUT_PATTERN, currentDirectory, output);
  }

}
