package dialogs;


import java.awt.Color;
import geometry.Point;
import geometry.Shape;

public class DlgPoint extends DlgShape {

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

		if (xCoordValue.isEmpty() || yCoordValue.isEmpty())
			return false;
		return true;
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
			
			Color borderColor = point.getColor();
			getTxtBorderColor().setBackground(borderColor);
			setBorderColor(borderColor);
		}
	}

	public Shape getShapeFromDialog() {
		int x = Integer.parseInt(getTxtXCoord().getText());
		int y = Integer.parseInt( getTxtYCoord().getText());	
		Color borderColor = getBorderColor();
		
		return new Point(x, y, borderColor);	
	}
	

}
