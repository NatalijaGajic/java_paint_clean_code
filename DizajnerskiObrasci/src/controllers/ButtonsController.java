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
		optionsToolBar.getBtnDelete().setEnabled(false);
		optionsToolBar.getBtnModify().setEnabled(false);
		disablePositionButtons();
	}
	
	private void disablePositionButtons() {
		optionsToolBar.getBtnBringToBack().setEnabled(false);
		optionsToolBar.getBtnBringToFront().setEnabled(false);
		optionsToolBar.getBtnToBack().setEnabled(false);
		optionsToolBar.getBtnToFront().setEnabled(false);
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
	
	private void enableOneSelectedButtons() {
		optionsToolBar.getBtnModify().setEnabled(true);
		optionsToolBar.getBtnDelete().setEnabled(true);
	}
	
	private void enableMultipleSelectedButtons() {
		optionsToolBar.getBtnDelete().setEnabled(true);
		optionsToolBar.getBtnModify().setEnabled(false);
		disablePositionButtons();
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
