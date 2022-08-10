package logger;

import java.awt.Color;

import geometry.*;

public class LogParser {

	public Point parsePointFromLog(String[] logLine) {
		int xCoord = Integer.parseInt(logLine[2]);
		int yCoord = Integer.parseInt(logLine[3]);
		Color edgeColor = new Color(Integer.parseInt(logLine[6]));
		return new Point(xCoord, yCoord, false, edgeColor);
	}
	
	public Line parseLineFromLog(String[] logLine) {
		int xCoordOfStartPoint = Integer.parseInt(logLine[2]);
		int yCoordOfStartPoint = Integer.parseInt(logLine[3]);
		int xCoordOfEndPoint = Integer.parseInt(logLine[6]);
		int yCoordOfEndPoint = Integer.parseInt(logLine[7]);
		Color edgeColor = new Color(Integer.parseInt(logLine[10]));
		Point startPoint = new Point(xCoordOfStartPoint, yCoordOfStartPoint);
		Point endPoint = new Point(xCoordOfEndPoint, yCoordOfEndPoint);
		return new Line(startPoint, endPoint, false, edgeColor);
	}
	
	public Circle parseCircleFromLog(String[] logLine) {
		int xCoordOfCenter = Integer.parseInt(logLine[2]);
		int yCoordOfCenter = Integer.parseInt(logLine[3]);
		int radius = Integer.parseInt(logLine[6]);
		Color edgeColor = new Color(Integer.parseInt(logLine[9]));
		Color innerColor = new Color(Integer.parseInt(logLine[12]));
		return new Circle(new Point(xCoordOfCenter, yCoordOfCenter), radius, false, edgeColor, innerColor);
	}
	
	public Donut parseDonutFromLog(String[] logLine) {
		int xCoordOfCenter = Integer.parseInt(logLine[2]);
		int yCoordOfCenter = Integer.parseInt(logLine[3]);
		int radius = Integer.parseInt(logLine[6]);
		int innerRadius = Integer.parseInt(logLine[9]);
		Color edgeColor = new Color(Integer.parseInt(logLine[12]));
		Color innerColor = new Color(Integer.parseInt(logLine[15]));
		return new Donut(new Point(xCoordOfCenter, yCoordOfCenter), radius, innerRadius, false, edgeColor, innerColor);
	}
	
	public Rectangle parseRectangleFromLog(String[] logLine) {
		int xCoordOfUpperLeftPoint = Integer.parseInt(logLine[2]);
		int yCoordOfUpperLeftPoint = Integer.parseInt(logLine[3]);
		int width = Integer.parseInt(logLine[6]);
		int height = Integer.parseInt(logLine[9]);
		Color edgeColor = new Color(Integer.parseInt(logLine[12]));
		Color innerColor = new Color(Integer.parseInt(logLine[15]));
		return new Rectangle(new Point(xCoordOfUpperLeftPoint, yCoordOfUpperLeftPoint), height, width, false, edgeColor, innerColor);
	}
	
	public HexagonAdapter parseHexagonFromLog(String[] logLine) {
		int xCoordOfCenter = Integer.parseInt(logLine[2]);
		int yCoordOfCenter = Integer.parseInt(logLine[3]);
		int radius = Integer.parseInt(logLine[6]);
		Color edgeColor = new Color(Integer.parseInt(logLine[9]));
		Color innerColor = new Color(Integer.parseInt(logLine[12]));
		return new HexagonAdapter(new Point(xCoordOfCenter, yCoordOfCenter), radius, edgeColor, innerColor, false);
	}
}
