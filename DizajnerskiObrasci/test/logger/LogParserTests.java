package logger;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import commands.*;
import geometry.*;
import model.DrawingModel;

class LogParserTests {

	private DrawingModel model;
	private LogReader logReader;
	private LogParser logParser;
	private Point testPoint;
	private Point modifiedTestPoint;
	private Line testLine;
	private Circle testCircle;
	private Donut testDonut;
	private Rectangle testRectangle;
	private HexagonAdapter testHexagon;
	
	@BeforeEach
	void setUp() {
		model = new DrawingModel();
		logReader = new LogReader();
		logParser = new LogParser(model, logReader);
		initializeShapes();
	}
	
	private void initializeShapes() {
		testPoint = new Point(1, 1, false, Color.BLACK);
		modifiedTestPoint = new Point(2, 2, false, Color.BLACK);
		testLine = new Line(new Point(1,1), new Point(2,2), false, Color.BLACK);
		testCircle = new Circle(new Point(1, 1), 10, false, Color.WHITE, Color.BLACK);
		testDonut = new Donut(new Point(1, 1), 10, 5, false, Color.WHITE, Color.BLACK);
		testRectangle = new Rectangle(new Point(1,1), 40, 50, false, Color.WHITE, Color.BLACK);
		testHexagon = new HexagonAdapter(new Point(1,2), 3, Color.WHITE, Color.BLACK);
	}
	
	@Test
	public void testParseCommandFromLog_AddPoint_Success() {
		CmdAdd cmdAdd = new CmdAdd(model, testPoint);
		String commandLog = cmdAdd.toString();
		logReader.addCommandToCommandsToBeExecutedLog(commandLog);
		LoggerCommand loggerCommand = logParser.parseCommandFromLog();
		assertEquals(cmdAdd, loggerCommand.getCommand());
	}
	
	@Test
	public void testParseCommandFromLog_AddLine_Success() {
		CmdAdd cmdAdd = new CmdAdd(model, testLine);
		String commandLog = cmdAdd.toString();
		logReader.addCommandToCommandsToBeExecutedLog(commandLog);
		LoggerCommand loggerCommand = logParser.parseCommandFromLog();
		assertEquals(cmdAdd, loggerCommand.getCommand());
	}
	
	@Test
	public void testParseCommandFromLog_AddCircle_Success() {
		CmdAdd cmdAdd = new CmdAdd(model, testCircle);
		String commandLog = cmdAdd.toString();
		logReader.addCommandToCommandsToBeExecutedLog(commandLog);
		LoggerCommand loggerCommand = logParser.parseCommandFromLog();
		assertEquals(cmdAdd, loggerCommand.getCommand());
	}
	
	@Test
	public void testParseCommandFromLog_AddDonut_Success() {
		CmdAdd cmdAdd = new CmdAdd(model, testDonut);
		String commandLog = cmdAdd.toString();
		logReader.addCommandToCommandsToBeExecutedLog(commandLog);
		LoggerCommand loggerCommand = logParser.parseCommandFromLog();
		assertEquals(cmdAdd, loggerCommand.getCommand());
	}
	
	@Test
	public void testParseCommandFromLog_AddRectangle_Success() {
		CmdAdd cmdAdd = new CmdAdd(model, testRectangle);
		String commandLog = cmdAdd.toString();
		logReader.addCommandToCommandsToBeExecutedLog(commandLog);
		LoggerCommand loggerCommand = logParser.parseCommandFromLog();
		assertEquals(cmdAdd, loggerCommand.getCommand());
	}
	
	@Test
	public void testParseCommandFromLog_AddHexagon_Success() {
		CmdAdd cmdAdd = new CmdAdd(model, testHexagon);
		String commandLog = cmdAdd.toString();
		logReader.addCommandToCommandsToBeExecutedLog(commandLog);
		LoggerCommand loggerCommand = logParser.parseCommandFromLog();
		assertEquals(cmdAdd, loggerCommand.getCommand());
	}
	
	@Test
	public void testParseCommandFromLog_ModifyPoint_Success() {
		addSelectedTestPointToModel();
		CmdModify cmdModify = new CmdModify(testPoint, modifiedTestPoint);
		String cmdModifyLog = cmdModify.toString();
		logReader.addCommandToCommandsToBeExecutedLog(cmdModifyLog);
		LoggerCommand loggerCommand = logParser.parseCommandFromLog();
		assertEquals(cmdModify, loggerCommand.getCommand());
	}
	
	@Test
	public void testParseCommandFromLog_SelectPoint_Success() {
		model.addShape(testPoint);
		CmdSelect cmdSelect = new CmdSelect(model, testPoint);
		String cmdSelectLog = cmdSelect.toString();
		logReader.addCommandToCommandsToBeExecutedLog(cmdSelectLog);
		LoggerCommand loggerCommand = logParser.parseCommandFromLog();
		assertEquals(cmdSelect, loggerCommand.getCommand());
	}
	
	@Test
	public void testParseCommandFromLog_DeselectPoint_Success() {
		addSelectedTestPointToModel();
		CmdDeselect cmdDeselect = new CmdDeselect(model, testPoint);
		String cmdDeselectLog = cmdDeselect.toString();
		logReader.addCommandToCommandsToBeExecutedLog(cmdDeselectLog);
		LoggerCommand loggerCommand = logParser.parseCommandFromLog();
		assertEquals(cmdDeselect, loggerCommand.getCommand());
	}
	
	@Test
	public void testParseCommandFromLog_MovePointToFront_Success() {
		addSelectedTestPointToModel();
		CmdToFront cmdToFront = new CmdToFront(model, testPoint);
		String cmdToFrontLog = cmdToFront.toString();
		logReader.addCommandToCommandsToBeExecutedLog(cmdToFrontLog);
		LoggerCommand loggerCommand = logParser.parseCommandFromLog();
		assertEquals(cmdToFront, loggerCommand.getCommand());
	}
	
	@Test
	public void testParseCommandFromLog_MovePointToBack_Success() {
		addSelectedTestPointToModel();
		CmdToBack cmdToBack = new CmdToBack(model, testPoint);
		String cmdToBackLog = cmdToBack.toString();
		logReader.addCommandToCommandsToBeExecutedLog(cmdToBackLog);
		LoggerCommand loggerCommand = logParser.parseCommandFromLog();
		assertEquals(cmdToBack, loggerCommand.getCommand());
	}
	
	@Test
	public void testParseCommandFromLog_BringPointToFront_Success() {
		addSelectedTestPointToModel();
		CmdBringToFront cmdBringToFront = new CmdBringToFront(model, testPoint);
		String cmdBringToFrontLog = cmdBringToFront.toString();
		logReader.addCommandToCommandsToBeExecutedLog(cmdBringToFrontLog);
		LoggerCommand loggerCommand = logParser.parseCommandFromLog();
		assertEquals(cmdBringToFront, loggerCommand.getCommand());
	}
	
	@Test
	public void testParseCommandFromLog_BringPointToBack_Success() {
		addSelectedTestPointToModel();
		CmdBringToBack cmdBringToBack = new CmdBringToBack(model, testPoint);
		String cmdBringToBackLog = cmdBringToBack.toString();
		logReader.addCommandToCommandsToBeExecutedLog(cmdBringToBackLog);
		LoggerCommand loggerCommand = logParser.parseCommandFromLog();
		assertEquals(cmdBringToBack, loggerCommand.getCommand());
	}
	
	@Test
	public void testParseCommandFromLog_DeletePoint_Success() {
		addSelectedTestPointToModel();
		ArrayList<Shape> shapesToDelete = new ArrayList<Shape>();
		shapesToDelete.add(testPoint);
		CmdDelete cmdDelete = new CmdDelete(model, shapesToDelete);
		String cmdDeleteLog = cmdDelete.toString();
		logReader.addCommandToCommandsToBeExecutedLog(cmdDeleteLog);
		LoggerCommand loggerCommand = logParser.parseCommandFromLog();
		assertEquals(cmdDelete, loggerCommand.getCommand());
	}
	
	@Test
	public void testParseCommandFromLog_DeleteMultipleShapes_Success() {
		addSelectedTestPointToModel();
		addSelectedLineToModel();
		ArrayList<Shape> shapesToDelete = new ArrayList<Shape>();
		shapesToDelete.add(testPoint);
		shapesToDelete.add(testLine);
		CmdDelete cmdDelete = new CmdDelete(model, shapesToDelete);
		String cmdDeleteLog = cmdDelete.toString();
		logReader.addCommandToCommandsToBeExecutedLog(cmdDeleteLog);
		LoggerCommand loggerCommand = logParser.parseCommandFromLog();
		assertEquals(cmdDelete, loggerCommand.getCommand());
	}
	
	@Test
	public void testParseCommandFromLog_Undo_Success() {
		logReader.addCommandToCommandsToBeExecutedLog(TypeOfCommand.UNDO_COMMAND.toString());
		LoggerCommand loggerCommand = logParser.parseCommandFromLog();
		assertEquals(null, loggerCommand.getCommand());
		assertEquals(TypeOfCommand.UNDO_COMMAND, loggerCommand.getTypeOfCommand());
	}
	
	@Test
	public void testParseCommandFromLog_Redo_Success() {
		logReader.addCommandToCommandsToBeExecutedLog(TypeOfCommand.REDO_COMMAND.toString());
		LoggerCommand loggerCommand = logParser.parseCommandFromLog();
		assertEquals(null, loggerCommand.getCommand());
		assertEquals(TypeOfCommand.REDO_COMMAND, loggerCommand.getTypeOfCommand());
	}
	
	
	private void addSelectedTestPointToModel() {
		testPoint.setSelected(true);
		model.addShape(testPoint);
		model.addSelectedShape(testPoint);
	}
	
	private void addSelectedLineToModel() {
		testLine.setSelected(true);
		model.addShape(testLine);
		model.addSelectedShape(testLine);
	}

}
