package commands;

import geometry.Shape;
import logger.TypeOfCommand;
import model.DrawingModel;

public class CmdDeselect implements Command{

	private DrawingModel model;
	private Shape shape;
	
	public CmdDeselect(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
	}
	
	@Override
	public void execute() {
		shape.setSelected(false);
		model.removeSelectedShape(shape);
	}

	@Override
	public void unexecute() {
		shape.setSelected(true);
		model.addSelectedShape(shape);		
	}
	
	@Override
	public String toString() {
		return TypeOfCommand.DESELECT_COMMAND + " " + shape.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof CmdDeselect) {
			Shape forwardedShape = ((CmdDeselect) obj).shape;
			return shape.equals(forwardedShape);
		}
		return false;
	}

}
