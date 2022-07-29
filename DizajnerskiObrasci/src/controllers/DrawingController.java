package controllers;

import applications.DrawingFrame;
import applications.DrawingModel;
import commands.*;
import dialogs.*;
import geometry.*;

public class DrawingController {

	DrawingModel model;
	DrawingFrame frame;
	
	public DrawingController(DrawingModel model, DrawingFrame frame){
		this.model = model;
		this.frame = frame;
	}
	
	public void addShapeIfAccepted(Dialog dlg) {
		if(dlg.isAccepted()) {
			Shape shape = dlg.getShapeFromDialog();
			executeCmdAddShape(shape);
		}
	}
	
	public void modifyShapeIfAccepted(Dialog dlg, Shape selectedShape) {
		if(dlg.isAccepted()) {
			Shape shapeWithNeValues = dlg.getShapeFromDialog();
			executeCmdModifyShape(selectedShape, shapeWithNeValues);
		}
	}
	
	private void executeCmdModifyShape(Shape selectedShape, Shape shapeWithNeValues) {
		CmdModify cmdModify = new CmdModify(selectedShape, shapeWithNeValues);
		cmdModify.execute();
		
	}

	private void executeCmdAddShape(Shape shape) {
		CmdAdd cmdAdd = new CmdAdd(model, shape);
		cmdAdd.execute();
	}
	
}
