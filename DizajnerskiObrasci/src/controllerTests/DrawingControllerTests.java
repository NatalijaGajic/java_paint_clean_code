package controllerTests;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;
import java.util.Stack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import commandHandler.CommandsHandler;
import commands.*;
import controllers.DrawingController;
import dialogs.*;
import frame.DrawingFrame;
import geometry.*;
import logger.*;
import model.DrawingModel;


class DrawingControllerTests {

	private DrawingModel model;
	private DrawingFrame frame;
	private DrawingController controller;
	private CommandsHandler commandsHandler;
	private LogWriter logWriter;
	private LogReader logReader;
	private Stack<Command> executedCommands;
	
	private Point click;
	private Point testPoint;
	private Line testLine;
	private Rectangle testRectangle;
	private Circle testCircle;
	private Donut testDonut;
	private HexagonAdapter testHexagon;
	
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
	
	
	@BeforeEach
	public void setUp() {
		model = new DrawingModel();
		frame = new DrawingFrame();
		controller = new DrawingController(model, frame);
		commandsHandler = new CommandsHandler();
		logWriter = new LogWriter();
		logReader = new LogReader(model);
		executedCommands = commandsHandler.getExecutedCommands();
		
		controller.setCommandsHandler(commandsHandler);
		controller.setLogWriter(logWriter);
		controller.setLogReader(logReader);
		frame.getView().setModel(model);
		frame.setDrawingController(controller);

		initializeShapes();
		initializeStrings();
	}
	
	private void initializeShapes() {
		click = new Point(10, 10);
		testPoint = new Point(1, 1, false, Color.BLACK);
		testLine = new Line(new Point(1,1), new Point(2,2), false, Color.BLACK);
		testRectangle = new Rectangle(new Point(1,1), 40, 50, false, Color.WHITE, Color.BLACK);
		testCircle = new Circle(new Point(1, 1), 10, false, Color.WHITE, Color.BLACK);
		testDonut = new Donut(new Point(1, 1), 10, 5, false, Color.WHITE, Color.BLACK);
		testHexagon = new HexagonAdapter(new Point(1,2), 3, Color.WHITE, Color.BLACK);
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
	
	@Test
	public void testAddPointIfAccepted_WhenAccepted_PointAddedToModel() {
		DlgPoint dlg = new DlgPoint();
		dlg.setModifyDialogFields(testPoint);
		dlg.setAccepted(true);
		controller.addShapeIfAccepted(dlg);
		assertTrue(model.doesContainShape(testPoint));
	}
	
	@Test
	public void testAddPointIfAccepted_WhenNotAccepted_PointNotAddedToModel() {
		DlgPoint dlg = new DlgPoint();
		testPoint.setX(12345);
		dlg.setModifyDialogFields(testPoint);
		dlg.setAccepted(false);
		controller.addShapeIfAccepted(dlg);
		assertFalse(model.doesContainShape(testPoint));
	}
	
	@Test
	public void testModifyPointIfAccepted_WhenAccepted_PointModified() {
		model.addShape(testPoint);
		DlgPoint dlg = new DlgPoint();
		Point modifiedShape = new Point(10, 10, false, Color.blue);
		dlg.setModifyDialogFields(modifiedShape);
		dlg.setAccepted(true);
		controller.modifyShapeIfAccepted(dlg, testPoint);
		assertTrue(model.doesContainShape(testPoint));
		assertTrue(testPoint.equals(modifiedShape));
	}
	
	@Test
	public void testModifyPointIfAccepted_WhenNotAccepted_PointNotModified() {
		model.addShape(testPoint);
		DlgPoint dlg = new DlgPoint();
		Point modifiedShape = new Point(10, 12345, false, Color.blue);
		dlg.setModifyDialogFields(modifiedShape);
		dlg.setAccepted(false);
		controller.modifyShapeIfAccepted(dlg, testPoint);
		assertTrue(model.doesContainShape(testPoint));
		assertFalse(testPoint.equals(modifiedShape));
	}
	
	@Test
	public void testAddLineIfAccepted_WhenAccepted_LineAddedToModel() {
		DlgLine dlg = new DlgLine();
		dlg.setModifyDialogFields(testLine);
		dlg.setAccepted(true);
		controller.addShapeIfAccepted(dlg);
		assertTrue(model.doesContainShape(testLine));
	}
	
	@Test
	public void testAddLineIfAccepted_WhenNotAccepted_LineNotAddedToModel() {
		DlgLine dlg = new DlgLine();
		dlg.setModifyDialogFields(testLine);
		dlg.setAccepted(false);
		controller.addShapeIfAccepted(dlg);
		assertFalse(model.doesContainShape(testLine));
	}
	
	@Test
	public void testModifyLineIfAccepted_WhenAccepted_LineModified() {
		model.addShape(testLine);
		DlgLine dlg = new DlgLine();
		Line modifiedShape = new Line(new Point(0, 0), new Point(3, 3), false, Color.blue);
		dlg.setModifyDialogFields(modifiedShape);
		dlg.setAccepted(true);
		controller.modifyShapeIfAccepted(dlg, testLine);
		assertTrue(model.doesContainShape(testLine));
		assertTrue(testLine.equals(modifiedShape));
	}
	
	@Test
	public void testModifyLineIfAccepted_WhenNotAccepted_LineNotModified() {
		model.addShape(testLine);
		DlgLine dlg = new DlgLine();
		Line modifiedShape = new Line(new Point(0, 0), new Point(3, 3), false, Color.blue);
		dlg.setModifyDialogFields(modifiedShape);
		dlg.setAccepted(false);
		controller.modifyShapeIfAccepted(dlg, testLine);
		assertTrue(model.doesContainShape(testLine));
		assertFalse(testLine.equals(modifiedShape));
	}
	
	@Test
	public void testAddRectangleIfAccepted_WhenAccepted_RectangleAddedToModel() {
		DlgRectangle dlg = new DlgRectangle();
		dlg.setModifyDialogFields(testRectangle);
		dlg.setAccepted(true);
		controller.addShapeIfAccepted(dlg);
		assertTrue(model.doesContainShape(testRectangle));
	}
	
	@Test
	public void testAddRectangleIfAccepted_WhenNotAccepted_RectangleNotAddedToModel() {
		DlgRectangle dlg = new DlgRectangle();
		testRectangle.setHeight(0);
		dlg.setModifyDialogFields(testRectangle);
		dlg.setAccepted(false);
		controller.addShapeIfAccepted(dlg);
		assertFalse(model.doesContainShape(testRectangle));
	}
	
	@Test
	public void testModifyRectangleIfAccepted_WhenAccepted_RectangleModified() {
		model.addShape(testRectangle);
		DlgRectangle dlg = new DlgRectangle();
		Rectangle modifiedShape = new Rectangle(new Point(0, 0), 10, 10, false, Color.green, Color.blue);
		dlg.setModifyDialogFields(modifiedShape);
		dlg.setAccepted(true);
		controller.modifyShapeIfAccepted(dlg, testRectangle);
		assertTrue(model.doesContainShape(testRectangle));
		assertTrue(testRectangle.equals(modifiedShape));
	}
	
	@Test
	public void testModifyRectangleIfAccepted_WhenNotAccepted_RectangleNotModified() {
		model.addShape(testRectangle);
		DlgRectangle dlg = new DlgRectangle();
		Rectangle modifiedShape = new Rectangle(new Point(0, 0), 0, 10, false, Color.green, Color.blue);
		dlg.setModifyDialogFields(modifiedShape);
		dlg.setAccepted(false);
		controller.modifyShapeIfAccepted(dlg, testRectangle);
		assertTrue(model.doesContainShape(testRectangle));
		assertFalse(testRectangle.equals(modifiedShape));
	}
	
	
	@Test
	public void testAddCircleIfAccepted_WhenAccepted_CircleAddedToModel() {
		DlgCircle dlg = new DlgCircle();
		dlg.setModifyDialogFields(testCircle);
		dlg.setAccepted(true);
		controller.addShapeIfAccepted(dlg);
		assertTrue(model.doesContainShape(testCircle));
	}
	
	@Test
	public void testAddCircleIfAccepted_WhenNotAccepted_CircleNotAddedToModels() {
		DlgCircle dlg = new DlgCircle();
		testCircle.setRadius(0);
		dlg.setModifyDialogFields(testCircle);
		dlg.setAccepted(false);
		controller.addShapeIfAccepted(dlg);
		assertFalse(model.doesContainShape(testCircle));
	}
	
	@Test
	public void testModifyCircleIfAccepted_WhenAccepted_CircleModified() {
		model.addShape(testCircle);
		DlgCircle dlg = new DlgCircle();
		Circle modifiedShape = new Circle(new Point(10, 10), 6, false, Color.blue, Color.green);
		dlg.setModifyDialogFields(modifiedShape);
		dlg.setAccepted(true);
		controller.modifyShapeIfAccepted(dlg, testCircle);
		assertTrue(model.doesContainShape(testCircle));
		assertTrue(testCircle.equals(modifiedShape));
	}
	
	@Test
	public void testModifyCircleIfAccepted_WhenNotAccepted_CircleNotModified() {
		model.addShape(testCircle);
		DlgCircle dlg = new DlgCircle();
		Circle modifiedShape = new Circle(new Point(10, 10), 0, false, Color.blue, Color.green);
		dlg.setModifyDialogFields(modifiedShape);
		dlg.setAccepted(false);
		controller.modifyShapeIfAccepted(dlg, testCircle);
		assertTrue(model.doesContainShape(testCircle));
		assertFalse(testCircle.equals(modifiedShape));
	}
	
	@Test
	public void testAddDonutIfAccepted_WhenAccepted_DonutAddedToModel() {
		DlgDonut dlg = new DlgDonut();
		dlg.setModifyDialogFields(testDonut);
		dlg.setAccepted(true);
		controller.addShapeIfAccepted(dlg);
		assertTrue(model.doesContainShape(testDonut));
	}
	
	@Test
	public void testAddDonutIfAccepted_WhenNotAccepted_DonutNotAddedToModel() {
		DlgDonut dlg = new DlgDonut();
		testDonut.setRadius(0);
		dlg.setModifyDialogFields(testDonut);
		dlg.setAccepted(false);
		controller.addShapeIfAccepted(dlg);
		assertFalse(model.doesContainShape(testDonut));
	}
	
	@Test
	public void testModifyDonutIfAccepted_WhenAccepted_DonutModified() {
		model.addShape(testDonut);
		DlgDonut dlg = new DlgDonut();
		Donut modifiedShape = new Donut(new Point(10, 10), 15, 10, false, Color.blue, Color.green);
		dlg.setModifyDialogFields(modifiedShape);
		dlg.setAccepted(true);
		controller.modifyShapeIfAccepted(dlg, testDonut);
		assertTrue(model.doesContainShape(testDonut));
		assertTrue(testDonut.equals(modifiedShape));
	}
	
	@Test
	public void testModifyDonutIfAccepted_WhenNotAccepted_DonutNotModified() {
		model.addShape(testDonut);
		DlgDonut dlg = new DlgDonut();
		Donut modifiedShape = new Donut(new Point(10, 10), 15, 20, false, Color.blue, Color.green);
		dlg.setModifyDialogFields(modifiedShape);
		dlg.setAccepted(false);
		controller.modifyShapeIfAccepted(dlg, testDonut);
		assertTrue(model.doesContainShape(testDonut));
		assertFalse(testDonut.equals(modifiedShape));
	}
	
	
	@Test
	public void testAddHexagonIfAccepted_WhenAccepted_HexagonAddedToModel() {
		DlgHexagon dlg = new DlgHexagon();
		dlg.setModifyDialogFields(testHexagon);
		dlg.setAccepted(true);
		controller.addShapeIfAccepted(dlg);
		assertTrue(model.doesContainShape(testHexagon));
	}
	
	@Test
	public void testAddHexagonIfAccepted_WhenNotAccepted_HexagonNotAddedToModel() {
		DlgHexagon dlg = new DlgHexagon();
		testHexagon.setRadius(0);
		dlg.setModifyDialogFields(testHexagon);
		dlg.setAccepted(false);
		controller.addShapeIfAccepted(dlg);
		assertFalse(model.doesContainShape(testHexagon));
	}
	
	@Test
	public void testModifyHexagonIfAccepted_WhenAccepted_HexagonModified() {
		model.addShape(testHexagon);
		DlgHexagon dlg = new DlgHexagon();
		HexagonAdapter modifiedShape = new HexagonAdapter(new Point(1,2), 3, Color.blue, Color.green, false);
		dlg.setModifyDialogFields(modifiedShape);
		dlg.setAccepted(true);
		controller.modifyShapeIfAccepted(dlg, testHexagon);
		assertTrue(model.doesContainShape(testHexagon));
		assertTrue(testHexagon.equals(modifiedShape));
	}
	
	@Test
	public void testModifyHexagonIfAccepted_WhenNotAccepted_HexagonNotModified() {
		model.addShape(testHexagon);
		DlgHexagon dlg = new DlgHexagon();
		HexagonAdapter modifiedShape = new HexagonAdapter(new Point(1,2), 0, Color.blue, Color.green, false);
		dlg.setModifyDialogFields(modifiedShape);
		dlg.setAccepted(false);
		controller.modifyShapeIfAccepted(dlg, testHexagon);
		assertTrue(model.doesContainShape(testHexagon));
		assertFalse(testHexagon.equals(modifiedShape));
	}
	
	@Test
	public void testSelectOrDeselectShape_Select_ShapeSelected() {
		Point point = new Point(10, 10, false, Color.BLACK);
		model.addShape(point);
		controller.selectOrDeselectShape(click);
		assertTrue(point.isSelected());
	}
	
	@Test
	public void testSelectOrDeselectShape_Select_ShapeAddedToSelectedShapes() {
		Point point = new Point(10, 10, false, Color.BLACK);
		model.addShape(point);
		controller.selectOrDeselectShape(click);
		assertTrue(model.doesContainSelectedShape(point));
	}

	@Test
	public void testSelectOrDeselectShape_Deselect_ShapeDeSelected() {
		Point point = new Point(10, 10, true, Color.BLACK);
		model.addShape(point);
		model.addSelectedShape(point);
		controller.selectOrDeselectShape(click);
		assertFalse(point.isSelected());
	}

	@Test
	public void testSelectOrDeselectShape_Deselect_ShapeRemovedFromSelectedShapes() {
		Point point = new Point(10, 10, true, Color.BLACK);
		model.addShape(point);
		model.addSelectedShape(point);
		controller.selectOrDeselectShape(click);
		assertFalse(model.doesContainSelectedShape(point));
	}
	
	@Test
	void testExecuteLog_CmdAdd_ShapeAddedToModel() {
		logReader.addCommandToCommandsToBeExecutedLog(cmdAddLog);
		controller.executeLog();
		assertEquals(testPoint, model.getShapeAtIndex(0));
	}
	
	@Test
	void testExecuteLog_CmdModify_ShapeModified() {
		testPoint.setSelected(true);
		model.addShape(testPoint);
		model.addSelectedShape(testPoint);
		Point modifiedPoint = new Point(2, 2, true, Color.BLACK);
		logReader.addCommandToCommandsToBeExecutedLog(cmdModifyLog);
		controller.executeLog();
		assertEquals(modifiedPoint, model.getShapeAtIndex(0));
	}
	
	@Test
	void testExecuteLog_CmdToBack_ShapeBroughtBack() {
		model.addShape(testLine);
		model.addShape(testPoint);
		model.addSelectedShape(testPoint);
		logReader.addCommandToCommandsToBeExecutedLog(cmdToBackLog);
		controller.executeLog();
		assertEquals(testPoint, model.getShapeAtIndex(0));
	}
	
	@Test
	void testExecuteLog_CmdBringToBack_ShapeBroughtToBack() {
		model.addShape(testLine);
		model.addShape(testPoint);
		model.addSelectedShape(testPoint);
		logReader.addCommandToCommandsToBeExecutedLog(cmdBringToBackLog);
		controller.executeLog();
		assertEquals(testPoint, model.getShapeAtIndex(0));
	}
	
	@Test
	void testExecuteLog_CmdToFront_ShapeBroughtFront() {
		model.addShape(testPoint);
		model.addShape(testLine);
		model.addSelectedShape(testPoint);
		logReader.addCommandToCommandsToBeExecutedLog(cmdToFrontLog);
		controller.executeLog();
		assertEquals(testPoint, model.getShapeAtIndex(1));
	}
	
	@Test
	void testExecuteLog_CmdBringToFront_ShapeBroughtToFront() {
		model.addShape(testPoint);
		model.addShape(testLine);
		model.addSelectedShape(testPoint);
		logReader.addCommandToCommandsToBeExecutedLog(cmdBringToFrontLog);
		controller.executeLog();
		assertEquals(testPoint, model.getShapeAtIndex(1));
	}
	
	@Test
	void testExecuteLog_CmdDelete_ShapeDeleted() {
		model.addShape(testPoint);
		testPoint.setSelected(true);
		model.addSelectedShape(testPoint);
		logReader.addCommandToCommandsToBeExecutedLog(cmdDeleteLog);
		controller.executeLog();
		assertFalse(model.doesContainShape(testPoint));
	}
	
	@Test
	void testExecuteLog_CmdSelect_ShapeSelected() {
		model.addShape(testPoint);
		logReader.addCommandToCommandsToBeExecutedLog(cmdSelectLog);
		controller.executeLog();
		assertTrue(testPoint.isSelected());
	}
	
	
	@Test
	void testExecuteLog_CmdDeselect_ShapeDeselected() {
		model.addShape(testPoint);
		testPoint.setSelected(true);
		model.addSelectedShape(testPoint);
		logReader.addCommandToCommandsToBeExecutedLog(cmdDeselectLog);
		controller.executeLog();
		assertFalse(testPoint.isSelected());
	}
	
	@Test
	void testExecuteLog_CmdAddAddedToExecutedCommands() {
		logReader.addCommandToCommandsToBeExecutedLog(cmdAddLog);
		controller.executeLog();
		assertTrue(executedCommands.get(0) instanceof CmdAdd);
	}
	
	@Test
	void testExecuteLog_CmdModifyAddedToExecutedCommands() {
		testPoint.setSelected(true);
		model.addShape(testPoint);
		model.addSelectedShape(testPoint);
		logReader.addCommandToCommandsToBeExecutedLog(cmdModifyLog);
		controller.executeLog();
		assertTrue(executedCommands.get(0) instanceof CmdModify);
	}
	
	@Test
	void testExecuteLog_CmdToBackAddedToExecutedCommands() {
		model.addShape(testLine);
		model.addShape(testPoint);
		model.addSelectedShape(testPoint);
		logReader.addCommandToCommandsToBeExecutedLog(cmdToBackLog);
		controller.executeLog();
		assertTrue(executedCommands.get(0) instanceof CmdToBack);
	}
	
	@Test
	void testExecuteLog_CmdToFrontAddedToExecutedCommands() {
		model.addShape(testPoint);
		model.addShape(testLine);
		model.addSelectedShape(testPoint);
		logReader.addCommandToCommandsToBeExecutedLog(cmdToFrontLog);
		controller.executeLog();
		assertTrue(executedCommands.get(0) instanceof CmdToFront);
	}
	
	@Test
	void testExecuteLog_CmdBringToFrontAddedToExecutedCommands() {
		model.addShape(testPoint);
		model.addShape(testLine);
		model.addSelectedShape(testPoint);
		logReader.addCommandToCommandsToBeExecutedLog(cmdBringToFrontLog);
		controller.executeLog();
		assertTrue(executedCommands.get(0) instanceof CmdBringToFront);
	}
	
	@Test
	void testExecuteLog_CmdBringToBackAddedToExecutedCommands() {
		model.addShape(testLine);
		model.addShape(testPoint);
		model.addSelectedShape(testPoint);
		logReader.addCommandToCommandsToBeExecutedLog(cmdBringToBackLog);
		controller.executeLog();
		assertTrue(executedCommands.get(0) instanceof CmdBringToBack);
	}
	
	@Test
	void testExecuteLog_CmdSelectAddedToExecutedCommands() {
		model.addShape(testPoint);
		logReader.addCommandToCommandsToBeExecutedLog(cmdSelectLog);
		controller.executeLog();
		assertTrue(executedCommands.get(0) instanceof CmdSelect);
	}
	
	@Test
	void testExecuteLog_CmdDeselectAddedToExecutedCommands() {
		model.addShape(testPoint);
		testPoint.setSelected(true);
		model.addSelectedShape(testPoint);
		logReader.addCommandToCommandsToBeExecutedLog(cmdDeselectLog);
		controller.executeLog();
		assertTrue(executedCommands.get(0) instanceof CmdDeselect);
	}
	
	@Test
	void testExecuteLog_CmdDeleteAddedToExecutedCommands() {
		model.addShape(testPoint);
		testPoint.setSelected(true);
		model.addSelectedShape(testPoint);
		logReader.addCommandToCommandsToBeExecutedLog(cmdDeleteLog);
		controller.executeLog();
		assertTrue(executedCommands.get(0) instanceof CmdDelete);
	}

}
