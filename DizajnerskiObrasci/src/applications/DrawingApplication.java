package applications;

import controllers.DrawingController;
import frame.DrawingFrame;
import model.DrawingModel;
import view.DrawingPanel;

public class DrawingApplication {

	public static void main(String[] args) {

		DrawingModel model = new DrawingModel();
		DrawingFrame frame = new DrawingFrame();
		DrawingController controller = new DrawingController(model, frame);
		DrawingPanel view = frame.getView();
		
		frame.setController(controller);
		frame.setControllerForToolBars(controller);
		frame.setView(view);
		view.setModel(model);
		
		frame.setVisible(true);
	}

}
