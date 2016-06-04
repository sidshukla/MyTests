package com.cisco.sample.server;

import java.io.IOException;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import com.cisco.sample.handler.CommandHandler;
import com.cisco.sample.handler.CommandsEnum;

/*
 * Worker thread class that process each request individualy
 * 
 */
public class RequestProcessor implements Runnable {

	private Socket socket;

	public RequestProcessor(Socket s) {
		this.socket = s;
	}

	@Override
	public void run() {
		InputStream inps = null;
		OutputStream outs = null;
		try {
			inps = this.socket.getInputStream();
			outs = this.socket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Scanner in = new Scanner(inps);
		PrintWriter out = new PrintWriter(outs, true);
		out.println("Server running...");

		boolean exit = false;
		String args = null;

		CommandHandler handler = new CommandHandler(
				System.getProperty("user.dir"));
		while (!exit && in.hasNextLine()) {
			String command = in.nextLine().trim().toLowerCase();

			String[] splitCommand = command.split("\\s+");

			CommandsEnum input = CommandsEnum.getMember(splitCommand[0]);

			if (splitCommand.length > 2 || input != null) {
				try{
				if (input.equals(CommandsEnum.EXIT)) {
					out.println("Server connection closed");
					exit = true;
				} else {
					if (input.equals(CommandsEnum.CD)) {
						args = splitCommand[1];
					}
					String output = handler.processCommand(input, args);
					out.println(output);
				}
				}catch (Exception e) {
					out.println("Error in executing command !!");
				}

			} else {
				out.println("Command is not supported or is not valid !!");
			}

		}

	}

}
