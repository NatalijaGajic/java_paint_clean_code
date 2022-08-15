package commands;

import geometry.Shape;
import logger.TypeOfCommand;
import model.DrawingModel;

public class CmdSelect implements Command {

	private DrawingModel model;
	private Shape shape;
	
	public CmdSelect(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
	}
	
	@Override
	public void execute() {
		shape.setSelected(true);
		model.addSelectedShape(shape);	
	}

	@Override
	public void unexecute() {
		shape.setSelected(false);
		model.removeSelectedShape(shape);
	}
	
	@Override
	public String toString() {
		return TypeOfCommand.SELECT_COMMAND + " " + shape.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof CmdSelect) {
			Shape forwardedShape = ((CmdSelect) obj).shape;
			return shape.equals(forwardedShape);
		}
		return false;
	}

}
