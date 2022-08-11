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
		frame.setDrawingController(controller);

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
	public void testAddPointIfAccepted_WhenAccepted_PointAddedToModel() {
		DlgPoint dlg = new DlgPoint();
		dlg.setModifyDialogFields(testPoint);
		dlg.setAccepted(true);
		controller.addShapeIfAccepted(dlg);
		assertTrue(model.doesContainShape(testPoint));
	}
	
	@Test
	public void testAddPointIfAccepted_WhenNotAccepted_PointNotAddedToModel() {
		DlgPoint dlg = new DlgPoint();
		testPoint.setX(12345);
		dlg.setModifyDialogFields(testPoint);
		dlg.setAccepted(false);
		controller.addShapeIfAccepted(dlg);
		assertFalse(model.doesContainShape(testPoint));
	}
	
	@Test
	public void testModifyPointIfAccepted_WhenAccepted_PointModified() {
		model.addShape(testPoint);
		DlgPoint dlg = new DlgPoint();
		Point modifiedShape = new Point(10, 10, false, Color.blue);
		dlg.setModifyDialogFields(modifiedShape);
		dlg.setAccepted(true);
		controller.modifyShapeIfAccepted(dlg, testPoint);
		assertTrue(model.doesContainShape(testPoint));
		assertTrue(testPoint.equals(modifiedShape));
	}
	
	@Test
	public void testModifyPointIfAccepted_WhenNotAccepted_PointNotModified() {
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
	public void testAddLineIfAccepted_WhenAccepted_LineAddedToModel() {
		DlgLine dlg = new DlgLine();
		dlg.setModifyDialogFields(testLine);
		dlg.setAccepted(true);
		controller.addShapeIfAccepted(dlg);
		assertTrue(model.doesContainShape(testLine));
	}
	
	@Test
	public void testAddLineIfAccepted_WhenNotAccepted_LineNotAddedToModel() {
		DlgLine dlg = new DlgLine();
		dlg.setModifyDialogFields(testLine);
		dlg.setAccepted(false);
		controller.addShapeIfAccepted(dlg);
		assertFalse(model.doesContainShape(testLine));
	}
	
	@Test
	public void testModifyLineIfAccepted_WhenAccepted_LineModified() {
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
	public void testModifyLineIfAccepted_WhenNotAccepted_LineNotModified() {
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
	public void testAddRectangleIfAccepted_WhenAccepted_RectangleAddedToModel() {
		DlgRectangle dlg = new DlgRectangle();
		dlg.setModifyDialogFields(testRectangle);
		dlg.setAccepted(true);
		controller.addShapeIfAccepted(dlg);
		assertTrue(model.doesContainShape(testRectangle));
	}
	
	@Test
	public void testAddRectangleIfAccepted_WhenNotAccepted_RectangleNotAddedToModel() {
		DlgRectangle dlg = new DlgRectangle();
		testRectangle.setHeight(0);
		dlg.setModifyDialogFields(testRectangle);
		dlg.setAccepted(false);
		controller.addShapeIfAccepted(dlg);
		assertFalse(model.doesContainShape(testRectangle));
	}
	
	@Test
	public void testModifyRectangleIfAccepted_WhenAccepted_RectangleModified() {
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
	public void testModifyRectangleIfAccepted_WhenNotAccepted_RectangleNotModified() {
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
	public void testAddCircleIfAccepted_WhenAccepted_CircleAddedToModel() {
		DlgCircle dlg = new DlgCircle();
		dlg.setModifyDialogFields(testCircle);
		dlg.setAccepted(true);
		controller.addShapeIfAccepted(dlg);
		assertTrue(model.doesContainShape(testCircle));
	}
	
	@Test
	public void testAddCircleIfAccepted_WhenNotAccepted_CircleNotAddedToModels() {
		DlgCircle dlg = new DlgCircle();
		testCircle.setRadius(0);
		dlg.setModifyDialogFields(testCircle);
		dlg.setAccepted(false);
		controller.addShapeIfAccepted(dlg);
		assertFalse(model.doesContainShape(testCircle));
	}
	
	@Test
	public void testModifyCircleIfAccepted_WhenAccepted_CircleModified() {
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
	public void testModifyCircleIfAccepted_WhenNotAccepted_CircleNotModified() {
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
	public void testAddDonutIfAccepted_WhenAccepted_DonutAddedToModel() {
		DlgDonut dlg = new DlgDonut();
		dlg.setModifyDialogFields(testDonut);
		dlg.setAccepted(true);
		controller.addShapeIfAccepted(dlg);
		assertTrue(model.doesContainShape(testDonut));
	}
	
	@Test
	public void testAddDonutIfAccepted_WhenNotAccepted_DonutNotAddedToModel() {
		DlgDonut dlg = new DlgDonut();
		testDonut.setRadius(0);
		dlg.setModifyDialogFields(testDonut);
		dlg.setAccepted(false);
		controller.addShapeIfAccepted(dlg);
		assertFalse(model.doesContainShape(testDonut));
	}
	
	@Test
	public void testModifyDonutIfAccepted_WhenAccepted_DonutModified() {
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
	public void testModifyDonutIfAccepted_WhenNotAccepted_DonutNotModified() {
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
	public void testAddHexagonIfAccepted_WhenAccepted_HexagonAddedToModel() {
		DlgHexagon dlg = new DlgHexagon();
		dlg.setModifyDialogFields(testHexagon);
		dlg.setAccepted(true);
		controller.addShapeIfAccepted(dlg);
		assertTrue(model.doesContainShape(testHexagon));
	}
	
	@Test
	public void testAddHexagonIfAccepted_WhenNotAccepted_HexagonNotAddedToModel() {
		DlgHexagon dlg = new DlgHexagon();
		testHexagon.setRadius(0);
		dlg.setModifyDialogFields(testHexagon);
		dlg.setAccepted(false);
		controller.addShapeIfAccepted(dlg);
		assertFalse(model.doesContainShape(testHexagon));
	}
	
	@Test
	public void testModifyHexagonIfAccepted_WhenAccepted_HexagonModified() {
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
	public void testModifyHexagonIfAccepted_WhenNotAccepted_HexagonNotModified() {
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
	public void testSelectOrDeselectShape_Select_ShapeSelected() {
		Point point = new Point(10, 10, false, Color.BLACK);
		model.addShape(point);
		controller.selectOrDeselectShape(click);
		assertTrue(point.isSelected());
	}
	
	@Test
	public void testSelectOrDeselectShape_Select_ShapeAddedToSelectedShapes() {
		Point point = new Point(10, 10, false, Color.BLACK);
		model.addShape(point);
		controller.selectOrDeselectShape(click);
		assertTrue(model.doesContainSelectedShape(point));
	}

	@Test
	public void testSelectOrDeselectShape_Deselect_ShapeDeSelected() {
		Point point = new Point(10, 10, true, Color.BLACK);
		model.addShape(point);
		model.addSelectedShape(point);
		controller.selectOrDeselectShape(click);
		assertFalse(point.isSelected());
	}

	@Test
	public void testSelectOrDeselectShape_Deselect_ShapeRemovedFromSelectedShapes() {
		Point point = new Point(10, 10, true, Color.BLACK);
		model.addShape(point);
		model.addSelectedShape(point);
		controller.selectOrDeselectShape(click);
		assertFalse(model.doesContainSelectedShape(point));
	}

}
