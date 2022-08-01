package commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import geometry.Shape;
import model.DrawingModel;

public class CmdDelete implements Command{

	private DrawingModel model;
	private HashMap<Integer, Shape> shapeIndexesBeforeDeletion = new HashMap<Integer, Shape>(); 
	private ArrayList<Shape> shapesToDelete;
	
	public CmdDelete(DrawingModel model, ArrayList<Shape> shapesToDelete) {
		this.model = model;
		this.shapesToDelete = shapesToDelete;
		populateIndexShapeHashMap(shapesToDelete);
		
	}

	@Override
	public void execute() {
		model.removeShapes(shapesToDelete);
		model.clearSelectedShapes();
	}

	@Override
	public void unexecute() {
		addShapesToIndexesBeforeDeletion();
		model.addSelectedShapes(shapesToDelete);
	}
	
	/**
	 * HashMap saves the index of the shape to be deleted, so the positions of shapes remain perserved
	 * @param shapesToDelete
	 */
	private void populateIndexShapeHashMap(ArrayList<Shape> shapesToDelete) {
		Iterator<Shape> it = shapesToDelete.iterator();
		while(it.hasNext()) {
			Shape shapeToDelete = it.next();
			int indexOfShapeToDelete = model.getIndexOfShape(shapeToDelete);
			shapeIndexesBeforeDeletion.put(indexOfShapeToDelete, shapeToDelete);
		}
	}
	
	/**
	 * Keys are sorted, so the element with the largest index is added last, to avoid IndexOutOfBoundsException 
	 */
	private void addShapesToIndexesBeforeDeletion() {
		 ArrayList<Integer> sortedKeys = new ArrayList<Integer>(shapeIndexesBeforeDeletion.keySet());
		 Collections.sort(sortedKeys);
		 for (Integer index : sortedKeys) {
			model.addShapeAtIndex(index, shapeIndexesBeforeDeletion.get(index));
		}
	}
}
