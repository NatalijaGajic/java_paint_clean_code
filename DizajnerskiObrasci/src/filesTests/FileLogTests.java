package filesTests;

import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.util.*;
import logger.*;
import org.junit.AfterClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import commandHandler.CommandsHandler;
import controllers.DrawingController;
import files.FileLog;
import frame.DrawingFrame;
import model.DrawingModel;

class FileLogTests {

	private static Scanner scanner;
	private DrawingModel model;
	private DrawingFrame frame;
	private CommandsHandler commandsHandler;
	private DrawingController controller;
	private LogWriter logWriter;
	private LogReader logReader;
	private FileLog fileLog;
	private String path;
	private ArrayList<String> listOfLogsSavedInFile;
	private ArrayList<String> listOfCommandLogsToSave;
	private Queue<String> queueOfCommandsReadFromFile;
	private ArrayList<String> listOfCommandsReadFromFile;
	
	private String pointLog = LoggerConstants.POINT + ":(1,1) BC:-16777216";
	private String cmdAddLog = LoggerConstants.ADD_COMMAND + " " + pointLog;
	private String cmdSelectLog = LoggerConstants.SELECT_COMMAND + " " + pointLog;
	
	@BeforeEach
	public void setUp() {
		model = new DrawingModel();
		frame = new DrawingFrame();
		commandsHandler = new CommandsHandler();
		controller = new DrawingController(model, frame, commandsHandler);
		logWriter = new LogWriter();
		logReader = new LogReader(controller);
		fileLog = new FileLog(logWriter, logReader);
		initializeLists();
		
	}
	
	private void initializeLists() {
		listOfLogsSavedInFile = new ArrayList<String>();
		listOfCommandLogsToSave = new ArrayList<String>();
		listOfCommandsReadFromFile = new ArrayList<String>();
	}

	@Test
	public void testSaveFile_CommandLogsSaved() throws IOException {
		path = "testSaveLog.txt";
		logWriter.log(cmdAddLog);
		logWriter.log(cmdSelectLog);
		listOfCommandLogsToSave.add(cmdAddLog);
		listOfCommandLogsToSave.add(cmdSelectLog);
		
		fileLog.saveFile(path);
	
		readFromFile();
		assertEquals(listOfCommandLogsToSave, listOfLogsSavedInFile);
	}
	
	private void readFromFile() throws FileNotFoundException {
		scanner = new Scanner(new FileReader(path));
		String lineOfFile;
		while (scanner.hasNextLine()) {
			lineOfFile = scanner.nextLine();
			listOfLogsSavedInFile.add(lineOfFile);
		}
	}
	
	@Test
	public void testOpenFile_CommandsAddedToCommandsToBeExecuted() throws IOException {
		path = "testSaveLog.txt";
		listOfLogsSavedInFile.add(cmdAddLog);
		listOfLogsSavedInFile.add(cmdSelectLog);
		
		fileLog.openFile(path);
		
		queueOfCommandsReadFromFile = logReader.getCommandsToBeExecutedLog();
		populateListOfCommandsReadFromFile();
		assertEquals(listOfLogsSavedInFile, listOfCommandsReadFromFile);
	}
	
	private void populateListOfCommandsReadFromFile() {
		while(!queueOfCommandsReadFromFile.isEmpty()) {
			listOfCommandsReadFromFile.add(queueOfCommandsReadFromFile.poll());
		}
	}
	
	@AfterClass
	public static void tearDown() throws IOException {
		scanner.close();
	}
	
}
