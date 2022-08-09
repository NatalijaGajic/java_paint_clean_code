package commands;

import geometry.Shape;
import logger.LoggerConstants;
import model.DrawingModel;

public class CmdToBack implements Command {
	
	private DrawingModel model;
	private Shape shapeToMove;
	private int indexOfShape;

	public CmdToBack(DrawingModel model, Shape shapeToMove) {
		this.model = model;
		this.shapeToMove = shapeToMove;
		this.indexOfShape = model.getIndexOfShape(shapeToMove);
	}

	@Override
	public void execute() {
		if(indexOfShape == 0)
			return;
		model.removeShape(shapeToMove);
		model.addShapeAtIndex(indexOfShape - 1, shapeToMove);
		
	}

	@Override
	public void unexecute() {
		model.removeShape(shapeToMove);
		model.addShapeAtIndex(indexOfShape, shapeToMove);
		
	}
	
	public String toString() {
		return LoggerConstants.TO_BACK_COMMAND + " " + shapeToMove.toString();
	}

}
