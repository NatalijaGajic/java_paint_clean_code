package geometry;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;
import java.awt.Color;
import java.awt.Graphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logger.TypeOfShape;

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
		initializeShapes();
	}
	
	private void initializeShapes() {
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
	public void testDraw_ShapeNotSelected_Success() {
		rectangle.draw(graphics);
		verify(graphics).setColor(rectangle.getColor());
		verify(graphics).drawRect(xCoord, yCoord, width, height);
		verify(graphics).setColor(rectangle.getInnerColor());
		verify(graphics).fillRect(xCoord + 1, yCoord + 1, width - 1, height - 1);
	}

	@Test
	public void testDraw_ShapeSelected_Success() {
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
	public void testContains_TrueExcepted() {
		assertTrue(rectangle.contains(1, 2));
	}

	@Test
	public void testContains_FalseExcepted() {
		assertFalse(rectangle.contains(100, 100));
	}

	@Test
	public void testEquals_NotSameType() {
		assertFalse(rectangle.equals(new Point(1, 2)));
	}

	@Test
	public void testEquals_FalseExpectedUpperLeftPoint() {
		assertFalse(rectangle.equals(new Rectangle(new Point(2, 2), 10, 10)));
	}

	@Test
	public void testEquals_FalseExpectedWidth() {
		assertFalse(rectangle.equals(new Rectangle(new Point(1, 2), 10, 3)));
	}

	@Test
	public void testEquals_FalseExpectedHeight() {
		assertFalse(rectangle.equals(new Rectangle(new Point(1, 2), 3, 10)));
	}

	@Test
	public void testEquals_TrueExpected() {
		assertTrue(rectangle.equals(new Rectangle(new Point(1, 2), 10, 10)));
	}

	@Test
	public void testToString_Sucess() {
		String expected = TypeOfShape.RECTANGLE + ":(" + rectangle.getUpperLeftPoint().getX() + "," + rectangle.getUpperLeftPoint().getY() + ") "
				+ "W:" + rectangle.getWidth() + ", H:" + rectangle.getHeight() + ", BC:" + rectangle.getColor().getRGB() + ", "
				+ "FC:" + rectangle.getInnerColor().getRGB();
		assertEquals(expected, rectangle.toString());
	}
	
	@Test 
	public void testClone_FieldsValuesAreSame() {
		Rectangle clonedRectangle = rectangle.clone();
		assertEquals(rectangle, clonedRectangle);
	}
	
	@Test 
	public void testClone_ReferencesAreDifferent() {
		Rectangle clonedRectangle = rectangle.clone();
		assertFalse(clonedRectangle == rectangle);
	}
	
	@Test
	public void testSetShapeFields_FieldsValuesAreSame() {
		Rectangle oldRefrence = rectangle;
		Rectangle rectangleWithNewFields = new Rectangle(new Point(10, 10, false, Color.BLUE), 33, 33, false,
				Color.blue, Color.green);
		rectangle.setShapeFileds(rectangleWithNewFields);
		assertEquals(rectangleWithNewFields, rectangle);
		assertTrue(oldRefrence == rectangle);
	}

}
