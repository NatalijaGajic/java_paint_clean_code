package frame;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class FrameMenuBar extends JMenuBar{

	private static final long serialVersionUID = 1L;
	private JMenu fileMenu;

	private JMenu menuOpen;
	private JMenuItem optionOpenLogFile;
	private JMenuItem optionOpenDrawingFile;

	private JMenu menuSave;
	private JMenuItem optionSaveAsLog;
	private JMenuItem optionSaveAsDrawing;

	private JMenu menuNew;
	private JMenuItem optionNewDraw;

	private JMenu menuHelp;
	private JMenuItem optionShowAppDetails;
	
	public FrameMenuBar() {

		fileMenu = new JMenu("File");
		add(fileMenu);
		fileMenu.setVisible(true);

		menuOpen = new JMenu("   Open   ");
		fileMenu.add(menuOpen);
		optionOpenLogFile = new JMenuItem(" Open Log File ");
		menuOpen.add(optionOpenLogFile);
		optionOpenDrawingFile = new JMenuItem(" Open Drawing File ");
		menuOpen.add(optionOpenDrawingFile);

		menuSave = new JMenu("   Save   ");
		fileMenu.add(menuSave);
		optionSaveAsLog = new JMenuItem(" Save As Log File ");
		menuSave.add(optionSaveAsLog);
		optionSaveAsDrawing = new JMenuItem(" Save As Drawing File ");
		menuSave.add(optionSaveAsDrawing);

		menuNew = new JMenu("New");
		add(menuNew);
		optionNewDraw = new JMenuItem("New Drawing");
		menuNew.add(optionNewDraw);
	}
}