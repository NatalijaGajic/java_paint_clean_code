package files;

import java.io.*;
import logger.*;
import javax.swing.DefaultListModel;


public class FileLog implements FileStrategy{
	
	private LogWriter logWriter;
	private LogReader logReader;
	
	public FileLog(LogWriter logWriter, LogReader logReader) {
		this.logWriter = logWriter;
		this.logReader = logReader;
	}
	
	@Override
	public void saveFile(String filePath) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			DefaultListModel<String> executedCommandsLog = logWriter.getExecutedCommandsLog();
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
