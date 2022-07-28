
package shapeTests;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.Graphics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import geometry.Line;
import geometry.Point;

class PointTests {

	private Graphics graphics;
	private int xCoord;
	private int yCoord;
	private Color edgeColor;
	private Point point;

	@BeforeEach
	public void setUp() {
		graphics = mock(Graphics.class);
		xCoord = 1;
		yCoord = 2;
		edgeColor = Color.BLACK;
		point = new Point(xCoord, yCoord, false, edgeColor);
	}

	@Test
	public void testDrawShapeNotSelected() {
		point.draw(graphics);
		verify(graphics).setColor(edgeColor);
		verify(graphics).drawLine(xCoord - 2, yCoord, xCoord + 2, yCoord);
		verify(graphics).drawLine(xCoord, yCoord - 2, xCoord, yCoord + 2);
	}

	@Test
	public void testDrawShapeSelected() {
		point.setSelected(true);
		point.draw(graphics);
		verify(graphics).setColor(edgeColor);
		verify(graphics).drawLine(xCoord - 2, yCoord, xCoord + 2, yCoord);
		verify(graphics).drawLine(xCoord, yCoord - 2, xCoord, yCoord + 2);
		verify(graphics).setColor(Color.BLUE);
		verify(graphics).drawRect(xCoord - 3, yCoord - 3, 6, 6);
	}

	@Test
	public void testContainsTrueExcepted() {
		assertTrue(point.contains(1, 1));
	}

	@Test
	public void testContainsFalseExcepted() {
		assertFalse(point.contains(10, 10));
	}

	@Test
	public void testEqualsNotSameType() {
		assertFalse(point.equals(new Line(new Point(1, 1), new Point(2, 2))));
	}

	@Test
	public void testEqualsFalseExpectedXCoord() {
		assertFalse(point.equals(new Point(0, 2)));
	}

	@Test
	public void testEqualsFalseExpectedYCoord() {
		assertFalse(point.equals((new Point(1, 3))));
	}

	@Test
	public void testEqualsTrueExpected() {
		assertTrue(point.equals(new Point(1, 2)));
	}
	
	@Test
	public void testToString() {
		String expected = "(" + point.getX() + ", " + point.getY() + ")";
		assertEquals(expected, point.toString());
	}

}
