package observer;

import java.util.ArrayList;
import java.util.Iterator;

import geometry.Shape;

public class CollectionOfSelectedShapes implements Subject{

	ArrayList<Shape> selectedShapes = new ArrayList<Shape>();
	ArrayList<SelectedShapesObserver> observers = new ArrayList<SelectedShapesObserver>();
	
	@Override
	public void registerObserver(SelectedShapesObserver o) {
		observers.add(o);
	}

	@Override
	public void unregisterObserver(SelectedShapesObserver o) {
		observers.remove(o);
	}

	@Override
	public void notifyObservers() {
		int numberOfSelectedShapes = selectedShapes.size();
		Iterator<SelectedShapesObserver> it = observers.iterator();
		while(it.hasNext()) {
			SelectedShapesObserver ob = it.next();
			ob.update(numberOfSelectedShapes);
		}
		
	}
	
	public void add(Shape shape) {
		selectedShapes.add(shape);
		notifyObservers();
	}
	
	public void addAll(ArrayList<Shape> shapes) {
		selectedShapes.addAll(shapes);
		notifyObservers();
	}
	
	public int size() {
		return selectedShapes.size();
	}
	
	public void clear() {
		selectedShapes.clear();
		notifyObservers();
	}
	
	public Shape get(int index) {
		return selectedShapes.get(index);
	}
	
	public boolean contains(Shape shape) {
		return selectedShapes.contains(shape);
	}
	
	public void remove(int index) {
		selectedShapes.remove(index);
		notifyObservers();
	}
	
	public void remove(Shape shape) {
		selectedShapes.remove(shape);
		notifyObservers();
	}

	public ArrayList<Shape> getSelectedShapes() {
		return selectedShapes;
	}

	public void setSelectedShapes(ArrayList<Shape> selectedShapes) {
		this.selectedShapes = selectedShapes;
	}

	public ArrayList<SelectedShapesObserver> getObservers() {
		return observers;
	}

	public void setObservers(ArrayList<SelectedShapesObserver> observers) {
		this.observers = observers;
	}
	
	
}
