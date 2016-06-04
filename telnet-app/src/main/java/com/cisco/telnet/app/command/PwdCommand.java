package com.cisco.telnet.app.command;

import org.springframework.stereotype.Component;

import com.cisco.telnet.app.request.Request;

/**
 * Command class to handle 'pwd' command
 * 
 * @author agautam
 * 
 */
@Component("pwdCommand")
public class PwdCommand extends AbstractCommand {

    /**
     * Method to execute 'pwd' command. It simply checks the current directory of
     * the user available in session and returns
     * 
     */
    @Override
    public String execute(Request request) {

        return decorateOutput("", request.getSession().getCurrentDirectory());
    }

}
