package commands;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import geometry.Point;
import logger.TypeOfCommand;
import model.DrawingModel;
import java.awt.Color;

public class CmdDeselectTests {

	private DrawingModel model;
	private Point point;
	private CmdDeselect cmdDeselect;

	@BeforeEach
	public void setUp() {
		model = new DrawingModel();
		point = new Point(10, 10, false, Color.black);
		model.addShape(point);
		cmdDeselect = new CmdDeselect(model, point);
	}

	@Test
	public void testExecute_ShapeNotSselected() {
		cmdDeselect.execute();
		assertFalse(point.isSelected());
	}

	@Test
	public void testExecute_ShapeRemovedFromSelectedShapes() {
		cmdDeselect.execute();
		assertFalse(model.doesContainSelectedShape(point));
	}

	@Test
	public void testUnexecute_ShapeIsSelected() {
		cmdDeselect.execute();
		cmdDeselect.unexecute();
		assertTrue(point.isSelected());
	}

	@Test
	public void testUnexecute_ShapeAddedToSelectedShapes() {
		cmdDeselect.execute();
		cmdDeselect.unexecute();
		assertTrue(model.doesContainSelectedShape(point));
	}
	
	@Test
	public void testToString_Success() {
		String expected =  TypeOfCommand.DESELECT_COMMAND + " " + point.toString();
		assertEquals(expected, cmdDeselect.toString());
	}
	
	@Test 
	public void testEquals_NotSameType() {
		CmdSelect cmdSelect = new CmdSelect(model, point);
		assertFalse(cmdDeselect.equals(cmdSelect));
	}
	
	@Test 
	public void testEquals_ExpectFalse() {
		Point otherPoint = new Point(20, 20, true);
		CmdDeselect otherCmd = new CmdDeselect(model, otherPoint);
		assertFalse(cmdDeselect.equals(otherCmd));
	}
	
	@Test 
	public void testEquals_ExpectTrue() {
		CmdDeselect sameCmd = new CmdDeselect(model, point);
		assertTrue(cmdDeselect.equals(sameCmd));
	}
}
