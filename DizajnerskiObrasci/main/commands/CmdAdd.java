package commands;

import geometry.Shape;
import logger.TypeOfCommand;
import model.DrawingModel;

public class CmdAdd implements Command{

	private DrawingModel model;
	private Shape shape;
	
	public CmdAdd(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		model.addShape(shape);
	}

	@Override
	public void unexecute() {
		model.removeShape(shape);
	}
	
	@Override
	public String toString() {
		return TypeOfCommand.ADD_COMMAND + " " + shape.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof CmdAdd) {
			Shape forwardedShape = ((CmdAdd) obj).shape;
			return shape.equals(forwardedShape);
		}
		return false;
	}

}
