package shapeTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.awt.Color;
import java.awt.Graphics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import geometry.HexagonAdapter;
import geometry.Point;
import hexagon.Hexagon;
import logger.LoggerConstants;

class HexagonAdapterTests {
	
	private Graphics graphics;
	private HexagonAdapter hexagonAdapter;

	@BeforeEach
	public void setUp() {
		graphics = mock(Graphics.class);
		hexagonAdapter = new HexagonAdapter(new Point(1, 1), 5, Color.BLACK, Color.WHITE, false);
	}

	@Test
	public void testDraw() {
		Hexagon hexagon = mock(Hexagon.class);
		HexagonAdapter hexagonAdapter = new HexagonAdapter(hexagon);
		hexagonAdapter.draw(graphics);
		verify(hexagon).paint(graphics);
	}

	@Test
	public void testFill() {
		assertThrows(UnsupportedOperationException.class,() -> hexagonAdapter.fill(graphics));
	}

	@Test
	public void testContainsTrueExcepted() {
		assertTrue(hexagonAdapter.contains(1, 1));
	}

	@Test
	public void testContainsFalseExcepted() {
		assertFalse(hexagonAdapter.contains(21, 61));
	}

	@Test
	public void testEqualsNotSameType() {
		assertFalse(hexagonAdapter.equals(new Point(1, 2)));
	}

	@Test
	public void testEqualsFalseExpectedRadiusDiffer() {
		assertFalse(hexagonAdapter.equals(new HexagonAdapter(new Point(1, 1), 6, Color.BLACK, Color.WHITE, false)));
	}

	@Test
	public void testEqualsFalseExpectedXCoordDiffers() {
		assertFalse(hexagonAdapter.equals(new HexagonAdapter(new Point(2, 1), 5, Color.BLACK, Color.WHITE, false)));
	}

	@Test
	public void testEqualsFalseExpectedYCoordDiffers() {
		assertFalse(hexagonAdapter.equals(new HexagonAdapter(new Point(1, 2), 5, Color.BLACK, Color.WHITE, false)));
	}

	@Test
	public void testEqualsTrueExpected() {
		assertTrue(hexagonAdapter.equals(new HexagonAdapter(new Point(1, 1), 5, Color.BLACK, Color.WHITE, false)));
	}
	
	@Test 
	public void testClone() {
		HexagonAdapter clonedHexagonAdapter = (HexagonAdapter) hexagonAdapter.clone();
		assertEquals(hexagonAdapter, clonedHexagonAdapter);
		assertFalse(clonedHexagonAdapter == hexagonAdapter);
	}
	
	@Test
	public void testSetShapeFields() {
		HexagonAdapter oldRefrence = hexagonAdapter;
		HexagonAdapter hexagonAdapterWithNewFileds = new HexagonAdapter(new Point(10, 10), 15, Color.BLUE, Color.GREEN, true);
		hexagonAdapter.setShapeFileds(hexagonAdapterWithNewFileds);
		assertEquals(hexagonAdapterWithNewFileds, hexagonAdapter);
		assertTrue(oldRefrence == hexagonAdapter);
	}
	
	@Test
	public void testToString() {
		String expected = LoggerConstants.HEXAGON + ":(" + hexagonAdapter.getX() + "," + hexagonAdapter.getY() + ") "
				+ "R:" + hexagonAdapter.getRadius() + ", BC:" + hexagonAdapter.getColor().getRGB() + ", "
				+ "FC:" + hexagonAdapter.getInnerColor().getRGB();
		assertEquals(expected, hexagonAdapter.toString());
	}

}
