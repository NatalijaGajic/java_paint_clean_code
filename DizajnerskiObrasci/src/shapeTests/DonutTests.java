package shapeTests;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import java.awt.Color;
import java.awt.Graphics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import geometry.*;

class DonutTests {

	private Graphics graphics;
	private int xCoord;
	private int yCoord;
	private int outerRadius;
	private int innerRadius;
	private Color edgeColor;
	private Color innerColor;
	private Donut donut;

	@BeforeEach
	public void setUp() {
		graphics = mock(Graphics.class);
		xCoord = 1;
		yCoord = 2;
		outerRadius = 3;
		innerRadius = 2;
		edgeColor = Color.BLACK;
		innerColor = Color.WHITE;

		donut = new Donut(new Point(xCoord, yCoord, false, Color.BLACK), outerRadius, innerRadius, false,
				edgeColor, innerColor);
	}

	
	@Test
	public void testDrawShapeSelected() {
		donut.setSelected(true);
		donut.draw(graphics);
		
		verify(graphics).setColor(Color.BLUE);
		verify(graphics).drawRect(xCoord - 3, yCoord - 3, 6, 6);
		verify(graphics).drawRect(xCoord + outerRadius - 3, yCoord - 3, 6, 6);
		verify(graphics).drawRect(xCoord - outerRadius - 3, yCoord - 3, 6, 6);
		verify(graphics).drawRect(xCoord - 3, yCoord + outerRadius - 3, 6, 6);
		verify(graphics).drawRect(xCoord - 3, yCoord - outerRadius - 3, 6, 6);
	}

	@Test
	public void testContainsTrueExcepted() {
		assertTrue(donut.contains(3, 3));
	}

	@Test
	public void testContainsFalseExceptedInnerCircle() {
		assertFalse(donut.contains(21, 61));
	}

	@Test
	public void testContainsFalseExceptedOuterCircle() {
		assertFalse(donut.contains(1, 1));
	}

	@Test
	public void testEqualsNotSameType() {
		assertFalse(donut.equals(new Point(1, 2)));
	}

	@Test
	public void testEqualsFalseExpectedRadius() {
		assertFalse(donut.equals(new Donut(new Point(1, 2), 1, 2)));
	}

	@Test
	public void testEqualsFalseExpectedInnerRadius() {
		assertFalse(donut.equals(new Donut(new Point(1, 2), 3, 1)));
	}

	@Test
	public void testEqualsTrueExpected() {
		assertTrue(donut.equals(new Donut(new Point(1, 2), 3, 2)));
	}

	@Test
	public void testEqualsFalseExpected() {
		assertFalse(donut.equals(new Donut(new Point(1, 2), 8, 5)));
	}

	@Test
	public void testToString() {
		String expected = "Center: " + donut.getCenter() + ", radius: " + donut.getRadius() + ", inner radius=" + donut.getInnerRadius(); 
		assertEquals(expected, donut.toString());
	}
}
