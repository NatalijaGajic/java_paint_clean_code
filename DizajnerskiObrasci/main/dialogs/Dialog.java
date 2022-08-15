package dialogs;

import java.awt.Color;

import geometry.Point;
import geometry.Shape;

public interface Dialog {

	void buildDialog();
	boolean areAllFieldsFilled();
	boolean areValuesValid();
	void setModifyDialogFields(Shape shape);
	void setCreateDialogFields(Point point, Color edgeColor);
	Shape getShapeFromDialog();
	boolean isAccepted();
}
