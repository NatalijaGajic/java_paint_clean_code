package geometry;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;
import java.awt.Color;
import java.awt.Graphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logger.TypeOfShape;

class LineTests {

	private Graphics graphics;
	int xCoordOfStartPoint;
	int yCoordOfStartPoint;
	int xCoordOfEndPoint;
	int yCoordOfEndPoint;
	Color edgeColor;
	private Line line;

	@BeforeEach
	public void setUp() {
		graphics = mock(Graphics.class);
		initializeShapes();
		
	}
	
	private void initializeShapes() {
		xCoordOfStartPoint = 1;
		yCoordOfStartPoint = 2;
		xCoordOfEndPoint = 3;
		yCoordOfEndPoint = 4;
		edgeColor = Color.BLACK;
		line = new Line(new Point(xCoordOfStartPoint, yCoordOfStartPoint, false, edgeColor),
				new Point(xCoordOfEndPoint, yCoordOfEndPoint, false, edgeColor), false, edgeColor);
	}

	@Test
	public void testDrawShape_NotSelected_Success() {
		line.draw(graphics);
		verify(graphics).setColor(edgeColor);
		verify(graphics).drawLine(xCoordOfStartPoint, yCoordOfStartPoint, xCoordOfEndPoint,
				yCoordOfEndPoint);
	}

	@Test
	public void testDraw_ShapeSelected_Success() {
		line.setSelected(true);
		line.draw(graphics);
		verify(graphics).setColor(line.getColor());
		verify(graphics).drawLine(xCoordOfStartPoint, yCoordOfStartPoint, xCoordOfEndPoint, yCoordOfEndPoint);
		verify(graphics).setColor(Color.BLUE);
		verify(graphics).drawRect(xCoordOfStartPoint - 3, yCoordOfStartPoint - 3, 6, 6);
		verify(graphics).drawRect(xCoordOfEndPoint - 3, yCoordOfEndPoint - 3, 6, 6);
		verify(graphics).drawRect(line.middleOfLine().getX() - 3, line.middleOfLine().getY() - 3, 6, 6);
	}

	@Test
	public void testmiddleOfLine_Success() {
		Point expectedMiddleOfLine = new Point((xCoordOfStartPoint + xCoordOfEndPoint) / 2,
				(yCoordOfStartPoint + yCoordOfEndPoint) / 2, false, edgeColor);
		assertEquals(expectedMiddleOfLine, line.middleOfLine());
	}

	@Test
	public void testContains_TrueExcepted() {
		assertTrue(line.contains(1, 2));
	}

	@Test
	public void testContains_FalseExcepted() {
		assertFalse(line.contains(21, 61));
	}

	@Test
	public void testCalculateLength_Success() {
		double extectedLength = line.getStartPoint().distance(xCoordOfEndPoint, yCoordOfEndPoint);
		assertEquals(extectedLength, line.length(), 0);
	}

	@Test
	public void testEquals_NotSameType() {
		assertFalse(line.equals(new Point(1, 2)));
	}

	@Test
	public void testEquals_FalseExpectedStartPoint() {
		assertFalse(line.equals(new Line(new Point(2, 2), new Point(3, 4))));
	}

	@Test
	public void testEquals_FalseExpectedEndPoint() {
		assertFalse(line.equals(new Line(new Point(1, 2), new Point(3, 5))));
	}

	@Test
	public void testEquals_TrueExpected() {
		assertTrue(line.equals(new Line(new Point(1, 2), new Point(3, 4))));
	}

	@Test
	public void testToString_Success() {
		String expected = TypeOfShape.LINE + ":SP(" + line.getStartPoint().getX() + "," + line.getStartPoint().getY() + ") EP(" +
				line.getEndPoint().getX() + "," + line.getEndPoint().getY() + ") " + "BC:" + line.getColor().getRGB();
		assertEquals(expected, line.toString());
	}
	
	@Test 
	public void testClone_FieldsValuesAreSame() {
		Line clonedLine = line.clone();
		assertEquals(line, clonedLine);
	}
	
	@Test 
	public void testClone_ReferencesAreDifferent() {
		Line clonedLine = line.clone();
		assertFalse(clonedLine == line);
	}
	
	@Test
	public void testSetShapeFields_FieldsValuesAreSame() {
		Line oldRefrence = line;
		Line lineWithNewFileds = new Line(new Point(44,44), new Point(55,55), true, Color.BLUE);
		line.setShapeFileds(lineWithNewFileds);
		assertEquals(lineWithNewFileds, line);
		assertTrue(oldRefrence == line);
	}

}
