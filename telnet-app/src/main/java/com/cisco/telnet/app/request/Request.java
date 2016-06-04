package com.cisco.telnet.app.request;

import com.cisco.telnet.app.command.CommandEnum;
import com.cisco.telnet.app.session.Session;

/**
 * Class to store all request details parsed from an input string
 * @Immutable
 * 
 * @author agautam
 * 
 */
public class Request {

    /**
     * Requested command
     */
    private CommandEnum command;

    /**
     * Argument passed to the command
     */
    private String argument;

    /**
     * Session having client session details like current directory associated
     * per connection 
     */
    private Session session;
    
    /**
     * Connection Id for the given connection.
     */
    private String connectionId;

    /**
     * Constructor to initialize the immutable instance
     * @param command
     * @param argument
     * @param session
     * @param connectionId
     */
    public Request(CommandEnum command, String argument, Session session, String connectionId) {
        this.command = command;
        this.argument = argument;
        this.session = session;
        this.connectionId = connectionId;
    }

    public CommandEnum getCommand() {
        return command;
    }

    public String getArgument() {
        return argument;
    }

    public Session getSession() {
        return session;
    }

    public String getConnectionId() {
        return connectionId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((argument == null) ? 0 : argument.hashCode());
        result = prime * result + ((command == null) ? 0 : command.hashCode());
        result = prime * result + ((connectionId == null) ? 0 : connectionId.hashCode());
        result = prime * result + ((session == null) ? 0 : session.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Request other = (Request) obj;
        if (argument == null) {
            if (other.argument != null)
                return false;
        } else if (!argument.equals(other.argument))
            return false;
        if (command != other.command)
            return false;
        if (connectionId == null) {
            if (other.connectionId != null)
                return false;
        } else if (!connectionId.equals(other.connectionId))
            return false;
        if (session == null) {
            if (other.session != null)
                return false;
        } else if (!session.equals(other.session))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Request [command=" + command + ", argument=" + argument + ", session=" + session + ", connectionId="
                + connectionId + "]";
    }

}
