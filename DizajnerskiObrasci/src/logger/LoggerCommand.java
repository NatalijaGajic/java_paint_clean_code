package logger;

import commands.Command;

public class LoggerCommand {
	private Command command;
	private String typeOfCommand;
	
	public LoggerCommand(Command command, String typeOfCommand) {
		this.command = command;
		this.typeOfCommand = typeOfCommand;
	}

	public Command getCommand() {
		return command;
	}

	public String getTypeOfCommand() {
		return typeOfCommand;
	}
	
	
}
