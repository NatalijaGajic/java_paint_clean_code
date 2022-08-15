package logger;

public enum TypeOfShape {

	POINT,
	LINE,
	CIRCLE,
	DONUT,
	RECTANGLE,
	HEXAGON;
	
	@Override
	public String toString() {
		switch(this) {
		case POINT: return "Point";
		case LINE: return "Line";
		case CIRCLE: return "Circle";
		case DONUT: return "Donut";
		case RECTANGLE: return "Rectangle";
		case HEXAGON: return "Hexagon";
		default: throw new IllegalArgumentException();
		}
			
	}
}
