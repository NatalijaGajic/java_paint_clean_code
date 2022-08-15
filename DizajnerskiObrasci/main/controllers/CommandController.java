package controllers;

import commands.Command;
import commandsHandler.CommandsHandler;
import frame.DrawingFrame;
import logger.*;

public class CommandController {

	private DrawingFrame frame;
	private CommandsHandler commandsHandler;
	private LogWriter logWriter;
	private LogParser logParser;
	
	public CommandController(DrawingFrame frame, CommandsHandler commandsHandler, LogWriter logWriter, LogParser logParser) {
		this.frame = frame;
		this.logWriter = logWriter;
		this.logParser = logParser;
		this.commandsHandler = commandsHandler;
	}
	
	public void executeCommand(Command cmd) {
		cmd.execute();
		commandsHandler.addExecutedCommand(cmd);
		logWriter.log(cmd.toString());
		frame.getView().repaint();
	}
	
	public void undoCommand() {
		commandsHandler.undoExecutedCommand();
		logWriter.log(TypeOfCommand.UNDO_COMMAND.toString());
		frame.getView().repaint();
	}
	
	public void redoCommand() {
		commandsHandler.redoUnexecutedCommand();
		logWriter.log(TypeOfCommand.REDO_COMMAND.toString());
		frame.getView().repaint();
	}
	
	//TODO: When option for executing log is available, other options should be disabled, or log should be cleared if new command is executed
	public void executeLog() {
		LoggerCommand commandFromLog = logParser.parseCommandFromLog();
		if(commandFromLog != null) {
			TypeOfCommand typeOfCommand = commandFromLog.getTypeOfCommand();
			if(typeOfCommand.equals(TypeOfCommand.UNDO_COMMAND))
				undoCommand();
			else if(typeOfCommand.equals(TypeOfCommand.REDO_COMMAND))
				redoCommand();
			else
				executeCommand(commandFromLog.getCommand());
		}
	}
}
