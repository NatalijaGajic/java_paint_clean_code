package applications;

import commandHandler.CommandsHandler;
import controllers.*;
import frame.DrawingFrame;
import logger.*;
import model.DrawingModel;
import view.DrawingPanel;

public class DrawingApplication {

	public static void main(String[] args) {

		try {
			DrawingModel model = new DrawingModel();
			DrawingFrame frame = new DrawingFrame();
			CommandsHandler commandsHandler = new CommandsHandler();
			DrawingController drawingController = new DrawingController(model, frame, commandsHandler);
			LogWriter logWriter = new LogWriter();
			LogReader logReader = new LogReader(drawingController);
			FileController fileController = new FileController(model, frame, logWriter, logReader);
			ButtonsController buttonsController = new ButtonsController(model, frame);
			DrawingPanel view = frame.getView();
			
			commandsHandler.registerObserver(frame.getOptionsToolBar());
			logReader.registerObserver(frame.getOptionsToolBar());
			model.getCollectionOfSelectedShapes().registerObserver(frame.getOptionsToolBar());
			model.getCollectionOfShapes().registerObserver(frame.getOptionsToolBar());
			
			drawingController.setLogWriter(logWriter);
			drawingController.setLogReader(logReader);
			
			frame.setDrawingController(drawingController);
			frame.setDrawingControllerForToolBars(drawingController);
			frame.setFileControllerForMenuBar(fileController);
			frame.setButtonsControllerForOptionsToolBar(buttonsController);
			frame.setDefaultListModelForScrollPane(logWriter.getExecutedCommandsLog());
			frame.setView(view);
			
			view.setModel(model);
			frame.setVisible(true);
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}

}
