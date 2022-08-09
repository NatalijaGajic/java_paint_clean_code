package commandsHandlerTests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import commandHandler.CommandsHandler;
import commands.*;
import controllers.DrawingController;

import static org.mockito.Mockito.*;

import frame.DrawingFrame;
import frame.FrameOptionsToolBar;
import geometry.*;
import model.DrawingModel;

class CommandsHandlerTests {


	private DrawingModel model;
	private DrawingFrame frame;
	private DrawingController controller;
	private FrameOptionsToolBar optionsToolBar;
	private CommandsHandler commandsHandler;
	private Point point;
	Circle circle;
	private CmdAdd cmdAddPoint;
	private CmdAdd cmdAddCircle;
	private CmdAdd cmdAddMock;
	Stack<Command> executedCommands;
	Stack<Command> unexecutedCommands;
	
	@BeforeEach
	public void setUp() {
		model = new DrawingModel();
		frame = new DrawingFrame();
		controller = new DrawingController(model, frame);
		optionsToolBar = frame.getOptionsToolBar();
		commandsHandler = new CommandsHandler();
		
		frame.setController(controller);
		frame.setControllerForToolBars(controller);
		
		commandsHandler.registerObserver(optionsToolBar);
		executedCommands = commandsHandler.getExecutedCommands();
		unexecutedCommands = commandsHandler.getUnexecutedCommands();
		
		point = new Point(100, 100, false);
		circle = new Circle(new Point(11, 11), 10, false);
		
		cmdAddPoint = new CmdAdd(model, point);
		cmdAddCircle = new CmdAdd(model, circle);
		cmdAddMock =  mock(CmdAdd.class);
	}
	
	@Test
	public void testAddExecutedCommand_CommandPushedToExecutedCommandsStack() {
		commandsHandler.addExecutedCommand(cmdAddPoint);
		assertEquals(1, executedCommands.size());
	}
	
	
	@Test
	public void testAddExecuted_CommandUnexecutedCommandsStackCleared() {
		unexecutedCommands.push(cmdAddPoint);
		commandsHandler.addExecutedCommand(cmdAddCircle);
		assertEquals(0, unexecutedCommands.size());
	}
	
	@Test
	public void testAddExecuted_CommandUndoButtonEnabled() {
		commandsHandler.addExecutedCommand(cmdAddCircle);
		assertTrue(optionsToolBar.isBtnUndoEnabled());
	}
	
	@Test
	public void testUndo_CommandPopedFromExecutedCommandsStack() {
		executedCommands.push(cmdAddPoint);
		commandsHandler.undo();
		assertEquals(0, executedCommands.size());
	}
	
	@Test
	public void testUndo_CommandPushedToUnexcutedCommandsStack() {
		executedCommands.push(cmdAddPoint);
		commandsHandler.undo();
		assertEquals(1, unexecutedCommands.size());
	}
	
	@Test
	public void testUndo_UnexecuteCalled() {
		executedCommands.push(cmdAddMock);
		commandsHandler.undo();
		verify(cmdAddMock).unexecute();
	}
	
	@Test
	public void testUndo_RedoButtonEnabled() {
		cmdAddPoint.execute();
		executedCommands.push(cmdAddPoint);
		commandsHandler.undo();
		assertTrue(optionsToolBar.isBtnRedoEnabled());
	}
	
	@Test
	public void testRedo_CommandPopedFromUnexecutedCommandsStack() {
		unexecutedCommands.push(cmdAddPoint);
		commandsHandler.redo();
		assertEquals(0, unexecutedCommands.size());
	}
	
	@Test
	public void testRedo_CommandPushedToExecutedCommandsStack() {
		unexecutedCommands.push(cmdAddPoint);
		commandsHandler.redo();
		assertEquals(1, executedCommands.size());
	}
	
	@Test
	public void testRedo_ExecuteCalled() {
		unexecutedCommands.push(cmdAddMock);
		commandsHandler.redo();
		verify(cmdAddMock).execute();
	}
	
	@Test
	public void testRedo_UndoButtonEnabled() {
		unexecutedCommands.push(cmdAddMock);
		commandsHandler.redo();
		assertTrue(optionsToolBar.isBtnUndoEnabled());
	}
	

}
