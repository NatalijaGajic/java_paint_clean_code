package frame;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

import controllers.DrawingController;

public class FrameColorToolBar extends JToolBar{
	
	private static final long serialVersionUID = 1L;
	private JPanel pnlActiveEdgeColor;
	private JPanel pnlActiveInnerColor;
	private JButton btnActiveEdgeColor;
	private JButton btnActiveInnerColor;
	private DrawingController drawingController;

	
	public FrameColorToolBar() {
		createToggleButtons();
		createPanels();
		addBtnActiveEdgeColorListener();
		addBtnActiveInnerColorListener();
		addToggleButtonsAndPanelsToToolBar();
	}
	
	private void createPanels() {
		pnlActiveEdgeColor = new JPanel();
		pnlActiveEdgeColor.setBackground(Color.BLACK);
		pnlActiveInnerColor = new JPanel();
		pnlActiveInnerColor.setBackground(Color.WHITE);
	}
	
	private void createToggleButtons() {
		btnActiveEdgeColor = new JButton("Active Edge Color");
		btnActiveInnerColor = new JButton("Active Inner Color");
	}
	
	private void addToggleButtonsAndPanelsToToolBar() {
		add(btnActiveEdgeColor);
		add(pnlActiveEdgeColor);
		add(btnActiveInnerColor);
		add(pnlActiveInnerColor);
	}
	
	private void addBtnActiveEdgeColorListener() {
		btnActiveEdgeColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawingController.setActiveEdgeColor();
			}
		});
	}
	
	private void addBtnActiveInnerColorListener() {
		btnActiveInnerColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawingController.setActiveInnerColor();
			}
		});
	}

	public JPanel getPnlActiveEdgeColor() {
		return pnlActiveEdgeColor;
	}

	public JPanel getPnlActiveInnerColor() {
		return pnlActiveInnerColor;
	}

	public JButton getBtnActiveEdgeColor() {
		return btnActiveEdgeColor;
	}

	public JButton getBtnActiveInnerColor() {
		return btnActiveInnerColor;
	}

	public void setDrawingController(DrawingController drawingController) {
		this.drawingController = drawingController;
	}
	
	
	
	
	
}