package loggerTests;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import commands.*;
import geometry.*;
import logger.*;
import model.DrawingModel;

class LogReaderTests {

	private DrawingModel model;
	private LogReader logReader;
	private Point testPoint;
	private Point modifiedTestPoint;
	
	@BeforeEach
	public void setUp() {
		model = new DrawingModel();
		logReader = new LogReader(model);
		initializeShapes();
	}

	private void initializeShapes() {
		testPoint = new Point(1, 1, false, Color.BLACK);
		modifiedTestPoint = new Point(2, 2, false, Color.BLACK);
	}
	
	@Test
	void testReadCommandFromLog_CmdAddRead() {
		CmdAdd cmdAdd = new CmdAdd(model, testPoint);
		String cmdAddLog = cmdAdd.toString();
		logReader.addCommandToCommandsToBeExecutedLog(cmdAddLog);
		LoggerCommand commandFromLog = logReader.readCommandFromLog();
		assertTrue(commandFromLog.getCommand().equals(cmdAdd));
	}
	
	
	@Test
	void testReadCommandFromLog_CmdModifyCommandRead() {
		testPoint.setSelected(true);
		model.addShape(testPoint);
		model.addSelectedShape(testPoint);
		CmdModify cmdModify = new CmdModify(testPoint, modifiedTestPoint);
		String cmdModifyLog = cmdModify.toString();
		logReader.addCommandToCommandsToBeExecutedLog(cmdModifyLog);
		LoggerCommand commandFromLog = logReader.readCommandFromLog();
		assertTrue(commandFromLog.getCommand().equals(cmdModify));
	}
	
	
	@Test
	void testReadCommandFromLog_CmdSelectRead() {
		model.addShape(testPoint);
		CmdSelect cmdSelect = new CmdSelect(model, testPoint);
		String cmdSelectLog = cmdSelect.toString();
		logReader.addCommandToCommandsToBeExecutedLog(cmdSelectLog);
		LoggerCommand commandFromLog = logReader.readCommandFromLog();
		assertTrue(commandFromLog.getCommand().equals(cmdSelect));
	}
	
	@Test
	void testReadCommandFromLog_CmdDeselectRead() {
		model.addShape(testPoint);
		testPoint.setSelected(true);
		model.addSelectedShape(testPoint);
		CmdDeselect cmdDeselect = new CmdDeselect(model, testPoint);
		String cmdDeselectLog = cmdDeselect.toString();
		logReader.addCommandToCommandsToBeExecutedLog(cmdDeselectLog);
		LoggerCommand commandFromLog = logReader.readCommandFromLog();
		assertTrue(commandFromLog.getCommand() instanceof CmdDeselect);
	}
	
	@Test
	void testReadCommandFromLog_CmdToFrontRead() {
		model.addShape(testPoint);
		testPoint.setSelected(true);
		model.addSelectedShape(testPoint);
		CmdToFront cmdToFront = new CmdToFront(model, testPoint);
		String cmdToFrontLog = cmdToFront.toString();
		logReader.addCommandToCommandsToBeExecutedLog(cmdToFrontLog);
		LoggerCommand commandFromLog = logReader.readCommandFromLog();
		assertTrue(commandFromLog.getCommand().equals(cmdToFront));
	}
	
	@Test
	void testReadCommandFromLog_CmdToBackRead() {
		model.addShape(testPoint);
		testPoint.setSelected(true);
		model.addSelectedShape(testPoint);
		CmdToBack cmdToBack = new CmdToBack(model, testPoint);
		String cmdToBackLog = cmdToBack.toString();
		logReader.addCommandToCommandsToBeExecutedLog(cmdToBackLog);
		LoggerCommand commandFromLog = logReader.readCommandFromLog();
		assertTrue(commandFromLog.getCommand().equals(cmdToBack));
	}
	
	@Test
	void testReadCommandFromLog_CmdBringToFrontRead() {
		model.addShape(testPoint);
		testPoint.setSelected(true);
		model.addSelectedShape(testPoint);
		CmdBringToFront cmdBringToFront = new CmdBringToFront(model, testPoint);
		String cmdBringToFrontLog = cmdBringToFront.toString();
		logReader.addCommandToCommandsToBeExecutedLog(cmdBringToFrontLog);
		LoggerCommand commandFromLog = logReader.readCommandFromLog();
		assertTrue(commandFromLog.getCommand().equals(cmdBringToFront));
	}
	
	@Test
	void testReadCommandFromLog_CmdToBringBackRead() {
		model.addShape(testPoint);
		testPoint.setSelected(true);
		model.addSelectedShape(testPoint);
		CmdBringToBack cmdBringToBack = new CmdBringToBack(model, testPoint);
		String cmdBringToBackLog = cmdBringToBack.toString();
		logReader.addCommandToCommandsToBeExecutedLog(cmdBringToBackLog);
		LoggerCommand commandFromLog = logReader.readCommandFromLog();
		assertTrue(commandFromLog.getCommand().equals(cmdBringToBack));
	}
	
	@Test
	void testReadCommandFromLog_CmdDeleteRead() {
		model.addShape(testPoint);
		testPoint.setSelected(true);
		model.addSelectedShape(testPoint);
		ArrayList<Shape> shapesToDelete = new ArrayList<Shape>();
		shapesToDelete.add(testPoint);
		CmdDelete cmdDelete = new CmdDelete(model, shapesToDelete);
		String cmdDeleteLog = cmdDelete.toString();
		logReader.addCommandToCommandsToBeExecutedLog(cmdDeleteLog);
		LoggerCommand commandFromLog = logReader.readCommandFromLog();
		assertTrue(commandFromLog.getCommand().equals(cmdDelete));
	}
	

}
