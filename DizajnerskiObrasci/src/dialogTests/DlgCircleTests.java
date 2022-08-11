package dialogTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Color;
import dialogs.DlgCircle;
import geometry.*;

class DlgCircleTests {

	private DlgCircle dialogCircle;

	@BeforeEach
	public void setUp() {
		dialogCircle = new DlgCircle();
	}

	@Test
	public void testBtnEdgeColorClicked_PnlEdgeColorBackgroundChanged() {
		dialogCircle.getBtnEdgeColor().doClick();
		assertEquals(dialogCircle.getEdgeColor(), dialogCircle.getPnlEdgeColor().getBackground());
	}

	@Test
	public void testBtnInnerColorClicked_PnlInnerColorBackgroundChanged() {
		dialogCircle.getBtnInnerColor().doClick();
		assertEquals(dialogCircle.getInnerColor(), dialogCircle.getPnlInnerColor().getBackground());
	}

	@Test
	public void testBtnOkClicked_EmptyValues_IsAcceptedFalse() {
		dialogCircle.getTxtXCoord().setText("1");
		dialogCircle.getTxtYCoord().setText("2");
		dialogCircle.getBtnOk().doClick();
		assertFalse(dialogCircle.isAccepted());
	}

	@Test
	public void testBtnOkClicked_InvalidValues_IsAcceptedFalse() {
		dialogCircle.getTxtXCoord().setText("1");
		dialogCircle.getTxtYCoord().setText("2");
		dialogCircle.getTxtRadius().setText("0");
		dialogCircle.getBtnOk().doClick();
		assertFalse(dialogCircle.isAccepted());
	}

	@Test
	public void testBtnOkClicked_IsAcceptedTrue() {
		dialogCircle.getTxtXCoord().setText("1");
		dialogCircle.getTxtYCoord().setText("2");
		dialogCircle.getTxtRadius().setText("3");
		dialogCircle.getBtnOk().doClick();
		assertTrue(dialogCircle.isAccepted());
		assertFalse(dialogCircle.isVisible());
	}
	
	@Test
	public void testGetShapeFromDialog_Success() {
		dialogCircle.getTxtXCoord().setText("1");
		dialogCircle.getTxtYCoord().setText("2");
		dialogCircle.getTxtRadius().setText("3");
		dialogCircle.setEdgeColor(Color.GREEN);
		dialogCircle.setInnerColor(Color.BLUE);
		Circle actual = (Circle) dialogCircle.getShapeFromDialog();
		Circle expected = new Circle(new Point(1,2), 3, Color.GREEN, Color.BLUE);
		assertEquals(expected, actual);
	}


}
