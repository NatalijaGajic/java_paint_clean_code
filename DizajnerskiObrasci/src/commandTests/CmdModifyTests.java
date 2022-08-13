package commandTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Color;
import commands.*;
import geometry.Point;
import logger.LoggerConstants;
import model.DrawingModel;

class CmdModifyTests {

	private Point oldState;
	private Point newState;
	private Point savedOldStatePoint;
	private CmdModify commandModifyPoint;

	@BeforeEach
	public void setUp() {
		oldState = new Point(10, 20, true, Color.BLACK);
		newState = new Point(20, 30, true, Color.BLACK);
		savedOldStatePoint = oldState.clone();
		commandModifyPoint = new CmdModify(oldState, newState);
	}

	@Test
	public void testExecute_ShapeEqualsToNewState() {
		commandModifyPoint.execute();
		assertTrue(oldState.equals(newState));
	}

	@Test
	public void testUnexecute_ShapeEqualsToOldState() {
		commandModifyPoint.execute();
		commandModifyPoint.unexecute();
		assertTrue(oldState.equals(savedOldStatePoint));
	}
	
	@Test
	public void testToString_Success() {
		String expected =  LoggerConstants.MODIFY_COMMAND + " " +  oldState.toString() + " to " + newState.toString();
		assertEquals(expected, commandModifyPoint.toString());
	}
	
	@Test 
	public void testEquals_NotSameType() {
		DrawingModel model = new DrawingModel();
		CmdSelect cmdSelect = new CmdSelect(model, oldState);
		assertFalse(commandModifyPoint.equals(cmdSelect));
	}
	
	@Test 
	public void testEquals_ExpectFalse() {
		Point otherPoint = new Point(20,20);
		CmdModify otherCmd = new CmdModify(oldState, otherPoint);
		assertFalse(commandModifyPoint.equals(otherCmd));
	}
	
	@Test 
	public void testEquals_ExpectTrue() {
		CmdModify sameCmd = new CmdModify(oldState, newState);
		assertTrue(commandModifyPoint.equals(sameCmd));
	}

}
