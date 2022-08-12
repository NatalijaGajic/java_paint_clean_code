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
	private CommandsHandler commandsHandler;
	private LogReader logReader;
	private Point testPoint;
	
	@BeforeEach
	public void setUp() {
		model = new DrawingModel();
		commandsHandler = new CommandsHandler();
		logReader = new LogReader(model);
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
	}
	
	@Test
	void testReadCommandFromLog_CmdAddRead() {
		logReader.addCommandToCommandsToBeExecutedLog(cmdAddLog);
		LoggerCommand commandFromLog = logReader.readCommandFromLog();
		assertTrue(commandFromLog.getCommand() instanceof CmdAdd);
	}
	
	
	@Test
	void testReadCommandFromLog_CmdModifyCommandRead() {
		testPoint.setSelected(true);
		model.addShape(testPoint);
		model.addSelectedShape(testPoint);
		logReader.addCommandToCommandsToBeExecutedLog(cmdModifyLog);
		LoggerCommand commandFromLog = logReader.readCommandFromLog();
		assertTrue(commandFromLog.getCommand() instanceof CmdModify);
	}
	
	
	@Test
	void testReadCommandFromLog_CmdSelectRead() {
		model.addShape(testPoint);
		logReader.addCommandToCommandsToBeExecutedLog(cmdSelectLog);
		LoggerCommand commandFromLog = logReader.readCommandFromLog();
		assertTrue(commandFromLog.getCommand() instanceof CmdSelect);
	}
	
	@Test
	void testReadCommandFromLog_CmdDeselectRead() {
		model.addShape(testPoint);
		testPoint.setSelected(true);
		model.addSelectedShape(testPoint);
		logReader.addCommandToCommandsToBeExecutedLog(cmdDeselectLog);
		LoggerCommand commandFromLog = logReader.readCommandFromLog();
		assertTrue(commandFromLog.getCommand() instanceof CmdDeselect);
	}
	
	@Test
	void testReadCommandFromLog_CmdToFrontRead() {
		model.addShape(testPoint);
		testPoint.setSelected(true);
		model.addSelectedShape(testPoint);
		logReader.addCommandToCommandsToBeExecutedLog(cmdToFrontLog);
		LoggerCommand commandFromLog = logReader.readCommandFromLog();
		assertTrue(commandFromLog.getCommand() instanceof CmdToFront);
	}
	
	@Test
	void testReadCommandFromLog_CmdToBackRead() {
		model.addShape(testPoint);
		testPoint.setSelected(true);
		model.addSelectedShape(testPoint);
		logReader.addCommandToCommandsToBeExecutedLog(cmdToBackLog);
		LoggerCommand commandFromLog = logReader.readCommandFromLog();
		assertTrue(commandFromLog.getCommand() instanceof CmdToBack);
	}
	
	@Test
	void testReadCommandFromLog_CmdBringToFrontRead() {
		model.addShape(testPoint);
		testPoint.setSelected(true);
		model.addSelectedShape(testPoint);
		logReader.addCommandToCommandsToBeExecutedLog(cmdBringToFrontLog);
		LoggerCommand commandFromLog = logReader.readCommandFromLog();
		assertTrue(commandFromLog.getCommand() instanceof CmdBringToFront);
	}
	
	@Test
	void testReadCommandFromLog_CmdToBringBackRead() {
		model.addShape(testPoint);
		testPoint.setSelected(true);
		model.addSelectedShape(testPoint);
		logReader.addCommandToCommandsToBeExecutedLog(cmdBringToBackLog);
		LoggerCommand commandFromLog = logReader.readCommandFromLog();
		assertTrue(commandFromLog.getCommand() instanceof CmdBringToBack);
	}
	
	@Test
	void testReadCommandFromLog_CmdDeleteRead() {
		model.addShape(testPoint);
		testPoint.setSelected(true);
		model.addSelectedShape(testPoint);
		logReader.addCommandToCommandsToBeExecutedLog(cmdDeleteLog);
		LoggerCommand commandFromLog = logReader.readCommandFromLog();
		assertTrue(commandFromLog.getCommand() instanceof CmdDelete);
	}
	

}
