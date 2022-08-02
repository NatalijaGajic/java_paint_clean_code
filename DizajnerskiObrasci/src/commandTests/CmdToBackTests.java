package commandTests;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import commands.*;
import geometry.*;
import model.DrawingModel;

class CmdToBackTests {

	private DrawingModel model;
	private Point point;
	private Circle circle;
	private Rectangle rect;
	private Line line;
	private CmdToBack cmdToBack;
	
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
		cmdToBack = new CmdToBack(model, circle);
		int indexBeforeMoving = model.getIndexOfShape(circle);
		cmdToBack.execute();
		int indexAfterMoving = model.getIndexOfShape(circle);
		int expectedIndex = indexBeforeMoving - 1;
		assertEquals(expectedIndex, indexAfterMoving);
		
	}
	
	@Test
	public void testExecuteLastAddedShapeMoved() {
		cmdToBack = new CmdToBack(model, line);
		int indexBeforeMoving = model.getIndexOfShape(line);
		cmdToBack.execute();
		int indexAfterMoving = model.getIndexOfShape(line);
		int expectedIndex = indexBeforeMoving - 1;
		assertEquals(expectedIndex, indexAfterMoving);
	}
	
	@Test
	public void testUnexecuteShapeMoved() {
		cmdToBack = new CmdToBack(model, circle);
		int indexBeforeMoving = model.getIndexOfShape(circle);
		cmdToBack.execute();
		cmdToBack.unexecute();
		int indexAfterMoving = model.getIndexOfShape(circle);
		assertEquals(indexBeforeMoving, indexAfterMoving);
	}
	
	@Test
	public void testUnexecuteLastAddedShapeMoved() {
		cmdToBack = new CmdToBack(model, line);
		int indexBeforeMoving = model.getIndexOfShape(line);
		cmdToBack.execute();
		cmdToBack.unexecute();
		int indexAfterMoving = model.getIndexOfShape(line);
		assertEquals(indexBeforeMoving, indexAfterMoving);
	}

}
