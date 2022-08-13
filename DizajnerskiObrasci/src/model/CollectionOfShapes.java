package model;

import java.util.ArrayList;
import java.util.Iterator;

import geometry.Shape;
import observer.ShapesObserver;
import observer.ShapesSubject;

public class CollectionOfShapes implements ShapesSubject {

	ArrayList<Shape> shapes;
	ArrayList<ShapesObserver> observers;
	
	public CollectionOfShapes() {
		shapes = new ArrayList<Shape>();
		observers = new ArrayList<ShapesObserver>();
	}
	
	@Override
	public void registerObserver(ShapesObserver o) {
		observers.add((ShapesObserver)o);
	}

	@Override
	public void unregisterObserver(ShapesObserver o) {
		observers.remove(o);
		
	}

	@Override
	public void notifyObservers() {
		Iterator<ShapesObserver> it = observers.iterator();
		while(it.hasNext()) {
			ShapesObserver ob = it.next();
			ob.updateShapesObserver();
		}
		
	}
	
	public void add(Shape shape) {
		shapes.add(shape);
		notifyObservers();
	}
	
	public void add(int index, Shape shape) {
		this.shapes.add(index, shape);
		notifyObservers();
	}
	
	public void addAll(ArrayList<Shape> shapes) {
		this.shapes.addAll(shapes);
		notifyObservers();
	}
	
	public int size() {
		return shapes.size();
	}
	
	public void clear() {
		shapes.clear();
		notifyObservers();
	}
	
	public Shape get(int index) {
		return shapes.get(index);
	}
	
	public int indexOf(Shape shape) {
		return shapes.indexOf(shape);
	}
	
	public boolean contains(Shape shape) {
		return shapes.contains(shape);
	}
	
	public void remove(int index) {
		shapes.remove(index);
		notifyObservers();
	}
	
	public void remove(Shape shape) {
		shapes.remove(shape);
		notifyObservers();
	}
	
	public void removeAll(ArrayList<Shape> shapesToRemove) {
		shapes.removeAll(shapesToRemove);
		notifyObservers();
	}
	
	public Shape getShapeEqualTo(Shape shape) {
		Iterator<Shape> it = shapes.iterator();
		while(it.hasNext()) {
			Shape shapeFromList = it.next();
			if(shapeFromList.equals(shape))
				return shapeFromList;
		}
		return null;
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}

	public ArrayList<ShapesObserver> getObservers() {
		return observers;
	}

	public void setObservers(ArrayList<ShapesObserver> observers) {
		this.observers = observers;
	}
	
}
