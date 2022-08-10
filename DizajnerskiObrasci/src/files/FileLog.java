package files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.DefaultListModel;

import logger.LogReader;
import logger.LogWriter;

public class FileLog implements FileStrategy{
	
	private LogWriter logWriter;
	private LogReader logReader;
	private DefaultListModel<String> executedCommandsLog;
	
	public FileLog(LogWriter logWriter, LogReader logReader) {
		this.logWriter = logWriter;
		this.logReader = logReader;
	}
	
	@Override
	public void saveFile(String filePath) {
		executedCommandsLog = logWriter.getExecutedCommandsLog();
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			for (int i = 0; i < executedCommandsLog.getSize(); i++) {
				writer.write(executedCommandsLog.get(i));
				writer.newLine();
			}
		} catch (IOException exception) {
			System.out.println(exception.getMessage());
		}
		
	}

	@Override
	public void openFile(String filePath) {
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String logLine = "";
			while ((logLine = reader.readLine()) != null)
				logReader.addCommandToCommandsToBeExecutedLog(logLine);
		} catch (IOException exception) {
			logReader.clearLog();
			System.out.println(exception.getMessage());
		}
	}

}
