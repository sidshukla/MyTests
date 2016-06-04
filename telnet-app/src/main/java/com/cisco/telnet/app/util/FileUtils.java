package com.cisco.telnet.app.util;

import java.io.File;

/**
 * File operations helping utility class
 * 
 * @author agautam
 * 
 */
public class FileUtils {

    /**
     * Method to create a concatenation path from the given input parameters. It
     * automatically takes care of path separator independent of operating system
     * differences
     * 
     * @param currentDirectoryPath
     * @param requestPath
     * @return new combined path
     */
    public static String createNewPath(String currentDirectoryPath, String requestPath) {

        StringBuilder sb = new StringBuilder(currentDirectoryPath);

        if (!requestPath.startsWith(File.separator) && !currentDirectoryPath.endsWith(File.separator)) {
            sb.append(File.separator);
        }

        sb.append(requestPath);

        return sb.toString();
    }

    /**
     * Method to check if the given path is an absolute path or not for a directory
     * 
     * @param path
     * @return
     */
    public static boolean isAbsolutePath(String path) {

        File f = new File(path);

        if (f.isAbsolute() && f.isDirectory()) {
            return true;
        }
        return false;
    }

}
