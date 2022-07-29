package commands;

import applications.DrawingModel;
import geometry.Shape;

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

}
