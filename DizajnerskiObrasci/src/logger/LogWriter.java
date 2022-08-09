package logger;

import javax.swing.DefaultListModel;

public class LogWriter {

	private DefaultListModel<String> defaultListModel;

	public LogWriter() {
		defaultListModel = new DefaultListModel<>();
	}
	
	public void log(String logLine) {
		defaultListModel.addElement(logLine);
	}
	
	public DefaultListModel<String> getDefaultListModel() {
		return defaultListModel;
	}

	public void setDefaultListModel(DefaultListModel<String> defaultListModel) {
		this.defaultListModel = defaultListModel;
	}
	
	
}
