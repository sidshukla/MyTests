package com.cisco.sample.handler;

import java.io.File;

/*
 * Class that process the commands and returns the output as string
 * 
 */
public class CommandHandler {
	private String currentWorkingDirectory = null;

	public CommandHandler(String currentWorkingDirectory) {
		this.currentWorkingDirectory = currentWorkingDirectory;
	}

	public String processCommand(CommandsEnum command, String args) {

		String output = null;
		if (command.equals(CommandsEnum.PWD)) {
			output = "Current directory is : " + currentWorkingDirectory;
		} else if (command.equals(CommandsEnum.LS)) {
			File folder = new File(currentWorkingDirectory);
			String[] listOfFiles = folder.list();
			StringBuilder tempOutput = new StringBuilder();
			for (int i = 0; i < listOfFiles.length; i++) {
				tempOutput = tempOutput.append(listOfFiles[i] + "\n");
			}
			output = tempOutput.toString();
		} else if (command.equals(CommandsEnum.CD)) {
			File changeDir = new File(currentWorkingDirectory, args);
			if (changeDir.exists()) {
				currentWorkingDirectory = changeDir.getAbsolutePath();
				output = "Changed the current working directory to " + args;
			} else {
				return "Directory " + args + " does not exist";
			}
		}
		return output;
	}
}
