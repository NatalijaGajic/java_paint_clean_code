package model;

import java.util.ArrayList;
import java.util.List;
import geometry.Shape;

public class DrawingModel {

	ArrayList<Shape> shapes = new ArrayList<Shape>();
	ArrayList<Shape> selectedShapes = new ArrayList<Shape>();
	
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
	
	public boolean doesContainShape(Shape shape) {
		return shapes.contains(shape);
	}
	
	public ArrayList<Shape> getShapes(){
		return shapes;
	}
	
	public ArrayList<Shape> getSelectedShapes(){
		return selectedShapes;
	}
	
	public int getNumberOfShapes() {
		return shapes.size();
	}
	
	public int getNumberOfSelectedShapes() {
		return selectedShapes.size();
	}
	
	public void clearSelectedShapes() {
		selectedShapes.clear();
	}
	
	public Shape getSelectedShape() {
		return selectedShapes.get(0);
	}
	
	public Shape getShapeByIndex(int index) {
		return shapes.get(index);
	}
	
	public boolean doesContainSelectedShape(Shape shape) {
		return selectedShapes.contains(shape);
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
		selectedShapes.addAll(shapes);
	}
	
	public void addShapes(ArrayList<Shape> shapesToAdd) {
		shapes.addAll(shapesToAdd);
	}
}
