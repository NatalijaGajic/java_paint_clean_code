package commands;

import geometry.Shape;
import logger.LoggerConstants;
import model.DrawingModel;

public class CmdBringToBack implements Command{

	private DrawingModel model;
	private Shape shapeToMove;
	private int indexOfShape;

	public CmdBringToBack(DrawingModel model, Shape shapeToMove) {
		this.model = model;
		this.shapeToMove = shapeToMove;
		this.indexOfShape = model.getIndexOfShape(shapeToMove);
	}

	
	@Override
	public void execute() {
		if(indexOfShape == 0)
			return;
		model.removeShape(shapeToMove);
		model.addShapeAtIndex(0, shapeToMove);
	}

	@Override
	public void unexecute() {
		model.removeShape(shapeToMove);
		model.addShapeAtIndex(indexOfShape, shapeToMove);
	}
	
	@Override
	public String toString() {
		return LoggerConstants.BRING_TO_BACK_COMMAND + " " + shapeToMove.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof CmdBringToBack) {
			Shape forwardedShape = ((CmdBringToBack) obj).shapeToMove;
			return shapeToMove.equals(forwardedShape);
		}
		return false;
	}


}
