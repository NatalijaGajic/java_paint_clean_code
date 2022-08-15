package commandHandler;

import java.util.*;
import observer.*;
import commands.Command;

public class CommandsHandler implements CommandsHandlerSubject{

	private Stack<Command> executedCommands;
	private Stack<Command> unexecutedCommands;
	private ArrayList<CommandsHandlerObserver> observers;
	
	public CommandsHandler() {
		executedCommands = new Stack<Command>();
		unexecutedCommands = new Stack<Command>();
		observers = new ArrayList<CommandsHandlerObserver>();
	}
	
	public void undoExecutedCommand() {
		Command executedCommand = executedCommands.pop();
		executedCommand.unexecute();
		unexecutedCommands.push(executedCommand);
		notifyObservers();
	}
	
	public void redoUnexecutedCommand() {
		Command unexecutedCommand = unexecutedCommands.pop();
		unexecutedCommand.execute();
		executedCommands.push(unexecutedCommand);
		notifyObservers();
	}
	
	/**
	 * After adding a new command, stack with unexecuted commands is cleared
	 * @param cmd
	 */
	public void addExecutedCommand(Command cmd) {
		executedCommands.push(cmd);
		unexecutedCommands.clear();
		notifyObservers();
	}

	@Override
	public void registerObserver(CommandsHandlerObserver o) {
		observers.add(o);
	}

	@Override
	public void unregisterObserver(CommandsHandlerObserver o) {
		observers.remove(o);
	}

	@Override
	public void notifyObservers() {
		int numberOfExecutedCommands = executedCommands.size();
		int numberOfUnexecutedCommands = unexecutedCommands.size();
		Iterator<CommandsHandlerObserver> it = observers.iterator();
		while(it.hasNext()) {
			CommandsHandlerObserver ob = it.next();
			ob.updateCommandsHandlerObserver(numberOfExecutedCommands, numberOfUnexecutedCommands);
		}
	}

	public Stack<Command> getExecutedCommands() {
		return executedCommands;
	}

	public void setExecutedCommands(Stack<Command> executedCommands) {
		this.executedCommands = executedCommands;
	}

	public Stack<Command> getUnexecutedCommands() {
		return unexecutedCommands;
	}

	public void setUnexecutedCommands(Stack<Command> unexecutedCommands) {
		this.unexecutedCommands = unexecutedCommands;
	}
	
	
}
