package model;

import java.util.ArrayList;
import java.util.List;
import geometry.Shape;
import observer.CollectionOfSelectedShapes;

public class DrawingModel {

	ArrayList<Shape> shapes = new ArrayList<Shape>();
	CollectionOfSelectedShapes collectionOfSelectedShapes = new CollectionOfSelectedShapes();
	
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
		collectionOfSelectedShapes.add(s);
	}
	
	public void removeSelectedShape(Shape s) {
		collectionOfSelectedShapes.remove(s);
	}
	
	public Shape getSelectedShapeAtIndex(int index) {
		return collectionOfSelectedShapes.get(index);
	}
	
	public void removeSelectedShapeAtIndex(int index) {
		collectionOfSelectedShapes.remove(index);
	}
	
	public boolean doesContainShape(Shape shape) {
		return shapes.contains(shape);
	}
	
	public ArrayList<Shape> getShapes(){
		return shapes;
	}
	
	public ArrayList<Shape> getSelectedShapes(){
		return collectionOfSelectedShapes.getSelectedShapes();
	}
	
	public int getNumberOfShapes() {
		return shapes.size();
	}
	
	public int getNumberOfSelectedShapes() {
		return collectionOfSelectedShapes.size();
	}
	
	public void clearSelectedShapes() {
		collectionOfSelectedShapes.clear();
	}
	
	public Shape getSelectedShape() {
		return collectionOfSelectedShapes.get(0);
	}
	
	public Shape getShapeByIndex(int index) {
		return shapes.get(index);
	}
	
	public boolean doesContainSelectedShape(Shape shape) {
		return collectionOfSelectedShapes.contains(shape);
	}
	
	public void removeShapes(ArrayList<Shape> shapesToRemove) {
		shapes.removeAll(shapesToRemove);
	}
	
	public int getIndexOfShape(Shape shape) {
		return shapes.indexOf(shape);
	}
	
	public void addShapeAtIndex(int index, Shape shape) {
		shapes.add(index, shape);
	}
	
	public void addSelectedShapes(ArrayList<Shape> shapes) {
		collectionOfSelectedShapes.addAll(shapes);
	}
	
	public void addShapes(ArrayList<Shape> shapesToAdd) {
		shapes.addAll(shapesToAdd);
	}

	public CollectionOfSelectedShapes getCollectionOfSelectedShapes() {
		return collectionOfSelectedShapes;
	}
	
	
}
