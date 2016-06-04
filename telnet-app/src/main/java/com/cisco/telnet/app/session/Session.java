package com.cisco.telnet.app.session;

/**
 * Session object to store client connection specific details like current
 * directory.
 * 
 * As the server running on a given socket does not maintain and state, this
 * class helps in storing the values between different requests coming from the
 * same client.
 * 
 * @Immutable
 * 
 * @author agautam
 * 
 */
public class Session {

    /**
     * Current directory path
     */
    private String currentDirectory;

    /**
     * Constructor for immutable class
     * @param currentDirectory
     */
    public Session(String currentDirectory) {
        this.currentDirectory = currentDirectory;
    }

    public String getCurrentDirectory() {
        return currentDirectory;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((currentDirectory == null) ? 0 : currentDirectory.hashCode());
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
        Session other = (Session) obj;
        if (currentDirectory == null) {
            if (other.currentDirectory != null)
                return false;
        } else if (!currentDirectory.equals(other.currentDirectory))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Session [currentDirectory=" + currentDirectory + "]";
    }

}
