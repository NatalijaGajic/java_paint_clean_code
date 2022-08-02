package commandTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import commands.CmdBringToBack;
import geometry.Circle;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import model.DrawingModel;

class CmdBringToBackTests {

	private DrawingModel model;
	private Point point;
	private Circle circle;
	private Rectangle rect;
	private Line line;
	private CmdBringToBack cmdToBack;
	
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
		cmdToBack = new CmdBringToBack(model, circle);
		int indexBeforeMoving = model.getIndexOfShape(circle);
		cmdToBack.execute();
		int indexAfterMoving = model.getIndexOfShape(circle);
		assertEquals(0, indexAfterMoving);
		
	}
	
	@Test
	public void testExecuteLastAddedShapeMoved() {
		cmdToBack = new CmdBringToBack(model, line);
		int indexBeforeMoving = model.getIndexOfShape(line);
		cmdToBack.execute();
		int indexAfterMoving = model.getIndexOfShape(line);
		assertEquals(0, indexAfterMoving);
	}
	
	@Test
	public void testUnexecuteShapeMoved() {
		cmdToBack = new CmdBringToBack(model, circle);
		int indexBeforeMoving = model.getIndexOfShape(circle);
		cmdToBack.execute();
		cmdToBack.unexecute();
		int indexAfterMoving = model.getIndexOfShape(circle);
		assertEquals(indexBeforeMoving, indexAfterMoving);
	}
	
	@Test
	public void testUnexecuteLastAddedShapeMoved() {
		cmdToBack = new CmdBringToBack(model, line);
		int indexBeforeMoving = model.getIndexOfShape(line);
		cmdToBack.execute();
		cmdToBack.unexecute();
		int indexAfterMoving = model.getIndexOfShape(line);
		assertEquals(indexBeforeMoving, indexAfterMoving);
	}

}
