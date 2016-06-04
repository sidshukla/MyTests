package com.cisco.telnet.app.command;

import com.cisco.telnet.app.request.Request;

/**
 * Interface to define command executor classes. It allows new commands to be added transparently
 * 
 * @author agautam
 *
 */
public interface Command {

    /**
     * variable to hold line separator character
     */
    String LINE_SEPARATOR = "\n";
    
    /**
     * common output pattern used to decorate the output line
     */
    String COMMAND_RESULT_OUTPUT_PATTERN = new StringBuilder().append("%s:$ %s").append(LINE_SEPARATOR).append(">")
            .toString();

    /**
     * Execute method to handle the requested command. It accepts request parameter that have all the details passed by client 
     * for the command execution.
     * 
     * @param request
     * @return command output string.
     */
    String execute(Request request);

}
