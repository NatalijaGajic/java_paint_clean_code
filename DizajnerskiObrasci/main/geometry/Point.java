package geometry;

import java.awt.Color;
import java.awt.Graphics;

import logger.LoggerConstants;

public class Point extends Shape{
	
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	
	public Point(){
		
	}
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point(int x, int y, Color color) {
		this(x,y);
		setColor(color);
	}
	
	public Point(int x, int y, boolean selected) {
		this(x, y);
		setSelected(selected);
	}
	
	public Point(int x, int y, boolean selected, Color color) {
		this(x, y, selected);
		setColor(color);
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine(this.x - 2, this.y, this.x + 2, this.y);
		g.drawLine(this.x, this.y - 2, this.x, this.y + 2);
		
		if(isSelected())
			drawSelection(g);
	}
	
	@Override
	protected void drawSelection(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect(this.x-3, this.y-3, 6, 6);
		
	}
	
	@Override
	public boolean contains(int x, int y) {
		return this.distance(x, y) <= 3 ;
	}
	
	@Override
	public boolean contains(Point point) {
		return contains(point.getX(), point.getY());
	}
	
	public double distance(int x2, int y2) {
		double dx = this.x - x2;
		double dy = this.y - y2;
		double d = Math.sqrt(dx * dx + dy * dy);
		return d; 
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point point = (Point)obj;
			return (this.x == point.getX() && this.y == point.getY());
		}
		return false;
	}
	
	@Override
	public Point clone() {
		return new Point(x, y, isSelected(), getColor());
	}
	
	@Override
	public void setShapeFileds(Shape shape) {
		if(shape instanceof Point) {
			Point p = (Point) shape;
			this.x = p.x;
			this.y = p.y;
			this.setSelected(p.isSelected());
			this.setColor(p.getColor());
		}
		
	}
	
	@Override
	public String toString() {
		return LoggerConstants.POINT + ":(" + this.getX() + "," + this.getY() + ") " + "BC:" + getColor().getRGB();
	}
	
	public int getX() {
		return this.x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setY(int y) {
		this.y = y;
	}

}
