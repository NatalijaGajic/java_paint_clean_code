package commandTests;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import commands.*;
import geometry.Point;
import logger.LoggerConstants;
import model.DrawingModel;

class CmdAddTests {

	private DrawingModel model;
	private Point point;
	private CmdAdd cmdAdd;

	@BeforeEach
	public void setUp() {
		model = new DrawingModel();
		point = new Point(10, 10, Color.black);
		cmdAdd = new CmdAdd(model, point);
	}

	@Test
	public void testExecute_ModelContainsShape() {
		cmdAdd.execute();
		assertTrue(model.doesContainShape(point));
	}

	@Test
	public void testUnexecute_ModelDoesntContainShape() {
		cmdAdd.execute();
		cmdAdd.unexecute();
		assertFalse(model.doesContainShape(point));
	}
	
	@Test
	public void testToString_Success() {
		String expected =  LoggerConstants.ADD_COMMAND + " " + point.toString();
		assertEquals(expected, cmdAdd.toString());
	}
	
	@Test 
	public void testEquals_NotSameType() {
		CmdSelect cmdSelect = new CmdSelect(model, point);
		assertFalse(cmdAdd.equals(cmdSelect));
	}
	
	@Test 
	public void testEquals_ExpectFalse() {
		Point otherPoint = new Point(20,20);
		CmdAdd otherCmd = new CmdAdd(model, otherPoint);
		assertFalse(cmdAdd.equals(otherCmd));
	}
	
	@Test 
	public void testEquals_ExpectTrue() {
		CmdAdd sameCmd = new CmdAdd(model, point);
		assertTrue(cmdAdd.equals(sameCmd));
	}

}
