package geometry;

import java.awt.Color;
import java.awt.Graphics;
import hexagon.Hexagon;
import logger.LoggerConstants;

public class HexagonAdapter extends SurfaceShape{

	private static final long serialVersionUID = 1L;
	private Hexagon hexagon;
	
	public HexagonAdapter() {

	}

	public HexagonAdapter(Point center, int radius, Color edgeColor, Color innerColor) {
		this.hexagon = new Hexagon(center.getX(), center.getY(), radius);
		this.hexagon.setAreaColor(innerColor);
		this.hexagon.setBorderColor(edgeColor);
	}

	public HexagonAdapter(Point center, int radius, Color innerColor, Color edgeColor, boolean selected) {
		this(center, radius, innerColor, edgeColor);
		this.hexagon.setSelected(selected);
	}
	
	
	public HexagonAdapter(Hexagon hexagon) {
		this.hexagon = hexagon;
	}

	@Override
	public void fill(Graphics g) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void draw(Graphics g) {
		hexagon.paint(g);
	}

	@Override
	protected void drawSelection(Graphics g) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean contains(int x, int y) {
		return hexagon.doesContain(x, y);
	}

	@Override
	public boolean contains(Point point) {
		return hexagon.doesContain(point.getX(), point.getY());
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof HexagonAdapter) {
			HexagonAdapter hex = (HexagonAdapter) obj;
			
			return (this.getX() == hex.getX() && this.getY() == hex.getY()
					&& this.getRadius() == hex.getRadius());
		}
		return false;
	}

	@Override
	public Shape clone() {
		return new HexagonAdapter(new Point(getX(), getY()), getRadius(),
				getColor(), getInnerColor(), isSelected());
	}

	@Override
	public void setShapeFileds(Shape shape) {
		if(shape instanceof HexagonAdapter) {
			HexagonAdapter hexagonAdapter = (HexagonAdapter) shape;
			this.setX(hexagonAdapter.getX());
			this.setY(hexagonAdapter.getY());
			this.setRadius(hexagonAdapter.getRadius());
			this.setColor(hexagonAdapter.getColor());
			this.setInnerColor(hexagonAdapter.getInnerColor());
			this.setSelected(hexagonAdapter.isSelected());
		}
		
	}
	
	public String toString() {
		return LoggerConstants.HEXAGON + ":(" + this.getX() + "," + this.getY() + ") "
				+ "R:" + this.getRadius() + ", BC:" + this.getColor().getRGB() + ", "
				+ "FC:" + this.getInnerColor().getRGB();
	}
	
	
	public int getX() {
		return hexagon.getX();
	}

	public void setX(int x) {
		hexagon.setX(x);
	}

	public int getY() {
		return hexagon.getY();
	}

	public void setY(int y) {
		hexagon.setY(y);
	}

	public int getRadius() {
		return hexagon.getR();
	}

	public void setRadius(int radius) {
		hexagon.setR(radius);
	}
	
	@Override
	public Color getColor() {
		return hexagon.getBorderColor();
	}
	
	@Override
	public Color getInnerColor() {
		return hexagon.getAreaColor();
	}
	
	@Override
	public void setColor(Color color) {
		hexagon.setBorderColor(color);
	}
	
	@Override
	public void setInnerColor(Color innerColor) {
		hexagon.setAreaColor(innerColor);
	}
	
	@Override
	public void setSelected(boolean selected) {
		hexagon.setSelected(selected);
	}
	
	@Override
	public boolean isSelected() {
		return hexagon.isSelected();
	}

}
