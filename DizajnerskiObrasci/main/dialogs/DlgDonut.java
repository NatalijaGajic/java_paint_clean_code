package dialogs;

import geometry.*;
import java.awt.Color;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;

public class DlgDonut extends DlgSurfaceShape {

	private static final long serialVersionUID = 1L;
	private JLabel lblRadius;
	private JLabel lblInnerRadius;
	private JTextField txtRad;
	private JTextField txtIRad;


	public static void main(String[] args) {
		try {
			DlgDonut dialog = new DlgDonut();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public DlgDonut() {
		
		lblRadius = new JLabel("Radius:");
		txtRad = new JTextField();
		lblInnerRadius = new JLabel("Inner Radius:");
		txtIRad = new JTextField();
		addToDlgSurfaceShape();
	}
	
	public void addToDlgSurfaceShape() {
		{
			GridBagConstraints gbc_lblRadius = new GridBagConstraints();
			gbc_lblRadius.anchor = GridBagConstraints.EAST;
			gbc_lblRadius.insets = new Insets(0, 0, 5, 5);
			gbc_lblRadius.gridx = 0;
			gbc_lblRadius.gridy = 2;
			getPanel().add(lblRadius, gbc_lblRadius);
		}
		{
			
			GridBagConstraints gbc_txtRad = new GridBagConstraints();
			gbc_txtRad.gridwidth = 2;
			gbc_txtRad.insets = new Insets(0, 0, 5, 5);
			gbc_txtRad.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtRad.gridx = 1;
			gbc_txtRad.gridy = 2;
			getPanel().add(txtRad, gbc_txtRad);
			txtRad.setColumns(10);
			txtRad.addKeyListener(getInputListener());
		}
		{

			GridBagConstraints gbc_lblInnerRadius = new GridBagConstraints();
			gbc_lblInnerRadius.anchor = GridBagConstraints.EAST;
			gbc_lblInnerRadius.insets = new Insets(0, 0, 5, 5);
			gbc_lblInnerRadius.gridx = 0;
			gbc_lblInnerRadius.gridy = 3;
			getPanel().add(lblInnerRadius, gbc_lblInnerRadius);
		}
		{
			txtIRad = new JTextField();
			GridBagConstraints gbc_txtIRad = new GridBagConstraints();
			gbc_txtIRad.gridwidth = 2;
			gbc_txtIRad.insets = new Insets(0, 0, 5, 5);
			gbc_txtIRad.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtIRad.gridx = 1;
			gbc_txtIRad.gridy = 3;
			getPanel().add(txtIRad, gbc_txtIRad);
			txtIRad.setColumns(10);
			txtIRad.addKeyListener(getInputListener());
		}
	}

	@Override
	public boolean areAllFieldsFilled() {
		String innerRadiusValue = txtIRad.getText();
		String radiusValue = txtRad.getText();
		String xCoordValue = getTxtXCoord().getText();
		String yCoordValue = getTxtYCoord().getText();

		if (xCoordValue.isEmpty() || yCoordValue.isEmpty() || innerRadiusValue.isEmpty()
				|| radiusValue.isEmpty())
			return false;
		return true;
	}

	@Override
	public boolean areValuesValid() {
		return getRadiusValue() > 0 && getInnerRadiusValue() > 0 && getInnerRadiusValue() < getRadiusValue()
				&& getTxtXCoord().getText().length() < 4 && getTxtYCoord().getText().length() < 4;
	}
	
	private int getRadiusValue() {
		if (txtRad.getText().length() > 3)
			return 0;

		return Integer.parseInt(txtRad.getText());
	}

	private int getInnerRadiusValue() {
		if (txtIRad.getText().length() > 3)
			return 0;

		return Integer.parseInt(txtIRad.getText());
	}
	
	public void setCreateDialogFields(Point point, Color edgeColor, Color innerColor) {
		super.setCreateDialogFields(point, edgeColor);
		
		getPnlInnerColor().setBackground(innerColor);
		setInnerColor(innerColor);
	}

	@Override
	public void setModifyDialogFields(Shape shape) {
		if(shape instanceof Donut) {
			Donut donut = (Donut) shape;
			Point center = donut.getCenter();
			
			String xCoord = String.valueOf(center.getX());
			getTxtXCoord().setText(xCoord);
			
			String yCoord = String.valueOf(center.getY());
			getTxtYCoord().setText(yCoord);	
			
			String innerRadius = String.valueOf(donut.getInnerRadius());
			this.txtIRad.setText(innerRadius);
			
			String radius = String.valueOf(donut.getRadius());
			this.txtRad.setText(radius);
			
			Color edgeColor = donut.getColor();
			getPnlEdgeColor().setBackground(edgeColor);
			setEdgeColor(edgeColor);
			
			Color innerColor = donut.getColor();
			getPnlInnerColor().setBackground(innerColor);
			setInnerColor(innerColor);
		}
		
	}

	@Override
	public Shape getShapeFromDialog() {
		int x = Integer.parseInt(getTxtXCoord().getText());
		int y = Integer.parseInt(getTxtYCoord().getText());
		int radius = Integer.parseInt(txtRad.getText());
		int innerRadius = Integer.parseInt(txtIRad.getText());
		Color edgeColor = getEdgeColor();
		Color innerColor = getInnerColor();
		
		return new Donut(new Point(x,y), radius, innerRadius, edgeColor, innerColor);
	}

	public JTextField getTxtRad() {
		return txtRad;
	}

	public JTextField getTxtIRad() {
		return txtIRad;
	}
	
}
