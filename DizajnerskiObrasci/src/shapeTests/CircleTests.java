package shapeTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.awt.Color;
import java.awt.Graphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import geometry.*;
import logger.LoggerConstants;

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
		initializeShapes();
	}

	private void initializeShapes() {
		xCoord = 1;
		yCoord = 2;
		radius = 3;
		edgeColor = Color.BLACK;
		innerColor = Color.WHITE;
		center = new Point(xCoord, yCoord, false, edgeColor);
		circle = new Circle(center, radius, false, edgeColor, innerColor);
	}
	@Test
	public void testDraw_ShapeNotSelected_Success() {
		circle.draw(graphics);
		verify(graphics).setColor(edgeColor);
		verify(graphics).drawOval(xCoord - radius, yCoord - radius, radius * 2, radius * 2);
		verify(graphics).setColor(innerColor);
		verify(graphics).fillOval(xCoord - radius + 1, yCoord - radius + 1, radius * 2 - 2, radius * 2 - 2);
	}

	@Test
	public void testDraw_ShapeSelected_Success() {
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
	public void testContains_TrueExcepted() {
		assertTrue(circle.contains(1, 1));
	}

	@Test
	public void testContains_FalseExcepted() {
		assertFalse(circle.contains(100, 100));
	}

	@Test
	public void testEquals_NotSameType() {
		assertFalse(circle.equals(new Point(1, 2)));
	}

	@Test
	public void testEquals_FalseExpectedRadius() {
		assertFalse(circle.equals(new Circle(new Point(1, 2), 1)));
	}

	@Test
	public void testEquals_TrueExpected() {
		assertTrue(circle.equals(new Circle(new Point(1, 2), 3)));
	}

	@Test
	public void testEquals_FalseExpectedCenter() {
		assertFalse(circle.equals(new Circle(new Point(2, 3), 1)));
	}

	@Test
	public void testToString_Success() {
		String expected = LoggerConstants.CIRCLE + ":(" + circle.getCenter().getX() + "," + circle.getCenter().getY()+") "
				+ "R:" + circle.getRadius() + ", BC:" + circle.getColor().getRGB() + ", "
				+ "FC:" + circle.getInnerColor().getRGB();
		assertEquals(expected, circle.toString());
	}
	
	@Test 
	public void testClone_FieldsValuesAreSame() {
		Circle clonedCircle = circle.clone();
		assertEquals(circle, clonedCircle);
	}
	
	@Test 
	public void testClone_RefrencesAreDifferent() {
		Circle clonedCircle = circle.clone();
		assertFalse(clonedCircle == circle);
	}
	
	@Test
	public void testSetShapeFields_FieldsValuesAreSame() {
		Circle oldRefrence = circle;
		Circle circleWithNewFileds = new Circle(new Point(44,44), 55, true, Color.BLUE, Color.GREEN);
		circle.setShapeFileds(circleWithNewFileds);
		assertEquals(circleWithNewFileds, circle);
		assertTrue(oldRefrence == circle);
	}

}
