package dialogs;

import geometry.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class DlgRectangle extends DlgSurfaceShape {

	private static final long serialVersionUID = 1L;
	private JLabel lblHeight;
	private JTextField txtHeight;
	private JLabel lblWidth;
	private JTextField txtWidth;
	

	public static void main(String[] args) {
		try {
			DlgRectangle dialog = new DlgRectangle();
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DlgRectangle() {
		lblHeight = new JLabel("Height:");
		txtHeight = new JTextField();
		lblWidth = new JLabel("Width:");
		txtWidth = new JTextField();
		addToDlgSurfaceShapeDialog();
	}
	
	private void addToDlgSurfaceShapeDialog() {
		{
			GridBagConstraints gbc_lblHeight = new GridBagConstraints();
			gbc_lblHeight.anchor = GridBagConstraints.EAST;
			gbc_lblHeight.insets = new Insets(0, 0, 5, 5);
			gbc_lblHeight.gridx = 0;
			gbc_lblHeight.gridy = 2;
			getPanel().add(lblHeight, gbc_lblHeight);
		}
		{
			txtHeight = new JTextField();
			GridBagConstraints gbc_txtHeight = new GridBagConstraints();
			gbc_txtHeight.gridwidth = 2;
			gbc_txtHeight.insets = new Insets(0, 0, 5, 5);
			gbc_txtHeight.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtHeight.gridx = 1;
			gbc_txtHeight.gridy = 2;
			getPanel().add(txtHeight, gbc_txtHeight);
			txtHeight.setColumns(10);
			txtHeight.addKeyListener(getInputListener());
		}
		{
			GridBagConstraints gbc_lblWidth = new GridBagConstraints();
			gbc_lblWidth.anchor = GridBagConstraints.EAST;
			gbc_lblWidth.insets = new Insets(0, 0, 5, 5);
			gbc_lblWidth.gridx = 0;
			gbc_lblWidth.gridy = 3;
			getPanel().add(lblWidth, gbc_lblWidth);
		}
		{
			txtWidth = new JTextField();
			GridBagConstraints gbc_txtWidth = new GridBagConstraints();
			gbc_txtWidth.gridwidth = 2;
			gbc_txtWidth.insets = new Insets(0, 0, 5, 5);
			gbc_txtWidth.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtWidth.gridx = 1;
			gbc_txtWidth.gridy = 3;
			getPanel().add(txtWidth, gbc_txtWidth);
			txtWidth.setColumns(10);
			txtWidth.addKeyListener(getInputListener());
		}
	}
	

	@Override
	public boolean areAllFieldsFilled() {
		String xCoordValue = getTxtXCoord().getText();
		String yCoordValue = getTxtYCoord().getText();
		String heightValue = txtHeight.getText();
		String widthValue = txtWidth.getText();

		return (!xCoordValue.isEmpty() && !yCoordValue.isEmpty() && !heightValue.isEmpty() && !widthValue.isEmpty());
	}

	@Override
	public boolean areValuesValid() {
		return getwidthValue() > 0 && getheightValue() > 0 && getTxtXCoord().getText().length() < 4
				&& getTxtYCoord().getText().length() < 4;
	}
	
	private int getheightValue() {
		if (txtHeight.getText().length() > 3)
			return 0;

		return Integer.parseInt(txtHeight.getText());
	}

	private int getwidthValue() {
		if (txtWidth.getText().length() > 3)
			return 0;

		return Integer.parseInt(txtWidth.getText());
	}
	
	public void setCreateDialogFields(Point point, Color edgeColor, Color innerColor) {
		super.setCreateDialogFields(point, edgeColor);
		
		getPnlInnerColor().setBackground(innerColor);
		setInnerColor(innerColor);
	}

	@Override
	public void setModifyDialogFields(Shape shape) {
		if(shape instanceof Rectangle) {
			Rectangle rect = (Rectangle)shape;
			
			int upperLeftX = rect.getUpperLeftPoint().getX();
			getTxtXCoord().setText(String.valueOf(upperLeftX));
			
			int upperLeftY = rect.getUpperLeftPoint().getY();
			getTxtYCoord().setText(String.valueOf(upperLeftY));
			
			String height = String.valueOf(rect.getHeight());
			txtHeight.setText(height);
			
			String width = String.valueOf(rect.getWidth());
			txtWidth.setText(width);
			
			Color edgColor = rect.getColor();
			getPnlEdgeColor().setBackground(edgColor);
			setEdgeColor(edgColor);
			
			Color innerColor = rect.getInnerColor();
			getPnlInnerColor().setBackground(innerColor);
			setInnerColor(innerColor);
		}
		
	}

	@Override
	public Shape getShapeFromDialog() {
		int x = Integer.parseInt(getTxtXCoord().getText());
		int y = Integer.parseInt(getTxtYCoord().getText());
		int height = Integer.parseInt(txtHeight.getText());
		int  width = Integer.parseInt(txtWidth.getText());
		Color edgeColor = getEdgeColor();
		Color innerColor = getInnerColor();
		
		return new Rectangle(new Point(x,y), height, width, edgeColor, innerColor);
		
	}

	public JTextField getTxtHeight() {
		return txtHeight;
	}

	public JTextField getTxtWidth() {
		return txtWidth;
	}
	
}
