package commandTests;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;
import org.junit.Before;
import org.junit.Test;
import commands.*;
import geometry.Point;
import logger.LoggerConstants;
import model.DrawingModel;

public class CmdSelectTests {
	
	private DrawingModel model;
	private Point point;
	private CmdSelect cmdSelect;

	@Before
	public void setUp() {
		model = new DrawingModel();
		point = new Point(10, 20, false, Color.black);
		model.addShape(point);
		cmdSelect = new CmdSelect(model, point);
	}

	@Test
	public void testExecute_ShapeIsSelected() {
		cmdSelect.execute();
		assertTrue(point.isSelected());
	}

	@Test
	public void testExecute_ShapeAddedToSelectedShapes() {
		cmdSelect.execute();
		assertTrue(model.doesContainSelectedShape(point));
	}

	@Test
	public void testUnexecute_ShapeNotSelected() {
		cmdSelect.unexecute();
		assertFalse(point.isSelected());
	}

	@Test
	public void testUnexecute_ShapeRemovedFromSelectedShapes() {
		cmdSelect.unexecute();
		assertFalse(model.doesContainSelectedShape(point));
	}
	
	@Test
	public void testToString_Success() {
		String expected =  LoggerConstants.SELECT_COMMAND + " " + point.toString();
		assertEquals(expected, cmdSelect.toString());
	}
	
	@Test 
	public void testEquals_NotSameType() {
		CmdDeselect cmdDeselect = new CmdDeselect(model, point);
		assertFalse(cmdSelect.equals(cmdDeselect));
	}
	
	@Test 
	public void testEquals_ExpectFalse() {
		Point otherPoint = new Point(20, 20, true);
		CmdSelect otherCmd = new CmdSelect(model, otherPoint);
		assertFalse(cmdSelect.equals(otherCmd));
	}
	
	@Test 
	public void testEquals_ExpectTrue() {
		CmdSelect sameCmd = new CmdSelect(model, point);
		assertTrue(cmdSelect.equals(sameCmd));
	}
}
