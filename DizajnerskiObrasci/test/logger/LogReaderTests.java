package logger;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;
import java.util.Queue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import commands.*;
import controllers.ButtonsController;
import frame.*;
import geometry.*;
import model.DrawingModel;

class LogReaderTests {

	private DrawingModel model;
	private DrawingFrame frame;
	private LogReader logReader;
	private ButtonsController buttonsController;
	private FrameCommandOptionsToolBar commandOptionsToolBar;
	private Point testPoint;
	private CmdAdd cmdAdd;
	
	@BeforeEach
	public void setUp() {
		model = new DrawingModel();
		frame = new DrawingFrame();
		logReader = new LogReader();
		buttonsController = new ButtonsController(model, frame);
		commandOptionsToolBar = frame.getCommandOptionsToolBar();
		logReader.registerObserver(commandOptionsToolBar);
		frame.setButtonsControllerForOptionsToolBar(buttonsController);
		initializeCommand();
	}

	private void initializeCommand() {
		testPoint = new Point(1, 1, false, Color.BLACK);
		cmdAdd = new CmdAdd(model, testPoint);
	}
	
	@Test
	public void testAddCommandToCommandsToBeExecutedLog_Success() {
		String commandLog = cmdAdd.toString();
		logReader.addCommandToCommandsToBeExecutedLog(commandLog);
		Queue<String> commandsToBeExecuted = logReader.getCommandsToBeExecutedLog();
		assertEquals(commandLog, commandsToBeExecuted.poll());
		
	}
	
	public void testAddCommandToCommandsToBeExecutedLog_FirstLog_ExecuteLogButtonEnabled() {
		String commandLog = cmdAdd.toString();
		logReader.addCommandToCommandsToBeExecutedLog(commandLog);
		assertTrue(commandOptionsToolBar.isBtnExecuteLogEnabled());
	}
	
	@Test
	public void testReadCommandFromLog_CommandRead() {
		String commandLog = cmdAdd.toString();
		logReader.addCommandToCommandsToBeExecutedLog(commandLog);
		String readLog = logReader.readCommandFromLog();
		assertEquals(commandLog, readLog);
	}
	
	@Test
	public void testReadCommandFromLog_CommandLogRemovedFromQueue() {
		String commandLog = cmdAdd.toString();
		logReader.addCommandToCommandsToBeExecutedLog(commandLog);
		logReader.readCommandFromLog();
		Queue<String> commandsToBeExecuted = logReader.getCommandsToBeExecutedLog();
		assertEquals(0, commandsToBeExecuted.size());
	}
	
	@Test
	public void testReadCommandFromLog_NoLogs_ExecuteLogButtonDisabled() {
		String commandLog = cmdAdd.toString();
		logReader.addCommandToCommandsToBeExecutedLog(commandLog);
		logReader.readCommandFromLog();
		assertFalse	(commandOptionsToolBar.isBtnExecuteLogEnabled());
	}
}
