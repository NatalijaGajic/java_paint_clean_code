package controllers;

import applications.DrawingFrame;
import applications.DrawingModel;
import commands.CmdAdd;
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
			makeCmdAddShape(shape);
		}
	}
	
	private void makeCmdAddShape(Shape shape) {
		CmdAdd cmdAdd = new CmdAdd(model, shape);
		cmdAdd.execute();
	}
	
}
