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
			CommandController commandController = new CommandController(frame, commandsHandler, logWriter, logParser);
			DrawingController drawingController = new DrawingController(model, frame, commandController);
			FileController fileController = new FileController(model, frame, logWriter, logReader);
			ButtonsController buttonsController = new ButtonsController(model, frame);
			DrawingPanel view = frame.getView();
			
			commandsHandler.registerObserver(frame.getCommandOptionsToolBar());
			logReader.registerObserver(frame.getCommandOptionsToolBar());
			model.getCollectionOfSelectedShapes().registerObserver(frame.getDrawingOptionsToolBar());
			model.getCollectionOfShapes().registerObserver(frame.getDrawingOptionsToolBar());
			
			frame.setDrawingController(drawingController);
			frame.setFileControllerForMenuBar(fileController);
			frame.setButtonsControllerForOptionsToolBar(buttonsController);
			frame.setCommandControllerForOptionsToolBar(commandController);
			frame.setDefaultListModelForScrollPane(logWriter.getExecutedCommandsLog());
			
			view.setModel(model);
			frame.setVisible(true);
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}

}
