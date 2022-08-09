package applications;

import commandHandler.CommandsHandler;
import controllers.DrawingController;
import frame.DrawingFrame;
import model.DrawingModel;
import observer.CollectionOfSelectedShapes;
import view.DrawingPanel;

public class DrawingApplication {

	public static void main(String[] args) {

		DrawingModel model = new DrawingModel();
		DrawingFrame frame = new DrawingFrame();
		DrawingController controller = new DrawingController(model, frame);
		DrawingPanel view = frame.getView();
		CommandsHandler commandsHandler = new CommandsHandler();
		
		commandsHandler.registerObserver(frame.getOptionsToolBar());
		controller.setCommandsHandler(commandsHandler);
		model.getCollectionOfSelectedShapes().registerObserver(frame.getOptionsToolBar());
		model.getCollectionOfShapes().registerObserver(frame.getOptionsToolBar());
		frame.setController(controller);
		frame.setControllerForToolBars(controller);
		frame.setView(view);
		view.setModel(model);
		
		frame.setVisible(true);
	}

}
