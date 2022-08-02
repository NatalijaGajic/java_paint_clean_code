package observerTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllers.DrawingController;
import frame.DrawingFrame;
import frame.FrameOptionsToolBar;
import geometry.Circle;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import model.DrawingModel;

class CollectionOfSelectedShapesTests {

	private DrawingModel model;
	private DrawingFrame frame;
	private DrawingController controller;
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
		optionsToolBar = frame.getOptionsToolBar();
		
		model.getCollectionOfSelectedShapes().registerObserver(frame.getOptionsToolBar());
		frame.setController(controller);
		frame.setControllerForToolBars(controller);
		
		point = new Point(100, 100, true);
		circle = new Circle(new Point(11, 11), 10, true);
		rect = new Rectangle(new Point(11, 11), 40, 40, true);
		line = new Line(new Point(1, 1), new Point(1, 10), true);
		addShapes();
	}
	
	private void addShapes() {
		model.addShape(point);
		model.addShape(circle);
		model.addShape(rect);
		model.addShape(line);
	}
	
	@Test
	void testAddOneSelectedButtonsEnabled() {
		rect.setSelected(true);
		model.addSelectedShape(rect);
		assertTrue(oneSelectedButtonsAreEnabled());
	}
	
	private boolean oneSelectedButtonsAreEnabled() {
		return editButtonsAreEnabled() && positionButtonsAreEnabled();
	}
	
	private boolean editButtonsAreEnabled() {
		return optionsToolBar.isBtnDeleteEnabled() && optionsToolBar.isBtnModifyEnabled();
	}
	
	private boolean positionButtonsAreEnabled() {
		return optionsToolBar.isBtnBringToFrontEnabled() && optionsToolBar.isBtnBringToBackEnabled()
				&& optionsToolBar.isBtnToFrontEnabled() && optionsToolBar.isBtnToBackEnabled();
	}
	
	@Test
	void testAddRemoveNoneSelectedButtonsDisabled() {
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
	void testAddMultipleSelectedButtonsDisabled() {
		model.addSelectedShape(rect);
		model.addSelectedShape(point);
		assertTrue(multipleSelectedButtonsAreDisabled());
	}
	
	private boolean multipleSelectedButtonsAreDisabled() {
		return !optionsToolBar.isBtnModifyEnabled() && positionButtonsAreDisabled();
	}
	
	@Test
	void testAddMultipleSelectedButtonsEnabled() {
		model.addSelectedShape(rect);
		model.addSelectedShape(point);
		assertTrue(optionsToolBar.isBtnDeleteEnabled());
	}
	

	@Test
	void testAddLastAddedShapeSelectedButtonsToFrontDisabled() {
		model.addSelectedShape(line);
		assertTrue(buttonsToFrontAreDisabled());
	}
	
	private boolean buttonsToFrontAreDisabled() {
		return !optionsToolBar.isBtnBringToFrontEnabled() && !optionsToolBar.isBtnToFrontEnabled();
	}
	
	@Test
	void testAddFirstAddedShapeSelectedButtonsToBackDisabled() {
		model.addSelectedShape(point);
		assertTrue(buttonsToBackAreDisabled());
	}
	
	private boolean buttonsToBackAreDisabled() {
		return !optionsToolBar.isBtnBringToBackEnabled() && !optionsToolBar.isBtnToBackEnabled();
	}


}
