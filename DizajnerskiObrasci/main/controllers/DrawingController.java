package controllers;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.*;
import commands.*;
import dialogs.*;
import frame.DrawingFrame;
import geometry.*;
import model.DrawingModel;

public class DrawingController {

	private DrawingModel model;
	private DrawingFrame frame;
	private CommandController commandController;
	private Color activeEdgeColor;
	private Color activeInnerColor;
	private Point startPoint;
	
	public DrawingController(DrawingModel model, DrawingFrame frame){
		this.model = model;
		this.frame = frame;
		activeEdgeColor = Color.BLACK;
		activeInnerColor = Color.WHITE;
	}
	
	public DrawingController(DrawingModel model, DrawingFrame frame, CommandController commandController){
		this(model, frame);
		this.commandController = commandController;
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
	
	public void drawDonutIfAccepted(Point click) {
		DlgDonut dlgDonut = new DlgDonut();
		dlgDonut.setCreateDialogFields(click, activeEdgeColor, activeInnerColor);
		dlgDonut.setVisible(true);
		addShapeIfAccepted(dlgDonut);
	}
	
	public void drawRectangleIfAccepted(Point click) {
		DlgRectangle dlgRect = new DlgRectangle();
		dlgRect.setCreateDialogFields(click, activeEdgeColor, activeInnerColor);
		dlgRect.setVisible(true);
		addShapeIfAccepted(dlgRect);
	}
	
	public void drawHexagonIfAccepted(Point click) {
		DlgHexagon dlgHexagon = new DlgHexagon();
		dlgHexagon.setCreateDialogFields(click, activeEdgeColor, activeInnerColor);
		dlgHexagon.setVisible(true);
		addShapeIfAccepted(dlgHexagon);
	}
	
	public void addShapeIfAccepted(Dialog dlg) {
		if(dlg.isAccepted()) {
			Shape shape = dlg.getShapeFromDialog();
			CmdAdd cmdAdd = new CmdAdd(model, shape);
			commandController.executeCommand(cmdAdd);
		}
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
		} else if (selectedShape instanceof HexagonAdapter) {
			modifyHexagonIfAccepted(selectedShape);
		}
	}
	
	public void modifyPointIfAccepted(Shape selectedShape) {
		Point point = (Point) selectedShape;
		DlgPoint dlg = new DlgPoint();
		dlg.setModifyDialogFields(point);
		dlg.setVisible(true);
		modifyShapeIfAccepted(dlg, point);
	}
	
	public void modifyLineIfAccepted(Shape selectedShape) {
		Line line = (Line) selectedShape;
		DlgLine dlg = new DlgLine();
		dlg.setModifyDialogFields(line);
		dlg.setVisible(true);
		modifyShapeIfAccepted(dlg, line);
	}
	
	public void modifyCircleIfAccepted(Shape selectedShape) {
		Circle circle = (Circle) selectedShape;
		DlgCircle dlg = new DlgCircle();
		dlg.setModifyDialogFields(circle);
		dlg.setVisible(true);
		modifyShapeIfAccepted(dlg, circle);
	}

	public void modifyDonutIfAccepted(Shape selectedShape) {
		Donut donut = (Donut) selectedShape;
		DlgDonut dlg = new DlgDonut();
		dlg.setModifyDialogFields(donut);
		dlg.setVisible(true);
		modifyShapeIfAccepted(dlg, donut);
	}
	
	public void modifyRectangleIfAccepted(Shape selectedShape) {
		Rectangle rectangle = (Rectangle) selectedShape;
		DlgRectangle dlg = new DlgRectangle();
		dlg.setModifyDialogFields(rectangle);
		dlg.setVisible(true);
		modifyShapeIfAccepted(dlg, rectangle);
	}
	
	public void modifyHexagonIfAccepted(Shape selectedShape) {
		HexagonAdapter hexagonAdapter = (HexagonAdapter) selectedShape;
		DlgHexagon dlg = new DlgHexagon();
		dlg.setModifyDialogFields(hexagonAdapter);
		dlg.setVisible(true);
		modifyShapeIfAccepted(dlg, hexagonAdapter);
	}
	
	public void modifyShapeIfAccepted(Dialog dlg, Shape selectedShape) {
		if(dlg.isAccepted()) {
			Shape shapeWithNewValues = dlg.getShapeFromDialog();
			shapeWithNewValues.setSelected(true);
			CmdModify cmdModify = new CmdModify(selectedShape, shapeWithNewValues);
			commandController.executeCommand(cmdModify);
		}
	}
	
	public void deleteShapesIfAccepted() {
		String[] options = { "Yes", "No" };
		int option = JOptionPane.showOptionDialog(null, "Are you sure?", "Deleting selected shapes", JOptionPane.OK_OPTION,
				JOptionPane.ERROR_MESSAGE, null, options, options[0]);
		if (option == 0) {
			deleteShapes();
		}
	}
	
	public void deleteShapes() {
		@SuppressWarnings("unchecked")
		ArrayList<Shape> shapesToDelete = (ArrayList<Shape>) model.getSelectedShapes().clone();
		CmdDelete cmdDelete = new CmdDelete(model, shapesToDelete);
		commandController.executeCommand(cmdDelete);
	}
	
	/**
	 * Start at the end of the list, (de)select the last added shape that contains the point of the click
	 * @param click
	 */
	public void selectOrDeselectShape(Point click) {
		for (Shape shape : model.getShapes()) {
			
			if(isShapeClickedAndNotSelected(shape, click)) {
				CmdSelect cmdSelect = new CmdSelect(model, shape);
				commandController.executeCommand(cmdSelect);
				break;
			}
			
			if(isShapeClickedAndSelected(shape, click)) {
				CmdDeselect cmdDeselect = new CmdDeselect(model, shape);
				commandController.executeCommand(cmdDeselect);
				break;
			}
		}
		
	}	
	
	private boolean isShapeClickedAndNotSelected(Shape shape, Point click) {
		return shape.contains(click) && !shape.isSelected();
	}
	
	private boolean isShapeClickedAndSelected(Shape shape, Point click) {
		return shape.contains(click) && shape.isSelected();
	}
	
	public void moveShapeToBack() {
		Shape selectedShape = model.getSelectedShape();
		if(model.getIndexOfShape(selectedShape) == 0)
			return;
		CmdToBack cmdToBack = new CmdToBack(model, selectedShape);
		commandController.executeCommand(cmdToBack);
	}
	
	public void moveShapeToFront() {
		Shape selectedShape = model.getSelectedShape();
		if(model.getIndexOfShape(selectedShape) == model.getNumberOfShapes() - 1)
			return;
		CmdToFront cmdToFront = new CmdToFront(model, selectedShape);
		commandController.executeCommand(cmdToFront);
	}
	
	public void bringShapeToFront() {
		Shape selectedShape = model.getSelectedShape();
		if(model.getIndexOfShape(selectedShape) == model.getNumberOfShapes() - 1)
			return;
		CmdBringToFront cmdBringToFront = new CmdBringToFront(model, selectedShape);
		commandController.executeCommand(cmdBringToFront);
	}
	
	public void bringShapeToBack() {
		Shape selectedShape = model.getSelectedShape();
		if(model.getIndexOfShape(selectedShape) == 0)
			return;
		CmdBringToBack cmdBringToBack = new CmdBringToBack(model, selectedShape);
		commandController.executeCommand(cmdBringToBack);
	}	
	
}
