package geometry;

import java.awt.Color;
import java.awt.Graphics;

import logger.LoggerConstants;

public class Circle extends SurfaceShape {
	
	private Point center;
	private int radius;
	
	public Circle() {
		
	}
	
	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}
	
	public Circle(Point center, int radius, Color color, Color innerColor) {
		this.center = center;
		this.radius = radius;
		setColor(color);
		setInnerColor(innerColor);
	}
	
	public Circle(Point center, int radius, boolean selected) {
		this(center, radius);
		setSelected(selected);
	}
	
	public Circle(Point center, int radius, boolean selected, Color color) {
		this(center, radius, selected);
		setColor(color);
	}
	
	public Circle(Point center, int radius, boolean selected, Color color, Color innerColor) {
		this(center, radius, selected, color);
		setInnerColor(innerColor);
	}
	
	@Override
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		g.fillOval(this.center.getX() - radius + 1, this.center.getY() - radius + 1, (radius * 2) - 2, (radius * 2) - 2);	
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawOval(this.center.getX() - radius, this.center.getY() - radius, radius * 2, radius * 2);
		fill(g);
		
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.center.getX() - 3, this.center.getY() - 3, 6, 6);
			g.drawRect(this.center.getX() - radius - 3, this.center.getY() - 3, 6, 6);
			g.drawRect(this.center.getX() + radius - 3, this.center.getY() - 3, 6, 6);
			g.drawRect(this.center.getX() - 3, this.center.getY() - radius - 3, 6, 6);
			g.drawRect(this.center.getX() - 3, this.center.getY() + radius - 3, 6, 6);
		}
		
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Circle) {
			Circle prosledjen = (Circle)obj;
			if (this.center.equals(prosledjen.getCenter()) && this.radius == prosledjen.getRadius()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean contains(int x, int y) {
		return this.getCenter().distance(x, y) <= radius;
	}
	
	public boolean contains(Point p) {
		return this.getCenter().distance(p.getX(), p.getY()) <= radius;
	}
	
	@Override
	public Circle clone() {
		return new Circle(center.clone(), radius, isSelected(), getColor(), getInnerColor());
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
			this.radius = radius;
	}
	
	public String toString() {
		return LoggerConstants.CIRCLE + ":(" + this.getCenter().getX() + "," + this.getCenter().getY() + ") "
				+ "R:" + this.getRadius() + ", BC:" + this.getColor().getRGB()+", "
				+ "FC:" + this.getInnerColor().getRGB();
	}

	@Override
	public void setShapeFileds(Shape shape) {
		if(shape instanceof Circle) {
			Circle circ = (Circle)shape;
			this.center = circ.center.clone();
			this.radius = circ.radius;
			this.setSelected(circ.isSelected());
			this.setColor(circ.getColor());
			this.setInnerColor(circ.getInnerColor());
		}
		
	}

}
