package geometry;

import java.awt.Color;
import java.awt.Graphics;

import logger.LoggerConstants;

public class Rectangle extends SurfaceShape {
	
	private static final long serialVersionUID = 1L;
	private Point upperLeftPoint = new Point();
	private int height;
	private int width;
	
	public Rectangle() {

	}
	
	public Rectangle(Point upperLeftPoint, int height, int width) {
		this.upperLeftPoint = upperLeftPoint;
		this.height = height;
		this.width = width;
		
	}
	
	public Rectangle(Point upperLeftPoint, int height, int width, Color color, Color innerColor) {
		this.upperLeftPoint = upperLeftPoint;
		this.height = height;
		this.width = width;
		setColor(color);
		setInnerColor(innerColor);
		
	}
	
	public Rectangle(Point upperLeftPoint, int height, int width, boolean selected) {
		this(upperLeftPoint, height, width);
		setSelected(selected);
	}
	
	public Rectangle(Point upperLeftPoint, int height, int width, boolean selected, Color color) {
		this(upperLeftPoint, height, width, selected);
		setColor(color);
	}
	
	public Rectangle(Point upperLeftPoint, int height, int width, boolean selected, Color color, Color innerColor) {
		this(upperLeftPoint, height, width, selected, color);
		setInnerColor(innerColor);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawRect(this.upperLeftPoint.getX(), this.upperLeftPoint.getY(), this.width, this.height);
		
		this.fill(g);
		
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.upperLeftPoint.getX() - 3, getUpperLeftPoint().getY() - 3, 6, 6); // svejedno da li je get() ili direktno sa this.obelezje
			g.drawRect(getUpperLeftPoint().getX() + getWidth() - 3, getUpperLeftPoint().getY() - 3, 6, 6);
			g.drawRect(getUpperLeftPoint().getX() - 3, getUpperLeftPoint().getY() + getHeight() - 3, 6, 6);
			g.drawRect(getUpperLeftPoint().getX() + getWidth() - 3, getUpperLeftPoint().getY() + getHeight() - 3, 6, 6);
		}
		
	}
	
	@Override
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		g.fillRect(this.upperLeftPoint.getX()+1, this.getUpperLeftPoint().getY()+1, width-1, height-1);
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle prosledjen = (Rectangle)obj;
			if (this.upperLeftPoint.equals(prosledjen.getUpperLeftPoint()) 
					&& this.height == prosledjen.getHeight() && this.width == prosledjen.getWidth()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public boolean contains(int x, int y) {
		if (upperLeftPoint.getX() <= x &&
				this.getUpperLeftPoint().getY() <= y &&
				x <= upperLeftPoint.getX() + width &&
				y <= upperLeftPoint.getY() + height) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean contains(Point p) {
		if (upperLeftPoint.getX() <= p.getX() &&
				this.getUpperLeftPoint().getY() <= p.getY() &&
				p.getX() <= upperLeftPoint.getX() + width &&
				p.getY() <= upperLeftPoint.getY() + height) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public Rectangle clone() {
		return new Rectangle(upperLeftPoint.clone(), height, width, isSelected(), getColor(), getInnerColor());
	}

	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}

	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	
	public String toString() {
		return LoggerConstants.RECTANGLE + ":(" + this.getUpperLeftPoint().getX() + "," + this.getUpperLeftPoint().getY() + ") "
				+ "W:" + this.getWidth() + ", H:" + this.getHeight() + ", BC:" + this.getColor().getRGB() + ", "
						+ "FC:" + this.getInnerColor().getRGB();
	}

	@Override
	public void setShapeFileds(Shape shape) {
		if(shape instanceof Rectangle) {
			Rectangle rect = (Rectangle)shape;
			this.upperLeftPoint = rect.upperLeftPoint.clone();
			this.height = rect.height;
			this.width = rect.width;
			this.setSelected(rect.isSelected());
			this.setColor(rect.getColor());
			this.setInnerColor(rect.getInnerColor());
		}
		
	}

}
