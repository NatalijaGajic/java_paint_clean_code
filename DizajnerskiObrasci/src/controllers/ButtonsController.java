package controllers;

import javax.swing.JButton;

import frame.*;
import geometry.Shape;
import model.DrawingModel;

public class ButtonsController {
	
	private DrawingModel model;
	private DrawingFrame frame;
	private JButton btnModify;
	private JButton btnDelete;
	private JButton btnToBack;
	private JButton btnToFront;
	private JButton btnBringToFront;
	private JButton btnBringToBack;
	private JButton btnUndo;
	private JButton btnRedo;
	private JButton btnExecuteLog;
	
	public ButtonsController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
		initializeButtons();
	}
	
	private void initializeButtons() {
		FrameDrawingOptionsToolBar drawingOptionsToolBar = frame.getDrawingOptionsToolBar();
		FrameCommandOptionsToolBar commandOptionsToolBar = frame.getCommandOptionsToolBar();
		btnModify = drawingOptionsToolBar.getBtnModify();
		btnDelete = drawingOptionsToolBar.getBtnDelete();
		btnToBack = drawingOptionsToolBar.getBtnToBack();
		btnToFront = drawingOptionsToolBar.getBtnToFront();
		btnBringToFront = drawingOptionsToolBar.getBtnBringToFront();
		btnBringToBack = drawingOptionsToolBar.getBtnBringToBack();
		btnUndo = commandOptionsToolBar.getBtnUndo();
		btnRedo = commandOptionsToolBar.getBtnRedo();
		btnExecuteLog = commandOptionsToolBar.getBtnExecuteLog();
	}
	
	/**
	 * If one shape is selected, when new shape is added or the selected shape is moved, enable property of position buttons is updated
	 * Use case 1: The selected shape is the only shape in the list, new shape is added, ToFront buttons are enabled
	 * Use case 2: The selected shape is the last shape in the list, selected shape is moved back, ToFront buttons are enabled
	 * Use case 3: The selected shape is the first shape in the list, selected shape is moved front, ToBack buttons are enabled
	 */
	public void updateShapesObserverButtons() {
		int numberOfSelectedShapes = model.getNumberOfSelectedShapes();
		if(numberOfSelectedShapes == 1) { 
				enablePositionButtons();
		}
	}
	
	private void disablePositionButtons() {
		btnBringToBack.setEnabled(false);
		btnBringToFront.setEnabled(false);
		btnToBack.setEnabled(false);
		btnToFront.setEnabled(false);
	}
	
	private void enablePositionButtons() {
		Shape selectedShape = model.getSelectedShape();
		int indexOfSelectedShape = model.getIndexOfShape(selectedShape);
		
		if(isSelectedShapeTheOnlyShape()) {
			disableToBackPositionButtons();
			disableToFrontPositionButtons();
			return;
		}
			
		if(areThereShapesAboveSelectedShape(indexOfSelectedShape))
			enableToFrontPositionButtons();
		else
			disableToFrontPositionButtons();
		
		if(areThereShapesUnderSelectedShape(indexOfSelectedShape))
			enableToBackPositionButtons();
		else
			disableToBackPositionButtons();
		
	}
	
	private boolean isSelectedShapeTheOnlyShape() {
		return model.getNumberOfShapes() == 1;
	}
	
	private boolean areThereShapesAboveSelectedShape(int indexOfSelectedShape) {
		return indexOfSelectedShape < model.getNumberOfShapes() - 1;
	}
	
	private boolean areThereShapesUnderSelectedShape(int indexOfSelectedShape) {
		return indexOfSelectedShape > 0;
	}
	
	public void updateSelectedShapesObserverButtons(int numberOfSelectedShapes) {
		if(numberOfSelectedShapes == 0) {
			disableNoneSelectedButtons();
			return;
		}
		
		if (numberOfSelectedShapes > 1) {
			enableMultipleSelectedButtons();
			return;
		}
		
		if(numberOfSelectedShapes == 1)
			enableOneSelectedButtons();
		
		if (isOneShapeSelectedAndTheOnlyShape(numberOfSelectedShapes))
			disablePositionButtons();
		
		if(isOneShapeSelectedAndNotTheOnlyShape(numberOfSelectedShapes))
			enablePositionButtons();
	}

	private boolean isOneShapeSelectedAndTheOnlyShape(int numOfSelectedShapes) {
		return numOfSelectedShapes == 1 && model.getNumberOfShapes() == 1;
	}

	private boolean isOneShapeSelectedAndNotTheOnlyShape(int numOfSelectedShapes) {
		return numOfSelectedShapes == 1 && model.getNumberOfShapes() != 1;
	}
	
	private void disableNoneSelectedButtons() {
		btnDelete.setEnabled(false);
		btnModify.setEnabled(false);
		disablePositionButtons();
	}
	
	private void enableOneSelectedButtons() {
		btnModify.setEnabled(true);
		btnDelete.setEnabled(true);
	}
	
	private void enableMultipleSelectedButtons() {
		btnDelete.setEnabled(true);
		btnModify.setEnabled(false);
		disablePositionButtons();
	}
	
	private void disableToFrontPositionButtons() {
		btnBringToFront.setEnabled(false);
		btnToFront.setEnabled(false);
	}
	
	private void disableToBackPositionButtons() {
		btnBringToBack.setEnabled(false);
		btnToBack.setEnabled(false);
	}
	
	private void enableToFrontPositionButtons() {
		btnBringToFront.setEnabled(true);
		btnToFront.setEnabled(true);
	}
	
	private void enableToBackPositionButtons() {
		btnBringToBack.setEnabled(true);
		btnToBack.setEnabled(true);
	}
	
	
	public void updateObservableUndoRedoButtons(int numberOfExecutedCommands, int numberOfUnexecutedCommands) {
		updateUndoButton(numberOfExecutedCommands);
		updateRedoButton(numberOfUnexecutedCommands);
	}
	
	private void updateUndoButton(int numberOfExecutedCommands) {
		if(numberOfExecutedCommands > 0) {
			btnUndo.setEnabled(true);
		}else if(numberOfExecutedCommands == 0) {
			btnUndo.setEnabled(false);
		}
	}
	
	private void updateRedoButton(int numberOfUnexecutedCommands) {
		if(numberOfUnexecutedCommands > 0) {
			btnRedo.setEnabled(true);
		}else if(numberOfUnexecutedCommands == 0) {
			btnRedo.setEnabled(false);
		}
	}
	
	public void updateLogReaderObserverButtons(int numberOfCommandsToBeExecuted) {
		if(numberOfCommandsToBeExecuted > 0) {
			btnExecuteLog.setEnabled(true);
		}else if(numberOfCommandsToBeExecuted == 0) {
			btnExecuteLog.setEnabled(false);
		}
	}
}
