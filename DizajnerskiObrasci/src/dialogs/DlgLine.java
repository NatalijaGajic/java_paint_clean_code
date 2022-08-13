package dialogs;


import java.awt.Color;
import geometry.*;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;

public class DlgLine extends DlgShape{

	private static final long serialVersionUID = 1L;
	private JLabel lblEndPointXCoord;
	private JTextField txtEndPointXCoord;
	private JLabel lblEndPointYCoord;
	private JTextField txtEndPointYCoord;

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
		addToDlgSurfaceShapeDialog();
	}
	
	private void addToDlgSurfaceShapeDialog() {
		
		{
			GridBagConstraints gbc_lblEndPointXCoord = new GridBagConstraints();
			gbc_lblEndPointXCoord.anchor = GridBagConstraints.EAST;
			gbc_lblEndPointXCoord.insets = new Insets(0, 0, 5, 5);
			gbc_lblEndPointXCoord.gridx = 0;
			gbc_lblEndPointXCoord.gridy = 2;
			getPanel().add(lblEndPointXCoord, gbc_lblEndPointXCoord);
		}
		{
			GridBagConstraints gbc_txtEndPointXCoord = new GridBagConstraints();
			gbc_txtEndPointXCoord.gridwidth = 2;
			gbc_txtEndPointXCoord.insets = new Insets(0, 0, 5, 5);
			gbc_txtEndPointXCoord.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtEndPointXCoord.gridx = 1;
			gbc_txtEndPointXCoord.gridy = 2;
			getPanel().add(txtEndPointXCoord, gbc_txtEndPointXCoord);
			txtEndPointXCoord.setColumns(10);
			txtEndPointXCoord.addKeyListener(getInputListener());
		}
		{
			GridBagConstraints gbc_lblEndPointYCoord = new GridBagConstraints();
			gbc_lblEndPointYCoord.anchor = GridBagConstraints.EAST;
			gbc_lblEndPointYCoord.insets = new Insets(0, 0, 5, 5);
			gbc_lblEndPointYCoord.gridx = 0;
			gbc_lblEndPointYCoord.gridy = 3;
			getPanel().add(lblEndPointYCoord, gbc_lblEndPointYCoord);
		}
		{
			GridBagConstraints gbc_txtEndPointYCoord = new GridBagConstraints();
			gbc_txtEndPointYCoord.gridwidth = 2;
			gbc_txtEndPointYCoord.insets = new Insets(0, 0, 5, 5);
			gbc_txtEndPointYCoord.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtEndPointYCoord.gridx = 1;
			gbc_txtEndPointYCoord.gridy = 3;
			getPanel().add(txtEndPointYCoord, gbc_txtEndPointYCoord);
			txtEndPointYCoord.setColumns(10);
			txtEndPointYCoord.addKeyListener(getInputListener());
		}
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
	
	public void setCreateDialogFields(Point startPoint, Point endPoint, Color edgeColor) {
		super.setCreateDialogFields(startPoint, edgeColor);
		
		String endXString = String.valueOf(endPoint.getX());
		this.txtEndPointXCoord.setText(endXString);
		
		String endYString = String.valueOf(endPoint.getY());
		this.txtEndPointYCoord.setText(endYString);
		
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
			
			String xCoord = String.valueOf(startPoint.getX());
			getTxtXCoord().setText(xCoord);
			
			String yCoord = String.valueOf(startPoint.getY());
			getTxtYCoord().setText(yCoord);	
			
			String endXString = String.valueOf(endPoint.getX());
			this.txtEndPointXCoord.setText(endXString);
			
			String endYString = String.valueOf(endPoint.getY());
			this.txtEndPointYCoord.setText(endYString);
			
			Color edgeColor = line.getColor();
			getPnlEdgeColor().setBackground(edgeColor);
			setEdgeColor(edgeColor);
		}
		
	}
	
	@Override
	public Shape getShapeFromDialog() {
		
		int startX = Integer.parseInt(getTxtXCoord().getText());
		int startY = Integer.parseInt(getTxtYCoord().getText());
		Point startPoint = new Point(startX,startY);
		
		int endX = Integer.parseInt(getTxtEndPointXCoord().getText());
		int endY = Integer.parseInt(getTxtEndPointYCoord().getText());
		Point endPoint = new Point(endX,endY);
		
		Color edgeColor = getEdgeColor();
		
		return new Line(startPoint, endPoint, true, edgeColor);
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
