package commands;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import logger.LoggerConstants;
import model.DrawingModel;
import java.awt.Color;
import geometry.*;

public class CmdBringToFrontTests {

	private DrawingModel model;
	private Point point;
	private Circle circle;
	private Rectangle rect;
	private Line line;
	
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
		CmdBringToFront cmdBringToFront = new CmdBringToFront(model, circle);
		cmdBringToFront.execute();
		int indexAfterMoving = model.getIndexOfShape(circle);
		int expectedIndex = model.getNumberOfShapes() - 1;
		assertEquals(expectedIndex, indexAfterMoving);
		
	}
	
	@Test
	public void testExecute_FirstAddedShapeMoved() {
		CmdBringToFront cmdBringToFront = new CmdBringToFront(model, point);
		cmdBringToFront.execute();
		int indexAfterMoving = model.getIndexOfShape(point);
		int expectedIndex = model.getNumberOfShapes() - 1;
		assertEquals(expectedIndex, indexAfterMoving);
	}
	
	@Test
	public void testUnexecute_ShapeMoved() {
		CmdBringToFront cmdBringToFront = new CmdBringToFront(model, circle);
		int indexBeforeMoving = model.getIndexOfShape(circle);
		cmdBringToFront.execute();
		cmdBringToFront.unexecute();
		int indexAfterMoving = model.getIndexOfShape(circle);
		assertEquals(indexBeforeMoving, indexAfterMoving);
	}
	
	@Test
	public void testUnexecute_FirstAddedShapeMoved() {
		CmdBringToFront cmdBringToFront = new CmdBringToFront(model, point);
		int indexBeforeMoving = model.getIndexOfShape(point);
		cmdBringToFront.execute();
		cmdBringToFront.unexecute();
		int indexAfterMoving = model.getIndexOfShape(point);
		assertEquals(indexBeforeMoving, indexAfterMoving);
	}
	
	@Test
	public void testToString_Success() {
		CmdBringToFront cmdBringToFront = new CmdBringToFront(model, circle);
		String expected =  LoggerConstants.BRING_TO_FRONT_COMMAND + " " + circle.toString();
		assertEquals(expected, cmdBringToFront.toString());
	}
	
	@Test 
	public void testEquals_NotSameType() {
		CmdBringToFront cmdBringToFront = new CmdBringToFront(model, circle);
		CmdSelect cmdSelect = new CmdSelect(model, point);
		assertFalse(cmdBringToFront.equals(cmdSelect));
		
	}
	
	@Test 
	public void testEquals_ExpectFalse() {
		CmdBringToFront cmdBringToFront = new CmdBringToFront(model, circle);
		Circle otherCircle = new Circle(new Point(10, 10), 10, Color.black, Color.blue);
		CmdBringToFront otherCmd = new CmdBringToFront(model, otherCircle);
		assertFalse(cmdBringToFront.equals(otherCmd));
	}
	
	@Test 
	public void testEquals_ExpectTrue() {
		CmdBringToFront cmdBringToFront = new CmdBringToFront(model, circle);
		CmdBringToFront otherCmd = new CmdBringToFront(model, circle);
		assertTrue(cmdBringToFront.equals(otherCmd));
	}
}
