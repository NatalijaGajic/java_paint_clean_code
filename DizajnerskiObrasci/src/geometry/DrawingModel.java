package geometry;


import java.util.ArrayList;
import java.util.List;

public class DrawingModel {

	List<Shape> shapes = new ArrayList<Shape>();
	
	public void add(Shape s) {
		shapes.add(s);
	}
	
	public void Remove(Shape s) {
		shapes.remove(s);
	}
	
	public List<Shape> getShapes(){
		return shapes;
	}
	
	public Shape getShape(int index) {
		return shapes.get(index);
	}
	
}
