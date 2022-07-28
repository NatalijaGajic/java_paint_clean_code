package dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Circle;
import geometry.Point;
import geometry.Shape;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgCircle extends DlgSurfaceShape {

	private JLabel lblRadius;
	private JTextField txtRadius;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgCircle dialog = new DlgCircle();
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgCircle() {
		lblRadius = new JLabel("Radius:");
		txtRadius = new JTextField();
		addToDlgSurfaceShapeDialog();
	}
	
	private void addToDlgSurfaceShapeDialog() {
		{
			GridBagConstraints gbc_lblRadius = new GridBagConstraints();
			gbc_lblRadius.anchor = GridBagConstraints.EAST;
			gbc_lblRadius.insets = new Insets(0, 0, 5, 5);
			gbc_lblRadius.gridx = 0;
			gbc_lblRadius.gridy = 2;
			getPanel().add(lblRadius, gbc_lblRadius);
		}
		{
			GridBagConstraints gbc_txtRadius = new GridBagConstraints();
			gbc_txtRadius.gridwidth = 2;
			gbc_txtRadius.insets = new Insets(0, 0, 5, 5);
			gbc_txtRadius.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtRadius.gridx = 1;
			gbc_txtRadius.gridy = 2;
			getPanel().add(txtRadius, gbc_txtRadius);
			txtRadius.setColumns(10);
			txtRadius.addKeyListener(getInputListener());
		}
	}

	@Override
	public boolean areAllFieldsFilled() {
		String radiusValue = txtRadius.getText();
		String xCoordValue = getTxtXCoord().getText();
		String yCoordValue = getTxtYCoord().getText();

		if (xCoordValue.isEmpty() || yCoordValue.isEmpty() || radiusValue.isEmpty())
			return false;
		return true;
	}

	@Override
	public boolean areValuesValid() {
		int radius = Integer.parseInt(txtRadius.getText());
		return getTxtXCoord().getText().length() < 4 && getTxtYCoord().getText().length() < 4 
				&& txtRadius.getText().length() < 4 && radius > 0;
	}

	@Override
	public void setModifyDialogFields(Shape shape) {
		if(shape instanceof Circle) {
			Circle circle = (Circle) shape;
			
			String centerX = String.valueOf(circle.getCenter().getX());
			getTxtXCoord().setText(centerX);
			
			String centerY = String.valueOf(circle.getCenter().getY());
			getTxtYCoord().setText(centerY);
			
			String radius = String.valueOf(circle.getRadius());
			txtRadius.setText(radius);
			
			Color edgeColor = circle.getColor();
			getPnlEdgeColor().setBackground(edgeColor);
			setEdgeColor(edgeColor);
			
			Color innerColor = circle.getInnerColor();
			getPnlInnerColor().setBackground(innerColor);
			setInnerColor(innerColor);
					
		}
		
	}

	@Override
	public Shape getShapeFromDialog() {
			
		int centerX = Integer.parseInt(getTxtXCoord().getText());
		int centerY = Integer.parseInt(getTxtYCoord().getText());
		Point center = new Point(centerX, centerY);
		
		int radius = Integer.parseInt(txtRadius.getText());
		Color edgeColor = getEdgeColor();
		Color innerColor = getInnerColor();
		
		return new Circle(center, radius, edgeColor, innerColor);
		
	}

	public JTextField getTxtRadius() {
		return txtRadius;
	}
	
	
	
	

}
