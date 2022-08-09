package commands;

import geometry.Shape;
import logger.LoggerConstants;
import model.DrawingModel;

public class CmdAdd implements Command{

	private DrawingModel model;
	private Shape shape;
	
	
	public CmdAdd(DrawingModel model, Shape shape) {
		super();
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
	
	public String toString() {
		return LoggerConstants.ADD_COMMAND + " " + shape.toString();
	}

}
