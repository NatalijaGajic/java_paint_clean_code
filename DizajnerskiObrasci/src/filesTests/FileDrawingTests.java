package filesTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.AfterClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.awt.Color;
import java.io.*;
import geometry.*;
import files.FileDrawing;
import model.DrawingModel;

public class FileDrawingTests {

	private static FileInputStream fileInputStream;
	private static ObjectInputStream objectInputStream;
	private FileDrawing fileDrawing;
	private DrawingModel model;
	private ArrayList<Object> listOfObjectsToSave;
	private ArrayList<Object> listOfSelectedObjectsToSave;
	private ArrayList<Object> listOfSavedShapes;
	private ArrayList<Object> listOfSavedSelectedShapes;
	private ArrayList<Shape> listOfTestShapes;
	private ArrayList<Shape> listOfTestSelectedShapes;
	private Point testPoint;
	private Rectangle testRectangle;
	private String testPath;
	

	@BeforeEach
	public void setUp() {
		model = new DrawingModel();
		fileDrawing = new FileDrawing(model);
		initializeLists();
		initializeTestObjects();
		
	}
	
	private void initializeLists() {
		listOfObjectsToSave = new ArrayList<Object>();
		listOfSelectedObjectsToSave = new ArrayList<Object>();
		listOfSavedShapes = new ArrayList<Object>();
		listOfSavedSelectedShapes = new ArrayList<Object>();
		listOfTestShapes = new ArrayList<Shape>();
		listOfTestSelectedShapes = new ArrayList<Shape>();
	}
	
	private void initializeTestObjects() {
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
		testPath = "testSaveShapesDrawing.txt";
		fileDrawing.saveFile(testPath);
		readFromFile();
		assertEquals(listOfObjectsToSave, listOfSavedShapes);
	}
	
	@Test
	public void testSaveFile_SelectedShapesFromModelSaved() throws IOException, ClassNotFoundException {
		model.addSelectedShapes(listOfTestSelectedShapes);
		listOfSelectedObjectsToSave.addAll(listOfTestSelectedShapes);
		testPath = "testSaveSelectedShapesDrawing.txt";
		fileDrawing.saveFile(testPath);
		readFromFile();
		assertEquals(listOfSelectedObjectsToSave, listOfSavedSelectedShapes);
	}
	
	@SuppressWarnings("unchecked")
	private void readFromFile() throws IOException, ClassNotFoundException {
		fileInputStream = new FileInputStream(testPath);
		objectInputStream = new ObjectInputStream(fileInputStream);
		ArrayList<ArrayList<Object>> savedObjects = (ArrayList<ArrayList<Object>>) objectInputStream.readObject();
		listOfSavedShapes = savedObjects.get(0);
		listOfSavedSelectedShapes = savedObjects.get(1);
	}
	
	@Test
	public void testOpenFile_ShapesAddedToModel() throws IOException {
		testPath = "testSaveShapesDrawing.txt";
		listOfSavedShapes.addAll(listOfTestShapes);
		fileDrawing.openFile(testPath);
		assertEquals(listOfSavedShapes, model.getShapes());
	}
	
	@Test
	public void testOpenFile_SelectedShapesAddedToModel() throws IOException {
		testPath = "testSaveSelectedShapesDrawing.txt";
		listOfSavedSelectedShapes.addAll(listOfTestSelectedShapes);
		fileDrawing.openFile(testPath);
		assertEquals(listOfSavedSelectedShapes, model.getSelectedShapes());
	}
	
	
	@AfterClass
	public static void tearDown() throws IOException {
		fileInputStream.close();
		objectInputStream.close();
	}
}
