package controllerTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import commandHandler.CommandsHandler;
import controllers.DrawingController;
import controllers.FileController;
import files.*;
import frame.DrawingFrame;
import logger.LogReader;
import logger.LogWriter;
import model.DrawingModel;

class FileControllerTests {

	private DrawingModel model;
	private DrawingFrame frame;
	private CommandsHandler commandsHandler;
	private DrawingController controller;
	private LogWriter logWriter;
	private LogReader logReader;
	private FileController fileController;
	private FileStrategy fileStrategy;
	
	@BeforeEach
	public void setUp() {
		model = new DrawingModel();
		frame = new DrawingFrame();
		commandsHandler = new CommandsHandler();
		controller = new DrawingController(model, frame, commandsHandler);
		logWriter = new LogWriter();
		logReader = new LogReader(controller);
		fileController = new FileController(model, frame, logWriter, logReader);
	}
	
	
	@Test
	void testSaveLog_StrategyFileLogIsSetUp() {
		fileController.saveLog();
		fileStrategy = fileController.getFileManagerFileStrategy();
		assertTrue(fileStrategy.getClass().equals(FileLog.class));
	}
	
	@Test
	void testLoadLog_StrategyFileLogIsSetUp() {
		fileController.loadLog();
		fileStrategy = fileController.getFileManagerFileStrategy();
		assertTrue(fileStrategy.getClass().equals(FileLog.class));
	}

}
