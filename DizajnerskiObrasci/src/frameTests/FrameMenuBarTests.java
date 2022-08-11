package frameTests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import controllers.FileController;
import frame.*;

public class FrameMenuBarTests {

	private DrawingFrame frame;
	private FileController controller;
	private FrameMenuBar menuBar;
	
	@BeforeEach
	public void setUp() {
		frame = new DrawingFrame();
		menuBar = frame.getFrameMenuBar();
		controller = mock(FileController.class);
		menuBar.setFileController(controller);
	}
	
	@Test
	public void testOpenLogFileClick_ControllerMethodCalled() {
		menuBar.getOptionOpenLogFile().doClick();
		verify(controller).loadLog();
	}
	
	@Test
	public void testOpenDrawingFileClick_ControllerMethodCalled() {
		menuBar.getOptionOpenDrawingFile().doClick();
		verify(controller).loadDrawing();
	}
	
	@Test
	public void testSaveAsLogClick_ControllerMethodCalled() {
		menuBar.getOptionSaveAsLog().doClick();
		verify(controller).saveLog();
	}
	
	@Test
	public void testSaveAsDrawingClick_ControllerMethodCalled() {
		menuBar.getOptionSaveAsDrawing().doClick();
		verify(controller).saveDrawing();
	}

}
