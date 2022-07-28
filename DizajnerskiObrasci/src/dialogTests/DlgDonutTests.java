package dialogTests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dialogs.DlgDonut;
import geometry.Donut;
import geometry.Point;

class DlgDonutTests {

	private DlgDonut dialogDonut;

	@BeforeEach
	public void setUp() {
		dialogDonut = new DlgDonut();
	}

	@Test
	public void testBtnEdgeColorClicked() {
		dialogDonut.getBtnEdgeColor().doClick();
		assertEquals(dialogDonut.getEdgeColor(), dialogDonut.getPnlEdgeColor().getBackground());
	}

	@Test
	public void testBtnInnerColorClicked() {
		dialogDonut.getBtnInnerColor().doClick();
		assertEquals(dialogDonut.getInnerColor(), dialogDonut.getPnlInnerColor().getBackground());
	}

	@Test
	public void testBtnOkClickedInvalidValues() {
		dialogDonut.getTxtXCoord().setText("1");
		dialogDonut.getTxtYCoord().setText("2");
		dialogDonut.getBtnOk().doClick();
		assertFalse(dialogDonut.isAccepted());
	}

	@Test
	public void testBtnOkClickedRadiusZero() {
		dialogDonut.getTxtXCoord().setText("1");
		dialogDonut.getTxtYCoord().setText("2");
		dialogDonut.getTxtRad().setText("0");
		dialogDonut.getTxtIRad().setText("4");
		dialogDonut.getBtnOk().doClick();
		assertFalse(dialogDonut.isAccepted());
	}
	
	@Test
	public void testBtnOkClickedInnerRadiusZero() {
		dialogDonut.getTxtXCoord().setText("1");
		dialogDonut.getTxtYCoord().setText("2");
		dialogDonut.getTxtRad().setText("3");
		dialogDonut.getTxtIRad().setText("0");
		dialogDonut.getBtnOk().doClick();
		assertFalse(dialogDonut.isAccepted());
	}

	@Test
	public void testBtnOkClickedInnerRadiusLargerThenRadius() {
		dialogDonut.getTxtXCoord().setText("1");
		dialogDonut.getTxtYCoord().setText("2");
		dialogDonut.getTxtRad().setText("3");
		dialogDonut.getTxtIRad().setText("4");
		dialogDonut.getBtnOk().doClick();
		assertFalse(dialogDonut.isAccepted());
	}
	
	@Test
	public void testBtnOkClicked() {
		dialogDonut.getTxtXCoord().setText("1");
		dialogDonut.getTxtYCoord().setText("2");
		dialogDonut.getTxtRad().setText("4");
		dialogDonut.getTxtIRad().setText("3");
		dialogDonut.getBtnOk().doClick();
		assertTrue(dialogDonut.isAccepted());
		assertFalse(dialogDonut.isVisible());
	}
	
	@Test
	public void testGetShapeFromDialogOK() {
		dialogDonut.getTxtXCoord().setText("1");
		dialogDonut.getTxtYCoord().setText("2");
		dialogDonut.getTxtRad().setText("4");
		dialogDonut.getTxtIRad().setText("3");
		dialogDonut.setEdgeColor(Color.GREEN);
		dialogDonut.setInnerColor(Color.BLUE);
		
		Donut actual = (Donut) dialogDonut.getShapeFromDialog();
		
		Donut expected = new Donut(new Point(1,2), 4, 3, Color.GREEN, Color.BLUE);
		assertEquals(expected, actual);
	}


}