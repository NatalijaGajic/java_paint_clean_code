package dialogTests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dialogs.DlgLine;
import geometry.Line;
import geometry.Point;

public class DlgLineTests {

	private DlgLine dialogLine;

	@BeforeEach
	public void setUp() {
		dialogLine = new DlgLine();
	}

	@Test
	public void testBtnEdgeColorClicked() {
		dialogLine.getBtnEdgeColor().doClick();
		assertEquals(dialogLine.getEdgeColor(), dialogLine.getPnlEdgeColor().getBackground());
	}

	@Test
	public void testBtnOKClickedInvalidValues() {
		dialogLine.getTxtXCoord().setText("1");
		dialogLine.getTxtYCoord().setText("2");
		dialogLine.getTxtEndPointXCoord().setText("3");
		dialogLine.getBtnOk().doClick();
		assertFalse(dialogLine.isAccepted());
	}

	@Test
	public void testBtnOkClicked() {
		dialogLine.getTxtXCoord().setText("1");
		dialogLine.getTxtYCoord().setText("2");
		dialogLine.getTxtEndPointXCoord().setText("3");
		dialogLine.getTxtEndPointYCoord().setText("4");
		dialogLine.getBtnOk().doClick();
		assertTrue(dialogLine.isAccepted());
		assertFalse(dialogLine.isVisible());
	}
	
	@Test
	public void testGetShapeFromDialogOK() {
		dialogLine.getTxtXCoord().setText("1");
		dialogLine.getTxtYCoord().setText("2");
		dialogLine.getTxtEndPointXCoord().setText("3");
		dialogLine.getTxtEndPointYCoord().setText("4");
		Line actual = (Line) dialogLine.getShapeFromDialog();
		dialogLine.setEdgeColor(Color.GREEN);
		Line expected = new Line(new Point(1,2), new Point(3,4), Color.GREEN);
		assertEquals(expected, actual);
	}


}
