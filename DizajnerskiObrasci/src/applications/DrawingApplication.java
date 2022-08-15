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
			LogWriter logWriter = new LogWriter();
			LogReader logReader = new LogReader();
			LogParser logParser = new LogParser(model, logReader);
			DrawingController drawingController = new DrawingController(model, frame, commandsHandler, logWriter, logParser);
			FileController fileController = new FileController(model, frame, logWriter, logReader);
			ButtonsController buttonsController = new ButtonsController(model, frame);
			DrawingPanel view = frame.getView();
			
			commandsHandler.registerObserver(frame.getOptionsToolBar());
			logReader.registerObserver(frame.getOptionsToolBar());
			model.getCollectionOfSelectedShapes().registerObserver(frame.getOptionsToolBar());
			model.getCollectionOfShapes().registerObserver(frame.getOptionsToolBar());
			
			frame.setDrawingController(drawingController);
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
