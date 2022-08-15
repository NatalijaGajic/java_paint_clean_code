package commands;

import java.util.*;
import geometry.Shape;
import logger.TypeOfCommand;
import model.DrawingModel;

public class CmdDelete implements Command{

	private DrawingModel model;
	private HashMap<Integer, Shape> shapeIndexesBeforeDeletion; 
	private ArrayList<Shape> shapesToDelete;
	
	public CmdDelete(DrawingModel model, ArrayList<Shape> shapesToDelete) {
		this.model = model;
		this.shapesToDelete = shapesToDelete;
		populateIndexShapeHashMap();
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
	private void populateIndexShapeHashMap() {
		shapeIndexesBeforeDeletion = new HashMap<Integer, Shape>(); 
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
	
	@Override
	public String toString() {
		StringBuilder compositionOfStrings = new StringBuilder("");
		Iterator<Shape> it = shapesToDelete.iterator();
		compositionOfStrings.append(TypeOfCommand.DELETE_COMMAND + " ");
		compositionOfStrings.append(it.next().toString());
		while(it.hasNext()) {
			compositionOfStrings.append(";");
			compositionOfStrings.append(it.next().toString());
		}
		return compositionOfStrings.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof CmdDelete) {
			ArrayList<Shape> forwardedShapes = ((CmdDelete) obj).shapesToDelete;
			return shapesToDelete.equals(forwardedShapes);
		}
		return false;
	}
}
