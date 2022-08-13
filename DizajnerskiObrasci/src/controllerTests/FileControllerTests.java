package controllerTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import controllers.FileController;
import files.*;
import frame.DrawingFrame;
import logger.LogReader;
import logger.LogWriter;
import model.DrawingModel;

class FileControllerTests {

	private DrawingModel model;
	private DrawingFrame frame;
	private LogWriter logWriter;
	private LogReader logReader;
	private FileController fileController;
	private FileStrategy fileStrategy;
	
	@BeforeEach
	public void setUp() {
		model = new DrawingModel();
		frame = new DrawingFrame();
		logWriter = new LogWriter();
		logReader = new LogReader();
		fileController = new FileController(model, frame, logWriter, logReader);
	}
	
	
	@Test
	public void testSaveLog_StrategyFileLogIsSetUp() {
		fileController.saveLog();
		fileStrategy = fileController.getFileManagerFileStrategy();
		assertTrue(fileStrategy.getClass().equals(FileLog.class));
	}
	
	@Test
	public void testLoadLog_StrategyFileLogIsSetUp() {
		fileController.loadLog();
		fileStrategy = fileController.getFileManagerFileStrategy();
		assertTrue(fileStrategy.getClass().equals(FileLog.class));
	}
	
	@Test
	public void testSaveDrawing_StrategyFileDrawingIsSetUp() {
		fileController.saveDrawing();
		fileStrategy = fileController.getFileManagerFileStrategy();
		assertTrue(fileStrategy.getClass().equals(FileDrawing.class));
	}
	
	@Test
	public void testLoadDrawing_StrategyFileDrawingIsSetUp() {
		fileController.loadDrawing();
		fileStrategy = fileController.getFileManagerFileStrategy();
		assertTrue(fileStrategy.getClass().equals(FileDrawing.class));
	}

}
