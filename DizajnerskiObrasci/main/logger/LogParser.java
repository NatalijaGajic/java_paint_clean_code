package logger;

import java.awt.Color;
import java.util.*;
import commands.*;
import geometry.*;
import model.DrawingModel;
import logger.TypeOfCommand;

public class LogParser {

	private DrawingModel model;
	private LogReader logReader;
	
	public LogParser(DrawingModel model, LogReader logReader) {
		this.model = model;
		this.logReader = logReader;
	}
	
	public LoggerCommand parseCommandFromLog() {
		String logLine = logReader.readCommandFromLog();
		return parseCommandFromLog(logLine);
	}
	
	private LoggerCommand parseCommandFromLog(String logLine) {
		String[] splitedLog = logLine.split("[, =():]");
		String typeOfCommand = splitedLog[0];
		
		if(typeOfCommand.equals(TypeOfCommand.ADD_COMMAND.toString()))
			return makeAddCommand(splitedLog);
		else if (typeOfCommand.equals(TypeOfCommand.SELECT_COMMAND.toString()))
			return makeSelectCommand(splitedLog);
		else if (typeOfCommand.equals(TypeOfCommand.DESELECT_COMMAND.toString()))
			return makeDeselectCommand(splitedLog);
		else if (typeOfCommand.equals(TypeOfCommand.MODIFY_COMMAND.toString()))
			return makeModifyCommand(splitedLog);
		else if (typeOfCommand.equals(TypeOfCommand.DELETE_COMMAND.toString()))
			return makeDeleteCommand();
		else if (typeOfCommand.equals(TypeOfCommand.UNDO_COMMAND.toString()))
			return makeUndoCommand();
		else if (typeOfCommand.equals(TypeOfCommand.REDO_COMMAND.toString()))
			return makeRedoCommand();
		else if (typeOfCommand.equals(TypeOfCommand.TO_BACK_COMMAND.toString()))
			return makeMoveShapeToBackCommand();
		else if (typeOfCommand.equals(TypeOfCommand.TO_FRONT_COMMAND.toString()))
			return makeMoveShapeToFrontCommand();
		else if (typeOfCommand.equals(TypeOfCommand.BRING_TO_FRONT_COMMAND.toString()))
			return makeBringShapeToFrontCommand();
		else if (typeOfCommand.equals(TypeOfCommand.BRING_TO_BACK_COMMAND.toString()))
			return makeBringShapeToBackCommand();
		
		return null;
	}
	
	private LoggerCommand makeAddCommand(String[] splitedLogLine) {
		Shape shape = parseShapeFromLog(splitedLogLine);
		Command command = new CmdAdd(model, shape);
		return new LoggerCommand(command, TypeOfCommand.ADD_COMMAND);
	}
	
	private LoggerCommand makeSelectCommand(String[] splitedLogLine) {
		Shape shape = parseShapeFromLog(splitedLogLine);
		shape = model.getShapeEqualTo(shape);
		Command command = new CmdSelect(model, shape);
		return new LoggerCommand(command, TypeOfCommand.SELECT_COMMAND);
	}
	
	private LoggerCommand makeDeselectCommand(String[] splitedLogLine) {
		Shape shape = parseShapeFromLog(splitedLogLine);
		Shape selectedShape = model.getSelectedShapeEqualTo(shape);
		Command command = new CmdDeselect(model, selectedShape);
		return new LoggerCommand(command, TypeOfCommand.DESELECT_COMMAND);
	}
	
	private LoggerCommand makeModifyCommand(String[] splitedLogLine) {
		Shape selectedShape = model.getSelectedShape();
		Shape modifiedShape = parseModifiedShapeFromLog(splitedLogLine);
		Command command = new CmdModify(selectedShape, modifiedShape.clone());
		return new LoggerCommand(command, TypeOfCommand.MODIFY_COMMAND);
	}
	
	private LoggerCommand makeDeleteCommand() {
		@SuppressWarnings("unchecked")
		ArrayList<Shape> shapesToDelete = (ArrayList<Shape>) model.getSelectedShapes().clone();
		Command command = new CmdDelete(model, shapesToDelete);
		return new LoggerCommand(command, TypeOfCommand.DELETE_COMMAND);
	}
	
	private LoggerCommand makeUndoCommand() {
		return new LoggerCommand(null, TypeOfCommand.UNDO_COMMAND);
	}
	
	private LoggerCommand makeRedoCommand() {
		return new LoggerCommand(null, TypeOfCommand.REDO_COMMAND);
	}
	
	private LoggerCommand makeMoveShapeToBackCommand() {
		Shape shape = model.getSelectedShape();
		Command command = new CmdToBack(model, shape);
		return new LoggerCommand(command, TypeOfCommand.TO_BACK_COMMAND);
	}
	
	private LoggerCommand makeMoveShapeToFrontCommand() {
		Shape shape = model.getSelectedShape();
		Command command = new CmdToFront(model, shape);
		return new LoggerCommand(command, TypeOfCommand.TO_FRONT_COMMAND);
	}
	
	private LoggerCommand makeBringShapeToFrontCommand() {
		Shape shape = model.getSelectedShape();
		Command command = new CmdBringToFront(model, shape);
		return new LoggerCommand(command, TypeOfCommand.BRING_TO_FRONT_COMMAND);
	}
	
	private LoggerCommand makeBringShapeToBackCommand() {
		Shape shape = model.getSelectedShape();
		Command command = new CmdBringToBack(model, shape);
		return new LoggerCommand(command, TypeOfCommand.BRING_TO_FRONT_COMMAND);
	}
	
	private Shape parseShapeFromLog(String[] splitedLogLine) {
		splitedLogLine = Arrays.copyOfRange(splitedLogLine, 1, splitedLogLine.length);
		String typeOfShape = splitedLogLine[0];
		
		if (typeOfShape.equals(TypeOfShape.POINT.toString()))
			return parsePointFromLog(splitedLogLine);
		else if(typeOfShape.equals(TypeOfShape.LINE.toString()))
			return parseLineFromLog(splitedLogLine);
		else if (typeOfShape.equals(TypeOfShape.CIRCLE.toString()))
			return parseCircleFromLog(splitedLogLine);
		else if (typeOfShape.equals(TypeOfShape.DONUT.toString()))
			return parseDonutFromLog(splitedLogLine);
		else if (typeOfShape.equals(TypeOfShape.HEXAGON.toString()))
			return parseHexagonFromLog(splitedLogLine);
		else if (typeOfShape.equals(TypeOfShape.RECTANGLE.toString()))
			return parseRectangleFromLog(splitedLogLine);
		return null;
	}
	
	private Shape parseModifiedShapeFromLog(String[] splitedLogLine) {
		Shape selectedShape = model.getSelectedShape();
		Shape modifiedShape = null;
		
		if(selectedShape instanceof Point) {
			splitedLogLine = Arrays.copyOfRange(splitedLogLine, 9, splitedLogLine.length);
			modifiedShape = parsePointFromLog(splitedLogLine);
		}else if(selectedShape instanceof Line) {
			splitedLogLine = Arrays.copyOfRange(splitedLogLine, 13, splitedLogLine.length);
			modifiedShape = parseLineFromLog(splitedLogLine);
		}else if(selectedShape instanceof Donut) {
			splitedLogLine = Arrays.copyOfRange(splitedLogLine, 18, splitedLogLine.length);
			modifiedShape = parseDonutFromLog(splitedLogLine);
		}else if(selectedShape instanceof Circle) {
			splitedLogLine = Arrays.copyOfRange(splitedLogLine, 15, splitedLogLine.length);
			modifiedShape = parseCircleFromLog(splitedLogLine);
		}else if(selectedShape instanceof Rectangle) {
			splitedLogLine = Arrays.copyOfRange(splitedLogLine, 18, splitedLogLine.length);
			modifiedShape = parseRectangleFromLog(splitedLogLine);
		}else if(selectedShape instanceof HexagonAdapter) {
			splitedLogLine = Arrays.copyOfRange(splitedLogLine, 15, splitedLogLine.length);
			modifiedShape = parseHexagonFromLog(splitedLogLine);
		}
		
		if(modifiedShape != null) {
			modifiedShape.setSelected(true);
		}
		return modifiedShape;
	}
	
	
	private Point parsePointFromLog(String[] logLine) {
		int xCoord = Integer.parseInt(logLine[2]);
		int yCoord = Integer.parseInt(logLine[3]);
		Color edgeColor = new Color(Integer.parseInt(logLine[6]));
		return new Point(xCoord, yCoord, false, edgeColor);
	}
	
	private Line parseLineFromLog(String[] logLine) {
		int xCoordOfStartPoint = Integer.parseInt(logLine[2]);
		int yCoordOfStartPoint = Integer.parseInt(logLine[3]);
		int xCoordOfEndPoint = Integer.parseInt(logLine[6]);
		int yCoordOfEndPoint = Integer.parseInt(logLine[7]);
		Color edgeColor = new Color(Integer.parseInt(logLine[10]));
		Point startPoint = new Point(xCoordOfStartPoint, yCoordOfStartPoint);
		Point endPoint = new Point(xCoordOfEndPoint, yCoordOfEndPoint);
		return new Line(startPoint, endPoint, false, edgeColor);
	}
	
	private Circle parseCircleFromLog(String[] logLine) {
		int xCoordOfCenter = Integer.parseInt(logLine[2]);
		int yCoordOfCenter = Integer.parseInt(logLine[3]);
		int radius = Integer.parseInt(logLine[6]);
		Color edgeColor = new Color(Integer.parseInt(logLine[9]));
		Color innerColor = new Color(Integer.parseInt(logLine[12]));
		return new Circle(new Point(xCoordOfCenter, yCoordOfCenter), radius, false, edgeColor, innerColor);
	}
	
	private Donut parseDonutFromLog(String[] logLine) {
		int xCoordOfCenter = Integer.parseInt(logLine[2]);
		int yCoordOfCenter = Integer.parseInt(logLine[3]);
		int radius = Integer.parseInt(logLine[6]);
		int innerRadius = Integer.parseInt(logLine[9]);
		Color edgeColor = new Color(Integer.parseInt(logLine[12]));
		Color innerColor = new Color(Integer.parseInt(logLine[15]));
		return new Donut(new Point(xCoordOfCenter, yCoordOfCenter), radius, innerRadius, false, edgeColor, innerColor);
	}
	
	private Rectangle parseRectangleFromLog(String[] logLine) {
		int xCoordOfUpperLeftPoint = Integer.parseInt(logLine[2]);
		int yCoordOfUpperLeftPoint = Integer.parseInt(logLine[3]);
		int width = Integer.parseInt(logLine[6]);
		int height = Integer.parseInt(logLine[9]);
		Color edgeColor = new Color(Integer.parseInt(logLine[12]));
		Color innerColor = new Color(Integer.parseInt(logLine[15]));
		return new Rectangle(new Point(xCoordOfUpperLeftPoint, yCoordOfUpperLeftPoint), height, width, false, edgeColor, innerColor);
	}
	
	private HexagonAdapter parseHexagonFromLog(String[] logLine) {
		int xCoordOfCenter = Integer.parseInt(logLine[2]);
		int yCoordOfCenter = Integer.parseInt(logLine[3]);
		int radius = Integer.parseInt(logLine[6]);
		Color edgeColor = new Color(Integer.parseInt(logLine[9]));
		Color innerColor = new Color(Integer.parseInt(logLine[12]));
		return new HexagonAdapter(new Point(xCoordOfCenter, yCoordOfCenter), radius, edgeColor, innerColor, false);
	}
}
