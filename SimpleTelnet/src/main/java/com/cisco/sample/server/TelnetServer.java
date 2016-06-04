package com.cisco.sample.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * Telnet server which can process concurrent requests
 *  max no of threads = 10
 */

public class TelnetServer {
	private static final ExecutorService executorService = Executors.newFixedThreadPool(10);

	public static void main(String[] args) throws IOException { 
		ServerSocket socket = new ServerSocket(4444);
		try {
			while(true) { 
				Runnable requestProcessor = new RequestProcessor(socket.accept());
				executorService.execute(requestProcessor);
			} 
		}catch (IOException ex) {
			executorService.shutdown();
			socket.close();
		}
	}
}
