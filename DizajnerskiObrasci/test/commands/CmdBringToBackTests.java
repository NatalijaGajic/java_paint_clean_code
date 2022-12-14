package commands;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.DrawingModel;
import java.awt.Color;
import geometry.*;
import logger.TypeOfCommand;


class CmdBringToBackTests {

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
		point = new Point(100, 100, true, Color.black);
		circle = new Circle(new Point(11, 11), 10, Color.black, Color.blue);
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
		CmdBringToBack cmdToBack = new CmdBringToBack(model, circle);
		cmdToBack.execute();
		int indexAfterMoving = model.getIndexOfShape(circle);
		assertEquals(0, indexAfterMoving);
		
	}
	
	@Test
	public void testExecute_LastAddedShapeMoved() {
		CmdBringToBack cmdToBack = new CmdBringToBack(model, line);
		cmdToBack.execute();
		int indexAfterMoving = model.getIndexOfShape(line);
		assertEquals(0, indexAfterMoving);
	}
	
	@Test
	public void testUnexecute_ShapeMoved() {
		CmdBringToBack cmdToBack = new CmdBringToBack(model, circle);
		int indexBeforeMoving = model.getIndexOfShape(circle);
		cmdToBack.execute();
		cmdToBack.unexecute();
		int indexAfterMoving = model.getIndexOfShape(circle);
		assertEquals(indexBeforeMoving, indexAfterMoving);
	}
	
	@Test
	public void testUnexecute_LastAddedShapeMoved() {
		CmdBringToBack cmdToBack = new CmdBringToBack(model, line);
		int indexBeforeMoving = model.getIndexOfShape(line);
		cmdToBack.execute();
		cmdToBack.unexecute();
		int indexAfterMoving = model.getIndexOfShape(line);
		assertEquals(indexBeforeMoving, indexAfterMoving);
	}
	
	@Test
	public void testToString_Success() {
		CmdBringToBack cmdToBack = new CmdBringToBack(model, circle);
		String expected =  TypeOfCommand.BRING_TO_BACK_COMMAND + " " + circle.toString();
		assertEquals(expected, cmdToBack.toString());
	}
	
	@Test 
	public void testEquals_NotSameType() {
		CmdBringToBack cmdToBack = new CmdBringToBack(model, circle);
		CmdSelect cmdSelect = new CmdSelect(model, point);
		assertFalse(cmdToBack.equals(cmdSelect));
		
	}
	
	@Test 
	public void testEquals_ExpectFalse() {
		CmdBringToBack cmdToBack = new CmdBringToBack(model, circle);
		Circle otherCircle = new Circle(new Point(10, 10), 10, Color.black, Color.blue);
		CmdBringToBack otherCmd = new CmdBringToBack(model, otherCircle);
		assertFalse(cmdToBack.equals(otherCmd));
	}
	
	@Test 
	public void testEquals_ExpectTrue() {
		CmdBringToBack cmdToBack = new CmdBringToBack(model, circle);
		CmdBringToBack otherCmd = new CmdBringToBack(model, circle);
		assertTrue(cmdToBack.equals(otherCmd));
	}

}
