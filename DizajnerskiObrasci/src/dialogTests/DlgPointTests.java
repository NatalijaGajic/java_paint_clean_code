package dialogTests;

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
	public void testBtnEdgeColorClicked() {
		dialogPoint.getBtnEdgeColor().doClick();
		assertEquals(dialogPoint.getEdgeColor(), dialogPoint.getPnlEdgeColor().getBackground());
	}

	@Test
	public void testBtnOkClickedInvalidValues() {
		dialogPoint.getTxtXCoord().setText("1");;
		dialogPoint.getBtnOk().doClick();
		assertFalse(dialogPoint.isAccepted());
	}

	@Test
	public void testBtnOkClicked() {
		dialogPoint.getTxtXCoord().setText("1");
		dialogPoint.getTxtYCoord().setText("2");
		dialogPoint.getBtnOk().doClick();
		assertTrue(dialogPoint.isAccepted());
		assertFalse(dialogPoint.isVisible());
	}
	
	@Test
	public void testGetShapeFromDialogOK() {
		dialogPoint.getTxtXCoord().setText("1");
		dialogPoint.getTxtYCoord().setText("2");
		Point actual = (Point) dialogPoint.getShapeFromDialog();
		dialogPoint.setEdgeColor(Color.GREEN);
		Point expected = new Point(1, 2, Color.GREEN);
		assertEquals(expected, actual);
	}

	

}
