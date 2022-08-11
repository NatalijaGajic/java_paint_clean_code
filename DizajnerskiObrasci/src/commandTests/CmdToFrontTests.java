package commandTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import commands.*;
import geometry.*;
import logger.LoggerConstants;
import model.DrawingModel;
import java.awt.Color;

public class CmdToFrontTests {

	private DrawingModel model;
	private Point point;
	private Circle circle;
	private Rectangle rect;
	private Line line;
	private CmdToFront cmdToFront;
	
	@BeforeEach
	public void setUp() {

		model = new DrawingModel();
		initializeShapes();
		addShapes();
	}
	
	private void initializeShapes() {
		point = new Point(100, 100, true);
		circle = new Circle(new Point(11, 11), 10, true, Color.black, Color.white);
		rect = new Rectangle(new Point(11, 11), 40, 40, true);
		line = new Line(new Point(1, 1), new Point(1, 10), true);
	}
	
	private void addShapes() {
		model.addShape(point);
		model.addShape(circle);
		model.addShape(rect);
		model.addShape(line);
	}
	
	@Test
	public void testExecute_ShapeMoved() {
		cmdToFront = new CmdToFront(model, circle);
		int indexBeforeMoving = model.getIndexOfShape(circle);
		cmdToFront.execute();
		int indexAfterMoving = model.getIndexOfShape(circle);
		int expectedIndex = indexBeforeMoving + 1;
		assertEquals(expectedIndex, indexAfterMoving);
		
	}
	
	@Test
	public void testExecute_FirstAddedShapeMoved() {
		cmdToFront = new CmdToFront(model, point);
		int indexBeforeMoving = model.getIndexOfShape(point);
		cmdToFront.execute();
		int indexAfterMoving = model.getIndexOfShape(point);
		int expectedIndex = indexBeforeMoving + 1;
		assertEquals(expectedIndex, indexAfterMoving);
	}
	
	@Test
	public void testUnexecute_ShapeMoved() {
		cmdToFront = new CmdToFront(model, circle);
		int indexBeforeMoving = model.getIndexOfShape(circle);
		cmdToFront.execute();
		cmdToFront.unexecute();
		int indexAfterMoving = model.getIndexOfShape(circle);
		assertEquals(indexBeforeMoving, indexAfterMoving);
	}
	
	@Test
	public void testUnexecute_FirstAddedShapeMoved() {
		cmdToFront = new CmdToFront(model, point);
		int indexBeforeMoving = model.getIndexOfShape(point);
		cmdToFront.execute();
		cmdToFront.unexecute();
		int indexAfterMoving = model.getIndexOfShape(point);
		assertEquals(indexBeforeMoving, indexAfterMoving);
	}
	
	@Test
	public void testToString_Success() {
		cmdToFront = new CmdToFront(model, circle);
		String expected =  LoggerConstants.TO_FRONT_COMMAND + " " + circle.toString();
		assertEquals(expected, cmdToFront.toString());
	}

}
