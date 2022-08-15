package frameTests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import controllers.DrawingController;
import frame.*;

import static org.mockito.Mockito.*;

class FrameOptionsToolBarTests {

	private DrawingFrame frame;
	private DrawingController controller;
	private FrameOptionsToolBar optionsToolBar;
	
	@BeforeEach
	public void setUp() {
		frame = new DrawingFrame();
		controller = mock(DrawingController.class);
		frame.setDrawingController(controller);
		optionsToolBar = frame.getOptionsToolBar();
	}
	
	@Test
	public void testBtnModifyClick_ControllerMethodCalled() {
		optionsToolBar.getBtnModify().setEnabled(true);
		optionsToolBar.getBtnModify().doClick();
		verify(controller).modifyShape();
	}

	@Test
	public void testBtnDeleteClick_ControllerMethodCalled() {
		optionsToolBar.getBtnDelete().setEnabled(true);
		optionsToolBar.getBtnDelete().doClick();
		verify(controller).deleteShapesIfAccepted();
	}
	
	@Test
	public void testBtnToBackClick_ControllerMethodCalled() {
		optionsToolBar.getBtnToBack().setEnabled(true);
		optionsToolBar.getBtnToBack().doClick();
		verify(controller).moveShapeToBack();
	}
	
	@Test
	public void testBtnToFrontClick_ControllerMethodCalled() {
		optionsToolBar.getBtnToFront().setEnabled(true);
		optionsToolBar.getBtnToFront().doClick();
		verify(controller).moveShapeToFront();
	}
	
	@Test
	public void testBtnBringToBackClick_ControllerMethodCalled() {
		optionsToolBar.getBtnBringToBack().setEnabled(true);
		optionsToolBar.getBtnBringToBack().doClick();
		verify(controller).bringShapeToBack();
	}
	
	@Test
	public void testBtnBringToFrontClick_ControllerMethodCalled() {
		optionsToolBar.getBtnBringToFront().setEnabled(true);
		optionsToolBar.getBtnBringToFront().doClick();
		verify(controller).bringShapeToFront();
	}
	
	@Test
	public void testBtnUndoClick_ControllerMethodCalled() {
		optionsToolBar.getBtnUndo().setEnabled(true);
		optionsToolBar.getBtnUndo().doClick();
		verify(controller).undoCommand();
	}
	
	@Test
	public void testBtnRedoClick_ControllerMethodCalled() {
		optionsToolBar.getBtnRedo().setEnabled(true);
		optionsToolBar.getBtnRedo().doClick();
		verify(controller).redoCommand();
	}
	
	@Test
	public void testBtnExecuteLogClick_ControllerMethodCalled() {
		optionsToolBar.getBtnExecuteLog().setEnabled(true);
		optionsToolBar.getBtnExecuteLog().doClick();
		verify(controller).executeLog();
	}

}
