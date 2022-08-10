package loggerTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import commandHandler.CommandsHandler;
import commands.*;
import controllers.DrawingController;
import frame.DrawingFrame;
import logger.LogReader;
import logger.LoggerConstants;
import model.DrawingModel;

class LogReaderTests {

	private String pointLog = LoggerConstants.POINT + ":(1,1) BC:-16777216";
	private String modifiedPointLog = LoggerConstants.POINT + ":(2,2) BC:-16777216";
	private String lineLog = LoggerConstants.LINE + ":SP(1,1) EP(2,2) BC:-16777216";
	private String circleLog = LoggerConstants.CIRCLE + ":(1,1) R:10, BC:-1, FC:-16777216";
	private String donutLog = LoggerConstants.DONUT + ":(1,1) OR:10, IR:5, BC:-1, FC:-16777216";
	private String rectangleLog = LoggerConstants.RECTANGLE + ":(1,1) W:50, H:40, BC:-1, FC:-16777216";
	private String hexagonLog = LoggerConstants.HEXAGON + ":(1,2) R:3, BC:-1, FC:-16777216";
	
	private String cmdAddLog = LoggerConstants.ADD_COMMAND + " " + pointLog;
	private String cmdModifyLog = LoggerConstants.MODIFY_COMMAND + " " + pointLog + " to " + modifiedPointLog;
	private String cmdBringToBackLog = LoggerConstants.BRING_TO_BACK_COMMAND + " " + pointLog;
	private String cmdBringToFrontLog = LoggerConstants.BRING_TO_FRONT_COMMAND + " " + pointLog;
	private String cmdToFrontLog = LoggerConstants.TO_FRONT_COMMAND + " " + pointLog;
	private String cmdToBackLog = LoggerConstants.TO_BACK_COMMAND + " " + pointLog;
	private String cmdSelectLog = LoggerConstants.SELECT_COMMAND + " " + pointLog;
	private String cmdDeselectLog = LoggerConstants.DESELECT_COMMAND + " " + pointLog;
	private String cmdDeleteLog = LoggerConstants.DELETE_COMMAND + " " + pointLog;
	
	private DrawingModel model;
	private DrawingFrame frame;
	private CommandsHandler commandsHandler;
	private DrawingController controller;
	private LogReader logReader;
	private Command commandFromLog;
	
	@BeforeEach
	public void setUp() {
		model = new DrawingModel();
		frame = new DrawingFrame();
		commandsHandler = new CommandsHandler();
		controller = new DrawingController(model, frame, commandsHandler);
		logReader = new LogReader(controller);
	}
	
	@Test
	void testReadCommandFromLog_AddCommandRead() {
		logReader.addCommandToCommandsToBeExecutedLog(cmdAddLog);
		logReader.readCommandFromLog();
		commandFromLog = logReader.getCmd();
		assertTrue(commandFromLog instanceof CmdAdd);
	}

}
