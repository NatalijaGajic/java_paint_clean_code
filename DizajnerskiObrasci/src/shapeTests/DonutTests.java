package shapeTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.awt.Color;
import java.awt.Graphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import geometry.*;
import logger.LoggerConstants;

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
		initializeShapes();
	}

	private void initializeShapes() {
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
	public void testDraw_ShapeSelected_Success() {
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
	public void testContains_TrueExcepted() {
		assertTrue(donut.contains(3, 3));
	}

	@Test
	public void testContains_FalseExceptedInnerCircle() {
		assertFalse(donut.contains(21, 61));
	}

	@Test
	public void testContains_FalseExceptedOuterCircle() {
		assertFalse(donut.contains(1, 1));
	}

	@Test
	public void testEquals_NotSameType() {
		assertFalse(donut.equals(new Point(1, 2)));
	}

	@Test
	public void testEquals_FalseExpectedRadius() {
		assertFalse(donut.equals(new Donut(new Point(1, 2), 1, 2)));
	}

	@Test
	public void testEquals_FalseExpectedInnerRadius() {
		assertFalse(donut.equals(new Donut(new Point(1, 2), 3, 1)));
	}

	@Test
	public void testEquals_TrueExpected() {
		assertTrue(donut.equals(new Donut(new Point(1, 2), 3, 2)));
	}

	@Test
	public void testToString_Success() {
		String expected = LoggerConstants.DONUT + ":(" + donut.getCenter().getX() + "," + donut.getCenter().getY() + ")"
				+ " OR:" + donut.getRadius() + ", IR:" + donut.getInnerRadius() + ", "
				+ "BC:" + donut.getColor().getRGB() + ", FC:" + donut.getInnerColor().getRGB();
		assertEquals(expected, donut.toString());
	}
	
	@Test 
	public void testClone_FieldsValuesAreSame() {
		Circle clonedDonut = donut.clone();
		assertEquals(donut, clonedDonut);
	}
	
	@Test 
	public void testClone_RefrencesAreDifferent() {
		Circle clonedDonut = donut.clone();
		assertFalse(clonedDonut == donut);
	}
	
	@Test
	public void testSetShapeFields_FieldsValuesAreSame() {
		Donut oldRefrence = donut;
		Donut donutWithNewFileds = new Donut(new Point(44,44), 55, 44, true, Color.BLUE, Color.GREEN);
		donut.setShapeFileds(donutWithNewFileds);
		assertEquals(donutWithNewFileds, donut);
		assertTrue(oldRefrence == donut);
	}
}
