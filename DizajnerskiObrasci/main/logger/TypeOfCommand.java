package logger;

public enum TypeOfCommand {

	ADD_COMMAND,
	MODIFY_COMMAND,
	DELETE_COMMAND,
	BRING_TO_BACK_COMMAND,
	BRING_TO_FRONT_COMMAND,
	TO_BACK_COMMAND,
	TO_FRONT_COMMAND,
	SELECT_COMMAND,
	DESELECT_COMMAND,
	UNDO_COMMAND,
	REDO_COMMAND;
	
	@Override
	public String toString() {
		switch(this) {
		case ADD_COMMAND: return "Added";
		case MODIFY_COMMAND: return "Modified";
		case DELETE_COMMAND: return "Deleted";
		case BRING_TO_BACK_COMMAND: return "BrougthToBack";
		case BRING_TO_FRONT_COMMAND: return "BrougthToFront";
		case TO_BACK_COMMAND: return "MovedToBack";
		case TO_FRONT_COMMAND: return "MovedToFront";
		case SELECT_COMMAND: return "Selected";
		case DESELECT_COMMAND: return "Deselected";
		case UNDO_COMMAND: return "Undo";
		case REDO_COMMAND: return "Redo";
		default: throw new IllegalArgumentException();
		}
			
	}
}
