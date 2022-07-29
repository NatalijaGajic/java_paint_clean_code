package controllerTests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import applications.DrawingFrame;
import applications.DrawingModel;
import controllers.DrawingController;
import dialogs.*;
import geometry.*;


class DrawingControllerTests {


	private DrawingModel model;
	private DrawingFrame frame;
	private DrawingController controller;
	private Rectangle testedShape;
	
	
	@BeforeEach
	public void setUp() {
		model = new DrawingModel();
		frame = new DrawingFrame();
		controller = new DrawingController(model, frame);
		frame.getView().setModel(model);
		frame.setController(controller);
		testedShape = new Rectangle(new Point(1,1), 40, 50, false, Color.white, Color.black);
	}
	
	@Test
	public void testAddShapeIfAcceptedWhenAccepted() {
		DlgRectangle dlg = new DlgRectangle();
		dlg.setModifyDialogFields(testedShape);
		dlg.setAccepted(true);
		
		controller.addShapeIfAccepted(dlg);
		assertTrue(model.doesContainShape(testedShape));
	}
	
	@Test
	public void testModifyShapeIfAcceptedWhenAccepted() {
		model.addShape(testedShape);
		DlgRectangle dlg = new DlgRectangle();
		Rectangle modifiedShape = new Rectangle(new Point(0, 0), 10, 10, false, Color.green, Color.blue);
		dlg.setModifyDialogFields(modifiedShape);
		dlg.setAccepted(true);
		
		controller.modifyShapeIfAccepted(dlg, testedShape);
		assertTrue(model.doesContainShape(testedShape));
		assertTrue(testedShape.equals(modifiedShape));
	}
	
	@Test
	public void testAddShapeIfAcceptedWhenNotAccepted() {
		DlgRectangle dlg = new DlgRectangle();
		testedShape.setHeight(0);
		dlg.setModifyDialogFields(testedShape);
		dlg.setAccepted(false);
		
		controller.addShapeIfAccepted(dlg);
		assertFalse(model.doesContainShape(testedShape));
	}
	
	@Test
	public void testModifyShapeIfAcceptedWhenNotAccepted() {
		model.addShape(testedShape);
		DlgRectangle dlg = new DlgRectangle();
		Rectangle modifiedShape = new Rectangle(new Point(0, 0), 0, 10, false, Color.green, Color.blue);
		dlg.setModifyDialogFields(modifiedShape);
		dlg.setAccepted(false);
		
		controller.modifyShapeIfAccepted(dlg, testedShape);
		assertTrue(model.doesContainShape(testedShape));
		assertFalse(testedShape.equals(modifiedShape));
	}

}
