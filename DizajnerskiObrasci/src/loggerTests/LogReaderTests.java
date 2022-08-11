package loggerTests;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;
import java.util.Stack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import commandHandler.CommandsHandler;
import commands.*;
import controllers.DrawingController;
import frame.DrawingFrame;
import geometry.*;
import logger.*;
import model.DrawingModel;

class LogReaderTests {

	private String pointLog;
	private String modifiedPointLog;
	
	private String cmdAddLog;
	private String cmdModifyLog;
	private String cmdBringToBackLog;
	private String cmdBringToFrontLog;
	private String cmdToBackLog;
	private String cmdToFrontLog;
	private String cmdSelectLog;
	private String cmdDeselectLog;
	private String cmdDeleteLog;
	
	private DrawingModel model;
	private DrawingFrame frame;
	private CommandsHandler commandsHandler;
	private DrawingController controller;
	private LogReader logReader;
	private LogWriter logWriter;
	private Command commandFromLog;
	private Point testPoint;
	private Point modifiedPoint;
	private Line testLine;
	private Stack<Command> executedCommands;
	
	@BeforeEach
	public void setUp() {
		model = new DrawingModel();
		frame = new DrawingFrame();
		commandsHandler = new CommandsHandler();
		controller = new DrawingController(model, frame, commandsHandler);
		logWriter = new LogWriter();
		logReader = new LogReader(controller);
		controller.setLogWriter(logWriter);
		controller.setLogReader(logReader);
		executedCommands = commandsHandler.getExecutedCommands();
		initializeStrings();
		initializeShapes();
	}
	
	private void initializeStrings() {
		pointLog = LoggerConstants.POINT + ":(1,1) BC:-16777216";
		modifiedPointLog = LoggerConstants.POINT + ":(2,2) BC:-16777216";
		
		cmdAddLog = LoggerConstants.ADD_COMMAND + " " + pointLog;
		cmdModifyLog = LoggerConstants.MODIFY_COMMAND + " " + pointLog + " to " + modifiedPointLog;
		cmdBringToBackLog = LoggerConstants.BRING_TO_BACK_COMMAND + " " + pointLog;
		cmdBringToFrontLog = LoggerConstants.BRING_TO_FRONT_COMMAND + " " + pointLog;
		cmdToBackLog = LoggerConstants.TO_BACK_COMMAND + " " + pointLog;
		cmdToFrontLog = LoggerConstants.TO_FRONT_COMMAND + " " + pointLog;
		cmdSelectLog = LoggerConstants.SELECT_COMMAND + " " + pointLog;
		cmdDeselectLog = LoggerConstants.DESELECT_COMMAND + " " + pointLog;
		cmdDeleteLog = LoggerConstants.DELETE_COMMAND + " " + pointLog;
	}
	
	private void initializeShapes() {
		testPoint = new Point(1, 1, false, Color.BLACK);
		modifiedPoint = new Point(2, 2, true, Color.BLACK);
		testLine = new Line(new Point(1,1), new Point(2,2), false, Color.BLACK);
	}
	
	@Test
	void testReadCommandFromLog_CmdAddRead() {
		logReader.addCommandToCommandsToBeExecutedLog(cmdAddLog);
		logReader.readCommandFromLog();
		commandFromLog = logReader.getCmd();
		assertTrue(commandFromLog instanceof CmdAdd);
	}
	
	@Test
	void testReadCommandFromLog_CmdAdd_ShapeAddedToModel() {
		logReader.addCommandToCommandsToBeExecutedLog(cmdAddLog);
		logReader.readCommandFromLog();
		assertEquals(testPoint, model.getShapeAtIndex(0));
	}
	
	@Test
	void testReadCommandFromLog_CmdAddAddedToExecutedCommands() {
		logReader.addCommandToCommandsToBeExecutedLog(cmdAddLog);
		logReader.readCommandFromLog();
		commandFromLog = logReader.getCmd();
		assertEquals(commandFromLog, executedCommands.get(0));
	}
	
	@Test
	void testReadCommandFromLog_CmdModifyCommandRead() {
		testPoint.setSelected(true);
		model.addShape(testPoint);
		model.addSelectedShape(testPoint);
		logReader.addCommandToCommandsToBeExecutedLog(cmdModifyLog);
		logReader.readCommandFromLog();
		commandFromLog = logReader.getCmd();
		assertTrue(commandFromLog instanceof CmdModify);
	}
	
	@Test
	void testReadCommandFromLog_CmdModify_ShapeModified() {
		testPoint.setSelected(true);
		model.addShape(testPoint);
		model.addSelectedShape(testPoint);
		logReader.addCommandToCommandsToBeExecutedLog(cmdModifyLog);
		logReader.readCommandFromLog();
		commandFromLog = logReader.getCmd();
		assertEquals(modifiedPoint, model.getShapeAtIndex(0));
	}
	
	@Test
	void testReadCommandFromLog_CmdModifyAddedToExecutedCommands() {
		testPoint.setSelected(true);
		model.addShape(testPoint);
		model.addSelectedShape(testPoint);
		logReader.addCommandToCommandsToBeExecutedLog(cmdModifyLog);
		logReader.readCommandFromLog();
		commandFromLog = logReader.getCmd();
		assertEquals(commandFromLog, executedCommands.get(0));
	}
	
	@Test
	void testReadCommandFromLog_CmdBringToBack_ShapeBroughtBack() {
		model.addShape(testLine);
		model.addShape(testPoint);
		model.addSelectedShape(testPoint);
		logReader.addCommandToCommandsToBeExecutedLog(cmdBringToBackLog);
		logReader.readCommandFromLog();
		assertEquals(testPoint, model.getShapeAtIndex(0));
	}
	
	@Test
	void testReadCommandFromLog_CmdBringToBackAddedToExecutedCommands() {
		model.addShape(testLine);
		model.addShape(testPoint);
		model.addSelectedShape(testPoint);
		logReader.addCommandToCommandsToBeExecutedLog(cmdBringToBackLog);
		logReader.readCommandFromLog();
		assertTrue(executedCommands.get(0) instanceof CmdBringToBack);
	}
	
	@Test
	void testReadCommandFromLog_CmdBringToFront_ShapeBroughtBack() {
		model.addShape(testPoint);
		model.addShape(testLine);
		model.addSelectedShape(testPoint);
		logReader.addCommandToCommandsToBeExecutedLog(cmdBringToFrontLog);
		logReader.readCommandFromLog();
		assertEquals(testPoint, model.getShapeAtIndex(1));
	}
	
	@Test
	void testReadCommandFromLog_CmdBringToFrontAddedToExecutedCommands() {
		model.addShape(testPoint);
		model.addShape(testLine);
		model.addSelectedShape(testPoint);
		logReader.addCommandToCommandsToBeExecutedLog(cmdBringToFrontLog);
		logReader.readCommandFromLog();
		assertTrue(executedCommands.get(0) instanceof CmdBringToFront);
	}
	
	@Test
	void testReadCommandFromLog_CmdToBack_ShapeBroughtBack() {
		model.addShape(testLine);
		model.addShape(testPoint);
		model.addSelectedShape(testPoint);
		logReader.addCommandToCommandsToBeExecutedLog(cmdToBackLog);
		logReader.readCommandFromLog();
		assertEquals(testPoint, model.getShapeAtIndex(0));
	}
	
	@Test
	void testReadCommandFromLog_CmdToBackAddedToExecutedCommands() {
		model.addShape(testLine);
		model.addShape(testPoint);
		model.addSelectedShape(testPoint);
		logReader.addCommandToCommandsToBeExecutedLog(cmdToBackLog);
		logReader.readCommandFromLog();
		assertTrue(executedCommands.get(0) instanceof CmdToBack);
	}
	
	@Test
	void testReadCommandFromLog_CmdToFront_ShapeBroughtBack() {
		model.addShape(testPoint);
		model.addShape(testLine);
		model.addSelectedShape(testPoint);
		logReader.addCommandToCommandsToBeExecutedLog(cmdToFrontLog);
		logReader.readCommandFromLog();
		assertEquals(testPoint, model.getShapeAtIndex(1));
	}
	
	@Test
	void testReadCommandFromLog_CmdToFrontAddedToExecutedCommands() {
		model.addShape(testPoint);
		model.addShape(testLine);
		model.addSelectedShape(testPoint);
		logReader.addCommandToCommandsToBeExecutedLog(cmdToFrontLog);
		logReader.readCommandFromLog();
		assertTrue(executedCommands.get(0) instanceof CmdToFront);
	}
	
	@Test
	void testReadCommandFromLog_CmdSelectRead() {
		model.addShape(testPoint);
		logReader.addCommandToCommandsToBeExecutedLog(cmdSelectLog);
		logReader.readCommandFromLog();
		commandFromLog = logReader.getCmd();
		assertTrue(commandFromLog instanceof CmdSelect);
	}
	
	@Test
	void testReadCommandFromLog_CmdSelect_ShapeSelected() {
		model.addShape(testPoint);
		logReader.addCommandToCommandsToBeExecutedLog(cmdSelectLog);
		logReader.readCommandFromLog();
		assertTrue(testPoint.isSelected());
	}
	
	@Test
	void testReadCommandFromLog_CmdSelectAddedToExecutedCommands() {
		model.addShape(testPoint);
		logReader.addCommandToCommandsToBeExecutedLog(cmdSelectLog);
		logReader.readCommandFromLog();
		commandFromLog = logReader.getCmd();
		assertEquals(commandFromLog, executedCommands.get(0));
	}
	
	@Test
	void testReadCommandFromLog_CmdDeselectRead() {
		model.addShape(testPoint);
		testPoint.setSelected(true);
		model.addSelectedShape(testPoint);
		logReader.addCommandToCommandsToBeExecutedLog(cmdDeselectLog);
		logReader.readCommandFromLog();
		commandFromLog = logReader.getCmd();
		assertTrue(commandFromLog instanceof CmdDeselect);
	}
	
	@Test
	void testReadCommandFromLog_CmdDeselect_ShapeDeselected() {
		model.addShape(testPoint);
		testPoint.setSelected(true);
		model.addSelectedShape(testPoint);
		logReader.addCommandToCommandsToBeExecutedLog(cmdDeselectLog);
		logReader.readCommandFromLog();
		assertFalse(testPoint.isSelected());
	}
	
	@Test
	void testReadCommandFromLog_CmdDeselectAddedToExecutedCommands() {
		model.addShape(testPoint);
		testPoint.setSelected(true);
		model.addSelectedShape(testPoint);
		logReader.addCommandToCommandsToBeExecutedLog(cmdDeselectLog);
		logReader.readCommandFromLog();
		commandFromLog = logReader.getCmd();
		assertEquals(commandFromLog, executedCommands.get(0));
	}
	
	@Test
	void testReadCommandFromLog_CmdDelete_ShapeDeleted() {
		model.addShape(testPoint);
		testPoint.setSelected(true);
		model.addSelectedShape(testPoint);
		logReader.addCommandToCommandsToBeExecutedLog(cmdDeleteLog);
		logReader.readCommandFromLog();
		assertFalse(model.doesContainShape(testPoint));
	}
	
	@Test
	void testReadCommandFromLog_CmdDeleteAddedToExecutedCommands() {
		model.addShape(testPoint);
		testPoint.setSelected(true);
		model.addSelectedShape(testPoint);
		logReader.addCommandToCommandsToBeExecutedLog(cmdDeleteLog);
		logReader.readCommandFromLog();
		assertTrue(executedCommands.get(0) instanceof CmdDelete);
	}


}
