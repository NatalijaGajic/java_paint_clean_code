package logger;

import commands.Command;

public class LoggerCommand {
	private Command command;
	private TypeOfCommand typeOfCommand;
	
	public LoggerCommand(Command command, TypeOfCommand typeOfCommand) {
		this.command = command;
		this.typeOfCommand = typeOfCommand;
	}

	public Command getCommand() {
		return command;
	}

	public TypeOfCommand getTypeOfCommand() {
		return typeOfCommand;
	}
	
	
}
