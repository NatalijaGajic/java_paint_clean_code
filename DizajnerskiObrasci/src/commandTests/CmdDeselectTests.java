package commandTests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import commands.CmdDeselect;
import geometry.Point;
import model.DrawingModel;

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
	public void testExecuteShapeNotSselected() {
		cmdDeselect.execute();
		assertFalse(point.isSelected());
	}

	@Test
	public void testExecuteShapeRemovedFromSelectedShapes() {
		cmdDeselect.execute();
		assertFalse(model.doesContainSelectedShape(point));
	}

	@Test
	public void testUnexecuteShapeIsSelected() {
		cmdDeselect.unexecute();
		assertTrue(point.isSelected());
	}

	@Test
	public void testUnexecuteShapeAddedToSelectedShapes() {
		cmdDeselect.unexecute();
		assertTrue(model.doesContainSelectedShape(point));
	}
	
	@Test
	public void testToString() {
		String expected =  "Deselected " + point.toString();
		assertEquals(expected, cmdDeselect.toString());
	}
}
