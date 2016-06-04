package com.cisco.telnet.app.handler;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.Message;
import org.springframework.stereotype.Component;

import com.cisco.telnet.app.command.Command;
import com.cisco.telnet.app.command.CommandEnum;
import com.cisco.telnet.app.exception.CommandException;
import com.cisco.telnet.app.request.Request;
import com.cisco.telnet.app.request.RequestBuilder;

/**
 * Input Handler class that handles all client requests. This class parse the
 * incoming message and create a request object from the parsed information.
 * Based on command given by the client, it forwards the control to its specific
 * command executor and returns the final output
 * 
 * 
 * @author agautam
 * 
 */
@Component("inputHandler")
public class InputHandlerImpl implements InputHandler {

    /**
     * Instance of request builder that creates the request object from the
     * given input string
     */
    @Autowired
    private RequestBuilder requestBuilder;

    /**
     * Map that maintains all available command executors
     */
    @Resource
    private Map<CommandEnum, Command> commandMap;

    /**
     * Method to handle incoming user requests. It parse the incoming message
     * and create a request object from the parsed information. Based on command
     * given by the client, it forwards the control to its specific command
     * executor and returns the final output
     */
    @Override
    public String handle(Message<String> message) {

        Request request = null;
        String output = "";
        try {
            //building request object
            request = requestBuilder.build(message.getPayload(), message.getHeaders().getCorrelationId().toString());

            //delegating execution to specific command object
            if (null != request) {
                output = commandMap.get(request.getCommand()).execute(request);
            }
        } catch (CommandException ce) {
            return String.format("error: %s", ce.getMessage());
        }

        return output;
    }

}
