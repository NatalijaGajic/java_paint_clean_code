package model;

import java.util.ArrayList;
import java.util.List;
import geometry.Shape;
import observer.CollectionOfSelectedShapes;
import observer.CollectionOfShapes;

public class DrawingModel {

	CollectionOfShapes collectionOfShapes = new CollectionOfShapes();
	CollectionOfSelectedShapes collectionOfSelectedShapes = new CollectionOfSelectedShapes();
	
	public DrawingModel() {
	}
	
	public void addShape(Shape s) {
		collectionOfShapes.add(s);
	}
	
	public void removeShape(Shape s) {
		collectionOfShapes.remove(s);
	}
	
	public Shape getShapeAtIndex(int index) {
		return collectionOfShapes.get(index);
	}
	
	public void removeShapeAtIndex(int index) {
		collectionOfShapes.remove(index);
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
		return collectionOfShapes.contains(shape);
	}
	
	public ArrayList<Shape> getShapes(){
		return collectionOfShapes.getShapes();
	}
	
	public ArrayList<Shape> getSelectedShapes(){
		return collectionOfSelectedShapes.getSelectedShapes();
	}
	
	public int getNumberOfShapes() {
		return collectionOfShapes.size();
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
		return collectionOfShapes.get(index);
	}
	
	public boolean doesContainSelectedShape(Shape shape) {
		return collectionOfSelectedShapes.contains(shape);
	}
	
	public void removeShapes(ArrayList<Shape> shapesToRemove) {
		collectionOfShapes.removeAll(shapesToRemove);
	}
	
	public int getIndexOfShape(Shape shape) {
		return collectionOfShapes.indexOf(shape);
	}
	
	public void addShapeAtIndex(int index, Shape shape) {
		collectionOfShapes.add(index, shape);
	}
	
	public void addSelectedShapes(ArrayList<Shape> shapes) {
		collectionOfSelectedShapes.addAll(shapes);
	}
	
	public void addShapes(ArrayList<Shape> shapesToAdd) {
		collectionOfShapes.addAll(shapesToAdd);
	}
	
	public Shape getShapeEqualTo(Shape shape) {
		return collectionOfShapes.getShapeEqualTo(shape);
	}
	
	public Shape getSelectedShapeEqualTo(Shape shape) {
		return collectionOfSelectedShapes.getShapeEqualTo(shape);
	}
	
	public CollectionOfSelectedShapes getCollectionOfSelectedShapes() {
		return collectionOfSelectedShapes;
	}

	public CollectionOfShapes getCollectionOfShapes() {
		return collectionOfShapes;
	}
	
	
	
}
