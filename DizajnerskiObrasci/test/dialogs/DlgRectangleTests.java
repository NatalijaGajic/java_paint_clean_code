package dialogs;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dialogs.DlgRectangle;
import geometry.*;

class DlgRectangleTests {

	private DlgRectangle dialogRectangle;

	@BeforeEach
	public void setUp() {
		dialogRectangle = new DlgRectangle();
	}

	@Test
	public void testBtnEdgeColorClicked_PnlEdgeColorBackgroundChanged() {
		dialogRectangle.getBtnEdgeColor().doClick();
		assertEquals(dialogRectangle.getEdgeColor(), dialogRectangle.getPnlEdgeColor().getBackground());
	}

	@Test
	public void testBtnInnerColorClicked_PnlInnerColorBackgroundChanged() {
		dialogRectangle.getBtnInnerColor().doClick();
		assertEquals(dialogRectangle.getInnerColor(), dialogRectangle.getPnlInnerColor().getBackground());
	}

	@Test
	public void testBtnOkClicked_InvalidValues_IsAcceptedFalse() {
		dialogRectangle.getTxtXCoord().setText("1");
		dialogRectangle.getTxtYCoord().setText("2");
		dialogRectangle.getBtnOk().doClick();
		assertFalse(dialogRectangle.isAccepted());
	}

	@Test
	public void testBtnOkClicked_WidthZero_IsAcceptedFalse() {
		dialogRectangle.getTxtXCoord().setText("1");
		dialogRectangle.getTxtYCoord().setText("2");
		dialogRectangle.getTxtWidth().setText("0");
		dialogRectangle.getTxtHeight().setText("4");
		dialogRectangle.getBtnOk().doClick();
		assertFalse(dialogRectangle.isAccepted());
		assertFalse(dialogRectangle.isVisible());
	}

	@Test
	public void testBtnOkClicked_HeightZero_IsAcceptedFalse() {
		dialogRectangle.getTxtXCoord().setText("1");
		dialogRectangle.getTxtYCoord().setText("2");
		dialogRectangle.getTxtWidth().setText("3");
		dialogRectangle.getTxtHeight().setText("0");
		dialogRectangle.getBtnOk().doClick();
		assertFalse(dialogRectangle.isAccepted());
		assertFalse(dialogRectangle.isVisible());
	}

	@Test
	public void testBtnOkClicked() {
		dialogRectangle.getTxtXCoord().setText("1");
		dialogRectangle.getTxtYCoord().setText("2");
		dialogRectangle.getTxtWidth().setText("3");
		dialogRectangle.getTxtHeight().setText("4");
		dialogRectangle.getBtnOk().doClick();
		assertTrue(dialogRectangle.isAccepted());
		assertFalse(dialogRectangle.isVisible());
	}
	
	@Test
	public void testGetShapeFromDialog_Success() {
		dialogRectangle.getTxtXCoord().setText("1");
		dialogRectangle.getTxtYCoord().setText("2");
		dialogRectangle.getTxtWidth().setText("3");
		dialogRectangle.getTxtHeight().setText("4");
		dialogRectangle.setEdgeColor(Color.GREEN);
		dialogRectangle.setInnerColor(Color.BLUE);
		Rectangle expected = new Rectangle(new Point(1,2), 4, 3, Color.GREEN, Color.BLUE);
		Rectangle actual = (Rectangle)dialogRectangle.getShapeFromDialog();
		assertEquals(expected, actual);
	}


}
