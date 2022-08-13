package observerTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import controllers.ButtonsController;
import controllers.DrawingController;
import frame.*;
import geometry.*;
import model.DrawingModel;

class CollectionOfSelectedShapesTests {

	private DrawingModel model;
	private DrawingFrame frame;
	private DrawingController controller;
	private ButtonsController buttonsController;
	private FrameOptionsToolBar optionsToolBar;
	private Point point;
	private Circle circle;
	private Rectangle rect;
	private Line line;
	
	@BeforeEach
	public void setUp() {
		model = new DrawingModel();
		frame = new DrawingFrame();
		controller = new DrawingController(model, frame);
		buttonsController = new ButtonsController(model, frame);
		optionsToolBar = frame.getOptionsToolBar();
		model.getCollectionOfSelectedShapes().registerObserver(frame.getOptionsToolBar());
		frame.setDrawingController(controller);
		frame.setDrawingControllerForToolBars(controller);
		frame.setButtonsControllerForOptionsToolBar(buttonsController);
		initializeShapes();
		addShapes();
	}
	
	private void initializeShapes() {
		point = new Point(100, 100, true);
		circle = new Circle(new Point(11, 11), 10, true);
		rect = new Rectangle(new Point(11, 11), 40, 40, true);
		line = new Line(new Point(1, 1), new Point(1, 10), true);
	}
	
	private void addShapes() {
		model.addShape(point);
		model.addShape(circle);
		model.addShape(rect);
		model.addShape(line);
	}
	
	@Test
	public void testAddSelectedShape_OneSelected_ButtonsEnabled() {
		model.addSelectedShape(rect);
		assertTrue(oneSelectedButtonsAreEnabled());
	}
	
	private boolean oneSelectedButtonsAreEnabled() {
		return optionsToolBar.isBtnDeleteEnabled() && optionsToolBar.isBtnModifyEnabled();
	}
	
	@Test
	public void testRemoveSelectedShape_NoneSelected_ButtonsDisabled() {
		model.addSelectedShape(rect);
		model.removeSelectedShape(rect);
		assertTrue(noneSelectedButtonsAreDisabled());
	}
	
	private boolean noneSelectedButtonsAreDisabled() {
		return editButtonsAreDisabled() && positionButtonsAreDisabled(); 
	}
	
	private boolean editButtonsAreDisabled() {
		return !optionsToolBar.isBtnDeleteEnabled() && !optionsToolBar.isBtnModifyEnabled();
	}
	
	private boolean positionButtonsAreDisabled() {
		return !optionsToolBar.isBtnBringToFrontEnabled() && !optionsToolBar.isBtnBringToBackEnabled()
				&& !optionsToolBar.isBtnToFrontEnabled() && !optionsToolBar.isBtnToBackEnabled();
	}
	
	@Test
	public void testAddSelectedShape_MultipleSelected_ButtonsDisabled() {
		model.addSelectedShape(rect);
		model.addSelectedShape(point);
		assertTrue(multipleSelectedButtonsAreDisabled());
	}
	
	private boolean multipleSelectedButtonsAreDisabled() {
		return !optionsToolBar.isBtnModifyEnabled() && positionButtonsAreDisabled();
	}
	
	@Test
	public void testAddSelectedShape_MultipleSelected_ButtonsEnabled() {
		model.addSelectedShape(rect);
		model.addSelectedShape(point);
		assertTrue(optionsToolBar.isBtnDeleteEnabled());
	}
	

	@Test
	public void testAddSelectedShape_LastAddedShapeSelected_ButtonsToFrontDisabled() {
		model.addSelectedShape(line);
		assertTrue(buttonsToFrontAreDisabled());
	}
	
	private boolean buttonsToFrontAreDisabled() {
		return !optionsToolBar.isBtnBringToFrontEnabled() && !optionsToolBar.isBtnToFrontEnabled();
	}
	
	@Test
	public void testAddSelectedShape_FirstAddedShapeSelected_ButtonsToBackDisabled() {
		model.addSelectedShape(point);
		assertTrue(buttonsToBackAreDisabled());
	}
	
	private boolean buttonsToBackAreDisabled() {
		return !optionsToolBar.isBtnBringToBackEnabled() && !optionsToolBar.isBtnToBackEnabled();
	}
	
	@Test
	public void testAddSelectedShape_PositionButtonsEnabled() {
		model.addSelectedShape(rect);
		assertTrue(positionButtonsAreEnabled());
	}
	
	private boolean positionButtonsAreEnabled() {
		return buttonsToBackAreEnabled() && buttonsToFrontAreEnabled();
	}
	
	private boolean buttonsToBackAreEnabled() {
		return optionsToolBar.isBtnBringToBackEnabled() && optionsToolBar.isBtnToBackEnabled();
	}
	
	private boolean buttonsToFrontAreEnabled() {
		return optionsToolBar.isBtnBringToFrontEnabled() && optionsToolBar.isBtnToFrontEnabled();
	}


}
