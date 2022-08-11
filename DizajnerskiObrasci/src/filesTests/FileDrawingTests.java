package filesTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import files.FileDrawing;
import geometry.*;
import model.DrawingModel;

public class FileDrawingTests {

	private String path;
	private FileDrawing fileDrawing;
	private DrawingModel model;
	private ArrayList<Object> listOfObjectsToSave;
	private ArrayList<Object> listOfSelectedObjectsToSave;
	private ArrayList<Object> listOfSavedShapes;
	private ArrayList<Object> listOfSavedSelectedShapes;
	private ArrayList<Shape> listOfTestShapes;
	private ArrayList<Shape> listOfTestSelectedShapes;
	private static FileInputStream fileInputStream;
	private static ObjectInputStream objectInputStream;
	private Point testPoint;
	private Rectangle testRectangle;
	

	@BeforeEach
	public void setUp() {
		model = new DrawingModel();
		fileDrawing = new FileDrawing(model);
		
		listOfObjectsToSave = new ArrayList<Object>();
		listOfSelectedObjectsToSave = new ArrayList<Object>();
		listOfSavedShapes = new ArrayList<Object>();
		listOfSavedSelectedShapes = new ArrayList<Object>();
		listOfTestShapes = new ArrayList<Shape>();
		listOfTestSelectedShapes = new ArrayList<Shape>();
		
		testPoint = new Point(10, 10, true, Color.black);
		testRectangle = new Rectangle(new Point(15, 15), 10, 10, Color.black, Color.white);
		
		listOfTestShapes.add(testPoint);
		listOfTestShapes.add(testRectangle);
		listOfTestSelectedShapes.add(testPoint);
	}
	
	@Test
	public void testSaveFile_ShapesFromModelSaved() throws IOException, ClassNotFoundException {
		model.addShapes(listOfTestShapes);
		listOfObjectsToSave.addAll(listOfTestShapes);
		path = "testSaveShapesDrawing.txt";
		
		fileDrawing.saveFile(path);
	
		readFromFile();
		assertEquals(listOfObjectsToSave, listOfSavedShapes);
	}
	
	@Test
	public void testSaveFile_SelectedShapesFromModelSaved() throws IOException, ClassNotFoundException {
		model.addSelectedShapes(listOfTestSelectedShapes);
		listOfSelectedObjectsToSave.addAll(listOfTestSelectedShapes);
		path = "testSaveSelectedShapesDrawing.txt";
		
		fileDrawing.saveFile(path);
	
		readFromFile();
		assertEquals(listOfSelectedObjectsToSave, listOfSavedSelectedShapes);
	}
	
	@SuppressWarnings("unchecked")
	private void readFromFile() throws IOException, ClassNotFoundException {
		fileInputStream = new FileInputStream(path);
		objectInputStream = new ObjectInputStream(fileInputStream);
		ArrayList<ArrayList<Object>> savedObjects = (ArrayList<ArrayList<Object>>) objectInputStream.readObject();
		listOfSavedShapes = savedObjects.get(0);
		listOfSavedSelectedShapes = savedObjects.get(1);
	}
	
	@Test
	public void testOpenFile_ShapesAddedToModel() throws IOException {
		path = "testSaveShapesDrawing.txt";
		listOfSavedShapes.addAll(listOfTestShapes);
		
		fileDrawing.openFile(path);
		
		assertEquals(listOfSavedShapes, model.getShapes());
	}
	
	@Test
	public void testOpenFile_SelectedShapesAddedToModel() throws IOException {
		path = "testSaveSelectedShapesDrawing.txt";
		listOfSavedSelectedShapes.addAll(listOfTestSelectedShapes);
		
		fileDrawing.openFile(path);
		
		assertEquals(listOfSavedSelectedShapes, model.getSelectedShapes());
	}
	
	
	@AfterClass
	public static void tearDown() throws IOException {
		fileInputStream.close();
		objectInputStream.close();
	}
}
