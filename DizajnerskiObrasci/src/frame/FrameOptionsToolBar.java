package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import controllers.DrawingController;

public class FrameOptionsToolBar extends JToolBar{

	private JToggleButton tglbtnDraw;
	private JToggleButton tglbtnSelect;
	private JButton btnModify;
	private JButton btnDelete;
	private JButton btnToBack;
	private JButton btnToFront;
	private JButton btnBringToFront;
	private JButton btnBringToBack;
	private JButton btnUndo;
	private JButton btnRedo;
	private JButton btnExecuteLog;
	private final ButtonGroup btnMode = new ButtonGroup();
	
	private DrawingController controller;
	
	public FrameOptionsToolBar() {
		setOrientation(SwingConstants.VERTICAL);
		createButtons();
		addBtnModifyListener();
		addBtnDeleteListener();
		addButtonsToToolBar();
		addButtonsToButtonGroup();
	}
	
	private void createButtons() {
		tglbtnDraw = new JToggleButton("         Draw          ");
		tglbtnSelect = new JToggleButton("        Select         ");
		btnModify = new JButton("         Modify        ");
		btnDelete = new JButton("         Delete        ");
		btnToBack = new JButton("      To Back        ");
		btnToFront = new JButton("      To Front        ");
		btnBringToFront = new JButton("  Bring To Front ");
		btnBringToBack = new JButton("  Bring To Back ");
		btnUndo = new JButton("          Undo          ");
		btnRedo = new JButton("          Redo          ");
		btnExecuteLog = new JButton("   Execute Log   ");
	}
	
	private void addButtonsToToolBar() {
		add(tglbtnDraw);
		add(tglbtnSelect);
		add(btnModify);
		add(btnDelete);
		add(btnToBack);
		add(btnToFront);
		add(btnBringToBack);
		add(btnBringToFront);
		add(btnUndo);
		add(btnRedo);
		add(btnExecuteLog);
	}
	
	
	private void addButtonsToButtonGroup() {
		btnMode.add(tglbtnSelect);
		btnMode.add(tglbtnDraw);
	}
	
	
	private void addBtnModifyListener() {
		btnModify.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.modifyShape();
			}
		});
	}
	
	private void addBtnDeleteListener() {
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.deleteShapes();
			}
		});
	}
	
	public boolean isTglBtnSelectSelected() {
		return tglbtnSelect.isSelected();
	}
	
	public boolean isTglBtnDrawSelected() {
		return tglbtnDraw.isSelected();
	}

	public JButton getBtnModify() {
		return btnModify;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}
	
	

}
