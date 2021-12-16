package controllers;

import java.awt.event.MouseEvent;

import geometry.DrawingModel;
import views.DrawingFrame;
import views.DrawingView;

public class DrawingController {

	DrawingModel model;
	DrawingFrame frame;
	
	DrawingController(DrawingModel model, DrawingFrame frame){
		this.model = model;
		this.frame = frame;
	}
	
	public void MouseClicked(MouseEvent e) {
		
		//Verovatno ce ici obzerver obrazac ovde
	}
	
}
