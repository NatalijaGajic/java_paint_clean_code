package dialogs;

import geometry.Point;
import geometry.Shape;

public interface Dialog {

	void buildDialog();
	boolean areAllFieldsFilled();
	boolean areValuesValid();
	void setModifyDialogFields(Shape shape);
	void setCreateDialogFields(Point point);
	Shape getShapeFromDialog();
}
