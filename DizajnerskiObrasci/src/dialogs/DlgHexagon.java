package dialogs;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JTextField;
import geometry.*;

public class DlgHexagon extends DlgSurfaceShape{

	
	private static final long serialVersionUID = 1L;
	private JLabel lblRadius;
	private JTextField txtRadius;
	

	public static void main(String[] args) {
		try {
			DlgHexagon dialog = new DlgHexagon();
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DlgHexagon() {
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
	
	public void setCreateDialogFields(Point point, Color edgeColor, Color innerColor) {
		super.setCreateDialogFields(point, edgeColor);
		
		getPnlInnerColor().setBackground(innerColor);
		setInnerColor(innerColor);
	}

	@Override
	public void setModifyDialogFields(Shape shape) {
		if(shape instanceof HexagonAdapter) {
			HexagonAdapter hexagon = (HexagonAdapter) shape;
			
			String centerX = String.valueOf(hexagon.getX());
			getTxtXCoord().setText(centerX);
			
			String centerY = String.valueOf(hexagon.getY());
			getTxtYCoord().setText(centerY);
			
			String radius = String.valueOf(hexagon.getRadius());
			txtRadius.setText(radius);
			
			Color edgeColor = hexagon.getColor();
			getPnlEdgeColor().setBackground(edgeColor);
			setEdgeColor(edgeColor);
			
			Color innerColor = hexagon.getInnerColor();
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
		
		return new HexagonAdapter(center, radius, edgeColor, innerColor, true);
	}
	
	public JTextField getTxtRadius() {
		return txtRadius;
	}

}
