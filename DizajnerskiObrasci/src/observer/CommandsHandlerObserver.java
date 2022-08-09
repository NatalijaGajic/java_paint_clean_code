package observer;

public interface CommandsHandlerObserver {
	public void update(int numberOfExecutedCommands, int numberOfUnexecutedCommands);
}
