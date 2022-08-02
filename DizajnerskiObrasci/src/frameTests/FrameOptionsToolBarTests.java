package frameTests;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllers.DrawingController;
import frame.DrawingFrame;
import frame.FrameOptionsToolBar;

import static org.mockito.Mockito.*;

class FrameOptionsToolBarTests {

	private DrawingFrame frame;
	private DrawingController controller;
	private FrameOptionsToolBar optionsToolBar;
	
	@Before
	public void setUp() {
		frame = new DrawingFrame();
		controller = mock(DrawingController.class);
		frame.setController(controller);
		frame.setControllerForToolBars(controller);
		optionsToolBar = frame.getOptionsToolBar();
	}
	
	@Test
	public void testBtnModifyClick() {
		optionsToolBar.getBtnModify().setEnabled(true);
		optionsToolBar.getBtnModify().doClick();
		verify(controller).modifyShape();
	}

	@Test
	public void testBtnDeleteClick() {
		optionsToolBar.getBtnDelete().setEnabled(true);
		optionsToolBar.getBtnDelete().doClick();
		verify(controller).deleteShapes();
	}
	
	@Test
	public void testBtnToBackClick() {
		optionsToolBar.getBtnToBack().setEnabled(true);
		optionsToolBar.getBtnToBack().doClick();
		verify(controller).moveShapeToBack();
	}

}
