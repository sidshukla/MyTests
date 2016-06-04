package com.cisco.telnet.app.command;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.cisco.telnet.app.exception.CommandException;
import com.cisco.telnet.app.request.Request;

/**
 * Command class to handle 'ls' command (listing files)
 * 
 * @author agautam
 * 
 */
@Component("lsCommand")
public class LsCommand extends AbstractCommand {

    /**
     * Field holding message for invalid listing command argument
     */
    @Value("${command.listing.invalid.argument.output.format}")
    private String commandListingInvalidArgumentOutputFormat;

    /**
     * Method to execute 'ls' command
     */
    @Override
    public String execute(Request request) {

        if (StringUtils.hasLength(request.getArgument())) {
            throw new CommandException(String.format(commandListingInvalidArgumentOutputFormat, request.getArgument()));
        }

        String output = listFilesInDirectory(request.getSession().getCurrentDirectory());

        return decorateOutput(request.getSession().getCurrentDirectory(), output);
    }

    /**
     * Method to list all files in the given path
     * 
     * @param currentDirectory
     * @return list of files as string 
     */
    private String listFilesInDirectory(String currentDirectory) {

        StringBuilder fileList = new StringBuilder(LINE_SEPARATOR);
        File folder = new File(currentDirectory);

        for (File f : folder.listFiles()) {
            fileList.append(f.getName()).append(LINE_SEPARATOR);
        }

        return fileList.toString();
    }

}
