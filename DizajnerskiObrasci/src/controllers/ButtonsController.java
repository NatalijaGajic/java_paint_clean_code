package controllers;

import frame.DrawingFrame;
import frame.FrameOptionsToolBar;
import geometry.Shape;
import model.DrawingModel;

public class ButtonsController {
	
	private DrawingModel model;
	private FrameOptionsToolBar optionsToolBar;
	
	public ButtonsController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		optionsToolBar = frame.getOptionsToolBar();
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
		optionsToolBar.getBtnBringToBack().setEnabled(false);
		optionsToolBar.getBtnBringToFront().setEnabled(false);
		optionsToolBar.getBtnToBack().setEnabled(false);
		optionsToolBar.getBtnToFront().setEnabled(false);
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
		optionsToolBar.getBtnDelete().setEnabled(false);
		optionsToolBar.getBtnModify().setEnabled(false);
		disablePositionButtons();
	}
	
	private void enableOneSelectedButtons() {
		optionsToolBar.getBtnModify().setEnabled(true);
		optionsToolBar.getBtnDelete().setEnabled(true);
	}
	
	private void enableMultipleSelectedButtons() {
		optionsToolBar.getBtnDelete().setEnabled(true);
		optionsToolBar.getBtnModify().setEnabled(false);
		disablePositionButtons();
	}
	
	private void disableToFrontPositionButtons() {
		optionsToolBar.getBtnBringToFront().setEnabled(false);
		optionsToolBar.getBtnToFront().setEnabled(false);
	}
	
	private void disableToBackPositionButtons() {
		optionsToolBar.getBtnBringToBack().setEnabled(false);
		optionsToolBar.getBtnToBack().setEnabled(false);
	}
	
	private void enableToFrontPositionButtons() {
		optionsToolBar.getBtnBringToFront().setEnabled(true);
		optionsToolBar.getBtnToFront().setEnabled(true);
	}
	
	private void enableToBackPositionButtons() {
		optionsToolBar.getBtnBringToBack().setEnabled(true);
		optionsToolBar.getBtnToBack().setEnabled(true);
	}
	
	
	public void updateObservableUndoRedoButtons(int numberOfExecutedCommands, int numberOfUnexecutedCommands) {
		updateUndoButton(numberOfExecutedCommands);
		updateRedoButton(numberOfUnexecutedCommands);
	}
	
	private void updateUndoButton(int numberOfExecutedCommands) {
		if(numberOfExecutedCommands > 0) {
			optionsToolBar.getBtnUndo().setEnabled(true);
		}else if(numberOfExecutedCommands == 0) {
			optionsToolBar.getBtnUndo().setEnabled(false);
		}
	}
	
	private void updateRedoButton(int numberOfUnexecutedCommands) {
		if(numberOfUnexecutedCommands > 0) {
			optionsToolBar.getBtnRedo().setEnabled(true);
		}else if(numberOfUnexecutedCommands == 0) {
			optionsToolBar.getBtnRedo().setEnabled(false);
		}
	}
	
	public void updateLogReaderObserverButtons(int numberOfCommandsToBeExecuted) {
		if(numberOfCommandsToBeExecuted > 0) {
			optionsToolBar.getBtnExecuteLog().setEnabled(true);
		}else if(numberOfCommandsToBeExecuted == 0) {
			optionsToolBar.getBtnExecuteLog().setEnabled(false);
		}
	}
}
