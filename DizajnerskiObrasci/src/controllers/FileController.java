package controllers;

import files.FileChooser;
import files.*;
import frame.DrawingFrame;
import logger.LogReader;
import logger.LogWriter;
import model.DrawingModel;

public class FileController {

	private LogWriter logWriter;
	private LogReader logReader;
	private FileManager fileManager;
	private FileStrategy fileStrategy;
	private FileChooser fileChooser;
	private DrawingModel model;
	private DrawingFrame frame;
	private String fileToSaveTo;
	private String fileToOpen;
	
	public FileController(DrawingModel model, DrawingFrame frame, LogWriter logWriter,LogReader logReader) {
		this.model = model;
		this.frame = frame;
		this.logWriter = logWriter;
		this.logReader = logReader;
		fileChooser = new FileChooser();
		fileManager = new FileManager();
	}
	
	public void loadDrawing() {
		fileToOpen = fileChooser.showOpenTxtFileDialog();
		fileStrategy = new FileDrawing(model);
		fileManager.setFileStrategy(fileStrategy);
		fileManager.open(fileToOpen);
		frame.getView().repaint();
	}
	
	public void loadLog() {
		fileToOpen = fileChooser.showOpenTxtFileDialog();
		fileStrategy = new FileLog(logWriter,logReader);
		fileManager.setFileStrategy(fileStrategy);
		fileManager.open(fileToOpen);
	}
	
	public void saveDrawing() {
		fileToSaveTo = fileChooser.showSaveToTxtFileDialog();
		fileStrategy = new FileDrawing(model);
		fileManager.setFileStrategy(fileStrategy);
		fileManager.save(fileToSaveTo);
	}
	
	public void saveLog() {
		fileToSaveTo = fileChooser.showSaveToTxtFileDialog();
		fileStrategy = new FileLog(logWriter,logReader);
		fileManager.setFileStrategy(fileStrategy);
		fileManager.save(fileToSaveTo);
	}
	
	public FileStrategy getFileManagerFileStrategy() {
		return this.fileManager.getFileStrategy();
	}
	
}
