package dialogs;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dialogs.DlgPoint;
import geometry.Point;

class DlgPointTests {

	private DlgPoint dialogPoint;

	@BeforeEach
	public void setUp() {
		dialogPoint = new DlgPoint();
	}

	@Test
	public void testBtnEdgeColorClicked_PnlEdgeColorBackgroundChanged() {
		dialogPoint.getBtnEdgeColor().doClick();
		assertEquals(dialogPoint.getEdgeColor(), dialogPoint.getPnlEdgeColor().getBackground());
	}

	@Test
	public void testBtnOkClicked_EmptyValues_IsAcceptedFalse() {
		dialogPoint.getTxtXCoord().setText("1");;
		dialogPoint.getBtnOk().doClick();
		assertFalse(dialogPoint.isAccepted());
	}

	@Test
	public void testBtnOkClicked_Success() {
		dialogPoint.getTxtXCoord().setText("1");
		dialogPoint.getTxtYCoord().setText("2");
		dialogPoint.getBtnOk().doClick();
		assertTrue(dialogPoint.isAccepted());
		assertFalse(dialogPoint.isVisible());
	}
	
	@Test
	public void testGetShapeFromDialog_Success() {
		dialogPoint.getTxtXCoord().setText("1");
		dialogPoint.getTxtYCoord().setText("2");
		Point actual = (Point) dialogPoint.getShapeFromDialog();
		dialogPoint.setEdgeColor(Color.GREEN);
		Point expected = new Point(1, 2, Color.GREEN);
		assertEquals(expected, actual);
	}

	

}
