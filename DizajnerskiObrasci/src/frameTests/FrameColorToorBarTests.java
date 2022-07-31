package frameTests;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllers.DrawingController;
import frame.DrawingFrame;
import frame.FrameColorToolBar;

class FrameColorToorBarTests {

	private DrawingFrame frame;
	private DrawingController controller;
	private FrameColorToolBar colorToolBar;
	
	@BeforeEach
	public void setUp() {
		frame = new DrawingFrame();
		controller = mock(DrawingController.class);
		frame.setController(controller);
		colorToolBar = frame.getColorToolBar();
	}
	
	@Test
	public void testBtnActiveEdgeColorClick() {
		colorToolBar.getBtnActiveEdgeColor().doClick();
		verify(controller).setActiveEdgeColor();
	}

	@Test
	public void testBtnActiveInnerColorClick() {
		colorToolBar.getBtnActiveInnerColor().doClick();
		verify(controller).setActiveInnerColor();
	}

}
