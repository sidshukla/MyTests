package com.cisco.telnet.app.command;

/**
 * Enum class to maintain all valid commands for the application
 * 
 * @author agautam
 *
 */
public enum CommandEnum {
    
    CD("cd"),
    LS("ls"),
    MKDIR("mkdir"),
    PWD("pwd");
    
    /**
     * Field to store string command for enum
     */
    private String command;
    
    /**
     * Constructor for command enum
     * @param command
     */
    CommandEnum(String command){
        this.command = command;
    }

    /**
     * Method to return value of enum
     * @return
     */
    public String value(){
        return command;
    }
    
    /**
     * Method to get the enum instance for the given command string
     * 
     * @param commandString
     * @return CommandEnum mapped to the given string
     */
    public static CommandEnum getCommand(String commandString){
        CommandEnum commandEnum = null;
        for(CommandEnum command : CommandEnum.values()){
            if(command.value().equals(commandString)){
                commandEnum = command;
                break;
            }
        }        
        return commandEnum;
    }
}
