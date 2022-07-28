package applications;

import java.util.ArrayList;
import java.util.List;
import geometry.Shape;

public class DrawingModel {

	List<Shape> shapes = new ArrayList<Shape>();
	List<Shape> selectedShapes = new ArrayList<Shape>();
	
	public DrawingModel() {
	}
	
	public void addShape(Shape s) {
		shapes.add(s);
	}
	
	public void removeShape(Shape s) {
		shapes.remove(s);
	}
	
	public Shape getShapeAtIndex(int index) {
		return shapes.get(index);
	}
	
	public void removeShapeAtIndex(int index) {
		shapes.remove(index);
	}
	
	public void addSelectedShape(Shape s) {
		selectedShapes.add(s);
	}
	
	public void removeSelectedShape(Shape s) {
		selectedShapes.remove(s);
	}
	
	public Shape getSelectedShapeAtIndex(int index) {
		return selectedShapes.get(index);
	}
	
	public void removeSelectedShapeAtIndex(int index) {
		selectedShapes.remove(index);
	}
	
	public List<Shape> getShapes(){
		return shapes;
	}
	
	public List<Shape> getSelectedShapes(){
		return selectedShapes;
	}
	
}
