package dialogTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dialogs.DlgPoint;

class DlgPointTests {

	private DlgPoint dialogPoint;

	@BeforeEach
	public void setUp() {
		dialogPoint = new DlgPoint();
	}

	@Test
	public void testBtnBorderColorClicked() {
		dialogPoint.getBtnBorderColor().doClick();
		assertEquals(dialogPoint.getBorderColor(), dialogPoint.getTxtBorderColor().getBackground());
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



}
