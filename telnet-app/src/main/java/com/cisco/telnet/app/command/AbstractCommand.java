package com.cisco.telnet.app.command;

/**
 * Abstract class for all command classes having common utility methods
 * 
 * @author agautam
 *
 */
public abstract class AbstractCommand implements Command {

    /**
     * Method to decorate the command output before sending it back to client. This allows easy change
     * of output pattern
     * 
     * @param currentDirectory
     * @param output
     * @return decorated output
     */
  protected String decorateOutput(String currentDirectory, String output) {

    return String.format(COMMAND_RESULT_OUTPUT_PATTERN, currentDirectory, output);
  }

}
