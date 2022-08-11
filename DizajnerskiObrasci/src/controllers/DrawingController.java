package controllers;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.*;
import commandHandler.CommandsHandler;
import commands.*;
import dialogs.*;
import frame.DrawingFrame;
import geometry.*;
import logger.*;
import model.DrawingModel;

public class DrawingController {

	private DrawingModel model;
	private DrawingFrame frame;
	private CommandsHandler commandsHandler;
	private LogWriter logWriter;
	private LogReader logReader;
	private Color activeEdgeColor;
	private Color activeInnerColor;
	private Point startPoint;
	
	public DrawingController(DrawingModel model, DrawingFrame frame){
		this.model = model;
		this.frame = frame;
		activeEdgeColor = Color.BLACK;
		activeInnerColor = Color.WHITE;
	}
	
	public DrawingController(DrawingModel model, DrawingFrame frame, CommandsHandler commandsHandler){
		this(model, frame);
		this.commandsHandler = commandsHandler;

	}
	
	public void executeCommand(Command cmd) {
		cmd.execute();
		commandsHandler.addExecutedCommand(cmd);
		logWriter.log(cmd.toString());
		frame.getView().repaint();
	}
	
	public void drawPointIfAccepted(Point click) {
		DlgPoint dlgPoint = new DlgPoint();
		dlgPoint.setCreateDialogFields(click, activeEdgeColor);
		dlgPoint.setVisible(true);
		addShapeIfAccepted(dlgPoint);
	}
	
	public void drawLineIfAccepted(Point click) {
		if (startPoint == null)
			startPoint = click;
		else {
			DlgLine dlgLine = new DlgLine();
			dlgLine.setCreateDialogFields(startPoint, click, activeEdgeColor);
			dlgLine.setVisible(true);
			addShapeIfAccepted(dlgLine);
			startPoint = null;
		}
	}
	
	public void drawCircleIfAccepted(Point click) {
		DlgCircle dlgCircle = new DlgCircle();
		dlgCircle.setCreateDialogFields(click, activeEdgeColor, activeInnerColor);
		dlgCircle.setVisible(true);
		addShapeIfAccepted(dlgCircle);
	}
	
	public void drawDonutIfAccepted(Point click) {
		DlgDonut dlgDonut = new DlgDonut();
		dlgDonut.setCreateDialogFields(click, activeEdgeColor, activeInnerColor);
		dlgDonut.setVisible(true);
		addShapeIfAccepted(dlgDonut);
	}
	
	public void drawRectangleIfAccepted(Point click) {
		DlgRectangle dlgRect = new DlgRectangle();
		dlgRect.setCreateDialogFields(click, activeEdgeColor, activeInnerColor);
		dlgRect.setVisible(true);
		addShapeIfAccepted(dlgRect);
	}
	
	public void drawHexagonIfAccepted(Point click) {
		DlgHexagon dlgHexagon = new DlgHexagon();
		dlgHexagon.setCreateDialogFields(click, activeEdgeColor, activeInnerColor);
		dlgHexagon.setVisible(true);
		addShapeIfAccepted(dlgHexagon);
	}
	
	public void addShapeIfAccepted(Dialog dlg) {
		if(dlg.isAccepted()) {
			Shape shape = dlg.getShapeFromDialog();
			CmdAdd cmdAdd = new CmdAdd(model, shape);
			executeCommand(cmdAdd);
		}
	}
	
	public void modifyShape() {
		Shape selectedShape = model.getSelectedShape();

		if (selectedShape instanceof Point) {
			modifyPointIfAccepted(selectedShape);
		} else if (selectedShape instanceof Line) {
			modifyLineIfAccepted(selectedShape);
		} else if (selectedShape instanceof Rectangle) {
			modifyRectangleIfAccepted(selectedShape);
		} else if (selectedShape instanceof Donut) {
			modifyDonutIfAccepted(selectedShape);
		} else if (selectedShape instanceof Circle) {
			modifyCircleIfAccepted(selectedShape);
		} else if (selectedShape instanceof HexagonAdapter) {
			modifyHexagonIfAccepted(selectedShape);
		}
	}
	
	public void modifyPointIfAccepted(Shape selectedShape) {
		Point point = (Point) selectedShape;
		DlgPoint dlg = new DlgPoint();
		dlg.setModifyDialogFields(point);
		dlg.setVisible(true);
		modifyShapeIfAccepted(dlg, point);
	}
	
	public void modifyLineIfAccepted(Shape selectedShape) {
		Point point = (Point) selectedShape;
		DlgPoint dlg = new DlgPoint();
		dlg.setModifyDialogFields(point);
		dlg.setVisible(true);
		modifyShapeIfAccepted(dlg, point);
	}
	
	public void modifyCircleIfAccepted(Shape selectedShape) {
		Circle circle = (Circle) selectedShape;
		DlgCircle dlg = new DlgCircle();
		dlg.setModifyDialogFields(circle);
		dlg.setVisible(true);
		modifyShapeIfAccepted(dlg, circle);
	}

	public void modifyDonutIfAccepted(Shape selectedShape) {
		Donut donut = (Donut) selectedShape;
		DlgDonut dlg = new DlgDonut();
		dlg.setModifyDialogFields(donut);
		dlg.setVisible(true);
		modifyShapeIfAccepted(dlg, donut);
	}
	
	public void modifyRectangleIfAccepted(Shape selectedShape) {
		Rectangle rectangle = (Rectangle) selectedShape;
		DlgRectangle dlg = new DlgRectangle();
		dlg.setModifyDialogFields(rectangle);
		dlg.setVisible(true);
		modifyShapeIfAccepted(dlg, rectangle);
	}
	
	public void modifyHexagonIfAccepted(Shape selectedShape) {
		HexagonAdapter hexagonAdapter = (HexagonAdapter) selectedShape;
		DlgHexagon dlg = new DlgHexagon();
		dlg.setModifyDialogFields(hexagonAdapter);
		dlg.setVisible(true);
		modifyShapeIfAccepted(dlg, hexagonAdapter);
	}
	
	public void modifyShapeIfAccepted(Dialog dlg, Shape selectedShape) {
		if(dlg.isAccepted()) {
			Shape shapeWithNewValues = dlg.getShapeFromDialog();
			shapeWithNewValues.setSelected(true);
			CmdModify cmdModify = new CmdModify(selectedShape, shapeWithNewValues);
			executeCommand(cmdModify);
		}
	}
	
	public void deleteShapesIfAccepted() {
		String[] options = { "Yes", "No" };
		int option = JOptionPane.showOptionDialog(null, "Are you sure?", "Deleting selected shapes", JOptionPane.OK_OPTION,
				JOptionPane.ERROR_MESSAGE, null, options, options[0]);
		if (option == 0) {
			deleteShapes();
		}
	}
	
	public void deleteShapes() {
		@SuppressWarnings("unchecked")
		ArrayList<Shape> shapesToDelete = (ArrayList<Shape>) model.getSelectedShapes().clone();
		CmdDelete cmdDelete = new CmdDelete(model, shapesToDelete);
		executeCommand(cmdDelete);
	}
	
	/**
	 * Start at the end of the list, (de)select the last added shape that contains the point of the click
	 * @param click
	 */
	public void selectOrDeselectShape(Point click) {
		for(int indexOfShape = model.getNumberOfShapes() - 1; indexOfShape >= 0; indexOfShape--) {
			Shape shape = model.getShapeAtIndex(indexOfShape);
			
			if(shape.contains(click) && !shape.isSelected()) {
				CmdSelect cmdSelect = new CmdSelect(model, shape);
				executeCommand(cmdSelect);
				break;
			}else if(shape.contains(click) && shape.isSelected()) {
				CmdDeselect cmdDeselect = new CmdDeselect(model, shape);
				executeCommand(cmdDeselect);
				break;
			}
		}
		
	}	
	
	public void setActiveEdgeColor() {
		Color chosenColor = JColorChooser.showDialog(null, "Choose edge color", Color.BLACK);
		if (chosenColor != null) {
			activeEdgeColor = chosenColor;
			JPanel activeEdgeColorPanel = frame.getColorToolBar().getPnlActiveEdgeColor();
			activeEdgeColorPanel.setBackground(activeEdgeColor);
		}
	}
	
	public void setActiveInnerColor() {
		Color chosenColor = JColorChooser.showDialog(null, "Choose inner color", Color.WHITE);
		if (chosenColor != null) {
			activeInnerColor = chosenColor;
			JPanel pnlActiveInnerColor = frame.getColorToolBar().getPnlActiveInnerColor();
			pnlActiveInnerColor.setBackground(activeInnerColor);
		}
	}
	
	public void moveShapeToBack() {
		Shape selectedShape = model.getSelectedShape();
		if(model.getIndexOfShape(selectedShape) == 0)
			return;
		CmdToBack cmdToBack = new CmdToBack(model, selectedShape);
		executeCommand(cmdToBack);
	}
	
	public void moveShapeToFront() {
		Shape selectedShape = model.getSelectedShape();
		if(model.getIndexOfShape(selectedShape) == model.getNumberOfShapes() - 1)
			return;
		CmdToFront cmdToFront = new CmdToFront(model, selectedShape);
		executeCommand(cmdToFront);
	}
	
	public void bringShapeToFront() {
		Shape selectedShape = model.getSelectedShape();
		if(model.getIndexOfShape(selectedShape) == model.getNumberOfShapes() - 1)
			return;
		CmdBringToFront cmdBringToFront = new CmdBringToFront(model, selectedShape);
		executeCommand(cmdBringToFront);
	}
	
	public void bringShapeToBack() {
		Shape selectedShape = model.getSelectedShape();
		if(model.getIndexOfShape(selectedShape) == 0)
			return;
		CmdBringToBack cmdBringToBack = new CmdBringToBack(model, selectedShape);
		executeCommand(cmdBringToBack);
	}	
	
	public void undoCommand() {
		commandsHandler.undo();
		logWriter.log(LoggerConstants.UNDO_COMMAND);
		frame.getView().repaint();
	}
	
	public void redoCommand() {
		commandsHandler.redo();
		logWriter.log(LoggerConstants.REDO_COMMAND);
		frame.getView().repaint();
	}
	
	public void executeLog() {
		logReader.readCommandFromLog();
		frame.getView().repaint();
	}
	
	public void updateShapesObserverButtons() {
		int numberOfSelectedShapes = model.getNumberOfSelectedShapes();
		if(numberOfSelectedShapes == 1) { 
			if(model.getNumberOfShapes() == 1) {
				disablePositionButtons();
			}else {
				enablePositionButtons();
			}
		}
	}
	
	public void updateSelectedShapesObserverButtons(int numberOfSelectedShapes) {
		
		if(numberOfSelectedShapes == 0) {
			disableNoneSelectedButtons();
		} else if(numberOfSelectedShapes == 1) { 
			if(model.getNumberOfShapes() == 1) {
				disablePositionButtons();
			}else {
				enablePositionButtons();
			}
			
			enableOneSelectedButtons();
			
		}else {
			enableMultipleSelectedButtons();
		}
	}
	
	private void disableNoneSelectedButtons() {
		frame.getOptionsToolBar().getBtnDelete().setEnabled(false);
		frame.getOptionsToolBar().getBtnModify().setEnabled(false);
		disablePositionButtons();
	}
	
	private void disablePositionButtons() {
		frame.getOptionsToolBar().getBtnBringToBack().setEnabled(false);
		frame.getOptionsToolBar().getBtnBringToFront().setEnabled(false);
		frame.getOptionsToolBar().getBtnToBack().setEnabled(false);
		frame.getOptionsToolBar().getBtnToFront().setEnabled(false);
	}
	
	private void enablePositionButtons() {
		Shape selectedShape = model.getSelectedShape();
		int indexOfSelectedShape = model.getIndexOfShape(selectedShape);
		
		if(indexOfSelectedShape < model.getNumberOfShapes() - 1) {
			if(indexOfSelectedShape == 0) {
				disableToBackPositionButtons();
			}
			enableToFrontPositionButtons();
		}
		if(indexOfSelectedShape > 0) {
			if(indexOfSelectedShape == model.getNumberOfShapes() - 1) {
				disableToFrontPositionButtons();
			}
			enableToBackPositionButtons();
		}
	}
	
	private void disableToFrontPositionButtons() {
		frame.getOptionsToolBar().getBtnBringToFront().setEnabled(false);
		frame.getOptionsToolBar().getBtnToFront().setEnabled(false);
	}
	
	private void disableToBackPositionButtons() {
		frame.getOptionsToolBar().getBtnBringToBack().setEnabled(false);
		frame.getOptionsToolBar().getBtnToBack().setEnabled(false);
	}
	
	private void enableToFrontPositionButtons() {
		frame.getOptionsToolBar().getBtnBringToFront().setEnabled(true);
		frame.getOptionsToolBar().getBtnToFront().setEnabled(true);
	}
	
	private void enableToBackPositionButtons() {
		frame.getOptionsToolBar().getBtnBringToBack().setEnabled(true);
		frame.getOptionsToolBar().getBtnToBack().setEnabled(true);
	}
	
	private void enableOneSelectedButtons() {
		frame.getOptionsToolBar().getBtnModify().setEnabled(true);
		frame.getOptionsToolBar().getBtnDelete().setEnabled(true);
	}
	
	private void enableMultipleSelectedButtons() {
		frame.getOptionsToolBar().getBtnDelete().setEnabled(true);
		frame.getOptionsToolBar().getBtnModify().setEnabled(false);
		disablePositionButtons();
	}
	
	public void updateObservableUndoRedoButtons(int numberOfExecutedCommands, int numberOfUnexecutedCommands) {
		updateUndoButton(numberOfExecutedCommands);
		updateRedoButton(numberOfUnexecutedCommands);
	}
	
	private void updateUndoButton(int numberOfExecutedCommands) {
		if(numberOfExecutedCommands > 0) {
			frame.getOptionsToolBar().getBtnUndo().setEnabled(true);
		}else if(numberOfExecutedCommands == 0) {
			frame.getOptionsToolBar().getBtnUndo().setEnabled(false);
		}
	}
	
	private void updateRedoButton(int numberOfUnexecutedCommands) {
		if(numberOfUnexecutedCommands > 0) {
			frame.getOptionsToolBar().getBtnRedo().setEnabled(true);
		}else if(numberOfUnexecutedCommands == 0) {
			frame.getOptionsToolBar().getBtnRedo().setEnabled(false);
		}
	}
	
	public void updateLogReaderObserverButtons(int numberOfCommandsToBeExecuted) {
		if(numberOfCommandsToBeExecuted > 0) {
			frame.getOptionsToolBar().getBtnExecuteLog().setEnabled(true);
		}else if(numberOfCommandsToBeExecuted == 0) {
			frame.getOptionsToolBar().getBtnExecuteLog().setEnabled(false);
		}
	}

	public CommandsHandler getCommandsHandler() {
		return commandsHandler;
	}

	public DrawingModel getModel() {
		return model;
	}

	public void setCommandsHandler(CommandsHandler commandsHandler) {
		this.commandsHandler = commandsHandler;
	}

	public void setLogWriter(LogWriter logWriter) {
		this.logWriter = logWriter;
	}

	public void setLogReader(LogReader logReader) {
		this.logReader = logReader;
	}
	
	
	
}
