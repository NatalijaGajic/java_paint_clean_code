package geometry;

import java.awt.Color;
import java.awt.Graphics;
import logger.LoggerConstants;

public class Donut extends Circle {

	private static final long serialVersionUID = 1L;
	private int innerRadius;
	
	public Donut() {
		
	}
	
	public Donut(Point center, int radius, int innerRadius) {
		super(center, radius);
		this.innerRadius = innerRadius;
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		this(center, radius, innerRadius);
		setSelected(selected);
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color) { 
		this(center, radius, innerRadius, selected);
		setColor(color);
	}
	
	public Donut(Point center, int radius, int innerRadius, Color color, Color innerColor) { 
		this(center, radius, innerRadius);
		setColor(color);
		setInnerColor(innerColor);
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color, Color innerColor) { 
		this(center, radius, innerRadius, selected, color);
		setInnerColor(innerColor);
	}
	
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		g.setColor(getColor());
		g.drawOval(getCenter().getX() - this.innerRadius, getCenter().getY() - this.innerRadius, getInnerRadius() * 2, getInnerRadius() * 2);
	}
	
	@Override
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		super.fill(g);
		g.setColor(Color.WHITE);
		g.fillOval(getCenter().getX() - getInnerRadius(), getCenter().getY() - getInnerRadius(), getInnerRadius() * 2, getInnerRadius() * 2);
	}
	
	@Override
	public boolean contains(int x, int y) {
		double dFromCenter = this.getCenter().distance(x, y);
		return super.contains(x, y) && dFromCenter > innerRadius;
	}
	
	@Override
	public boolean contains(Point p) {
		double dFromCenter = this.getCenter().distance(p.getX(), p.getY());
		return super.contains(p) && dFromCenter > innerRadius;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut donut = (Donut) obj;
			return (super.equals(donut) && this.innerRadius == donut.getInnerRadius());
		}
		return false;
	}
	
	@Override
	public Donut clone() {
		return new Donut(getCenter().clone(), getRadius(), innerRadius, isSelected(), getColor(), getInnerColor());
	}
	
	@Override
	public void setShapeFileds(Shape shape) {
		if(shape instanceof Donut) {
			super.setShapeFileds(shape);
			Donut donut = (Donut)shape;
			this.innerRadius = donut.innerRadius;
		}	
	}

	@Override
	public String toString() {
		return LoggerConstants.DONUT + ":(" + this.getCenter().getX() + "," + this.getCenter().getY() + ")"
				+ " OR:" + this.getRadius() + ", IR:" + this.getInnerRadius() + ", "
						+ "BC:" + this.getColor().getRGB() + ", FC:" + this.getInnerColor().getRGB();
	}
	
	public int getInnerRadius() {
		return this.innerRadius;
	}
	
	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}
	
	

}
