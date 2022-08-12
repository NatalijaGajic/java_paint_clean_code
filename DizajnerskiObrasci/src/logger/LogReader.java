package logger;

import java.util.*;
import geometry.*;
import commands.*;
import model.DrawingModel;
import observer.*;

public class LogReader implements LogReaderSubject{

	private LogParser logParser;
	private Queue<String> commandsToBeExecutedLog;
	private DrawingModel model;
	private String[] logLine;
	private ArrayList<LogReaderObserver> observers;
	
	public LogReader(DrawingModel model) {
		this.model = model;
		logParser = new LogParser();
		commandsToBeExecutedLog = new LinkedList<String>();
		observers = new ArrayList<LogReaderObserver>();
	}
	
	public void addCommandToCommandsToBeExecutedLog(String command) {
		commandsToBeExecutedLog.add(command);
		notifyObservers();
	}
	
	public LoggerCommand readCommandFromLog(){
		this.logLine = commandsToBeExecutedLog.poll().split("[, =():]");
		
		if(logLine[0].equals(LoggerConstants.ADD_COMMAND))
			return makeAddCommand();
		else if (logLine[0].equals(LoggerConstants.SELECT_COMMAND))
			return makeSelectCommand();
		else if (logLine[0].equals(LoggerConstants.DESELECT_COMMAND))
			return makeDeselectCommand();
		else if (logLine[0].equals(LoggerConstants.MODIFY_COMMAND))
			return makeModifyCommand();
		else if (logLine[0].equals(LoggerConstants.DELETE_COMMAND))
			return makeDeleteCommand();
		else if (logLine[0].equals(LoggerConstants.UNDO_COMMAND))
			return makeUndoCommand();
		else if (logLine[0].equals(LoggerConstants.REDO_COMMAND))
			return makeRedoCommand();
		else if (logLine[0].equals(LoggerConstants.TO_BACK_COMMAND))
			return makeMoveShapeToBackCommand();
		else if (logLine[0].equals(LoggerConstants.TO_FRONT_COMMAND))
			return makeMoveShapeToFrontCommand();
		else if (logLine[0].equals(LoggerConstants.BRING_TO_FRONT_COMMAND))
			return makeBringShapeToFrontCommand();
		else if (logLine[0].equals(LoggerConstants.BRING_TO_BACK_COMMAND))
			return makeBringShapeToBackCommand();
		
		notifyObservers();
		return null;
	}
	
	public LoggerCommand makeAddCommand() {
		Shape shape = parseShapeFromLog();
		Command command = new CmdAdd(model, shape);
		return new LoggerCommand(command, LoggerConstants.ADD_COMMAND);
	}
	
	public LoggerCommand makeSelectCommand() {
		Shape shape = parseShapeFromLog();
		shape = model.getShapeEqualTo(shape);
		Command command = new CmdSelect(model, shape);
		return new LoggerCommand(command, LoggerConstants.SELECT_COMMAND);
	}
	
	public LoggerCommand makeDeselectCommand() {
		Shape shape = parseShapeFromLog();
		Shape selectedShape = model.getSelectedShapeEqualTo(shape);
		Command command = new CmdDeselect(model, selectedShape);
		return new LoggerCommand(command, LoggerConstants.DESELECT_COMMAND);
	}
	
	public LoggerCommand makeModifyCommand() {
		Shape selectedShape = model.getSelectedShape();
		Shape modifiedShape = parseModifiedShapeFromLog();
		Command command = new CmdModify(selectedShape, modifiedShape.clone());
		return new LoggerCommand(command, LoggerConstants.MODIFY_COMMAND);
	}
	
	public LoggerCommand makeDeleteCommand() {
		@SuppressWarnings("unchecked")
		ArrayList<Shape> shapesToDelete = (ArrayList<Shape>) model.getSelectedShapes().clone();
		Command command = new CmdDelete(model, shapesToDelete);
		return new LoggerCommand(command, LoggerConstants.DELETE_COMMAND);
	}
	
	public LoggerCommand makeUndoCommand() {
		return new LoggerCommand(null, LoggerConstants.UNDO_COMMAND);
	}
	
	public LoggerCommand makeRedoCommand() {
		return new LoggerCommand(null, LoggerConstants.REDO_COMMAND);
	}
	
	public LoggerCommand makeMoveShapeToBackCommand() {
		Shape shape = model.getSelectedShape();
		Command command = new CmdToBack(model, shape);
		return new LoggerCommand(command, LoggerConstants.TO_BACK_COMMAND);
	}
	
	public LoggerCommand makeMoveShapeToFrontCommand() {
		Shape shape = model.getSelectedShape();
		Command command = new CmdToFront(model, shape);
		return new LoggerCommand(command, LoggerConstants.TO_FRONT_COMMAND);
	}
	
	public LoggerCommand makeBringShapeToFrontCommand() {
		Shape shape = model.getSelectedShape();
		Command command = new CmdBringToFront(model, shape);
		return new LoggerCommand(command, LoggerConstants.BRING_TO_FRONT_COMMAND);
	}
	
	public LoggerCommand makeBringShapeToBackCommand() {
		Shape shape = model.getSelectedShape();
		Command command = new CmdBringToBack(model, shape);
		return new LoggerCommand(command, LoggerConstants.BRING_TO_FRONT_COMMAND);
	}
	
	private Shape parseShapeFromLog() {
		logLine = Arrays.copyOfRange(logLine, 1, logLine.length);
		
		if (logLine[0].equals(LoggerConstants.POINT))
			return logParser.parsePointFromLog(logLine);
		else if(logLine[0].equals(LoggerConstants.LINE))
			return logParser.parseLineFromLog(logLine);
		else if (logLine[0].equals(LoggerConstants.CIRCLE))
			return logParser.parseCircleFromLog(logLine);
		else if (logLine[0].equals(LoggerConstants.DONUT))
			return logParser.parseDonutFromLog(logLine);
		else if (logLine[0].equals(LoggerConstants.HEXAGON))
			return logParser.parseHexagonFromLog(logLine);
		else if (logLine[0].equals(LoggerConstants.RECTANGLE))
			return logParser.parseRectangleFromLog(logLine);
		return null;
	}
	
	private Shape parseModifiedShapeFromLog() {
		Shape selectedShape = model.getSelectedShape();
		Shape modifiedShape = null;
		
		if(selectedShape instanceof Point) {
			logLine = Arrays.copyOfRange(logLine, 9, logLine.length);
			modifiedShape = logParser.parsePointFromLog(logLine);
		}else if(selectedShape instanceof Line) {
			logLine = Arrays.copyOfRange(logLine, 13, logLine.length);
			modifiedShape = logParser.parseLineFromLog(logLine);
		}else if(selectedShape instanceof Donut) {
			logLine = Arrays.copyOfRange(logLine, 18, logLine.length);
			modifiedShape = logParser.parseDonutFromLog(logLine);
		}else if(selectedShape instanceof Circle) {
			logLine = Arrays.copyOfRange(logLine, 15, logLine.length);
			modifiedShape = logParser.parseCircleFromLog(logLine);
		}else if(selectedShape instanceof Rectangle) {
			logLine = Arrays.copyOfRange(logLine, 18, logLine.length);
			modifiedShape = logParser.parseRectangleFromLog(logLine);
		}else if(selectedShape instanceof HexagonAdapter) {
			logLine = Arrays.copyOfRange(logLine, 15, logLine.length);
			modifiedShape = logParser.parseHexagonFromLog(logLine);
		}
		
		if(modifiedShape != null) {
			modifiedShape.setSelected(true);
		}
		
		return modifiedShape;
	}
	
	public void clearLog() {
		commandsToBeExecutedLog.clear();
		notifyObservers();
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
	
	public Queue<String> getCommandsToBeExecutedLog() {
		return commandsToBeExecutedLog;
	}
	
}
