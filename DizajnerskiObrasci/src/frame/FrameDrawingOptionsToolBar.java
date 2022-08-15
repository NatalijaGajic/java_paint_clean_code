package frame;

import java.awt.event.*;
import javax.swing.*;
import controllers.*;
import observer.*;

public class FrameDrawingOptionsToolBar extends JToolBar implements SelectedShapesObserver, ShapesObserver {

	private static final long serialVersionUID = 1L;
	private final ButtonGroup btnMode = new ButtonGroup();
	private JToggleButton tglbtnDraw;
	private JToggleButton tglbtnSelect;
	private JButton btnModify;
	private JButton btnDelete;
	private JButton btnToBack;
	private JButton btnToFront;
	private JButton btnBringToFront;
	private JButton btnBringToBack;

	private DrawingController drawingController;
	private ButtonsController buttonsController;
	
	public FrameDrawingOptionsToolBar() {
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
	}
	
	public void disableButtons() {
		btnModify.setEnabled(false);
		btnDelete.setEnabled(false);
		btnToBack.setEnabled(false);
		btnToFront.setEnabled(false);
		btnBringToFront.setEnabled(false);
		btnBringToBack.setEnabled(false);
	}
	
	private void addButtonsToButtonGroup() {
		btnMode.add(tglbtnSelect);
		btnMode.add(tglbtnDraw);
	}
	
	
	private void addBtnModifyListener() {
		btnModify.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawingController.modifyShape();
			}
		});
	}
	
	private void addBtnDeleteListener() {
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawingController.deleteShapesIfAccepted();
			}
		});
	}
	
	private void addMovePositionButtonsListeners() {
		btnToBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawingController.moveShapeToBack();
			}
		});
		
		btnToFront.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawingController.moveShapeToFront();
			}
		});
		
		btnBringToBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawingController.bringShapeToBack();
			}
		});
		
		btnBringToFront.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawingController.bringShapeToFront();
			}
		});
	}
	
	
	
	@Override
	public void updateSelectedShapesObserver(int numberOfSelectedShapes) {
		buttonsController.updateSelectedShapesObserverButtons(numberOfSelectedShapes);
	}

	@Override
	public void updateShapesObserver() {
		buttonsController.updateShapesObserverButtons();
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

	public void setDrawingController(DrawingController drawingController) {
		this.drawingController = drawingController;
	}

	public void setButtonsController(ButtonsController buttonsController) {
		this.buttonsController = buttonsController;
	}


	
	


	
	
}
