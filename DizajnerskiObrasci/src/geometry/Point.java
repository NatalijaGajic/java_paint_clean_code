package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape{
	
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
	public int compareTo(Object o) {
		if (o instanceof Point) {
			Point start = new Point(0, 0);
			return  (int) (this.distance(start.getX(), start.getY()) - ((Point) o).distance(start.getX(), start.getY()));
		}
		return 0;
	}
	
	@Override
	public void moveBy(int byX, int byY) {
		this.x = this.x + byX;
		this.y += byY;
	}	
	
	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine(this.x-2, this.y, this.x+2, this.y);
		g.drawLine(this.x, this.y-2, this.x, this.y+2);
		
		if(isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.x-3, this.y-3, 6, 6);
		}
		
	}
	
	public boolean contains(int x, int y) {
		return this.distance(x, y) <= 3 ;
	}
	
	public boolean contains(Point point) {
		return this.distance(point.x, point.y) <=3;
	}
	
	public double distance(int x2, int y2) {
		double dx = this.x - x2;
		double dy = this.y - y2;
		double d = Math.sqrt(dx * dx + dy * dy);
		return d; 
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point prosledjena = (Point)obj;
			if (this.x == prosledjena.getX() 
					&& this.y == prosledjena.getY()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	@Override
	public Point clone() {
		return new Point(x, y, isSelected(), getColor());
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

	@Override
	public String toString() {
		return "(" + getX() + ", " + getY() + ")";
	}

	@Override
	public void moveOn(int x, int y) {
		this.x=x;
		this.y=y;
		
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

}
