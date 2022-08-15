
package geometry;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;
import java.awt.Graphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import logger.LoggerConstants;

class PointTests {

	private Graphics graphics;
	private int xCoord;
	private int yCoord;
	private Color edgeColor;
	private Point point;

	@BeforeEach
	public void setUp() {
		graphics = mock(Graphics.class);
		initializeShapes();
	}
	
	private void initializeShapes() {
		xCoord = 1;
		yCoord = 2;
		edgeColor = Color.BLACK;
		point = new Point(xCoord, yCoord, false, edgeColor);
	}

	@Test
	public void testDrawShape_NotSelected_Success() {
		point.draw(graphics);
		verify(graphics).setColor(edgeColor);
		verify(graphics).drawLine(xCoord - 2, yCoord, xCoord + 2, yCoord);
		verify(graphics).drawLine(xCoord, yCoord - 2, xCoord, yCoord + 2);
	}

	@Test
	public void testDraw_ShapeSelected_Success() {
		point.setSelected(true);
		point.draw(graphics);
		verify(graphics).setColor(edgeColor);
		verify(graphics).drawLine(xCoord - 2, yCoord, xCoord + 2, yCoord);
		verify(graphics).drawLine(xCoord, yCoord - 2, xCoord, yCoord + 2);
		verify(graphics).setColor(Color.BLUE);
		verify(graphics).drawRect(xCoord - 3, yCoord - 3, 6, 6);
	}

	@Test
	public void testContains_TrueExcepted() {
		assertTrue(point.contains(1, 1));
	}

	@Test
	public void testContains_FalseExcepted() {
		assertFalse(point.contains(10, 10));
	}

	@Test
	public void testEquals_NotSameType() {
		assertFalse(point.equals(new Line(new Point(1, 1), new Point(2, 2))));
	}

	@Test
	public void testEquals_FalseExpectedXCoord() {
		assertFalse(point.equals(new Point(0, 2)));
	}

	@Test
	public void testEquals_FalseExpectedYCoord() {
		assertFalse(point.equals((new Point(1, 3))));
	}

	@Test
	public void testEquals_TrueExpected() {
		assertTrue(point.equals(new Point(1, 2)));
	}
	
	@Test
	public void testToString_Success() {
		String expected = LoggerConstants.POINT + ":(" + point.getX() + "," + point.getY() + ") " + "BC:" + point.getColor().getRGB();
		assertEquals(expected, point.toString());
	}
	
	@Test 
	public void testClone_FieldsValuesAreSame() {
		Point clonedPoint = point.clone();
		assertEquals(point, clonedPoint);
	}
	
	@Test 
	public void testClone_ReferencesAreDifferent() {
		Point clonedPoint = point.clone();
		assertFalse(clonedPoint == point);
	}
	
	@Test
	public void testSetShapeFields_FieldsValuesAreSame() {
		Point oldRefrence = point;
		Point pointWithNewFileds = new Point(44, 44, true, Color.BLUE);
		point.setShapeFileds(pointWithNewFileds);
		assertEquals(pointWithNewFileds, point);
		assertTrue(oldRefrence == point);
	}

}
