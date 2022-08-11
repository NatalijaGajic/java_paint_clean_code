package commands;

import geometry.Shape;
import logger.LoggerConstants;
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
		return LoggerConstants.SELECT_COMMAND + " " + shape.toString();
	}

}
