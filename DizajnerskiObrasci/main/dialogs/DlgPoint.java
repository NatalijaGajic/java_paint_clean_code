package dialogs;

import java.awt.Color;
import geometry.*;

public class DlgPoint extends DlgShape {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		try {
			DlgPoint dialog = new DlgPoint();
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public DlgPoint() {
		setTitle("Point dialog");
	}
	
	@Override
	public boolean areAllFieldsFilled() {
		String xCoordValue = getTxtXCoord().getText();
		String yCoordValue = getTxtYCoord().getText();

		return (!xCoordValue.isEmpty() && !yCoordValue.isEmpty());
	}

	@Override
	public boolean areValuesValid() {
		return getTxtXCoord().getText().length() < 4 && getTxtYCoord().getText().length() < 4;
	}

	@Override
	public void setModifyDialogFields(Shape shape) {
		if(shape instanceof Point) {
			Point point = (Point) shape;
			
			String xCoord = String.valueOf(point.getX());
			getTxtXCoord().setText(xCoord);
			
			String yCoord = String.valueOf(point.getY());
			getTxtYCoord().setText(yCoord);	
			
			Color edgeColor = point.getColor();
			getPnlEdgeColor().setBackground(edgeColor);
			setEdgeColor(edgeColor);
		}
	}

	public Shape getShapeFromDialog() {
		int x = Integer.parseInt(getTxtXCoord().getText());
		int y = Integer.parseInt( getTxtYCoord().getText());	
		Color edgeColor = getEdgeColor();
		
		return new Point(x, y, edgeColor);	
	}
	

}
