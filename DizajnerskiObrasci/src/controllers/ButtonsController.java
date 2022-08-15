package controllers;

import frame.*;
import geometry.Shape;
import model.DrawingModel;

public class ButtonsController {
	
	private DrawingModel model;
	private FrameDrawingOptionsToolBar drawingOptionsToolBar;
	private FrameCommandOptionsToolBar commandOptionsToolBar;
	
	public ButtonsController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		drawingOptionsToolBar = frame.getDrawingOptionsToolBar();
		commandOptionsToolBar = frame.getCommandOptionsToolBar();
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
		drawingOptionsToolBar.getBtnBringToBack().setEnabled(false);
		drawingOptionsToolBar.getBtnBringToFront().setEnabled(false);
		drawingOptionsToolBar.getBtnToBack().setEnabled(false);
		drawingOptionsToolBar.getBtnToFront().setEnabled(false);
	}
	
	/**
	 * If the selected shape is the only shape, all position buttons are disabled
	 * If the selected shape is the last shape in the list, ToFront buttons are disabled, ToBack enabled
	 * If the selected shape is the first shape in the list, ToBack buttons are disabled, ToFront enabled
	 * If the selected shape is nor the only shape, nor the first, nor the last, all position buttons are enabled 
	 */
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
	
	/**
	 * When the number of selected shapes is changed, enable property of Modify, Delete and position buttons is updated
	 * If no shape is selected, Modify, Delete and position buttons are disabled
	 * If one shape is selected, Modify, Delete and appropriate position buttons are enabled
	 * If multiple shapes are selected, Delete button is enabled, Modify and position buttons are disabled
	 * @param numberOfSelectedShapes
	 */
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
		drawingOptionsToolBar.getBtnDelete().setEnabled(false);
		drawingOptionsToolBar.getBtnModify().setEnabled(false);
		disablePositionButtons();
	}
	
	private void enableOneSelectedButtons() {
		drawingOptionsToolBar.getBtnModify().setEnabled(true);
		drawingOptionsToolBar.getBtnDelete().setEnabled(true);
	}
	
	private void enableMultipleSelectedButtons() {
		drawingOptionsToolBar.getBtnDelete().setEnabled(true);
		drawingOptionsToolBar.getBtnModify().setEnabled(false);
		disablePositionButtons();
	}
	
	private void disableToFrontPositionButtons() {
		drawingOptionsToolBar.getBtnBringToFront().setEnabled(false);
		drawingOptionsToolBar.getBtnToFront().setEnabled(false);
	}
	
	private void disableToBackPositionButtons() {
		drawingOptionsToolBar.getBtnBringToBack().setEnabled(false);
		drawingOptionsToolBar.getBtnToBack().setEnabled(false);
	}
	
	private void enableToFrontPositionButtons() {
		drawingOptionsToolBar.getBtnBringToFront().setEnabled(true);
		drawingOptionsToolBar.getBtnToFront().setEnabled(true);
	}
	
	private void enableToBackPositionButtons() {
		drawingOptionsToolBar.getBtnBringToBack().setEnabled(true);
		drawingOptionsToolBar.getBtnToBack().setEnabled(true);
	}
	
	
	public void updateObservableUndoRedoButtons(int numberOfExecutedCommands, int numberOfUnexecutedCommands) {
		updateUndoButton(numberOfExecutedCommands);
		updateRedoButton(numberOfUnexecutedCommands);
	}
	
	private void updateUndoButton(int numberOfExecutedCommands) {
		if(numberOfExecutedCommands > 0) {
			commandOptionsToolBar.getBtnUndo().setEnabled(true);
		}else if(numberOfExecutedCommands == 0) {
			commandOptionsToolBar.getBtnUndo().setEnabled(false);
		}
	}
	
	private void updateRedoButton(int numberOfUnexecutedCommands) {
		if(numberOfUnexecutedCommands > 0) {
			commandOptionsToolBar.getBtnRedo().setEnabled(true);
		}else if(numberOfUnexecutedCommands == 0) {
			commandOptionsToolBar.getBtnRedo().setEnabled(false);
		}
	}
	
	public void updateLogReaderObserverButtons(int numberOfCommandsToBeExecuted) {
		if(numberOfCommandsToBeExecuted > 0) {
			commandOptionsToolBar.getBtnExecuteLog().setEnabled(true);
		}else if(numberOfCommandsToBeExecuted == 0) {
			commandOptionsToolBar.getBtnExecuteLog().setEnabled(false);
		}
	}
}
