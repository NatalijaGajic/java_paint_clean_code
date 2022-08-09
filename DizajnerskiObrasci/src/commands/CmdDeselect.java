package commands;

import geometry.Shape;
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
		return "Deselected " + shape.toString();
	}

}
