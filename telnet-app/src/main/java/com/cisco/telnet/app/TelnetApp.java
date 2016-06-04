package com.cisco.telnet.app;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.MapPropertySource;
import org.springframework.integration.ip.tcp.connection.AbstractServerConnectionFactory;
import org.springframework.integration.ip.util.TestingUtilities;
import org.springframework.integration.test.util.SocketUtils;
import org.springframework.stereotype.Component;

import com.cisco.telnet.app.request.RequestGateway;


/**
 * Main class to start the telnet application. As the application starts, it also makes the console as one of available
 * command entry source for users. To quit the application user can enter 'q' and enter
 * 
 * This application uses spring framework to bind all objects together and spring-integration framework for creating a server
 * that listens on a given port. 
 * 
 * @author agautam
 *
 */
@Component
public class TelnetApp 
{    
    /**
     * path to spring context file
     */
    private static final String contextFilePath = "classpath:/telnetApp-context.xml";
    
    /**
     * Request gateway object through which input can be sent to the running server port
     */
    @Autowired
    private RequestGateway requestGateway;
    
    /**
     * Instance of spring initialized server that listens on a given port
     */
    @Autowired
    AbstractServerConnectionFactory crLfServer;
    
    /**
     * Main method to start application. It starts the application context initialization 
     * and then fetch instance of its own class via spring to get all the benefits of spring auto-wiring
     * 
     * @param args
     */
    public static void main(String[] args) {
        
        GenericXmlApplicationContext context = TelnetApp.initContext();
        TelnetApp p = context.getBean(TelnetApp.class);
        p.start(args);
       
    }

    /**
     * method to start the server setup and welcome screen
     * 
     * @param args
     */
    protected void start(String[] args) {
        
        final Scanner scanner = new Scanner(System.in);

        System.out.println("\n========================================================="
                        + "\n                                                         "
                        + "\n    Welcome to the Telnet App Server                     "
                        + "\n                                                         "
                        + "\n=========================================================" );
        
        System.out.print("Waiting for server get initialized and ready for accepting connections... ");
        TestingUtilities.waitListening(crLfServer, 10000L);
        System.out.println("server up.\n\n");
        
        System.out.println("Please enter command (cd, ls, mkdir, pwd) and press <enter>: \n");
        System.out.println("NOTE: Entering q will quit the application\n");
        System.out.println("THIS SERVER CAN ALSO BE CONNECTED ON SERVER PORT '" + crLfServer.getPort() + "' USING TELNET.\n\n>");
        
        //this will make program to keep listening for user input on console till user requests to quit 
        while (true) {

            final String input = scanner.nextLine();

            if("q".equalsIgnoreCase(input.trim())) {
                break;
            }
            else {
                final String result = requestGateway.execute(input);
                System.out.println(result);
            }
        }

        System.out.println("Exiting application...bye.");
        System.exit(0);

    }
    
    /**
     * Method to setup the spring context and also set the default port on which to start the server.
     * Port number can also be overridden by providing availableServerSocket property as system property.
     * 
     * @return spring application context instance
     */
    public static GenericXmlApplicationContext initContext() {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();

        System.out.print("Detecting open server socket... ");
        int availableServerSocket = SocketUtils.findAvailableServerSocket(8282);

        final Map<String, Object> sockets = new HashMap<String, Object>();
        sockets.put("availableServerSocket", availableServerSocket);

        final MapPropertySource propertySource = new MapPropertySource("sockets", sockets);

        context.getEnvironment().getPropertySources().addLast(propertySource);

        System.out.println("using port " + context.getEnvironment().getProperty("availableServerSocket"));

        context.load(contextFilePath);
        context.registerShutdownHook();
        context.refresh();
        
        return context;
    }
}
