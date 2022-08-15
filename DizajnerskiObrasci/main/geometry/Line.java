package geometry;

import java.awt.Color;
import java.awt.Graphics;
import logger.LoggerConstants;

public class Line extends Shape{
	
	private static final long serialVersionUID = 1L;
	private Point startPoint;
	private Point endPoint;
	
	public Line() {
		
	}
	
	public Line(Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}
	
	public Line(Point startPoint, Point endPoint, boolean selected) {
		this(startPoint, endPoint);
		setSelected(selected);
	}
	
	public Line(Point startPoint, Point endPoint, Color color) {
		this(startPoint, endPoint);
		setColor(color);
	}
	
	public Line(Point startPoint, Point endPoint, boolean selected, Color color) {
		this(startPoint, endPoint, selected);
		setColor(color);
	}

	public Point middleOfLine() {
		int middleByX = (this.startPoint.getX() + this.endPoint.getX()) / 2;
		int middleByY = (this.startPoint.getY() + this.endPoint.getY()) / 2;
		Point p = new Point(middleByX, middleByY);
		return p;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine(this.startPoint.getX(), this.startPoint.getY(), this.endPoint.getX(), this.endPoint.getY());
		if(isSelected())
			drawSelection(g);
	}
	
	@Override
	protected void drawSelection(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect(this.startPoint.getX() - 3 , this.startPoint.getY() - 3, 6, 6);
		g.drawRect(this.endPoint.getX() - 3, this.endPoint.getY() - 3, 6, 6);
		g.drawRect(middleOfLine().getX() - 3, middleOfLine().getY() - 3, 6, 6);
		
	}
	
	@Override
	public boolean contains(int x, int y) {
		double distanceFromPoint = startPoint.distance(x, y) + endPoint.distance(x, y) - length();
		return distanceFromPoint <= 0.05;
	}
	
	@Override
	public boolean contains(Point p) {
		return contains(p.getX(), p.getY());
	}
	
	public double length() {
		return startPoint.distance(endPoint.getX(), endPoint.getY());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Line) {
			Line line = (Line)obj;
			return (this.startPoint.equals(line.getStartPoint()) && this.endPoint.equals(line.getEndPoint()));
		} 
		return false;
	}
	
	@Override
	public Line clone() {
		return new Line(startPoint.clone(), endPoint.clone(), isSelected(), getColor());
	}
	
	@Override
	public void setShapeFileds(Shape shape) {
		if(shape instanceof Line) {
			Line l = (Line)shape;
			this.startPoint = l.getStartPoint().clone();
			this.endPoint = l.getEndPoint().clone();
			this.setSelected(l.isSelected());
			this.setColor(l.getColor());
		}
		
	}
	
	public String toString() {
		return LoggerConstants.LINE + ":SP(" + this.getStartPoint().getX() + "," + this.getStartPoint().getY() + ") EP("+
				this.getEndPoint().getX() + "," + this.getEndPoint().getY() + ") " + "BC:" + getColor().getRGB();
	}
	
	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}
	

}
