package controllerTests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import commandHandler.CommandsHandler;
import controllers.DrawingController;
import dialogs.*;
import frame.DrawingFrame;
import geometry.*;
import hexagon.Hexagon;
import logger.LogWriter;
import model.DrawingModel;


class DrawingControllerTests {


	private DrawingModel model;
	private DrawingFrame frame;
	private DrawingController controller;
	private CommandsHandler commandsHandler;
	private LogWriter logWriter;
	private Point click;
	private Point testPoint;
	private Line testLine;
	private Rectangle testRectangle;
	private Circle testCircle;
	private Donut testDonut;
	private HexagonAdapter testHexagon;
	
	
	@BeforeEach
	public void setUp() {
		model = new DrawingModel();
		frame = new DrawingFrame();
		controller = new DrawingController(model, frame);
		commandsHandler = new CommandsHandler();
		logWriter = new LogWriter();
		
		controller.setCommandsHandler(commandsHandler);
		controller.setLogWriter(logWriter);
		frame.getView().setModel(model);
		frame.setController(controller);

		initializeShapes();
	}
	
	private void initializeShapes() {
		click = new Point(10, 10);
		testPoint = new Point(1, 1, false, Color.BLACK);
		testLine = new Line(new Point(1,1), new Point(2,2), false, Color.BLACK);
		testRectangle = new Rectangle(new Point(1,1), 40, 50, false, Color.WHITE, Color.BLACK);
		testCircle = new Circle(new Point(1, 1), 10, false, Color.WHITE, Color.BLACK);
		testDonut = new Donut(new Point(1, 1), 10, 5, false, Color.WHITE, Color.BLACK);
		testHexagon = new HexagonAdapter(new Point(1,2), 3, Color.WHITE, Color.BLACK);
	}
	
	
	@Test
	public void testAddPointIfAcceptedWhenAccepted() {
		DlgPoint dlg = new DlgPoint();
		dlg.setModifyDialogFields(testPoint);
		dlg.setAccepted(true);
		
		controller.addShapeIfAccepted(dlg);
		assertTrue(model.doesContainShape(testPoint));
	}
	
	@Test
	public void testAddPointIfAcceptedWhenNotAccepted() {
		DlgPoint dlg = new DlgPoint();
		testPoint.setX(12345);
		dlg.setModifyDialogFields(testPoint);
		dlg.setAccepted(false);
		
		controller.addShapeIfAccepted(dlg);
		assertFalse(model.doesContainShape(testPoint));
	}
	
	@Test
	public void testModifyPointIfAcceptedWhenAccepted() {
		model.addShape(testPoint);
		DlgPoint dlg = new DlgPoint();
		Point modifiedShape = new Point(10, 10, false, Color.blue);
		dlg.setModifyDialogFields(testPoint);
		dlg.setAccepted(true);
		
		controller.modifyShapeIfAccepted(dlg, testPoint);
		assertTrue(model.doesContainShape(testPoint));
		assertTrue(testPoint.equals(testPoint));
	}
	
	@Test
	public void testModifyPointIfAcceptedWhenNotAccepted() {
		model.addShape(testPoint);
		DlgPoint dlg = new DlgPoint();
		Point modifiedShape = new Point(10, 12345, false, Color.blue);
		dlg.setModifyDialogFields(modifiedShape);
		dlg.setAccepted(false);
		
		controller.modifyShapeIfAccepted(dlg, testPoint);
		assertTrue(model.doesContainShape(testPoint));
		assertFalse(testPoint.equals(modifiedShape));
	}
	
	@Test
	public void testAddLineIfAcceptedWhenAccepted() {
		DlgLine dlg = new DlgLine();
		dlg.setModifyDialogFields(testLine);
		dlg.setAccepted(true);
		
		controller.addShapeIfAccepted(dlg);
		assertTrue(model.doesContainShape(testLine));
	}
	
	@Test
	public void testAddLineIfAcceptedWhenNotAccepted() {
		DlgLine dlg = new DlgLine();
		dlg.setModifyDialogFields(testLine);
		dlg.setAccepted(false);
		
		controller.addShapeIfAccepted(dlg);
		assertFalse(model.doesContainShape(testLine));
	}
	
	@Test
	public void testModifyLineIfAcceptedWhenAccepted() {
		model.addShape(testLine);
		DlgLine dlg = new DlgLine();
		Line modifiedShape = new Line(new Point(0, 0), new Point(3, 3), false, Color.blue);
		dlg.setModifyDialogFields(modifiedShape);
		dlg.setAccepted(true);
		
		controller.modifyShapeIfAccepted(dlg, testLine);
		assertTrue(model.doesContainShape(testLine));
		assertTrue(testLine.equals(modifiedShape));
	}
	
	@Test
	public void testModifyLineIfAcceptedWhenNotAccepted() {
		model.addShape(testLine);
		DlgLine dlg = new DlgLine();
		Line modifiedShape = new Line(new Point(0, 0), new Point(3, 3), false, Color.blue);
		dlg.setModifyDialogFields(modifiedShape);
		dlg.setAccepted(false);
		
		controller.modifyShapeIfAccepted(dlg, testLine);
		assertTrue(model.doesContainShape(testLine));
		assertFalse(testLine.equals(modifiedShape));
	}
	
	@Test
	public void testAddRectangleIfAcceptedWhenAccepted() {
		DlgRectangle dlg = new DlgRectangle();
		dlg.setModifyDialogFields(testRectangle);
		dlg.setAccepted(true);
		
		controller.addShapeIfAccepted(dlg);
		assertTrue(model.doesContainShape(testRectangle));
	}
	
	@Test
	public void testAddRectangleIfAcceptedWhenNotAccepted() {
		DlgRectangle dlg = new DlgRectangle();
		testRectangle.setHeight(0);
		dlg.setModifyDialogFields(testRectangle);
		dlg.setAccepted(false);
		
		controller.addShapeIfAccepted(dlg);
		assertFalse(model.doesContainShape(testRectangle));
	}
	
	@Test
	public void testModifyRectangleIfAcceptedWhenAccepted() {
		model.addShape(testRectangle);
		DlgRectangle dlg = new DlgRectangle();
		Rectangle modifiedShape = new Rectangle(new Point(0, 0), 10, 10, false, Color.green, Color.blue);
		dlg.setModifyDialogFields(modifiedShape);
		dlg.setAccepted(true);
		
		controller.modifyShapeIfAccepted(dlg, testRectangle);
		assertTrue(model.doesContainShape(testRectangle));
		assertTrue(testRectangle.equals(modifiedShape));
	}
	
	@Test
	public void testModifyRectangleIfAcceptedWhenNotAccepted() {
		model.addShape(testRectangle);
		DlgRectangle dlg = new DlgRectangle();
		Rectangle modifiedShape = new Rectangle(new Point(0, 0), 0, 10, false, Color.green, Color.blue);
		dlg.setModifyDialogFields(modifiedShape);
		dlg.setAccepted(false);
		
		controller.modifyShapeIfAccepted(dlg, testRectangle);
		assertTrue(model.doesContainShape(testRectangle));
		assertFalse(testRectangle.equals(modifiedShape));
	}
	
	
	@Test
	public void testAddCircleIfAcceptedWhenAccepted() {
		DlgCircle dlg = new DlgCircle();
		dlg.setModifyDialogFields(testCircle);
		dlg.setAccepted(true);
		
		controller.addShapeIfAccepted(dlg);
		assertTrue(model.doesContainShape(testCircle));
	}
	
	@Test
	public void testAddCircleIfAcceptedWhenNotAccepted() {
		DlgCircle dlg = new DlgCircle();
		testCircle.setRadius(0);
		dlg.setModifyDialogFields(testCircle);
		dlg.setAccepted(false);
		
		controller.addShapeIfAccepted(dlg);
		assertFalse(model.doesContainShape(testCircle));
	}
	
	@Test
	public void testModifyCircleIfAcceptedWhenAccepted() {
		model.addShape(testCircle);
		DlgCircle dlg = new DlgCircle();
		Circle modifiedShape = new Circle(new Point(10, 10), 6, false, Color.blue, Color.green);
		dlg.setModifyDialogFields(modifiedShape);
		dlg.setAccepted(true);
		
		controller.modifyShapeIfAccepted(dlg, testCircle);
		assertTrue(model.doesContainShape(testCircle));
		assertTrue(testCircle.equals(modifiedShape));
	}
	
	@Test
	public void testModifyCircleIfAcceptedWhenNotAccepted() {
		model.addShape(testCircle);
		DlgCircle dlg = new DlgCircle();
		Circle modifiedShape = new Circle(new Point(10, 10), 0, false, Color.blue, Color.green);
		dlg.setModifyDialogFields(modifiedShape);
		dlg.setAccepted(false);
		
		controller.modifyShapeIfAccepted(dlg, testCircle);
		assertTrue(model.doesContainShape(testCircle));
		assertFalse(testCircle.equals(modifiedShape));
	}
	
	@Test
	public void testAddDonutIfAcceptedWhenAccepted() {
		DlgDonut dlg = new DlgDonut();
		dlg.setModifyDialogFields(testDonut);
		dlg.setAccepted(true);
		
		controller.addShapeIfAccepted(dlg);
		assertTrue(model.doesContainShape(testDonut));
	}
	
	@Test
	public void testAddDonutIfAcceptedWhenNotAccepted() {
		DlgDonut dlg = new DlgDonut();
		testDonut.setRadius(0);
		dlg.setModifyDialogFields(testDonut);
		dlg.setAccepted(false);
		
		controller.addShapeIfAccepted(dlg);
		assertFalse(model.doesContainShape(testDonut));
	}
	
	@Test
	public void testModifyDonutIfAcceptedWhenAccepted() {
		model.addShape(testDonut);
		DlgDonut dlg = new DlgDonut();
		Donut modifiedShape = new Donut(new Point(10, 10), 15, 10, false, Color.blue, Color.green);
		dlg.setModifyDialogFields(modifiedShape);
		dlg.setAccepted(true);
		
		controller.modifyShapeIfAccepted(dlg, testDonut);
		assertTrue(model.doesContainShape(testDonut));
		assertTrue(testDonut.equals(modifiedShape));
	}
	
	@Test
	public void testModifyDonutIfAcceptedWhenNotAccepted() {
		model.addShape(testDonut);
		DlgDonut dlg = new DlgDonut();
		Donut modifiedShape = new Donut(new Point(10, 10), 15, 20, false, Color.blue, Color.green);
		dlg.setModifyDialogFields(modifiedShape);
		dlg.setAccepted(false);
		
		controller.modifyShapeIfAccepted(dlg, testDonut);
		assertTrue(model.doesContainShape(testDonut));
		assertFalse(testDonut.equals(modifiedShape));
	}
	
	
	@Test
	public void testAddHexagonIfAcceptedWhenAccepted() {
		DlgHexagon dlg = new DlgHexagon();
		dlg.setModifyDialogFields(testHexagon);
		dlg.setAccepted(true);
		
		controller.addShapeIfAccepted(dlg);
		assertTrue(model.doesContainShape(testHexagon));
	}
	
	@Test
	public void testAddHexagonIfAcceptedWhenNotAccepted() {
		DlgHexagon dlg = new DlgHexagon();
		testHexagon.setRadius(0);
		dlg.setModifyDialogFields(testHexagon);
		dlg.setAccepted(false);
		
		controller.addShapeIfAccepted(dlg);
		assertFalse(model.doesContainShape(testHexagon));
	}
	
	@Test
	public void testModifyHexagonIfAcceptedWhenAccepted() {
		model.addShape(testHexagon);
		DlgHexagon dlg = new DlgHexagon();
		HexagonAdapter modifiedShape = new HexagonAdapter(new Point(1,2), 3, Color.blue, Color.green, false);
		dlg.setModifyDialogFields(modifiedShape);
		dlg.setAccepted(true);
		
		controller.modifyShapeIfAccepted(dlg, testHexagon);
		assertTrue(model.doesContainShape(testHexagon));
		assertTrue(testHexagon.equals(modifiedShape));
	}
	
	@Test
	public void testModifyHexagonIfAcceptedWhenNotAccepted() {
		model.addShape(testHexagon);
		DlgHexagon dlg = new DlgHexagon();
		HexagonAdapter modifiedShape = new HexagonAdapter(new Point(1,2), 0, Color.blue, Color.green, false);
		dlg.setModifyDialogFields(modifiedShape);
		dlg.setAccepted(false);
		
		controller.modifyShapeIfAccepted(dlg, testHexagon);
		assertTrue(model.doesContainShape(testHexagon));
		assertFalse(testHexagon.equals(modifiedShape));
	}
	
	@Test
	public void testSelectOrDeselectShapeShapeIsNotSelected() {
		Point point = new Point(10, 10, false, Color.BLACK);
		model.addShape(point);
		controller.selectOrDeselectShape(click);
		assertTrue(point.isSelected());
		assertTrue(model.doesContainSelectedShape(point));
	}

	@Test
	public void testSelectOrDeselectShapeShapeIsSelected() {
		Point point = new Point(10, 10, true, Color.BLACK);
		model.addShape(point);
		model.addSelectedShape(point);
		controller.selectOrDeselectShape(click);
		assertFalse(point.isSelected());
		assertFalse(model.doesContainSelectedShape(point));
	}


}
