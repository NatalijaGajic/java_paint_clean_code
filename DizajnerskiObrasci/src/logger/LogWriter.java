package logger;

import javax.swing.DefaultListModel;

public class LogWriter {

	private DefaultListModel<String> executedCommandsLog;

	public LogWriter() {
		executedCommandsLog = new DefaultListModel<>();
	}
	
	public void log(String logLine) {
		executedCommandsLog.addElement(logLine);
	}

	public void setExecutedCommandsLog(DefaultListModel<String> executedCommandsLog) {
		this.executedCommandsLog = executedCommandsLog;
	}

	public DefaultListModel<String> getExecutedCommandsLog() {
		return executedCommandsLog;
	}
	
}
