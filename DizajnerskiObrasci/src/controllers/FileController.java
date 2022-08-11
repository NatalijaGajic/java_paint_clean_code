package controllers;

import files.*;
import logger.*;
import frame.DrawingFrame;
import model.DrawingModel;

public class FileController {

	private LogWriter logWriter;
	private LogReader logReader;
	private FileManager fileManager;
	private FileStrategy fileStrategy;
	private FileChooser fileChooser;
	private DrawingModel model;
	private DrawingFrame frame;
	
	public FileController(DrawingModel model, DrawingFrame frame, LogWriter logWriter,LogReader logReader) {
		this.model = model;
		this.frame = frame;
		this.logWriter = logWriter;
		this.logReader = logReader;
		fileChooser = new FileChooser();
		fileManager = new FileManager();
	}
	
	public void loadDrawing() {
		String fileToOpen = fileChooser.showOpenTxtFileDialog();
		if(!fileToOpen.isEmpty()) {
			fileStrategy = new FileDrawing(model);
			fileManager.setFileStrategy(fileStrategy);
			fileManager.open(fileToOpen);
			frame.getView().repaint();
		}
	}
	
	public void loadLog() {
		String fileToOpen = fileChooser.showOpenTxtFileDialog();
		if(!fileToOpen.isEmpty()) {
			fileStrategy = new FileLog(logWriter,logReader);
			fileManager.setFileStrategy(fileStrategy);
			fileManager.open(fileToOpen);
		}
	}
	
	public void saveDrawing() {
		String fileToSaveTo = fileChooser.showSaveToTxtFileDialog();
		if(!fileToSaveTo.isEmpty()) {
			fileStrategy = new FileDrawing(model);
			fileManager.setFileStrategy(fileStrategy);
			fileManager.save(fileToSaveTo);
		}
	}
	
	public void saveLog() {
		String fileToSaveTo = fileChooser.showSaveToTxtFileDialog();
		if(!fileToSaveTo.isEmpty()) {
			fileStrategy = new FileLog(logWriter,logReader);
			fileManager.setFileStrategy(fileStrategy);
			fileManager.save(fileToSaveTo);
		}
	}
	
	public FileStrategy getFileManagerFileStrategy() {
		return this.fileManager.getFileStrategy();
	}
	
}
