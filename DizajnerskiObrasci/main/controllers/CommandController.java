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
		logWriter.log(LoggerConstants.UNDO_COMMAND);
		frame.getView().repaint();
	}
	
	public void redoCommand() {
		commandsHandler.redoUnexecutedCommand();
		logWriter.log(LoggerConstants.REDO_COMMAND);
		frame.getView().repaint();
	}
	
	//TODO: When option for executing log is available, other options should be disabled, or log should be cleared if new command is executed
	public void executeLog() {
		LoggerCommand commandFromLog = logParser.parseCommandFromLog();
		if(commandFromLog != null) {
			String typeOfCommand = commandFromLog.getTypeOfCommand();
			if(typeOfCommand.equals(LoggerConstants.UNDO_COMMAND))
				undoCommand();
			else if(typeOfCommand.equals(LoggerConstants.REDO_COMMAND))
				redoCommand();
			else
				executeCommand(commandFromLog.getCommand());
		}
	}
}
