package com.cisco.sample.handler;

/*
 * Enum of valid and supported commands
 */
public enum CommandsEnum {

	PWD("pwd"), CD("cd"), LS("ls"), EXIT("exit");
	private final String command;

	CommandsEnum(String command) {
		this.command = command;
	}

	public String getCommand() {
		return command;
	}

	static public CommandsEnum getMember(String aName) {
		CommandsEnum[] commands = CommandsEnum.values();
		for (CommandsEnum command : commands)
			if (command.getCommand().equals(aName)) {
				return command;
			}
		return null;
	}
}
