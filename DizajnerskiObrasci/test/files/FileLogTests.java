package files;

import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.util.*;
import logger.*;
import org.junit.AfterClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import files.FileLog;

class FileLogTests {

	private static Scanner scanner;
	private LogWriter logWriter;
	private LogReader logReader;
	private FileLog fileLog;
	private String path;
	private ArrayList<String> listOfLogsSavedInFile;
	private ArrayList<String> listOfCommandLogsToSave;
	private Queue<String> queueOfCommandsReadFromFile;
	private ArrayList<String> listOfCommandsReadFromFile;
	
	private String pointLog;
	private String cmdAddLog;
	private String cmdSelectLog;
	private String basePath;
	
	@BeforeEach
	public void setUp() {
		logWriter = new LogWriter();
		logReader = new LogReader();
		fileLog = new FileLog(logWriter, logReader);
		basePath = "./test/files/";
		initializeLists();
		initializeLogs();
		
	}
	
	private void initializeLists() {
		listOfLogsSavedInFile = new ArrayList<String>();
		listOfCommandLogsToSave = new ArrayList<String>();
		listOfCommandsReadFromFile = new ArrayList<String>();
	}
	
	private void initializeLogs() {
		pointLog = TypeOfShape.POINT + ":(1,1) BC:-16777216";
		cmdAddLog = TypeOfCommand.ADD_COMMAND + " " + pointLog;
		cmdSelectLog = TypeOfCommand.SELECT_COMMAND + " " + pointLog;
	}

	@Test
	public void testSaveFile_CommandLogsSaved() throws IOException {
		path = basePath + "testSaveLog.txt";
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
		path = basePath + "testSaveLog.txt";
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
