package dialogs;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;

import geometry.Point;

public abstract class DlgSurfaceShape extends DlgShape{

	private Color fillColor;
	private JButton btnFillColor;
	private TextField txtFillColor;

	public DlgSurfaceShape() {
		fillColor = Color.WHITE;
		txtFillColor = new TextField();
		btnFillColor = new JButton("Fill color");
		addBtnFillColorListener();
		addToDialog();
	}
	
	private void addToDialog() {
		GridBagConstraints gbc_btnFillColor = new GridBagConstraints();
		gbc_btnFillColor.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnFillColor.insets = new Insets(0, 0, 5, 5);
		gbc_btnFillColor.gridx = 1;
		gbc_btnFillColor.gridy = 3;
		getContentPanel().add(btnFillColor, gbc_btnFillColor);
		
		
		txtFillColor.setEditable(false);
		txtFillColor.setBackground(Color.WHITE);
		GridBagConstraints gbc_txtFillColor = new GridBagConstraints();
		gbc_txtFillColor.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFillColor.insets = new Insets(0, 0, 5, 0);
		gbc_txtFillColor.gridx = 5;
		gbc_txtFillColor.gridy = 3;
		getContentPanel().add(txtFillColor, gbc_txtFillColor);
		txtFillColor.setColumns(12);
	}
	

	private void addBtnFillColorListener() {
		btnFillColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				fillColor = JColorChooser.showDialog(getContentPane(), "Choose fill color", fillColor);
				txtFillColor.setBackground(fillColor);
			}
		});
	}
	
	

	public JButton getBtnFillColor() {
		return btnFillColor;
	}

	public Color getFillColor() {
		return fillColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}

	public TextField getTxtFillColor() {
		return txtFillColor;
	}
	
	
}
