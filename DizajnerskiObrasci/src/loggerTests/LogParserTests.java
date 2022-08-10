package loggerTests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import geometry.*;
import logger.LogParser;
import logger.LoggerConstants;

class LogParserTests {

	private String pointLog = LoggerConstants.POINT + ":(1,1) BC:-16777216";
	private String modifiedPointLog = LoggerConstants.POINT + ":(2,2) BC:-16777216";
	private String lineLog = LoggerConstants.LINE + ":SP(1,1) EP(2,2) BC:-16777216";
	private String circleLog = LoggerConstants.CIRCLE + ":(1,1) R:10, BC:-1, FC:-16777216";
	private String donutLog = LoggerConstants.DONUT + ":(1,1) OR:10, IR:5, BC:-1, FC:-16777216";
	private String rectangleLog = LoggerConstants.RECTANGLE + ":(1,1) W:50, H:40, BC:-1, FC:-16777216";
	private String hexagonLog = LoggerConstants.HEXAGON + ":(1,2) R:3, BC:-1, FC:-16777216";
	private Point testPoint;
	private Line testLine;
	private Circle testCircle;
	private Donut testDonut;
	private Rectangle testRectangle;
	private HexagonAdapter testHexagon;
	private String[] logLine;
	private LogParser logParser;
	private Shape parsedShape;
	
	@BeforeEach
	void setUp() {
		logParser = new LogParser();
		
		testPoint = new Point(1, 1, false, Color.BLACK);
		testLine = new Line(new Point(1,1), new Point(2,2), false, Color.BLACK);
		testCircle = new Circle(new Point(1, 1), 10, false, Color.WHITE, Color.BLACK);
		testDonut = new Donut(new Point(1, 1), 10, 5, false, Color.WHITE, Color.BLACK);
		testRectangle = new Rectangle(new Point(1,1), 40, 50, false, Color.WHITE, Color.BLACK);
		testHexagon = new HexagonAdapter(new Point(1,2), 3, Color.WHITE, Color.BLACK);
	}
	
	@Test
	public void testParsePointFromLog_OK() {
		logLine = pointLog.split("[, =():]");
		parsedShape = logParser.parsePointFromLog(logLine);
		assertEquals(testPoint, parsedShape);
	}
	
	@Test
	public void testParseLineFromLog_OK() {
		logLine = lineLog.split("[, =():]");
		parsedShape = logParser.parseLineFromLog(logLine);
		assertEquals(testLine, parsedShape);
	}
	
	@Test
	public void testParseCircleFromLog_OK() {
		logLine = circleLog.split("[, =():]");
		parsedShape = logParser.parseCircleFromLog(logLine);
		assertEquals(testCircle, parsedShape);
	}
	
	@Test
	public void testParseDonutFromLog_OK() {
		logLine = donutLog.split("[, =():]");
		parsedShape = logParser.parseDonutFromLog(logLine);
		assertEquals(testDonut, parsedShape);
	}
	
	@Test
	public void testParseRectangleFromLog_OK() {
		logLine = rectangleLog.split("[, =():]");
		parsedShape = logParser.parseRectangleFromLog(logLine);
		assertEquals(testRectangle, parsedShape);
	}
	
	@Test
	public void testParseHexagonFromLog_OK() {
		logLine = hexagonLog.split("[, =():]");
		parsedShape = logParser.parseHexagonFromLog(logLine);
		assertEquals(testHexagon, parsedShape);
	}

}
