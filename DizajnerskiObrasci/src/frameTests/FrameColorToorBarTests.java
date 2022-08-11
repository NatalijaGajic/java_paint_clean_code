package frameTests;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import controllers.DrawingController;
import frame.*;

class FrameColorToorBarTests {

	private DrawingFrame frame;
	private DrawingController controller;
	private FrameColorToolBar colorToolBar;
	
	@BeforeEach
	public void setUp() {
		frame = new DrawingFrame();
		controller = mock(DrawingController.class);
		frame.setDrawingController(controller);
		frame.setDrawingControllerForToolBars(controller);
		colorToolBar = frame.getColorToolBar();
	}
	
	@Test
	public void testBtnActiveEdgeColorClick_ControllerMethodCalled() {
		colorToolBar.getBtnActiveEdgeColor().doClick();
		verify(controller).setActiveEdgeColor();
	}

	@Test
	public void testBtnActiveInnerColorClick_ControllerMethodCalled() {
		colorToolBar.getBtnActiveInnerColor().doClick();
		verify(controller).setActiveInnerColor();
	}

}
