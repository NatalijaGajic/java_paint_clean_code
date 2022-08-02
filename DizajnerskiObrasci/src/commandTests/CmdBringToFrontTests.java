package commandTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import commands.CmdBringToFront;
import geometry.Circle;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import model.DrawingModel;

public class CmdBringToFrontTests {

	private DrawingModel model;
	private Point point;
	private Circle circle;
	private Rectangle rect;
	private Line line;
	private CmdBringToFront cmdBringToFront;
	
	@BeforeEach
	public void setUp() {

		model = new DrawingModel();
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
	public void testExecuteShapeMoved() {
		cmdBringToFront = new CmdBringToFront(model, circle);
		int indexBeforeMoving = model.getIndexOfShape(circle);
		cmdBringToFront.execute();
		int indexAfterMoving = model.getIndexOfShape(circle);
		int expectedIndex = model.getNumberOfSelectedShapes();
		assertEquals(expectedIndex, indexAfterMoving);
		
	}
	
	@Test
	public void testExecuteFirstAddedShapeMoved() {
		cmdBringToFront = new CmdBringToFront(model, point);
		int indexBeforeMoving = model.getIndexOfShape(point);
		cmdBringToFront.execute();
		int indexAfterMoving = model.getIndexOfShape(point);
		int expectedIndex = model.getNumberOfShapes();
		assertEquals(expectedIndex, indexAfterMoving);
	}
	
	@Test
	public void testUnexecuteShapeMoved() {
		cmdBringToFront = new CmdBringToFront(model, circle);
		int indexBeforeMoving = model.getIndexOfShape(circle);
		cmdBringToFront.execute();
		cmdBringToFront.unexecute();
		int indexAfterMoving = model.getIndexOfShape(circle);
		assertEquals(indexBeforeMoving, indexAfterMoving);
	}
	
	@Test
	public void testUnexecuteFirstAddedShapeMoved() {
		cmdBringToFront = new CmdBringToFront(model, point);
		int indexBeforeMoving = model.getIndexOfShape(point);
		cmdBringToFront.execute();
		cmdBringToFront.unexecute();
		int indexAfterMoving = model.getIndexOfShape(point);
		assertEquals(indexBeforeMoving, indexAfterMoving);
	}
}
