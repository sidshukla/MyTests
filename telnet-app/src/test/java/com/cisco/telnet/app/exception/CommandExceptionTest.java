package com.cisco.telnet.app.exception;

import junit.framework.Assert;

import org.junit.Test;

public class CommandExceptionTest {

    @Test
    public void testDefaultConstrutor() {

        CommandException e = new CommandException();

        Assert.assertEquals(null, e.getMessage());
        Assert.assertEquals(null, e.getCause());
    }

    @Test
    public void testConstructorWithStringParameter() {

        CommandException e = new CommandException("Exception Details");

        Assert.assertEquals("Exception Details", e.getMessage());
        Assert.assertEquals(null, e.getCause());
    }

    @Test
    public void testConstructorWithStringAndExceptionParameter() {

        CommandException e = new CommandException("Exception Details",
                new Exception("Message"));

        Assert.assertEquals("Exception Details", e.getMessage());
        Assert.assertEquals("Message", e.getCause().getMessage());
    }

}
