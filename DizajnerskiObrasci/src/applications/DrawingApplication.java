package applications;

import commandHandler.CommandsHandler;
import controllers.DrawingController;
import controllers.FileController;
import frame.DrawingFrame;
import logger.LogReader;
import logger.LogWriter;
import model.DrawingModel;
import view.DrawingPanel;

public class DrawingApplication {

	public static void main(String[] args) {

		try {
			DrawingModel model = new DrawingModel();
			DrawingFrame frame = new DrawingFrame();
			CommandsHandler commandsHandler = new CommandsHandler();
			DrawingController controller = new DrawingController(model, frame, commandsHandler);
			LogWriter logWriter = new LogWriter();
			LogReader logReader = new LogReader(controller);
			FileController fileController = new FileController(model, frame, logWriter, logReader);
			DrawingPanel view = frame.getView();
			
			commandsHandler.registerObserver(frame.getOptionsToolBar());
			
			controller.setLogWriter(logWriter);
			controller.setLogReader(logReader);
			
			model.getCollectionOfSelectedShapes().registerObserver(frame.getOptionsToolBar());
			model.getCollectionOfShapes().registerObserver(frame.getOptionsToolBar());
			
			frame.setController(controller);
			frame.setDrawingControllerForToolBars(controller);
			frame.setFileControllerForMenuBar(fileController);
			frame.setDefaultListModelForScrollPane(logWriter.getExecutedCommandsLog());
			frame.setView(view);
			
			view.setModel(model);
			
			frame.setVisible(true);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}

}
