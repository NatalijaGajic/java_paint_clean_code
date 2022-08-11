package commandTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Color;
import commands.CmdModify;
import geometry.Point;
import logger.LoggerConstants;

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

}
