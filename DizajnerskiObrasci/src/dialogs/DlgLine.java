package dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Line;
import geometry.Point;
import geometry.Shape;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgLine extends DlgShape{

	private JLabel lblEndPointXCoord;
	private JTextField txtEndPointXCoord;
	private JLabel lblEndPointYCoord;
	private JTextField txtEndPointYCoord;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgLine dialog = new DlgLine();
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DlgLine(){
		lblEndPointXCoord = new JLabel("X coordinate of end point:");
		txtEndPointXCoord = new JTextField();
		lblEndPointYCoord = new JLabel("Y coordinate of end point:");
		txtEndPointYCoord = new JTextField();
		addToDialog();
	}
	
	private void addToDialog() {
		GridBagConstraints gbc_lblEndPointXCoord = new GridBagConstraints();
		gbc_lblEndPointXCoord.insets = new Insets(0, 0, 5, 5);
		gbc_lblEndPointXCoord.gridx = 1;
		gbc_lblEndPointXCoord.gridy = 3;
		getContentPanel().add(lblEndPointXCoord, gbc_lblEndPointXCoord);
		
		GridBagConstraints gbc_txtEndPointXCoord = new GridBagConstraints();
		gbc_txtEndPointXCoord.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEndPointXCoord.insets = new Insets(0, 0, 5, 5);
		gbc_txtEndPointXCoord.gridx = 5;
		gbc_txtEndPointXCoord.gridy = 3;
		getContentPanel().add(txtEndPointXCoord, gbc_txtEndPointXCoord);
		txtEndPointXCoord.setColumns(12);
		txtEndPointXCoord.addKeyListener(getInputListener());
		
		GridBagConstraints gbc_lblEndPointYCoord = new GridBagConstraints();
		gbc_lblEndPointYCoord.insets = new Insets(0, 0, 0, 5);
		gbc_lblEndPointYCoord.gridx = 1;
		gbc_lblEndPointYCoord.gridy = 4;
		getContentPanel().add(lblEndPointYCoord, gbc_lblEndPointYCoord);
		
		GridBagConstraints gbc_txtEndPointYCoord = new GridBagConstraints();
		gbc_txtEndPointYCoord.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEndPointYCoord.insets = new Insets(0, 0, 0, 5);
		gbc_txtEndPointYCoord.gridx = 5;
		gbc_txtEndPointYCoord.gridy = 4;
		getContentPanel().add(txtEndPointYCoord, gbc_txtEndPointYCoord);
		txtEndPointYCoord.setColumns(12);
		txtEndPointYCoord.addKeyListener(getInputListener());
	}
	

	@Override
	public boolean areAllFieldsFilled() {
		String xCoorfStartPointValue = getTxtXCoord().getText();
		String yCoordOfStartPointValue = getTxtYCoord().getText();
		String xCoordOfEndPointValue = txtEndPointXCoord.getText();
		String yCoordOfEndPointValue = txtEndPointYCoord.getText();

		if (xCoorfStartPointValue.isEmpty() || yCoordOfStartPointValue.isEmpty()
				|| xCoordOfEndPointValue.isEmpty() || yCoordOfEndPointValue.isEmpty())
			return false;
		return true;
	}

	@Override
	public boolean areValuesValid() {
		return getTxtXCoord().getText().length() < 4 && getTxtYCoord().getText().length() < 4 && txtEndPointXCoord.getText().length() < 4 && txtEndPointYCoord.getText().length() < 4;
	}

	@Override
	public void setModifyDialogFields(Shape shape) {
		if(shape instanceof Line) {
			Line line = (Line) shape;
			Point startPoint = line.getStartPoint();
			Point endPoint = line.getEndPoint();

			
			setCreateDialogFields(startPoint);
			
			int endX = endPoint.getX();
			int endY = endPoint.getY();
			String endXString = String.valueOf(endX);
			String endYString = String.valueOf(endY);
			this.txtEndPointXCoord.setText(endXString);
			this.txtEndPointYCoord.setText(endYString);
			
			Color borderColor = line.getColor();
			getTxtBorderColor().setBackground(borderColor);
		}
		
	}
	

	@Override
	public Shape getShapeFromDialog() {
		
		String trimedStartXCoordValue = getTxtXCoord().getText().trim();
		String trimedStartYCoordValue = getTxtYCoord().getText().trim();
		int startX = Integer.parseInt(trimedStartXCoordValue);
		int startY = Integer.parseInt(trimedStartYCoordValue);
		Point startPoint = new Point(startX,startY);
		
		String trimedEndXCoordValue = getTxtXCoord().getText().trim();
		String trimedEndYCoordValue = getTxtYCoord().getText().trim();
		int endX = Integer.parseInt(trimedEndXCoordValue);
		int endY = Integer.parseInt(trimedEndYCoordValue);
		Point endPoint = new Point(endX,endY);
		
		Color borderColor = getBorderColor();
		Line line = new Line(startPoint, endPoint, borderColor);
		return line;
	}

	public JTextField getTxtEndPointXCoord() {
		return txtEndPointXCoord;
	}

	public void setTxtEndPointXCoord(JTextField txtEndPointXCoord) {
		this.txtEndPointXCoord = txtEndPointXCoord;
	}

	public JTextField getTxtEndPointYCoord() {
		return txtEndPointYCoord;
	}

	public void setTxtEndPointYCoord(JTextField txtEndPointYCoord) {
		this.txtEndPointYCoord = txtEndPointYCoord;
	}
	
	

}
