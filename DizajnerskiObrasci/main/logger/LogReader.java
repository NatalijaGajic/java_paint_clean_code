package logger;

import java.util.*;
import observer.*;

public class LogReader implements LogReaderSubject{

	private Queue<String> commandsToBeExecutedLog;
	private ArrayList<LogReaderObserver> observers;
	
	public LogReader() {
		commandsToBeExecutedLog = new LinkedList<String>();
		observers = new ArrayList<LogReaderObserver>();
	}
	
	public void addCommandToCommandsToBeExecutedLog(String command) {
		commandsToBeExecutedLog.add(command);
		notifyObservers();
	}
	
	public String readCommandFromLog(){
		String logLine = commandsToBeExecutedLog.poll();
		notifyObservers();
		return logLine;
	}
	
	public void clearLog() {
		commandsToBeExecutedLog.clear();
		notifyObservers();
	}

	@Override
	public void registerObserver(LogReaderObserver o) {
		observers.add(o);
		
	}

	@Override
	public void unregisterObserver(LogReaderObserver o) {
		observers.remove(o);
		
	}

	@Override
	public void notifyObservers() {
		int numberOfCommandsToBeExecuted = commandsToBeExecutedLog.size();
		Iterator<LogReaderObserver> it = observers.iterator();
		while(it.hasNext()) {
			LogReaderObserver ob = it.next();
			ob.updateLogReaderObserver(numberOfCommandsToBeExecuted);
		}
		
	}
	
	public Queue<String> getCommandsToBeExecutedLog() {
		return commandsToBeExecutedLog;
	}
	
}
