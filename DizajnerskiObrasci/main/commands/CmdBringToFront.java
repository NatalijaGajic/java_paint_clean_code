package commands;

import geometry.Shape;
import logger.LoggerConstants;
import model.DrawingModel;

public class CmdBringToFront implements Command {

	private DrawingModel model;
	private Shape shapeToMove;
	private int indexOfShape;

	public CmdBringToFront(DrawingModel model, Shape shapeToMove) {
		this.model = model;
		this.shapeToMove = shapeToMove;
		this.indexOfShape = model.getIndexOfShape(shapeToMove);
	}

	@Override
	public void execute() {
		if(indexOfShape == model.getNumberOfShapes() - 1)
			return;
		model.removeShape(shapeToMove);
		model.addShapeAtIndex(model.getNumberOfShapes(), shapeToMove);
	}

	@Override
	public void unexecute() {
		model.removeShape(shapeToMove);
		model.addShapeAtIndex(indexOfShape, shapeToMove);
	}
	
	@Override
	public String toString() {
		return LoggerConstants.BRING_TO_FRONT_COMMAND + " " + shapeToMove.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof CmdBringToFront) {
			Shape forwardedShape = ((CmdBringToFront) obj).shapeToMove;
			return shapeToMove.equals(forwardedShape);
		}
		return false;
	}

}
