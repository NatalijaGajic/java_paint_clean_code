package controllerTests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import commandHandler.CommandsHandler;
import commands.*;
import controllers.CommandController;
import frame.DrawingFrame;
import geometry.*;
import logger.*;
import model.DrawingModel;

class CommandControllerTests {

	private DrawingModel model;
	private DrawingFrame frame;
	private CommandController commandController;
	private CommandsHandler commandsHandler;
	private LogWriter logWriter;
	private LogReader logReader;
	private LogParser logParser;
	private Stack<Command> executedCommands;
	private Point testPoint;
	private Line testLine;
	private Point modifiedTestPoint;
	
	@BeforeEach
	public void setUp() {	
		model = new DrawingModel();
		frame = new DrawingFrame();
		commandsHandler = new CommandsHandler();
		logWriter = new LogWriter();
		logReader = new LogReader();
		logParser = new LogParser(model, logReader);
		commandController = new CommandController(frame, commandsHandler, logWriter, logParser);
		executedCommands = commandsHandler.getExecutedCommands();
		initializeShapes();
	}
	
	private void initializeShapes() {
		testPoint = new Point(1, 1, true, Color.BLACK);
		testLine = new Line(new Point(1,1), new Point(2,2), false, Color.BLACK);
		modifiedTestPoint = new Point(2, 2, false, Color.BLACK);
	}
	
	@Test
	public void testExecuteLog_CmdAdd_ShapeAddedToModel() {
		CmdAdd cmdAdd = new CmdAdd(model, testPoint);
		String cmdAddLog = cmdAdd.toString();
		logReader.addCommandToCommandsToBeExecutedLog(cmdAddLog);
		commandController.executeLog();
		assertEquals(testPoint, model.getShapeAtIndex(0));
	}
	
	@Test
	public void testExecuteLog_CmdAddAddedToExecutedCommands() {
		CmdAdd cmdAdd = new CmdAdd(model, testPoint);
		String cmdAddLog = cmdAdd.toString();
		logReader.addCommandToCommandsToBeExecutedLog(cmdAddLog);
		commandController.executeLog();
		assertTrue(executedCommands.get(0).equals(cmdAdd));
	}
	
	@Test
	public void testExecuteLog_CmdModify_ShapeModified() {
		testPoint.setSelected(true);
		model.addShape(testPoint);
		model.addSelectedShape(testPoint);
		Point modifiedPoint = new Point(2, 2, true, Color.BLACK);
		CmdModify cmdModify = new CmdModify(testPoint, modifiedTestPoint);
		String cmdModifyLog = cmdModify.toString();
		logReader.addCommandToCommandsToBeExecutedLog(cmdModifyLog);
		commandController.executeLog();
		assertEquals(modifiedPoint, model.getShapeAtIndex(0));
	}
	
	@Test
	public void testExecuteLog_CmdModifyAddedToExecutedCommands() {
		model.addShape(testPoint);
		model.addSelectedShape(testPoint);
		CmdModify cmdModify = new CmdModify(testPoint, modifiedTestPoint);
		String cmdModifyLog = cmdModify.toString();
		logReader.addCommandToCommandsToBeExecutedLog(cmdModifyLog);
		commandController.executeLog();
		assertTrue(executedCommands.get(0).equals(cmdModify));
	}
	
	@Test
	public void testExecuteLog_CmdSelect_ShapeSelected() {
		testPoint.setSelected(false);
		model.addShape(testPoint);
		CmdSelect cmdSelect = new CmdSelect(model, testPoint);
		String cmdSelectLog = cmdSelect.toString();
		logReader.addCommandToCommandsToBeExecutedLog(cmdSelectLog);
		commandController.executeLog();
		assertTrue(testPoint.isSelected());
	}
	
	@Test
	public void testExecuteLog_CmdSelectAddedToExecutedCommands() {
		testPoint.setSelected(false);
		model.addShape(testPoint);
		CmdSelect cmdSelect = new CmdSelect(model, testPoint);
		String cmdSelectLog = cmdSelect.toString();
		logReader.addCommandToCommandsToBeExecutedLog(cmdSelectLog);
		commandController.executeLog();
		assertTrue(executedCommands.get(0).equals(cmdSelect));
	}
	@Test
	public void testExecuteLog_CmdDeselect_ShapeDeselected() {
		model.addShape(testPoint);
		model.addSelectedShape(testPoint);
		CmdDeselect cmdDeselect = new CmdDeselect(model, testPoint);
		String cmdDeselectLog = cmdDeselect.toString();
		logReader.addCommandToCommandsToBeExecutedLog(cmdDeselectLog);
		commandController.executeLog();
		assertFalse(testPoint.isSelected());
	}
	
	@Test
	public void testExecuteLog_CmdDeselectAddedToExecutedCommands() {
		model.addShape(testPoint);
		model.addSelectedShape(testPoint);
		CmdDeselect cmdDeselect = new CmdDeselect(model, testPoint);
		String cmdDeselectLog = cmdDeselect.toString();
		logReader.addCommandToCommandsToBeExecutedLog(cmdDeselectLog);
		commandController.executeLog();
		assertTrue(executedCommands.get(0).equals(cmdDeselect));
	}
	
	@Test
	public void testExecuteLog_CmdToFront_ShapeBroughtFront() {
		model.addShape(testPoint);
		model.addShape(testLine);
		model.addSelectedShape(testPoint);
		CmdToFront cmdToFront = new CmdToFront(model, testPoint);
		String cmdToFrontLog = cmdToFront.toString();
		logReader.addCommandToCommandsToBeExecutedLog(cmdToFrontLog);
		commandController.executeLog();
		assertEquals(testPoint, model.getShapeAtIndex(1));
	}
	
	@Test
	public void testExecuteLog_CmdToFrontAddedToExecutedCommands() {
		model.addShape(testPoint);
		model.addShape(testLine);
		model.addSelectedShape(testPoint);
		CmdToFront cmdToFront = new CmdToFront(model, testPoint);
		String cmdToFrontLog = cmdToFront.toString();
		logReader.addCommandToCommandsToBeExecutedLog(cmdToFrontLog);
		commandController.executeLog();
		assertTrue(executedCommands.get(0).equals(cmdToFront));
	}
	
	@Test
	public void testExecuteLog_CmdToBack_ShapeBroughtBack() {
		model.addShape(testLine);
		model.addShape(testPoint);
		model.addSelectedShape(testPoint);
		CmdToBack cmdToBack = new CmdToBack(model, testPoint);
		String cmdToBackLog = cmdToBack.toString();
		logReader.addCommandToCommandsToBeExecutedLog(cmdToBackLog);
		commandController.executeLog();
		assertEquals(testPoint, model.getShapeAtIndex(0));
	}
	
	@Test
	public void testExecuteLog_CmdToBackAddedToExecutedCommands() {
		model.addShape(testLine);
		model.addShape(testPoint);
		model.addSelectedShape(testPoint);
		CmdToBack cmdToBack = new CmdToBack(model, testPoint);
		String cmdToBackLog = cmdToBack.toString();
		logReader.addCommandToCommandsToBeExecutedLog(cmdToBackLog);
		commandController.executeLog();
		assertTrue(executedCommands.get(0).equals(cmdToBack));
	}
	
	@Test
	public void testExecuteLog_CmdBringToFront_ShapeBroughtToFront() {
		model.addShape(testPoint);
		model.addShape(testLine);
		model.addSelectedShape(testPoint);
		CmdBringToFront cmdBringToFront = new CmdBringToFront(model, testPoint);
		String cmdBringToFrontLog = cmdBringToFront.toString();
		logReader.addCommandToCommandsToBeExecutedLog(cmdBringToFrontLog);
		commandController.executeLog();
		assertEquals(testPoint, model.getShapeAtIndex(1));
	}
	
	@Test
	public void testExecuteLog_CmdBringToFrontAddedToExecutedCommands() {
		model.addShape(testPoint);
		model.addShape(testLine);
		model.addSelectedShape(testPoint);
		CmdBringToFront cmdBringToFront = new CmdBringToFront(model, testPoint);
		String cmdBringToFrontLog = cmdBringToFront.toString();
		logReader.addCommandToCommandsToBeExecutedLog(cmdBringToFrontLog);
		commandController.executeLog();
		assertTrue(executedCommands.get(0).equals(cmdBringToFront));
	}
	
	@Test
	public void testExecuteLog_CmdBringToBack_ShapeBroughtToBack() {
		model.addShape(testLine);
		model.addShape(testPoint);
		model.addSelectedShape(testPoint);
		CmdBringToBack cmdBringToBack = new CmdBringToBack(model, testPoint);
		String cmdBringToBackLog = cmdBringToBack.toString();
		logReader.addCommandToCommandsToBeExecutedLog(cmdBringToBackLog);
		commandController.executeLog();
		assertEquals(testPoint, model.getShapeAtIndex(0));
	}
	
	@Test
	public void testExecuteLog_CmdBringToBackAddedToExecutedCommands() {
		model.addShape(testLine);
		model.addShape(testPoint);
		model.addSelectedShape(testPoint);
		CmdBringToBack cmdBringToBack = new CmdBringToBack(model, testPoint);
		String cmdBringToBackLog = cmdBringToBack.toString();
		logReader.addCommandToCommandsToBeExecutedLog(cmdBringToBackLog);
		commandController.executeLog();
		assertTrue(executedCommands.get(0).equals(cmdBringToBack));
	}
	
	@Test
	public void testExecuteLog_CmdDelete_ShapeDeleted() {
		model.addShape(testPoint);
		model.addSelectedShape(testPoint);
		ArrayList<Shape> shapesToDelete = new ArrayList<Shape>();
		shapesToDelete.add(testPoint);
		CmdDelete cmdDelete = new CmdDelete(model, shapesToDelete);
		String cmdDeleteLog = cmdDelete.toString();
		logReader.addCommandToCommandsToBeExecutedLog(cmdDeleteLog);
		commandController.executeLog();
		assertFalse(model.doesContainShape(testPoint));
	}
	
	@Test
	public void testExecuteLog_CmdDeleteAddedToExecutedCommands() {
		model.addShape(testPoint);
		model.addSelectedShape(testPoint);
		ArrayList<Shape> shapesToDelete = new ArrayList<Shape>();
		shapesToDelete.add(testPoint);
		CmdDelete cmdDelete = new CmdDelete(model, shapesToDelete);
		String cmdDeleteLog = cmdDelete.toString();
		logReader.addCommandToCommandsToBeExecutedLog(cmdDeleteLog);
		commandController.executeLog();
		assertTrue(executedCommands.get(0).equals(cmdDelete));
	}

}
