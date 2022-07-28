package shapeTests;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import java.awt.Color;
import java.awt.Graphics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import geometry.*;

class CircleTests {

	private Graphics graphics;
	private int xCoord;
	private int yCoord;
	private int radius;
	private Color edgeColor;
	private Color innerColor;
	private Point center;
	private Circle circle;

	@BeforeEach
	public void setUp() {
		graphics = mock(Graphics.class);
		xCoord = 1;
		yCoord = 2;
		radius = 3;
		edgeColor = Color.BLACK;
		innerColor = Color.WHITE;
		center = new Point(xCoord, yCoord, false, edgeColor);
		circle = new Circle(center, radius, false, edgeColor, innerColor);
	}

	@Test
	public void testDrawShapeNotSelected() {
		circle.draw(graphics);
		verify(graphics).setColor(edgeColor);
		verify(graphics).drawOval(xCoord - radius, yCoord - radius, radius * 2, radius * 2);
		verify(graphics).setColor(innerColor);
		verify(graphics).fillOval(xCoord - radius + 1, yCoord - radius + 1, radius * 2 - 2, radius * 2 - 2);
	}

	@Test
	public void testDrawShapeSelected() {
		circle.setSelected(true);
		circle.draw(graphics);
		verify(graphics).setColor(edgeColor);
		verify(graphics).drawOval(xCoord - radius, yCoord - radius, radius * 2, radius * 2);
		verify(graphics).setColor(innerColor);
		verify(graphics).fillOval(xCoord - radius + 1, yCoord - radius + 1, radius * 2 - 2, radius * 2 - 2);
		verify(graphics).setColor(Color.BLUE);
		verify(graphics).drawRect(xCoord - 3, yCoord - 3, 6, 6);
		verify(graphics).drawRect(xCoord + radius - 3, yCoord - 3, 6, 6);
		verify(graphics).drawRect(xCoord - radius - 3, yCoord - 3, 6, 6);
		verify(graphics).drawRect(xCoord - 3, yCoord + radius - 3, 6, 6);
		verify(graphics).drawRect(xCoord - 3, yCoord - radius - 3, 6, 6);
	}

	@Test
	public void testContainsTrueExcepted() {
		assertTrue(circle.contains(1, 1));
	}

	@Test
	public void testContainsFalseExcepted() {
		assertFalse(circle.contains(100, 100));
	}

	@Test
	public void testEqualsNotSameType() {
		assertFalse(circle.equals(new Point(1, 2)));
	}

	@Test
	public void testEqualsFalseExpectedRadius() {
		assertFalse(circle.equals(new Circle(new Point(1, 2), 1)));
	}

	@Test
	public void testEqualsTrueExpected() {
		assertTrue(circle.equals(new Circle(new Point(1, 2), 3)));
	}

	@Test
	public void testEqualsFalseExpected() {
		assertFalse(circle.equals(new Circle(new Point(2, 3), 1)));
	}

	@Test
	public void testToString() {
		String expected = "Center: " + circle.getCenter() + ", radius: " + circle.getRadius();
		assertEquals(expected, circle.toString());
	}

}
