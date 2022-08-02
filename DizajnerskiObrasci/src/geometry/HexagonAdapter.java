package geometry;

import java.awt.Color;
import java.awt.Graphics;

import hexagon.Hexagon;

public class HexagonAdapter extends SurfaceShape{

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Point point) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Shape clone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setShapeFileds(Shape shape) {
		// TODO Auto-generated method stub
		
	}

}
