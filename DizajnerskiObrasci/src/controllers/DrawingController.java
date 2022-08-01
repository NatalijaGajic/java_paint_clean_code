package controllers;

import java.awt.Color;
import java.awt.List;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import commands.*;
import dialogs.*;
import frame.DrawingFrame;
import geometry.*;
import model.DrawingModel;

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
		Shape selectedShape = model.getSelectedShape();

		if (selectedShape instanceof Point) {
			modifyPointIfAccepted(selectedShape);
		} else if (selectedShape instanceof Line) {
			modifyLineIfAccepted(selectedShape);
		} else if (selectedShape instanceof Rectangle) {
			modifyRectangleIfAccepted(selectedShape);
		} else if (selectedShape instanceof Donut) {
			modifyDonutIfAccepted(selectedShape);
		} else if (selectedShape instanceof Circle) {
			modifyCircleIfAccepted(selectedShape);
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
	
	/**
	 * Start at the end of the list, (de)select the last added shape that contains the point of the click
	 * @param click
	 */
	public void selectOrDeselectShape(Point click) {
		for(int indexOfShape = model.getNumberOfShapes() - 1; indexOfShape >= 0; indexOfShape--) {
			Shape shape = model.getShapeAtIndex(indexOfShape);
			
			if(shape.contains(click) && !shape.isSelected()) {
				executeCmdSelectShape(shape);
				break;
			}else if(shape.contains(click) && shape.isSelected()) {
				executeCmdDeselectShape(shape);
				break;
			}
		}
		
	}
	
	private void executeCmdSelectShape(Shape shape) {
		CmdSelect cmdSelect = new CmdSelect(model, shape);
		cmdSelect.execute();
	}
	
	private void executeCmdDeselectShape(Shape shape) {
		CmdDeselect cmdDeselect = new CmdDeselect(model, shape);
		cmdDeselect.execute();
	}
	
	public void setActiveEdgeColor() {
		Color chosenColor = JColorChooser.showDialog(null, "Choose edge color", Color.BLACK);
		if (chosenColor != null) {
			activeEdgeColor = chosenColor;
			JPanel activeEdgeColorPanel = frame.getColorToolBar().getPnlActiveEdgeColor();
			activeEdgeColorPanel.setBackground(activeEdgeColor);
		}
	}
	
	public void setActiveInnerColor() {
		Color chosenColor = JColorChooser.showDialog(null, "Choose inner color", Color.WHITE);
		if (chosenColor != null) {
			activeInnerColor = chosenColor;
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
		Point point = (Point) selectedShape;
		DlgPoint dlg = new DlgPoint();
		dlg.setModifyDialogFields(point);
		dlg.setVisible(true);
		modifyShapeIfAccepted(dlg, point);
	}
	
	public void modifyLineIfAccepted(Shape selectedShape) {
		Point point = (Point) selectedShape;
		DlgPoint dlg = new DlgPoint();
		dlg.setModifyDialogFields(point);
		dlg.setVisible(true);
		modifyShapeIfAccepted(dlg, point);
	}
	
	public void modifyRectangleIfAccepted(Shape selectedShape) {
		Rectangle rectangle = (Rectangle) selectedShape;
		DlgRectangle dlg = new DlgRectangle();
		dlg.setModifyDialogFields(rectangle);
		dlg.setVisible(true);
		modifyShapeIfAccepted(dlg, rectangle);
	}
	
	public void modifyDonutIfAccepted(Shape selectedShape) {
		Donut donut = (Donut) selectedShape;
		DlgDonut dlg = new DlgDonut();
		dlg.setModifyDialogFields(donut);
		dlg.setVisible(true);
		modifyShapeIfAccepted(dlg, donut);
	}
	
	public void modifyCircleIfAccepted(Shape selectedShape) {
		Circle circle = (Circle) selectedShape;
		DlgCircle dlg = new DlgCircle();
		dlg.setModifyDialogFields(circle);
		dlg.setVisible(true);
		modifyShapeIfAccepted(dlg, circle);
	}
	
	public void modifyHexagonIfAccepted(Shape selectedShape) {
		
	}
}
