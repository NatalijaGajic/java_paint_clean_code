package shapeTests;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;
import java.awt.Color;
import java.awt.Graphics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import geometry.*;

class RectangleTests {

	private Graphics graphics;
	private int xCoord;
	private int yCoord;
	private int height;
	private int width;
	private Color edgeColor;
	private Color fillColor;
	private Rectangle rectangle;

	@BeforeEach
	public void setUp() {
		graphics = mock(Graphics.class);
		xCoord = 1;
		yCoord = 2;
		height = 10;
		width = 10;
		edgeColor = Color.BLACK;
		fillColor = Color.WHITE;

		rectangle = new Rectangle(new Point(xCoord, yCoord, false, Color.BLACK), height, width, false,
				edgeColor, fillColor);
	}

	@Test
	public void testDrawShapeNotSelected() {
		rectangle.draw(graphics);
		verify(graphics).setColor(rectangle.getColor());
		verify(graphics).drawRect(xCoord, yCoord, width, height);
		verify(graphics).setColor(rectangle.getInnerColor());
		verify(graphics).fillRect(xCoord + 1, yCoord + 1, width - 1, height - 1);
	}

	@Test
	public void testDrawShapeSelected() {
		rectangle.setSelected(true);
		rectangle.draw(graphics);
		verify(graphics).setColor(edgeColor);
		verify(graphics).drawRect(xCoord, yCoord, width, height);
		verify(graphics).setColor(fillColor);
		verify(graphics).fillRect(xCoord + 1, yCoord + 1, width - 1, height - 1);
		verify(graphics).setColor(Color.BLUE);
		verify(graphics).drawRect(xCoord - 3, yCoord - 3, 6, 6);
		verify(graphics).drawRect(xCoord - 3 + width, yCoord - 3, 6, 6);
		verify(graphics).drawRect(xCoord - 3, yCoord - 3 + height, 6, 6);
		verify(graphics).drawRect(xCoord + width - 3, yCoord + height - 3, 6, 6);
	}

	@Test
	public void testContainsTrueExcepted() {
		assertTrue(rectangle.contains(1, 2));
	}

	@Test
	public void testContainsFalseExcepted() {
		assertFalse(rectangle.contains(100, 100));
	}

	@Test
	public void testEqualsNotSameType() {
		assertFalse(rectangle.equals(new Point(1, 2)));
	}

	@Test
	public void testEqualsFalseExpectedUpperLeftPoint() {
		assertFalse(rectangle.equals(new Rectangle(new Point(2, 2), 10, 10)));
	}

	@Test
	public void testEqualsFalseExpectedWidth() {
		assertFalse(rectangle.equals(new Rectangle(new Point(1, 2), 10, 3)));
	}

	@Test
	public void testEqualsFalseExpectedHeight() {
		assertFalse(rectangle.equals(new Rectangle(new Point(1, 2), 3, 10)));
	}

	@Test
	public void testEqualsTrueExpected() {
		assertTrue(rectangle.equals(new Rectangle(new Point(1, 2), 10, 10)));
	}

	@Test
	public void testToString() {
		String expected = "Upper left point: " + rectangle.getUpperLeftPoint() + ", height: " + rectangle.getHeight() + ", width: " + rectangle.getWidth();
		assertEquals(expected, rectangle.toString());
	}

}
