package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controllers.FileController;

public class FrameMenuBar extends JMenuBar{

	private FileController controller;
	private JMenu fileMenu;
	private JMenu menuOpen;
	private JMenuItem optionOpenLogFile;
	private JMenuItem optionOpenDrawingFile;
	private JMenu menuSave;
	private JMenuItem optionSaveAsLog;
	private JMenuItem optionSaveAsDrawing;
	
	public FrameMenuBar() {

		fileMenu = new JMenu("       File      ");
		
		menuOpen = new JMenu("   Open   ");
		optionOpenLogFile = new JMenuItem(" Open Log File ");
		optionOpenDrawingFile = new JMenuItem(" Open Drawing File ");
		addOpenMenuListeners();
		
		menuSave = new JMenu("   Save   ");
		optionSaveAsLog = new JMenuItem(" Save As Log File ");
		optionSaveAsDrawing = new JMenuItem(" Save As Drawing File ");
		addSaveMenuListeners();
		
		buildMenuBar();
	}
	
	private void buildMenuBar() {
		add(fileMenu);
	
		fileMenu.setVisible(true);
		fileMenu.add(menuSave);
		fileMenu.add(menuOpen);
		
		menuOpen.add(optionOpenLogFile);
		menuOpen.add(optionOpenDrawingFile);
		
		menuSave.add(optionSaveAsLog);
		menuSave.add(optionSaveAsDrawing);

	}
	
	public void addOpenMenuListeners() {
		optionSaveAsLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.saveLog();
			}
		});
		optionSaveAsDrawing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.saveDrawing();
			}
		});
	}
	
	public void addSaveMenuListeners() {
		optionOpenLogFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.loadLog();
			}
		});

		optionOpenDrawingFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.loadDrawing();
			}
		});
	}

	public FileController getController() {
		return controller;
	}

	public void setController(FileController controller) {
		this.controller = controller;
	}

	public JMenuItem getOptionOpenLogFile() {
		return optionOpenLogFile;
	}

	public JMenuItem getOptionOpenDrawingFile() {
		return optionOpenDrawingFile;
	}

	public JMenuItem getOptionSaveAsLog() {
		return optionSaveAsLog;
	}

	public JMenuItem getOptionSaveAsDrawing() {
		return optionSaveAsDrawing;
	}
	
	
	
}