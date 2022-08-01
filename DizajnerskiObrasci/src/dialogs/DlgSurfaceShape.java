package dialogs;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;


public abstract class DlgSurfaceShape extends DlgShape{

	private JPanel pnlInnerColor;
	private JLabel lblInnerColor;
	private JButton btnInnerColor;
	private Color innerColor;
	
	
	public DlgSurfaceShape() {
		
		pnlInnerColor = new JPanel();
		lblInnerColor = new JLabel("Inner Color:");
		btnInnerColor = new JButton("Choose color");
		addBtnInnerColorListener();
		addToShapeDialog();
	}

	

	public void addToShapeDialog() {
		{
			GridBagConstraints gbc_lblInnerColor = new GridBagConstraints();
			gbc_lblInnerColor.anchor = GridBagConstraints.EAST;
			gbc_lblInnerColor.insets = new Insets(0, 0, 0, 5);
			gbc_lblInnerColor.gridx = 0;
			gbc_lblInnerColor.gridy = 5;
			getPanel().add(lblInnerColor, gbc_lblInnerColor);
		}
		{
			
			GridBagConstraints gbc_btnInnerColor = new GridBagConstraints();
			gbc_btnInnerColor.insets = new Insets(0, 0, 0, 5);
			gbc_btnInnerColor.anchor = GridBagConstraints.WEST;
			gbc_btnInnerColor.gridx = 1;
			gbc_btnInnerColor.gridy = 5;
			getPanel().add(btnInnerColor, gbc_btnInnerColor);
		}
		{
			JPanel panel_1 = new JPanel();
			GridBagConstraints gbc_panel_1 = new GridBagConstraints();
			gbc_panel_1.fill = GridBagConstraints.BOTH;
			gbc_panel_1.gridx = 2;
			gbc_panel_1.gridy = 5;
			getPanel().add(panel_1, gbc_panel_1);
			{
				
				panel_1.add(pnlInnerColor);
			}
		}
		
	}

	
	private void addBtnInnerColorListener() {
		
		btnInnerColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				Color chosenColor = JColorChooser.showDialog(getContentPane(), "Choose inner color", innerColor);
				if(chosenColor != null) {
					innerColor = chosenColor;
					pnlInnerColor.setBackground(innerColor);
				}
			}
		});
	}
	

	public JButton getBtnInnerColor() {
		return btnInnerColor;
	}

	public Color getInnerColor() {
		return innerColor;
	}

	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}

	public JPanel getPnlInnerColor() {
		return pnlInnerColor;
	}
	
	
	
	
}
