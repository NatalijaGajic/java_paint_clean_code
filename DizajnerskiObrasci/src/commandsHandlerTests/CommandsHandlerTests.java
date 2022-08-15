package commandsHandlerTests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Stack;
import model.DrawingModel;
import frame.*;
import controllers.*;
import commandHandler.CommandsHandler;
import geometry.*;
import commands.*;


class CommandsHandlerTests {

	private DrawingModel model;
	private DrawingFrame frame;
	private ButtonsController buttonsController;
	private FrameCommandOptionsToolBar commandOptionsToolBar;
	private CommandsHandler commandsHandler;
	private Point point;
	private Circle circle;
	private CmdAdd cmdAddPoint;
	private CmdAdd cmdAddCircle;
	private CmdAdd cmdAddMock;
	private Stack<Command> executedCommands;
	private Stack<Command> unexecutedCommands;
	
	@BeforeEach
	public void setUp() {
		model = new DrawingModel();
		frame = new DrawingFrame();
		buttonsController = new ButtonsController(model, frame);
		commandsHandler = new CommandsHandler();
		commandOptionsToolBar = frame.getCommandOptionsToolBar();
		executedCommands = commandsHandler.getExecutedCommands();
		unexecutedCommands = commandsHandler.getUnexecutedCommands();
		frame.setButtonsControllerForOptionsToolBar(buttonsController);
		commandsHandler.registerObserver(commandOptionsToolBar);
		initializeCommands();
		
	}
	
	private void initializeCommands() {
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
		assertTrue(commandOptionsToolBar.isBtnUndoEnabled());
	}
	
	@Test
	public void testUndoExecutedCommand_CommandPopedFromExecutedCommandsStack() {
		executedCommands.push(cmdAddPoint);
		commandsHandler.undoExecutedCommand();
		assertEquals(0, executedCommands.size());
	}
	
	@Test
	public void testUndoExecutedCommand_CommandPushedToUnexcutedCommandsStack() {
		executedCommands.push(cmdAddPoint);
		commandsHandler.undoExecutedCommand();
		assertEquals(1, unexecutedCommands.size());
	}
	
	@Test
	public void testUndoExecutedCommand_UnexecuteCalled() {
		executedCommands.push(cmdAddMock);
		commandsHandler.undoExecutedCommand();
		verify(cmdAddMock).unexecute();
	}
	
	@Test
	public void testUndoExecutedCommand_RedoButtonEnabled() {
		cmdAddPoint.execute();
		executedCommands.push(cmdAddPoint);
		commandsHandler.undoExecutedCommand();
		assertTrue(commandOptionsToolBar.isBtnRedoEnabled());
	}
	
	@Test
	public void testRedoUnexecutedCommand_CommandPopedFromUnexecutedCommandsStack() {
		unexecutedCommands.push(cmdAddPoint);
		commandsHandler.redoUnexecutedCommand();
		assertEquals(0, unexecutedCommands.size());
	}
	
	@Test
	public void testRedoUnexecutedCommand_CommandPushedToExecutedCommandsStack() {
		unexecutedCommands.push(cmdAddPoint);
		commandsHandler.redoUnexecutedCommand();
		assertEquals(1, executedCommands.size());
	}
	
	@Test
	public void testRedoUnexecutedCommand_ExecuteCalled() {
		unexecutedCommands.push(cmdAddMock);
		commandsHandler.redoUnexecutedCommand();
		verify(cmdAddMock).execute();
	}
	
	@Test
	public void testRedoUnexecutedCommand_UndoButtonEnabled() {
		unexecutedCommands.push(cmdAddMock);
		commandsHandler.redoUnexecutedCommand();
		assertTrue(commandOptionsToolBar.isBtnUndoEnabled());
	}
	

}
