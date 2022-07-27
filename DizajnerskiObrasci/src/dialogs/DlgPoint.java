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
			setCreateDialogFields(point);
			getTxtBorderColor().setBackground(point.getColor());
		}
	}

	@Override
	public void setCreateDialogFields(Point point) {
		String xCoord = String.valueOf(point.getX());
		String yCoord = String.valueOf(point.getY());
		getTxtXCoord().setText(xCoord);
		getTxtYCoord().setText(yCoord);
	}

	public Shape getShapeFromDialog() {
		String trimedXCoordValue = getTxtXCoord().getText().trim();
		String trimedYCoordValue = getTxtYCoord().getText().trim();
		int x = Integer.parseInt(trimedXCoordValue);
		int y = Integer.parseInt(trimedYCoordValue);
		Point point = new Point(x,y);
		Color borderColor = getBorderColor();
		point.setColor(borderColor);
		return point;	
	}
	

}
