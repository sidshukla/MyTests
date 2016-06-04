package com.cisco.telnet.app.util;

import java.io.File;

import junit.framework.Assert;

import org.junit.Test;

import com.cisco.telnet.app.BaseTelnetAppTest;

public class FileUtilsTest extends BaseTelnetAppTest {

    @Test
    public void testCreateNewPathWithoutPathSeparatorInParams() {
        
        String requestedPath = "target";
        String expectedPath = new StringBuilder().append(userDirectory).append(File.separator).append(requestedPath).toString();
        String newPath = FileUtils.createNewPath(userDirectory, requestedPath);
        Assert.assertEquals(expectedPath, newPath);
    }
    
    @Test
    public void testCreateNewPathWithPathSeparatorInParam1() {
        
        String requestedPath = "target";
        String expectedPath = new StringBuilder().append(userDirectory).append(File.separator).append(requestedPath).toString();
        String newPath = FileUtils.createNewPath(userDirectory + File.separator, requestedPath);
        Assert.assertEquals(expectedPath, newPath);
    }
    
    @Test
    public void testCreateNewPathWithPathSeparatorInParam2() {
        
        String requestedPath = "target";
        String expectedPath = new StringBuilder().append(userDirectory).append(File.separator).append(requestedPath).toString();
        String newPath = FileUtils.createNewPath(userDirectory, File.separator + requestedPath);
        Assert.assertEquals(expectedPath, newPath);
    }
    
    @Test
    public void testIsAbsolutePath() {
        
        Assert.assertTrue(FileUtils.isAbsolutePath(userDirectory));
        
        Assert.assertTrue(FileUtils.isAbsolutePath(File.separator + userDirectory));
        
        Assert.assertTrue(FileUtils.isAbsolutePath(userDirectory + File.separator + "src"));
        
        Assert.assertFalse(FileUtils.isAbsolutePath("temp" + userDirectory));
        
        //System.out.println(userDirectory);
    }
}
