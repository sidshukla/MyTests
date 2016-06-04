package com.cisco.telnet.app.command;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.cisco.telnet.app.exception.CommandException;
import com.cisco.telnet.app.request.Request;
import com.cisco.telnet.app.session.Session;
import com.cisco.telnet.app.session.SessionStore;
import com.cisco.telnet.app.util.FileUtils;

/**
 * Command class to handle change directory command. It reads all necessary
 * output messages from properties file to easily change values in future.
 * 
 * @author agautam
 * 
 */
@Component("cdCommand")
public class CdCommand extends AbstractCommand {

    /**
     * Instance of session store that maintains map of client and its associated
     * current directory
     */
    @Autowired
    private SessionStore sessionStore;

    /**
     * Field holding message for success output
     */
    @Value("${command.change.directory.success.output.format}")
    private String changeDirectorySuccessOutputFormat;

    /**
     * Field holding message for change directory command failure
     */
    @Value("${command.change.directory.failure.output.format}")
    private String changeDirectoryFailureOutputFormat;

    /**
     * Field holding change directory ouput message in case user is already at
     * root folder and further requesting to go one level up
     */
    @Value("${command.change.directory.already.root.output}")
    private String changeDirectoryAlreadyRootOutput;

    /**
     * Field holding message when there is no change to current directory
     */
    @Value("${command.change.directory.no.change.output}")
    private String changeDirectoryNoChangeOutput;

    /**
     * Current directory argument
     */
    private static final String currentDirectoryArg = ".";

    /**
     * Parent directory argument
     */
    private static final String parentDirectoryArg = "..";

    /**
     * Method to hand change directory command In case change directory is
     * successful, it also updates the sessionStore which maintains mapping of
     * client to its current directory
     */
    @Override
    public String execute(Request request) {

        String output = changeDirectoryNoChangeOutput;
        String changedCurrentDirectory = "";

        // checking if user has not provided any directory argument to change
        if (StringUtils.hasLength(request.getArgument()) && !request.getArgument().equals(currentDirectoryArg)) {

            // checking if user requested for parent directory
            if (request.getArgument().equals(parentDirectoryArg)) {
                changedCurrentDirectory = getParentDirectoryPath(request.getSession().getCurrentDirectory());
            } else {

                // handle change directory as per given argument
                changedCurrentDirectory = getNewCurrentDirectoryPath(request.getSession().getCurrentDirectory(),
                        request.getArgument());
            }

            //updating session store with new current directory for the user
            sessionStore.updateSession(request.getConnectionId(), new Session(changedCurrentDirectory));

            output = String.format(changeDirectorySuccessOutputFormat, changedCurrentDirectory);
        }

        return decorateOutput("", output);

    }

    /**
     * Method to get parent directory
     * 
     * @param currentDirectoryPath
     * @return parent directory
     */
    private String getParentDirectoryPath(String currentDirectoryPath) {

        File f = new File(currentDirectoryPath);

        String parentDirectoryPath = f.getParent();

        if (null == parentDirectoryPath) {
            throw new CommandException(changeDirectoryAlreadyRootOutput);
        }

        return parentDirectoryPath;

    }

    /**
     * Method to check and get the new directory path after evaluating requested input argument
     * 
     * @param currentDirectoryPath
     * @param requestPath
     * @return new changed directory path
     */
    private String getNewCurrentDirectoryPath(String currentDirectoryPath, String requestPath) {

        String requestChangeDirectoryPath = "";

        try {

            if (!FileUtils.isAbsolutePath(requestPath)) {

                requestChangeDirectoryPath = FileUtils.createNewPath(currentDirectoryPath, requestPath);

                File f = new File(requestChangeDirectoryPath);

                if (!f.isDirectory()) {
                    throw new CommandException(String.format(changeDirectoryFailureOutputFormat, requestPath));
                } else {
                    requestChangeDirectoryPath = f.getCanonicalPath();
                }
            } else {
                return requestPath;
            }
        } catch (IOException e) {
            throw new CommandException(String.format(changeDirectoryFailureOutputFormat, requestPath));
        }

        return requestChangeDirectoryPath;
    }

}
