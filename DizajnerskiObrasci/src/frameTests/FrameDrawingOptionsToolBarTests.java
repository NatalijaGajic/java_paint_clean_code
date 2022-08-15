package frameTests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import controllers.*;
import frame.*;

import static org.mockito.Mockito.*;

class FrameDrawingOptionsToolBarTests {

	private DrawingFrame frame;
	private DrawingController drawingController;
	private FrameDrawingOptionsToolBar drawingOptionsToolBar;
	
	@BeforeEach
	public void setUp() {
		frame = new DrawingFrame();
		drawingController = mock(DrawingController.class);
		frame.setDrawingController(drawingController);
		drawingOptionsToolBar = frame.getDrawingOptionsToolBar();
	}
	
	@Test
	public void testBtnModifyClick_ControllerMethodCalled() {
		drawingOptionsToolBar.getBtnModify().setEnabled(true);
		drawingOptionsToolBar.getBtnModify().doClick();
		verify(drawingController).modifyShape();
	}

	@Test
	public void testBtnDeleteClick_ControllerMethodCalled() {
		drawingOptionsToolBar.getBtnDelete().setEnabled(true);
		drawingOptionsToolBar.getBtnDelete().doClick();
		verify(drawingController).deleteShapesIfAccepted();
	}
	
	@Test
	public void testBtnToBackClick_ControllerMethodCalled() {
		drawingOptionsToolBar.getBtnToBack().setEnabled(true);
		drawingOptionsToolBar.getBtnToBack().doClick();
		verify(drawingController).moveShapeToBack();
	}
	
	@Test
	public void testBtnToFrontClick_ControllerMethodCalled() {
		drawingOptionsToolBar.getBtnToFront().setEnabled(true);
		drawingOptionsToolBar.getBtnToFront().doClick();
		verify(drawingController).moveShapeToFront();
	}
	
	@Test
	public void testBtnBringToBackClick_ControllerMethodCalled() {
		drawingOptionsToolBar.getBtnBringToBack().setEnabled(true);
		drawingOptionsToolBar.getBtnBringToBack().doClick();
		verify(drawingController).bringShapeToBack();
	}
	
	@Test
	public void testBtnBringToFrontClick_ControllerMethodCalled() {
		drawingOptionsToolBar.getBtnBringToFront().setEnabled(true);
		drawingOptionsToolBar.getBtnBringToFront().doClick();
		verify(drawingController).bringShapeToFront();
	}
	
	

}
