package commands;

import geometry.Shape;
import model.DrawingModel;

public class CmdToFront implements Command{

	private DrawingModel model;
	private Shape shapeToMove;
	private int indexOfShape;

	public CmdToFront(DrawingModel model, Shape shapeToMove) {
		this.model = model;
		this.shapeToMove = shapeToMove;
		this.indexOfShape = model.getIndexOfShape(shapeToMove);
	}

	@Override
	public void execute() {
		if(indexOfShape == model.getNumberOfShapes() - 1)
			return;
		model.removeShape(shapeToMove);
		model.addShapeAtIndex(indexOfShape + 1, shapeToMove);
		
	}

	@Override
	public void unexecute() {
		model.removeShape(shapeToMove);
		model.addShapeAtIndex(indexOfShape, shapeToMove);
		
	}
	
	public String toString() {
		return "Moved To Front " + shapeToMove.toString();
	}
}
