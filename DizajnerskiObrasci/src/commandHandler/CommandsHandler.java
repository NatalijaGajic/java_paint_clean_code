package commandHandler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import commands.Command;
import observer.CommandsHandlerObserver;
import observer.CommandsHandlerSubject;

public class CommandsHandler implements CommandsHandlerSubject{

	private Stack<Command> executedCommands;
	private Stack<Command> unexecutedCommands;
	ArrayList<CommandsHandlerObserver> observers = new ArrayList<CommandsHandlerObserver>();
	
	public CommandsHandler() {
		executedCommands = new Stack<Command>();
		unexecutedCommands = new Stack<Command>();
	}
	
	public void undo() {
		Command cmd = executedCommands.pop();
		cmd.unexecute();
		unexecutedCommands.push(cmd);
		notifyObservers();
	}
	
	public void redo() {
		Command cmd = unexecutedCommands.pop();
		cmd.execute();
		executedCommands.push(cmd);
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
			ob.update(numberOfExecutedCommands, numberOfUnexecutedCommands);
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
