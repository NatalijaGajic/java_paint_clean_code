package controllers;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import applications.DrawingModel;
import commands.*;
import dialogs.*;
import frame.DrawingFrame;
import geometry.*;

public class DrawingController {

	private DrawingModel model;
	private DrawingFrame frame;
	private Color activeEdgeColor = Color.BLACK;
	private Color activeInnerColor = Color.WHITE;
	
	private Point startPoint;
	private Shape selected;
	
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
	
	public void modifyShape() {
		if (selected != null) {
			if (selected instanceof Point) {
				modifyPointIfAccepted(selected);
			} else if (selected instanceof Line) {
				modifyLineIfAccepted(selected);
			} else if (selected instanceof Rectangle) {
				modifyRectangleIfAccepted(selected);
			} else if (selected instanceof Donut) {
				modifyDonutIfAccepted(selected);
			} else if (selected instanceof Circle) {
				modifyCircleIfAccepted(selected);
			}
		}
		frame.getView().repaint();
	}
	
	public void deleteShapes() {
		String[] options = { "Yes", "No" };
		if (selected != null) {
			int option = JOptionPane.showOptionDialog(null, "Are you sure?", "WARNING!", JOptionPane.OK_OPTION,
					JOptionPane.ERROR_MESSAGE, null, options, options[0]);
			if (option == 0) {
				model.getShapes().remove(selected);
				frame.getView().repaint();
			}
		}
	}
	
	//TODO: selected shapes in model
	public void selectOrDeselectShape(Point click) {
		selected = null;
		Iterator<Shape> it = model.getShapes().iterator();
		while(it.hasNext()) {
			Shape shape = it.next();
			shape.setSelected(false);
			if(shape.contains(click))
				selected = shape;
		}
	}
	
	public void setActiveEdgeColor() {
		activeEdgeColor = JColorChooser.showDialog(null, "Choose edge color", Color.BLACK);
		if (activeEdgeColor != null) {
			JPanel activeEdgeColorPanel = frame.getColorToolBar().getPnlActiveEdgeColor();
			activeEdgeColorPanel.setBackground(activeEdgeColor);
		}
	}
	
	public void setActiveInnerColor() {
		activeInnerColor = JColorChooser.showDialog(null, "Choose inner color", Color.WHITE);
		if (activeInnerColor != null) {
			JPanel pnlActiveInnerColor = frame.getColorToolBar().getPnlActiveInnerColor();
			pnlActiveInnerColor.setBackground(activeInnerColor);
		}
	}
	
	public void drawPointIfAccepted(Point click) {
		DlgPoint dlgPoint = new DlgPoint();
		dlgPoint.setCreateDialogFields(click, activeEdgeColor);
		dlgPoint.setVisible(true);
		addShapeIfAccepted(dlgPoint);
	}
	
	public void drawLineIfAccepted(Point click) {
		if (startPoint == null)
			startPoint = click;
		else {
			DlgLine dlgLine = new DlgLine();
			dlgLine.setCreateDialogFields(startPoint, click, activeEdgeColor);
			dlgLine.setVisible(true);
			addShapeIfAccepted(dlgLine);
			startPoint = null;
		}
	}
	
	public void drawCircleIfAccepted(Point click) {
		DlgCircle dlgCircle = new DlgCircle();
		dlgCircle.setCreateDialogFields(click, activeEdgeColor, activeInnerColor);
		dlgCircle.setVisible(true);
		addShapeIfAccepted(dlgCircle);
	}
	
	public void drawRectangleIfAccepted(Point click) {
		DlgRectangle dlgRect = new DlgRectangle();
		dlgRect.setCreateDialogFields(click, activeEdgeColor, activeInnerColor);
		dlgRect.setVisible(true);
		addShapeIfAccepted(dlgRect);
	}
	
	public void drawDonutIfAccepted(Point click) {
		DlgDonut dlgDonut = new DlgDonut();
		dlgDonut.setCreateDialogFields(click, activeEdgeColor, activeInnerColor);
		dlgDonut.setVisible(true);
		addShapeIfAccepted(dlgDonut);
	}
	
	public void drawHexagonIfAccepted(Point click) {
	}
	
	public void modifyPointIfAccepted(Shape selectedShape) {
		Point point = (Point) selected;
		DlgPoint dlg = new DlgPoint();
		dlg.setModifyDialogFields(point);
		dlg.setVisible(true);
		modifyShapeIfAccepted(dlg, point);
	}
	
	public void modifyLineIfAccepted(Shape selectedShape) {
		Point point = (Point) selected;
		DlgPoint dlg = new DlgPoint();
		dlg.setModifyDialogFields(point);
		dlg.setVisible(true);
		modifyShapeIfAccepted(dlg, point);
	}
	
	public void modifyRectangleIfAccepted(Shape selectedShape) {
		Rectangle rectangle = (Rectangle) selected;
		DlgRectangle dlg = new DlgRectangle();
		dlg.setModifyDialogFields(rectangle);
		dlg.setVisible(true);
		modifyShapeIfAccepted(dlg, rectangle);
	}
	
	public void modifyDonutIfAccepted(Shape selectedShape) {
		Donut donut = (Donut) selected;
		DlgDonut dlg = new DlgDonut();
		dlg.setModifyDialogFields(donut);
		dlg.setVisible(true);
		modifyShapeIfAccepted(dlg, donut);
	}
	
	public void modifyCircleIfAccepted(Shape selectedShape) {
		Circle circle = (Circle) selected;
		DlgCircle dlg = new DlgCircle();
		dlg.setModifyDialogFields(circle);
		dlg.setVisible(true);
		modifyShapeIfAccepted(dlg, circle);
	}
	
	public void modifyHexagonIfAccepted(Shape selectedShape) {
		
	}
}
