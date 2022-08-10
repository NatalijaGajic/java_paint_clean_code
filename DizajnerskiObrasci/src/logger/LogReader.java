package logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import geometry.*;
import commands.*;
import controllers.DrawingController;
import model.DrawingModel;
import observer.CommandsHandlerObserver;
import observer.CommandsHandlerSubject;
import observer.LogReaderObserver;
import observer.LogReaderSubject;

public class LogReader implements LogReaderSubject{

	private LogParser logParser;
	private DrawingController controller;
	private Queue<String> commandsToBeExecutedLog;
	private DrawingModel model;
	private String[] logLine;
	private Shape shape;
	private Shape modifiedShape;
	private Command cmd;
	
	private ArrayList<LogReaderObserver> observers;
	
	public LogReader(DrawingController controller) {
		this.controller = controller;
		model = controller.getModel();
		logParser = new LogParser();
		commandsToBeExecutedLog = new LinkedList<String>();
		observers = new ArrayList<LogReaderObserver>();
	}
	
	public void addCommandToCommandsToBeExecutedLog(String command) {
		commandsToBeExecutedLog.add(command);
		notifyObservers();
	}
	
	public void readCommandFromLog(){
		this.logLine = commandsToBeExecutedLog.poll().split("[, =():]");
		
		if(logLine[0].equals(LoggerConstants.ADD_COMMAND))
			executeAddCommand();
		else if (logLine[0].equals(LoggerConstants.SELECT_COMMAND))
			executeSelectCommand();
		else if (logLine[0].equals(LoggerConstants.DESELECT_COMMAND))
			executeDeselectCommand();
		else if (logLine[0].equals(LoggerConstants.MODIFY_COMMAND))
			executeModifyCommand();
		else if (logLine[0].equals(LoggerConstants.DELETE_COMMAND))
			controller.deleteShapes();
		else if (logLine[0].equals(LoggerConstants.UNDO_COMMAND))
			controller.undoCommand();
		else if (logLine[0].equals(LoggerConstants.REDO_COMMAND))
			controller.redoCommand();
		else if (logLine[0].equals(LoggerConstants.TO_BACK_COMMAND))
			controller.moveShapeToBack();
		else if (logLine[0].equals(LoggerConstants.TO_FRONT_COMMAND))
			controller.moveShapeToFront();
		else if (logLine[0].equals(LoggerConstants.BRING_TO_FRONT_COMMAND))
			controller.bringShapeToFront();
		else if (logLine[0].equals(LoggerConstants.BRING_TO_BACK_COMMAND))
			controller.bringShapeToBack();
		
		notifyObservers();
	}
	
	public void executeAddCommand() {
		parseShapeFromLog();
		cmd = new CmdAdd(model, shape);
		controller.executeCommand(cmd);
	}
	
	public void executeSelectCommand() {
		parseShapeFromLog();
		shape = model.getShapeEqualTo(shape);
		cmd = new CmdSelect(model, shape);
		controller.executeCommand(cmd);
	}
	
	public void executeDeselectCommand() {
		parseShapeFromLog();
		shape = model.getSelectedShapeEqualTo(shape);
		cmd = new CmdDeselect(model, shape);
		controller.executeCommand(cmd);
	}
	
	public void executeModifyCommand() {
		shape = model.getSelectedShape();
		parseModifiedShapeFromLog();
		cmd = new CmdModify(shape, modifiedShape.clone());
		controller.executeCommand(cmd);
	}
	
	public void parseShapeFromLog() {
		logLine = Arrays.copyOfRange(logLine, 1, logLine.length);
		
		if (logLine[0].equals(LoggerConstants.POINT))
			shape = logParser.parsePointFromLog(logLine);
		else if(logLine[0].equals(LoggerConstants.LINE))
			shape = logParser.parseLineFromLog(logLine);
		else if (logLine[0].equals(LoggerConstants.CIRCLE))
			shape = logParser.parseCircleFromLog(logLine);
		else if (logLine[0].equals(LoggerConstants.DONUT))
			shape = logParser.parseDonutFromLog(logLine);
		else if (logLine[0].equals(LoggerConstants.HEXAGON))
			shape = logParser.parseHexagonFromLog(logLine);
		else if (logLine[0].equals(LoggerConstants.RECTANGLE))
			shape = logParser.parseRectangleFromLog(logLine);

	}
	
	public void parseModifiedShapeFromLog() {
		
		if(shape instanceof Point) {
			logLine = Arrays.copyOfRange(logLine, 9, logLine.length);
			modifiedShape = logParser.parsePointFromLog(logLine);
		}else if(shape instanceof Line) {
			logLine = Arrays.copyOfRange(logLine, 13, logLine.length);
			modifiedShape = logParser.parseLineFromLog(logLine);
		}else if(shape instanceof Circle) {
			logLine = Arrays.copyOfRange(logLine, 15, logLine.length);
			modifiedShape = logParser.parseCircleFromLog(logLine);
		}else if(shape instanceof Donut) {
			logLine = Arrays.copyOfRange(logLine, 18, logLine.length);
			modifiedShape = logParser.parseDonutFromLog(logLine);
		}else if(shape instanceof Rectangle) {
			logLine = Arrays.copyOfRange(logLine, 18, logLine.length);
			modifiedShape = logParser.parseRectangleFromLog(logLine);
		}else if(shape instanceof HexagonAdapter) {
			logLine = Arrays.copyOfRange(logLine, 15, logLine.length);
			modifiedShape = logParser.parseHexagonFromLog(logLine);
		}
		
		modifiedShape.setSelected(true);
	}
	
	public void clearLog() {
		commandsToBeExecutedLog.clear();
		notifyObservers();
	}

	public Queue<String> getCommandsToBeExecutedLog() {
		return commandsToBeExecutedLog;
	}

	public Command getCmd() {
		return cmd;
	}

	@Override
	public void registerObserver(LogReaderObserver o) {
		observers.add(o);
		
	}

	@Override
	public void unregisterObserver(LogReaderObserver o) {
		observers.remove(o);
		
	}

	@Override
	public void notifyObservers() {
		int numberOfCommandsToBeExecuted = commandsToBeExecutedLog.size();
		Iterator<LogReaderObserver> it = observers.iterator();
		while(it.hasNext()) {
			LogReaderObserver ob = it.next();
			ob.updateLogReaderObserver(numberOfCommandsToBeExecuted);
		}
		
	}
	
	
	
}
