package dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import geometry.SurfaceShape;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgRectangle extends DlgSurfaceShape {

	private JLabel lblHeight;
	private JTextField txtHeight;
	private JLabel lblWidth;
	private JTextField txtWidth;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgRectangle dialog = new DlgRectangle();
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgRectangle() {
		lblHeight = new JLabel("Height:");
		txtHeight = new JTextField();
		lblWidth = new JLabel("Width:");
		txtWidth = new JTextField();
		addToDialog();
	}
	
	private void addToDialog() {
		GridBagConstraints gbc_lblHeight = new GridBagConstraints();
		gbc_lblHeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblHeight.gridx = 1;
		gbc_lblHeight.gridy = 4;
		getContentPanel().add(lblHeight, gbc_lblHeight);
		
		GridBagConstraints gbc_txtHeight = new GridBagConstraints();
		gbc_txtHeight.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtHeight.insets = new Insets(0, 0, 5, 5);
		gbc_txtHeight.gridx = 5;
		gbc_txtHeight.gridy = 4;
		getContentPanel().add(txtHeight, gbc_txtHeight);
		txtHeight.setColumns(12);
		txtHeight.addKeyListener(getInputListener());
		
		GridBagConstraints gbc_lblWidth = new GridBagConstraints();
		gbc_lblWidth.insets = new Insets(0, 0, 0, 5);
		gbc_lblWidth.gridx = 1;
		gbc_lblWidth.gridy = 5;
		getContentPanel().add(lblWidth, gbc_lblWidth);
		
		GridBagConstraints gbc_txtWidth = new GridBagConstraints();
		gbc_txtWidth.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtWidth.insets = new Insets(0, 0, 0, 5);
		gbc_txtWidth.gridx = 5;
		gbc_txtWidth.gridy = 5;
		getContentPanel().add(txtWidth, gbc_txtWidth);
		txtWidth.setColumns(12);
		txtWidth.addKeyListener(getInputListener());
	}
	

	@Override
	public boolean areAllFieldsFilled() {
		String xCoordValue = getTxtXCoord().getText();
		String yCoordValue = getTxtYCoord().getText();
		String heightValue = txtHeight.getText();
		String widthValue = txtWidth.getText();

		if (xCoordValue.isEmpty() || yCoordValue.isEmpty() || heightValue.isEmpty() || widthValue.isEmpty())
			return false;
		return true;
	}

	@Override
	public boolean areValuesValid() {
		return getwidthValue() > 0 && getheightValue() > 0 && getTxtXCoord().getText().length() < 4
				&& getTxtYCoord().getText().length() < 4;
	}
	
	public int getheightValue() {
		if (txtHeight.getText().length() > 3)
			return 0;

		return Integer.parseInt(txtHeight.getText());
	}

	public int getwidthValue() {
		if (txtWidth.getText().length() > 3)
			return 0;

		return Integer.parseInt(txtWidth.getText());
	}

	@Override
	public void setModifyDialogFields(Shape shape) {
		if(shape instanceof Rectangle) {
			Rectangle rect = (Rectangle)shape;
			
			int upperLeftX = rect.getUpperLeftPoint().getX();
			int upperLeftY = rect.getUpperLeftPoint().getY();
			getTxtXCoord().setText(String.valueOf(upperLeftX));
			getTxtXCoord().setText(String.valueOf(upperLeftY));
			
			int height = rect.getHeight();
			txtHeight.setText(String.valueOf(height));
			
			int width = rect.getWidth();
			txtWidth.setText(String.valueOf(width));
			
			Color borderColor = rect.getColor();
			getTxtBorderColor().setBackground(borderColor);
			setBorderColor(borderColor);
			
			Color innerColor = rect.getInnerColor();
			getTxtFillColor().setBackground(innerColor);
			setFillColor(innerColor);
		}
		
	}

	@Override
	public Shape getShapeFromDialog() {
		int x = Integer.parseInt(getTxtXCoord().getText());
		int y = Integer.parseInt(getTxtYCoord().getText());
		int height = Integer.parseInt(txtHeight.getText());
		int  width = Integer.parseInt(txtWidth.getText());
		Color borderColor = getBorderColor();
		Color fillColor = getFillColor();
		
		return new Rectangle(new Point(x,y), height, width, borderColor, fillColor);
		
	}

	public JTextField getTxtHeight() {
		return txtHeight;
	}

	public JTextField getTxtWidth() {
		return txtWidth;
	}

	
	
}
