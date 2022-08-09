package commandTests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import commands.CmdModify;
import geometry.Point;

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
	public void testExecuteModifyPointCommandExpectTrue() {
		commandModifyPoint.execute();
		assertTrue(oldState.equals(newState));
	}

	@Test
	public void testUnexecuteModifyPointCommandExpectTrue() {
		commandModifyPoint.execute();
		commandModifyPoint.unexecute();
		assertTrue(oldState.equals(savedOldStatePoint));
	}
	
	@Test
	public void testToString() {
		String expected =  "Modified " + oldState.toString() + " to " + newState.toString();
		assertEquals(expected, commandModifyPoint.toString());
	}

}
