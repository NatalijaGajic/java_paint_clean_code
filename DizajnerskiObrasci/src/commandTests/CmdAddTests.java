package commandTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import commands.CmdAdd;
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
		point = new Point(10, 10);
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
	
	public void testToString_Success() {
		String expected =  LoggerConstants.ADD_COMMAND + " " + point.toString();
		assertEquals(expected, cmdAdd.toString());
	}

}
