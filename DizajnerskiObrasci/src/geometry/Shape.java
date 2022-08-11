package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class Shape implements Cloneable, Serializable {
	
	private static final long serialVersionUID = 1L;
	private boolean selected;
	private Color color;
	protected abstract void drawSelection(Graphics g);
	public abstract void draw(Graphics g);
	public abstract boolean contains(int x, int y);
	public abstract boolean contains(Point point);
	public abstract Shape clone();
	public abstract void setShapeFileds(Shape shape);
	

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
