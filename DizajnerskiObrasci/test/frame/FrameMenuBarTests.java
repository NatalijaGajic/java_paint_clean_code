package frame;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import controllers.FileController;

public class FrameMenuBarTests {

	private DrawingFrame frame;
	private FileController fileController;
	private FrameMenuBar menuBar;
	
	@BeforeEach
	public void setUp() {
		frame = new DrawingFrame();
		menuBar = frame.getFrameMenuBar();
		fileController = mock(FileController.class);
		menuBar.setFileController(fileController);
	}
	
	@Test
	public void testOpenLogFileClick_ControllerMethodCalled() {
		menuBar.getOptionOpenLogFile().doClick();
		verify(fileController).loadLog();
	}
	
	@Test
	public void testOpenDrawingFileClick_ControllerMethodCalled() {
		menuBar.getOptionOpenDrawingFile().doClick();
		verify(fileController).loadDrawing();
	}
	
	@Test
	public void testSaveAsLogClick_ControllerMethodCalled() {
		menuBar.getOptionSaveAsLog().doClick();
		verify(fileController).saveLog();
	}
	
	@Test
	public void testSaveAsDrawingClick_ControllerMethodCalled() {
		menuBar.getOptionSaveAsDrawing().doClick();
		verify(fileController).saveDrawing();
	}

}
