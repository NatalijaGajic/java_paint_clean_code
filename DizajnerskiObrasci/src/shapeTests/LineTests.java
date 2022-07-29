package shapeTests;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;
import java.awt.Color;
import java.awt.Graphics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import geometry.Line;
import geometry.Point;

class LineTests {

	private Graphics graphics;
	int xCoordOfStartPoint;
	int yCoordOfStartPoint;
	int xCoordOfEndPoint;
	int yCoordOfEndPoint;
	Color EdgeColor;
	private Line line;

	@BeforeEach
	public void setUp() {
		graphics = mock(Graphics.class);
		xCoordOfStartPoint = 1;
		yCoordOfStartPoint = 2;
		xCoordOfEndPoint = 3;
		yCoordOfEndPoint = 4;
		EdgeColor = Color.BLACK;

		line = new Line(new Point(xCoordOfStartPoint, yCoordOfStartPoint, false, EdgeColor),
				new Point(xCoordOfEndPoint, yCoordOfEndPoint, false, EdgeColor), false, EdgeColor);
	}

	@Test
	public void testDrawShapeNotSelected() {
		line.draw(graphics);
		verify(graphics).setColor(EdgeColor);
		verify(graphics).drawLine(xCoordOfStartPoint, yCoordOfStartPoint, xCoordOfEndPoint,
				yCoordOfEndPoint);
	}

	@Test
	public void testDrawShapeSelected() {
		line.setSelected(true);
		line.draw(graphics);
		verify(graphics).setColor(line.getColor());

		verify(graphics).drawLine(xCoordOfStartPoint, yCoordOfStartPoint, xCoordOfEndPoint,
				yCoordOfEndPoint);

		verify(graphics).setColor(Color.BLUE);
		verify(graphics).drawRect(xCoordOfStartPoint - 3, yCoordOfStartPoint - 3, 6, 6);
		verify(graphics).drawRect(xCoordOfEndPoint - 3, yCoordOfEndPoint - 3, 6, 6);

		verify(graphics).drawRect(line.middleOfLine().getX() - 3,
				line.middleOfLine().getY() - 3, 6, 6);
	}

	@Test
	public void testmiddleOfLine() {
		assertEquals(
				new Point((xCoordOfStartPoint + xCoordOfEndPoint) / 2,
						(yCoordOfStartPoint + yCoordOfEndPoint) / 2, false, EdgeColor),
				line.middleOfLine());
	}

	@Test
	public void testContainsTrueExcepted() {
		assertTrue(line.contains(1, 2));
	}

	@Test
	public void testContainsFalseExcepted() {
		assertFalse(line.contains(21, 61));
	}

	@Test
	public void testCalculateLength() {
		assertEquals(line.getStartPoint().distance(xCoordOfEndPoint, yCoordOfEndPoint),
				line.length(), 0);
	}

	@Test
	public void testEqualsNotSameType() {
		assertFalse(line.equals(new Point(1, 2)));
	}

	@Test
	public void testEqualsFalseExpectedStartPoint() {
		assertFalse(line.equals(new Line(new Point(2, 2), new Point(3, 4))));
	}

	@Test
	public void testEqualsFalseExpectedEndPoint() {
		assertFalse(line.equals(new Line(new Point(1, 2), new Point(3, 5))));
	}

	@Test
	public void testEqualsTrueExpected() {
		assertTrue(line.equals(new Line(new Point(1, 2), new Point(3, 4))));
	}

	@Test
	public void testToString() {
		String startPoint = "(" + line.getStartPoint().getX() + ", " + line.getStartPoint().getY() + ")";
		String endPoint = "(" + line.getEndPoint().getX() + ", " + line.getEndPoint().getY() + ")";
		String expected = startPoint + "->" + endPoint;
		assertEquals(expected, line.toString());
	}
	
	@Test 
	public void testClone() {
		Line clonedLine = line.clone();
		assertEquals(line, clonedLine);
		assertFalse(clonedLine == line);
	}
	
	@Test
	public void testSetShapeFields() {
		Line oldRefrence = line;
		Line lineWithNewFileds = new Line(new Point(44,44), new Point(55,55), true, Color.BLUE);
		line.setShapeFileds(lineWithNewFileds);
		assertEquals(lineWithNewFileds, line);
		assertTrue(oldRefrence == line);
	}

}
