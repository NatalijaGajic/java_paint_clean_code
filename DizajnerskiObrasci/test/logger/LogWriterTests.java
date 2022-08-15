package logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import logger.LogWriter;

class LogWriterTests {

	private LogWriter logWriter;
	private String logLine;
	private String loggedCommand;
	
	@BeforeEach
	public void setUp() {
		logWriter = new LogWriter();
		logLine = "Added Rectangle:(217,86) W:44, H:44, BC:-16777216, FC:-1";
	}
	
	@Test
	public void testLog_CommandAddedToExecutedCommandsLog() {
		logWriter.log(logLine);
		loggedCommand = logWriter.getExecutedCommandsLog().get(0);
		assertEquals(logLine, loggedCommand);
	}

}
