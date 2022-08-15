package frameTests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import controllers.*;
import frame.*;

import static org.mockito.Mockito.*;

class FrameOptionsToolBarTests {

	private DrawingFrame frame;
	private DrawingController drawingController;
	private CommandController commandController;
	private FrameOptionsToolBar optionsToolBar;
	
	@BeforeEach
	public void setUp() {
		frame = new DrawingFrame();
		drawingController = mock(DrawingController.class);
		commandController = mock(CommandController.class);
		frame.setDrawingController(drawingController);
		frame.setCommandControllerForOptionsToolBar(commandController);
		optionsToolBar = frame.getOptionsToolBar();
	}
	
	@Test
	public void testBtnModifyClick_ControllerMethodCalled() {
		optionsToolBar.getBtnModify().setEnabled(true);
		optionsToolBar.getBtnModify().doClick();
		verify(drawingController).modifyShape();
	}

	@Test
	public void testBtnDeleteClick_ControllerMethodCalled() {
		optionsToolBar.getBtnDelete().setEnabled(true);
		optionsToolBar.getBtnDelete().doClick();
		verify(drawingController).deleteShapesIfAccepted();
	}
	
	@Test
	public void testBtnToBackClick_ControllerMethodCalled() {
		optionsToolBar.getBtnToBack().setEnabled(true);
		optionsToolBar.getBtnToBack().doClick();
		verify(drawingController).moveShapeToBack();
	}
	
	@Test
	public void testBtnToFrontClick_ControllerMethodCalled() {
		optionsToolBar.getBtnToFront().setEnabled(true);
		optionsToolBar.getBtnToFront().doClick();
		verify(drawingController).moveShapeToFront();
	}
	
	@Test
	public void testBtnBringToBackClick_ControllerMethodCalled() {
		optionsToolBar.getBtnBringToBack().setEnabled(true);
		optionsToolBar.getBtnBringToBack().doClick();
		verify(drawingController).bringShapeToBack();
	}
	
	@Test
	public void testBtnBringToFrontClick_ControllerMethodCalled() {
		optionsToolBar.getBtnBringToFront().setEnabled(true);
		optionsToolBar.getBtnBringToFront().doClick();
		verify(drawingController).bringShapeToFront();
	}
	
	@Test
	public void testBtnUndoClick_ControllerMethodCalled() {
		optionsToolBar.getBtnUndo().setEnabled(true);
		optionsToolBar.getBtnUndo().doClick();
		verify(commandController).undoCommand();
	}
	
	@Test
	public void testBtnRedoClick_ControllerMethodCalled() {
		optionsToolBar.getBtnRedo().setEnabled(true);
		optionsToolBar.getBtnRedo().doClick();
		verify(commandController).redoCommand();
	}
	
	@Test
	public void testBtnExecuteLogClick_ControllerMethodCalled() {
		optionsToolBar.getBtnExecuteLog().setEnabled(true);
		optionsToolBar.getBtnExecuteLog().doClick();
		verify(commandController).executeLog();
	}

}
