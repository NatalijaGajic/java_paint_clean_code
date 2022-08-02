package commandTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import commands.CmdSelect;
import geometry.Point;
import model.DrawingModel;

public class CmdSelectTests {
	
	private DrawingModel model;
	private Point point;
	private CmdSelect cmdSelect;

	@Before
	public void setUp() {
		model = new DrawingModel();
		point = new Point(10, 20, false);
		model.addShape(point);
		cmdSelect = new CmdSelect(model, point);
	}

	@Test
	public void testExecuteShapeNotSelected() {
		cmdSelect.execute();
		assertTrue(point.isSelected());
	}

	@Test
	public void testExecuteShapeRemovedFromSelectedShapes() {
		cmdSelect.execute();
		assertTrue(model.doesContainSelectedShape(point));
	}

	@Test
	public void testUnexecuteShapeIsSelected() {
		cmdSelect.unexecute();
		assertFalse(point.isSelected());
	}

	@Test
	public void testUnexecuteShapeAddedToSelectedShapes() {
		cmdSelect.unexecute();
		assertFalse(model.doesContainSelectedShape(point));
	}
}