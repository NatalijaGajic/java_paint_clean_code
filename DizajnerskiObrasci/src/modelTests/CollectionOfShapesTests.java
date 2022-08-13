package modelTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import controllers.*;
import frame.*;
import geometry.*;
import model.DrawingModel;

class CollectionOfShapesTests {

	private DrawingModel model;
	private DrawingFrame frame;
	private DrawingController controller;
	private ButtonsController buttonsController;
	private FrameOptionsToolBar optionsToolBar;
	private Point point;
	private Circle circle;
	private Rectangle rect;
	private Line line;
	private Rectangle newShape;
	
	@BeforeEach
	public void setUp() {
		model = new DrawingModel();
		frame = new DrawingFrame();
		controller = new DrawingController(model, frame);
		buttonsController = new ButtonsController(model, frame);
		optionsToolBar = frame.getOptionsToolBar();
		model.getCollectionOfShapes().registerObserver(frame.getOptionsToolBar());
		frame.setDrawingController(controller);
		frame.setDrawingControllerForToolBars(controller);
		frame.setButtonsControllerForOptionsToolBar(buttonsController);
		initializeShapes();
		addShapes();
	}
	
	private void initializeShapes() {
		point = new Point(100, 100);
		circle = new Circle(new Point(11, 11), 10);
		rect = new Rectangle(new Point(11, 11), 40, 40);
		line = new Line(new Point(1, 1), new Point(1, 10));
		newShape = new Rectangle(new Point(11, 11), 40, 40);
	}
	
	private void addShapes() {
		model.addShape(point);
		model.addShape(circle);
		model.addShape(rect);
		model.addShape(line);
	}
	
	@Test
	public void testAddShape_LastAddedSelected_ToFrontButtonsEnabled() {
		model.addSelectedShape(line);
		assertFalse(buttonsToFrontAreEnabled());
		model.addShape(newShape);
		assertTrue(buttonsToFrontAreEnabled());
	}
	
	private boolean buttonsToFrontAreEnabled() {
		return optionsToolBar.isBtnBringToFrontEnabled() && optionsToolBar.isBtnToFrontEnabled();
	}
	
	@Test
	public void testAddShapeAtIndex_FirstAddedSelected_MovedToFrontToBackButtonsEnabled() {
		model.addSelectedShape(point);
		assertFalse(buttonsToBackAreEnabled());
		model.removeShape(point);
		model.addShapeAtIndex(1, point);
		assertTrue(buttonsToBackAreEnabled());
	}
	
	private boolean buttonsToBackAreEnabled() {
		return optionsToolBar.isBtnBringToBackEnabled() && optionsToolBar.isBtnToBackEnabled();
	}

	@Test
	public void testAddShapeAtIndex_LastAddedSelected_MovedToBackToFrontButtonsEnabled() {
		model.addSelectedShape(line);
		assertFalse(buttonsToFrontAreEnabled());
		model.removeShape(line);
		model.addShapeAtIndex(2, line);
		assertTrue(buttonsToFrontAreEnabled());
	}

}
