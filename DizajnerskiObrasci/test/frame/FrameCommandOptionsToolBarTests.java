package frame;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import controllers.CommandController;
import frame.DrawingFrame;
import frame.FrameCommandOptionsToolBar;

class FrameCommandOptionsToolBarTests {

	private DrawingFrame frame;
	private CommandController commandController;
	private FrameCommandOptionsToolBar commandOptionsToolBar;
	
	@BeforeEach
	public void setUp() {
		frame = new DrawingFrame();
		commandController = mock(CommandController.class);
		frame.setCommandControllerForOptionsToolBar(commandController);
		commandOptionsToolBar = frame.getCommandOptionsToolBar();
	}
	
	@Test
	public void testBtnUndoClick_ControllerMethodCalled() {
		commandOptionsToolBar.getBtnUndo().setEnabled(true);
		commandOptionsToolBar.getBtnUndo().doClick();
		verify(commandController).undoCommand();
	}
	
	@Test
	public void testBtnRedoClick_ControllerMethodCalled() {
		commandOptionsToolBar.getBtnRedo().setEnabled(true);
		commandOptionsToolBar.getBtnRedo().doClick();
		verify(commandController).redoCommand();
	}
	
	@Test
	public void testBtnExecuteLogClick_ControllerMethodCalled() {
		commandOptionsToolBar.getBtnExecuteLog().setEnabled(true);
		commandOptionsToolBar.getBtnExecuteLog().doClick();
		verify(commandController).executeLog();
	}

}
