package logger;

import java.util.Arrays;
import geometry.*;
import commands.*;
import controllers.DrawingController;
import model.DrawingModel;

public class LogReader {

	private LogParser logParser;
	private DrawingController controller;
	private DrawingModel model;
	private String[] logLine;
	private Shape shape;
	private Shape modifiedShape;
	private Command cmd;
	
	public LogReader(DrawingController controller) {
		this.controller = controller;
		model = controller.getModel();
		logParser = new LogParser();
	}
	
	public void readLogCommand(String[] logLine){
		this.logLine = logLine;
		
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
		logLine = Arrays.copyOfRange(logLine, 1, logLine.length - 1);
		
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
			logLine = Arrays.copyOfRange(logLine, 9, logLine.length - 1);
			modifiedShape = logParser.parsePointFromLog(logLine);
		}else if(shape instanceof Line) {
			logLine = Arrays.copyOfRange(logLine, 13, logLine.length - 1);
			modifiedShape = logParser.parseLineFromLog(logLine);
		}else if(shape instanceof Circle) {
			logLine = Arrays.copyOfRange(logLine, 15, logLine.length - 1);
			modifiedShape = logParser.parseCircleFromLog(logLine);
		}else if(shape instanceof Donut) {
			logLine = Arrays.copyOfRange(logLine, 18, logLine.length - 1);
			modifiedShape = logParser.parseDonutFromLog(logLine);
		}else if(shape instanceof Rectangle) {
			logLine = Arrays.copyOfRange(logLine, 18, logLine.length - 1);
			modifiedShape = logParser.parseRectangleFromLog(logLine);
		}else if(shape instanceof HexagonAdapter) {
			logLine = Arrays.copyOfRange(logLine, 15, logLine.length - 1);
			modifiedShape = logParser.parseHexagonFromLog(logLine);
		}
	}
	
}
