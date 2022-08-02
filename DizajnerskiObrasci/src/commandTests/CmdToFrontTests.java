package commandTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import commands.*;
import geometry.Circle;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import model.DrawingModel;

public class CmdToFrontTests {

	private DrawingModel model;
	private Point point;
	private Circle circle;
	private Rectangle rect;
	private Line line;
	private CmdToFront cmdToFront;
	
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
		cmdToFront = new CmdToFront(model, circle);
		int indexBeforeMoving = model.getIndexOfShape(circle);
		cmdToFront.execute();
		int indexAfterMoving = model.getIndexOfShape(circle);
		int expectedIndex = indexBeforeMoving + 1;
		assertEquals(expectedIndex, indexAfterMoving);
		
	}
	
	@Test
	public void testExecuteFirstAddedShapeMoved() {
		cmdToFront = new CmdToFront(model, point);
		int indexBeforeMoving = model.getIndexOfShape(point);
		cmdToFront.execute();
		int indexAfterMoving = model.getIndexOfShape(point);
		int expectedIndex = indexBeforeMoving + 1;
		assertEquals(expectedIndex, indexAfterMoving);
	}
	
	@Test
	public void testUnexecuteShapeMoved() {
		cmdToFront = new CmdToFront(model, circle);
		int indexBeforeMoving = model.getIndexOfShape(circle);
		cmdToFront.execute();
		cmdToFront.unexecute();
		int indexAfterMoving = model.getIndexOfShape(circle);
		assertEquals(indexBeforeMoving, indexAfterMoving);
	}
	
	@Test
	public void testUnexecuteFirstAddedShapeMoved() {
		cmdToFront = new CmdToFront(model, point);
		int indexBeforeMoving = model.getIndexOfShape(point);
		cmdToFront.execute();
		cmdToFront.unexecute();
		int indexAfterMoving = model.getIndexOfShape(point);
		assertEquals(indexBeforeMoving, indexAfterMoving);
	}

}