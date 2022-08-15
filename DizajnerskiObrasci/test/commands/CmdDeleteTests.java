package commands;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import logger.LoggerConstants;
import model.DrawingModel;
import java.awt.Color;
import geometry.*;

class CmdDeleteTests {

	private DrawingModel model;
	private ArrayList<Shape> addedShapes;
	private ArrayList<Shape> shapesToDelete;
	private CmdDelete cmdDelete;
	private Point point;
	private Circle circle;
	private Rectangle rect;
	private Line line;

	@BeforeEach
	public void setUp() {
		shapesToDelete = new ArrayList<Shape>();
		addedShapes = new ArrayList<Shape>();
		model = new DrawingModel();
		initializeShapes();
		addShapes();
		addShapesToDelete();
		addShapesToModel();
		
		cmdDelete = new CmdDelete(model, shapesToDelete);
	}
	
	private void initializeShapes() {
		point = new Point(100, 100, true, Color.black);
		circle = new Circle(new Point(11, 11), 10, true);
		rect = new Rectangle(new Point(11, 11), 40, 40, true, Color.black, Color.white);
		line = new Line(new Point(1, 1), new Point(1, 10), true, Color.black);
	}
	
	private void addShapes() {
		addedShapes.add(point);
		addedShapes.add(circle);
		addedShapes.add(rect);
		addedShapes.add(line);
	}
	
	private void addShapesToDelete() {
		shapesToDelete.add(line);
		shapesToDelete.add(rect);
		shapesToDelete.add(point);
	}
	
	private void addShapesToModel() {
		model.addShapes(addedShapes);
		model.addSelectedShapes(shapesToDelete);
	}
	

	@Test
	public void testExecute_ShapesAreRemoved() {
		cmdDelete.execute();
		assertFalse(model.getShapes().containsAll(shapesToDelete));
		assertFalse(model.getSelectedShapes().containsAll(shapesToDelete));
	}


	@Test
	public void testUnexecute_DeletedShapesAreAdded() {
		cmdDelete.execute();
		cmdDelete.unexecute();
		assertTrue(model.getShapes().containsAll(shapesToDelete));
		assertTrue(model.getSelectedShapes().containsAll(shapesToDelete));
	}
	
	@Test
	public void testUnexecute_PositionsOfShapesRemainPerserved() {
		cmdDelete.execute();
		cmdDelete.unexecute();
		ArrayList<Shape> shapes = model.getShapes();
		assertTrue(shapes.equals(addedShapes));
	}
	
	@Test
	public void testToString_Success() {
		String expected =  LoggerConstants.DELETE_COMMAND + " " + line.toString() + ";" + rect.toString() + ";" + point.toString();
		assertEquals(expected, cmdDelete.toString());
	}
	
	@Test 
	void testEquals_NotSameType() {
		CmdSelect cmdSelect = new CmdSelect(model, point);
		assertFalse(cmdDelete.equals(cmdSelect));
	}
	
	@Test 
	void testEquals_ExpectFalse() {
		Point otherPoint = new Point(20,20);
		ArrayList<Shape> otherShapesToDelete = new ArrayList<Shape>();
		otherShapesToDelete.add(otherPoint);
		CmdDelete otherCmd = new CmdDelete(model, otherShapesToDelete);
		assertFalse(cmdDelete.equals(otherCmd));
	}
	
	@Test 
	void testEquals_ExpectTrue() {
		CmdDelete sameCmd = new CmdDelete(model, shapesToDelete);
		assertTrue(cmdDelete.equals(sameCmd));
	}

}
