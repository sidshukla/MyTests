package com.cisco.telnet.app.command;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.cisco.telnet.app.exception.CommandException;
import com.cisco.telnet.app.request.Request;
import com.cisco.telnet.app.util.FileUtils;

/**
 * Command class to handle 'mkdir' command from user
 * 
 * @author agautam
 * 
 */
@Component("mkdirCommand")
public class MkdirCommand extends AbstractCommand {

    /**
     * Field holding message when user has not provided directory name
     */
    @Value("${command.mkdir.need.directory.name.output}")
    private String commandMkdrNeedDirectoryNameOutput;

    /**
     * Field holding message for successful creation of directory
     */
    @Value("${command.mkdir.success.output.format}")
    private String commandMkdrSuccessOutputFormat;

    /**
     * Field holding message for failure of directory creation
     */
    @Value("${command.mkdir.failure.output.format}")
    private String commandMkdrFailureOutputFormat;

    /**
     * Method to execute 'mkdir' command. It checks if the requested directory name is valid
     * if yes then creates the directory or return error in case some problem occurs.
     */
    @Override
    public String execute(Request request) {

        if (!StringUtils.hasLength(request.getArgument())) {
            throw new CommandException(commandMkdrNeedDirectoryNameOutput);
        }

        makeDirectory(request.getSession().getCurrentDirectory(), request.getArgument());

        return decorateOutput("", String.format(commandMkdrSuccessOutputFormat, request.getArgument()));
    }

    /**
     * Method to make directory in the users current directory with the given name
     * @param currentDirectoryPath
     * @param requestPath
     */
    private void makeDirectory(String currentDirectoryPath, String requestPath) {

        String newPath = FileUtils.createNewPath(currentDirectoryPath, requestPath);

        boolean success = (new File(newPath)).mkdir();
        if (!success) {
            throw new CommandException(String.format(commandMkdrFailureOutputFormat, newPath));
        }
    }

}
