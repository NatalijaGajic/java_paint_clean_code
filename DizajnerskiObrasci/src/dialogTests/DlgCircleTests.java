package dialogTests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dialogs.DlgCircle;
import geometry.Circle;
import geometry.Point;

class DlgCircleTests {

	private DlgCircle dialogCircle;

	@BeforeEach
	public void setUp() {
		dialogCircle = new DlgCircle();
	}

	@Test
	public void testBtnEdgeColorClicked() {
		dialogCircle.getBtnEdgeColor().doClick();
		assertEquals(dialogCircle.getEdgeColor(), dialogCircle.getPnlEdgeColor().getBackground());
	}

	@Test
	public void testBtnInnerColorClicked() {
		dialogCircle.getBtnInnerColor().doClick();
		assertEquals(dialogCircle.getInnerColor(), dialogCircle.getPnlInnerColor().getBackground());
	}

	@Test
	public void testBtnOkClickedInvalidValues() {
		dialogCircle.getTxtXCoord().setText("1");
		dialogCircle.getTxtYCoord().setText("2");
		dialogCircle.getBtnOk().doClick();
		assertFalse(dialogCircle.isAccepted());
	}

	@Test
	public void testBtnOkClickedRadiusZero() {
		dialogCircle.getTxtXCoord().setText("1");
		dialogCircle.getTxtYCoord().setText("2");
		dialogCircle.getTxtRadius().setText("0");
		dialogCircle.getBtnOk().doClick();
		assertFalse(dialogCircle.isAccepted());
	}

	@Test
	public void testBtnOkClicked() {
		dialogCircle.getTxtXCoord().setText("1");
		dialogCircle.getTxtYCoord().setText("2");
		dialogCircle.getTxtRadius().setText("3");
		dialogCircle.getBtnOk().doClick();
		assertTrue(dialogCircle.isAccepted());
		assertFalse(dialogCircle.isVisible());
	}
	
	@Test
	public void testGetShapeFromDialogOK() {
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
