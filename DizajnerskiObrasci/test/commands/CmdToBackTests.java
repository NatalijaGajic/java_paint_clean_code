package commands;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import geometry.*;
import logger.TypeOfCommand;
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
		initializeShapes();
		addShapes();
	}
	
	private void initializeShapes() {
		point = new Point(100, 100, true);
		circle = new Circle(new Point(11, 11), 10, true, Color.black, Color.white);
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
	public void testExecute_ShapeMoved() {
		cmdToBack = new CmdToBack(model, circle);
		int indexBeforeMoving = model.getIndexOfShape(circle);
		cmdToBack.execute();
		int indexAfterMoving = model.getIndexOfShape(circle);
		int expectedIndex = indexBeforeMoving - 1;
		assertEquals(expectedIndex, indexAfterMoving);
		
	}
	
	@Test
	public void testExecute_LastAddedShapeMoved() {
		cmdToBack = new CmdToBack(model, line);
		int indexBeforeMoving = model.getIndexOfShape(line);
		cmdToBack.execute();
		int indexAfterMoving = model.getIndexOfShape(line);
		int expectedIndex = indexBeforeMoving - 1;
		assertEquals(expectedIndex, indexAfterMoving);
	}
	
	@Test
	public void testUnexecute_ShapeMoved() {
		cmdToBack = new CmdToBack(model, circle);
		int indexBeforeMoving = model.getIndexOfShape(circle);
		cmdToBack.execute();
		cmdToBack.unexecute();
		int indexAfterMoving = model.getIndexOfShape(circle);
		assertEquals(indexBeforeMoving, indexAfterMoving);
	}
	
	@Test
	public void testUnexecute_LastAddedShapeMoved() {
		cmdToBack = new CmdToBack(model, line);
		int indexBeforeMoving = model.getIndexOfShape(line);
		cmdToBack.execute();
		cmdToBack.unexecute();
		int indexAfterMoving = model.getIndexOfShape(line);
		assertEquals(indexBeforeMoving, indexAfterMoving);
	}
	
	@Test
	public void testToString_Success() {
		cmdToBack = new CmdToBack(model, circle);
		String expected =  TypeOfCommand.TO_BACK_COMMAND + " " + circle.toString();
		assertEquals(expected, cmdToBack.toString());
	}
	
	@Test 
	public void testEquals_NotSameType() {
		CmdToBack cmdToBack = new CmdToBack(model, circle);
		CmdSelect cmdSelect = new CmdSelect(model, point);
		assertFalse(cmdToBack.equals(cmdSelect));
		
	}
	
	@Test 
	public void testEquals_ExpectFalse() {
		CmdToBack cmdToBack = new CmdToBack(model, circle);
		Circle otherCircle = new Circle(new Point(10, 10), 10, Color.black, Color.blue);
		CmdToBack otherCmd = new CmdToBack(model, otherCircle);
		assertFalse(cmdToBack.equals(otherCmd));
	}
	
	@Test 
	public void testEquals_ExpectTrue() {
		CmdToBack cmdToBack = new CmdToBack(model, circle);
		CmdToBack otherCmd = new CmdToBack(model, circle);
		assertTrue(cmdToBack.equals(otherCmd));
	}

}
