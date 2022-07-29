package commandTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import commands.CmdAdd;
import geometry.Point;
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
	public void testExecute() {
		cmdAdd.execute();
		assertTrue(model.doesContainShape(point));
	}

	@Test
	public void testUnexecuteExecuteNotCalled() {
		cmdAdd.unexecute();
		assertFalse(model.doesContainShape(point));
	}

	@Test
	public void testUnexecuteExecuteCalled() {
		cmdAdd.execute();
		cmdAdd.unexecute();
		assertFalse(model.doesContainShape(point));
	}

}
