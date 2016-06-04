package com.cisco.telnet.app.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.cisco.telnet.app.command.CommandEnum;
import com.cisco.telnet.app.exception.CommandException;
import com.cisco.telnet.app.session.SessionStore;

/**
 * RequestBuilder implementation for parsing input string into a request object.
 * 
 * @author agautam
 *
 */
@Component
public class RequestBuilderImpl implements RequestBuilder {

    /**
     * Input command separator
     */
    private static final String INPUT_COMMAND_SEPARATOR = " ";

    /**
     * Field holding message for string input parsing error
     */
    @Value("${app.request.builder.not.able.to.process.command.output.format}")
    private String requestBuilderNotAbleToProcessCommandFormat;

    /**
     * Field holding message for command not found
     */
    @Value("${app.request.builder.command.not.found.output.format}")
    private String requestBuilderCommandNotFoundOutputFormat;

    /**
     * Instance of session store which maintains session data for each client connection
     */
    @Autowired
    private SessionStore sessionStore;

    /**
     * Method to build request object from a given user input. During parsing,
     * if he input does not end up in supported command and argument then it
     * will accordingly raised command exception with the details message
     * 
     * @param input
     * @param connectionId
     * @return Request object
     */
    public Request build(String input, String connectionId) {

        String argument = "";
        CommandEnum command = null;

        if (!StringUtils.hasLength(connectionId)) {
            throw new CommandException(String.format(requestBuilderNotAbleToProcessCommandFormat, input));
        }

        if (StringUtils.hasLength(input)) {
            String[] inputData = parseInput(input.trim());

            if ((command = CommandEnum.getCommand(inputData[0])) != null) {
                if (inputData.length > 1) {
                    argument = inputData[1];
                }
            } else {
                throw new CommandException(String.format(requestBuilderCommandNotFoundOutputFormat, inputData[0]));

            }
        } else {
            return null;
        }

        Request request = new Request(command, argument, sessionStore.getSession(connectionId), connectionId);

        return request;
    }

    /**
     * Method to break the input string into array of string
     * 
     * @param input
     * @return
     */
    private String[] parseInput(String input) {
        return input.split(INPUT_COMMAND_SEPARATOR);
    }

}
