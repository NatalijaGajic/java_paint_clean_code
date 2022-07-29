package frameTests;
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
	
	@BeforeEach
	public void setUp() {
		frame = new DrawingFrame();
		controller = mock(DrawingController.class);
		frame.setController(controller);
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

}
