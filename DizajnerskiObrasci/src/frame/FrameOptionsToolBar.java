package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import controllers.DrawingController;
import observer.SelectedShapesObserver;
import observer.ShapesObserver;

public class FrameOptionsToolBar extends JToolBar implements SelectedShapesObserver, ShapesObserver {

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
		addMovePositionButtonsListeners();
		addButtonsToToolBar();
		addButtonsToButtonGroup();
		disableButtons();
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
	
	private void disableButtons() {
		btnModify.setEnabled(false);
		btnDelete.setEnabled(false);
		btnToBack.setEnabled(false);
		btnToFront.setEnabled(false);
		btnBringToFront.setEnabled(false);
		btnBringToBack.setEnabled(false);
		btnUndo.setEnabled(false);
		btnRedo.setEnabled(false);
		btnExecuteLog.setEnabled(false);
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
	
	private void addMovePositionButtonsListeners() {
		btnToBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.moveShapeToBack();
			}
		});
		
		btnToFront.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.moveShapeToFront();
			}
		});
	}
	
	public boolean isTglBtnSelectSelected() {
		return tglbtnSelect.isSelected();
	}
	
	public boolean isTglBtnDrawSelected() {
		return tglbtnDraw.isSelected();
	}
	
	public boolean isBtnDeleteEnabled() {
		return btnDelete.isEnabled();
	}
	
	public boolean isBtnModifyEnabled() {
		return btnModify.isEnabled();
	}
	
	public boolean isBtnBringToFrontEnabled() {
		return btnBringToFront.isEnabled();
	}
	public boolean isBtnBringToBackEnabled() {
		return btnBringToBack.isEnabled();
	}
	
	public boolean isBtnToFrontEnabled() {
		return btnToFront.isEnabled();
	}
	public boolean isBtnToBackEnabled() {
		return btnToBack.isEnabled();
	}

	public JButton getBtnModify() {
		return btnModify;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}
	
	public JButton getBtnToBack() {
		return btnToBack;
	}

	public JButton getBtnToFront() {
		return btnToFront;
	}

	public JButton getBtnBringToFront() {
		return btnBringToFront;
	}

	public JButton getBtnBringToBack() {
		return btnBringToBack;
	}

	public void setController(DrawingController controller) {
		this.controller = controller;
	}

	@Override
	public void update(int numberOfSelectedShapes) {
		controller.updateObservableButtons(numberOfSelectedShapes);
	}

	@Override
	public void update() {
		controller.updateObservablePositionButtons();
		
	}
	

}
