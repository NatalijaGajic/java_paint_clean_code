package files;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import geometry.Shape;
import model.DrawingModel;

public class FileDrawing implements FileStrategy {

	private DrawingModel model;
	private ArrayList<ArrayList<Shape>> serializedShapes;

	public FileDrawing(DrawingModel model) {
		this.model = model;
		serializedShapes = new ArrayList<ArrayList<Shape>>();
	}

	@Override
	public void saveFile(String filePath) {
		serializedShapes.add(model.getShapes());
		serializedShapes.add(model.getSelectedShapes());
		
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
			outputStream.writeObject(serializedShapes);
		} catch (IOException exception) {
			System.out.println(exception.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void openFile(String filePath) {
		try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
			serializedShapes = (ArrayList<ArrayList<Shape>>) inputStream.readObject();
			model.addShapes(serializedShapes.get(0));
			model.addSelectedShapes(serializedShapes.get(1));
		} catch (IOException exception) {
			System.out.println(exception.getMessage());
		} catch (ClassNotFoundException exception) {
			model.getShapes().clear();
			System.out.println(exception.getMessage());
		}
	}

}
