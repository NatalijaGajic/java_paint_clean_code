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
	
	@Test
	public void testBtnToFrontClick() {
		optionsToolBar.getBtnToFront().setEnabled(true);
		optionsToolBar.getBtnToFront().doClick();
		verify(controller).moveShapeToFront();
	}
	
	@Test
	public void testBtnBringToBackClick() {
		optionsToolBar.getBtnBringToBack().setEnabled(true);
		optionsToolBar.getBtnBringToBack().doClick();
		verify(controller).bringShapeToBack();
	}
	
	@Test
	public void testBtnBringToFrontClick() {
		optionsToolBar.getBtnBringToFront().setEnabled(true);
		optionsToolBar.getBtnBringToFront().doClick();
		verify(controller).bringShapeToFront();
	}
	
	@Test
	public void testBtnUndoClick() {
		optionsToolBar.getBtnUndo().setEnabled(true);
		optionsToolBar.getBtnUndo().doClick();
		verify(controller).undoCommand();
	}
	
	@Test
	public void testBtnRedoClick() {
		optionsToolBar.getBtnRedo().setEnabled(true);
		optionsToolBar.getBtnRedo().doClick();
		verify(controller).redoCommand();
	}

}
