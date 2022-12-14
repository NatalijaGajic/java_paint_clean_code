package dialogs;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dialogs.DlgHexagon;
import geometry.*;

class DlgHexagonTests {

	private DlgHexagon dialogHexagon;

	@BeforeEach
	public void setUp() {
		dialogHexagon = new DlgHexagon();
	}

	@Test
	public void testBtnEdgeColorClicked_PnlEdgeColorBackgroundChanged() {
		dialogHexagon.getBtnEdgeColor().doClick();
		assertEquals(dialogHexagon.getEdgeColor(), dialogHexagon.getPnlEdgeColor().getBackground());
	}

	@Test
	public void testBtnInnerColorClicked_PnlInnerColorBackgroundChanged() {
		dialogHexagon.getBtnInnerColor().doClick();
		assertEquals(dialogHexagon.getInnerColor(), dialogHexagon.getPnlInnerColor().getBackground());
	}

	@Test
	public void testBtnOkClicked_EmptyValues_IsAcceptedFalse() {
		dialogHexagon.getTxtXCoord().setText("1");
		dialogHexagon.getTxtYCoord().setText("2");
		dialogHexagon.getBtnOk().doClick();
		assertFalse(dialogHexagon.isAccepted());
	}

	@Test
	public void testBtnOkClicked_RadiusZero_IsAcceptedFalse() {
		dialogHexagon.getTxtXCoord().setText("1");
		dialogHexagon.getTxtYCoord().setText("2");
		dialogHexagon.getTxtRadius().setText("0");
		dialogHexagon.getBtnOk().doClick();
		assertFalse(dialogHexagon.isAccepted());
	}

	@Test
	public void testBtnOkClicked_Success() {
		dialogHexagon.getTxtXCoord().setText("1");
		dialogHexagon.getTxtYCoord().setText("2");
		dialogHexagon.getTxtRadius().setText("3");
		dialogHexagon.getBtnOk().doClick();
		assertTrue(dialogHexagon.isAccepted());
		assertFalse(dialogHexagon.isVisible());
	}
	
	@Test
	public void testGetShapeFromDialog_Success() {
		dialogHexagon.getTxtXCoord().setText("1");
		dialogHexagon.getTxtYCoord().setText("2");
		dialogHexagon.getTxtRadius().setText("3");
		dialogHexagon.setEdgeColor(Color.GREEN);
		dialogHexagon.setInnerColor(Color.BLUE);
		HexagonAdapter actual = (HexagonAdapter) dialogHexagon.getShapeFromDialog();
		HexagonAdapter expected = new HexagonAdapter(new Point(1,2), 3, Color.GREEN, Color.BLUE);
		assertEquals(expected, actual);
	}

}
