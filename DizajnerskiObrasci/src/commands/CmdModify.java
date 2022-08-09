package commands;

import geometry.Shape;
import logger.LoggerConstants;

public class CmdModify implements Command{

	private Shape oldState;
	private Shape newState;
	private Shape originalState;

	public CmdModify(Shape oldState, Shape newState) {
		this.oldState = oldState;
		this.newState = newState;
		originalState = oldState.clone();
	}

	@Override
	public void execute() {
		oldState.setShapeFileds(newState);
	}

	@Override
	public void unexecute() {
		oldState.setShapeFileds(originalState);
	}

	public String toString() {
		return LoggerConstants.MODIFY_COMMAND + " " + originalState.toString() + " to " + newState.toString();
	}
}
