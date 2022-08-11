package commandTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Before;
import org.junit.Test;
import commands.CmdDeselect;
import geometry.Point;
import logger.LoggerConstants;
import model.DrawingModel;
import java.awt.Color;

public class CmdDeselectTests {

	private DrawingModel model;
	private Point point;
	private CmdDeselect cmdDeselect;

	@Before
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
		cmdDeselect.unexecute();
		assertTrue(point.isSelected());
	}

	@Test
	public void testUnexecute_ShapeAddedToSelectedShapes() {
		cmdDeselect.unexecute();
		assertTrue(model.doesContainSelectedShape(point));
	}
	
	@Test
	public void testToString_Success() {
		String expected =  LoggerConstants.DESELECT_COMMAND + " " + point.toString();
		assertEquals(expected, cmdDeselect.toString());
	}
}
