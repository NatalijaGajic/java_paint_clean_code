package frameTests;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import controllers.DrawingController;
import frame.*;

class FrameColorToorBarTests {

	private DrawingFrame frame;
	private DrawingController drawingController;
	private FrameColorToolBar colorToolBar;
	
	@BeforeEach
	public void setUp() {
		frame = new DrawingFrame();
		drawingController = mock(DrawingController.class);
		frame.setDrawingController(drawingController);
		colorToolBar = frame.getColorToolBar();
	}
	
	@Test
	public void testBtnActiveEdgeColorClick_ControllerMethodCalled() {
		colorToolBar.getBtnActiveEdgeColor().doClick();
		verify(drawingController).setActiveEdgeColor();
	}

	@Test
	public void testBtnActiveInnerColorClick_ControllerMethodCalled() {
		colorToolBar.getBtnActiveInnerColor().doClick();
		verify(drawingController).setActiveInnerColor();
	}

}
