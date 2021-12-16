package modification;

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

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgChangeCircle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCX;
	private JTextField txtCY;
	private JTextField txtRadius;
	private Circle circle=new Circle();
	private JPanel edgeColor = new JPanel();
	private JPanel innerColor = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgChangeCircle dialog = new DlgChangeCircle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgChangeCircle() {
		setModal(true);
		setBounds(100, 100, 274, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				JLabel lblEditCircle = new JLabel("Edit Circle");
				panel.add(lblEditCircle);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{86, 100, 0, 0};
			gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JLabel lblCenterX = new JLabel("Center X:");
				GridBagConstraints gbc_lblCenterX = new GridBagConstraints();
				gbc_lblCenterX.insets = new Insets(0, 0, 5, 5);
				gbc_lblCenterX.anchor = GridBagConstraints.EAST;
				gbc_lblCenterX.gridx = 0;
				gbc_lblCenterX.gridy = 0;
				panel.add(lblCenterX, gbc_lblCenterX);
			}
			{
				txtCX = new JTextField();
				GridBagConstraints gbc_txtCX = new GridBagConstraints();
				gbc_txtCX.gridwidth = 2;
				gbc_txtCX.insets = new Insets(0, 0, 5, 5);
				gbc_txtCX.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtCX.gridx = 1;
				gbc_txtCX.gridy = 0;
				panel.add(txtCX, gbc_txtCX);
				txtCX.setColumns(10);
			}
			{
				JLabel lblCenterY = new JLabel("Center Y:");
				GridBagConstraints gbc_lblCenterY = new GridBagConstraints();
				gbc_lblCenterY.anchor = GridBagConstraints.EAST;
				gbc_lblCenterY.insets = new Insets(0, 0, 5, 5);
				gbc_lblCenterY.gridx = 0;
				gbc_lblCenterY.gridy = 1;
				panel.add(lblCenterY, gbc_lblCenterY);
			}
			{
				txtCY = new JTextField();
				GridBagConstraints gbc_txtCY = new GridBagConstraints();
				gbc_txtCY.gridwidth = 2;
				gbc_txtCY.insets = new Insets(0, 0, 5, 5);
				gbc_txtCY.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtCY.gridx = 1;
				gbc_txtCY.gridy = 1;
				panel.add(txtCY, gbc_txtCY);
				txtCY.setColumns(10);
			}
			{
				JLabel lblRadius = new JLabel("Radius:");
				GridBagConstraints gbc_lblRadius = new GridBagConstraints();
				gbc_lblRadius.anchor = GridBagConstraints.EAST;
				gbc_lblRadius.insets = new Insets(0, 0, 5, 5);
				gbc_lblRadius.gridx = 0;
				gbc_lblRadius.gridy = 2;
				panel.add(lblRadius, gbc_lblRadius);
			}
			{
				txtRadius = new JTextField();
				GridBagConstraints gbc_txtRadius = new GridBagConstraints();
				gbc_txtRadius.gridwidth = 2;
				gbc_txtRadius.insets = new Insets(0, 0, 5, 5);
				gbc_txtRadius.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtRadius.gridx = 1;
				gbc_txtRadius.gridy = 2;
				panel.add(txtRadius, gbc_txtRadius);
				txtRadius.setColumns(10);
			}
			{
				JLabel lblEdgeColor = new JLabel("Edge Color:");
				GridBagConstraints gbc_lblEdgeColor = new GridBagConstraints();
				gbc_lblEdgeColor.anchor = GridBagConstraints.EAST;
				gbc_lblEdgeColor.insets = new Insets(0, 0, 5, 5);
				gbc_lblEdgeColor.gridx = 0;
				gbc_lblEdgeColor.gridy = 3;
				panel.add(lblEdgeColor, gbc_lblEdgeColor);
			}
			{
				JButton btnChooseColor = new JButton("Choose Color");
				btnChooseColor.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Color color=JColorChooser.showDialog(null, "Choose edge color", circle.getColor());
						if(color!=null) {
							circle.setColor(color);
							edgeColor.setBackground(color);
						}
					}
				});
				GridBagConstraints gbc_btnChooseColor = new GridBagConstraints();
				gbc_btnChooseColor.insets = new Insets(0, 0, 5, 5);
				gbc_btnChooseColor.gridx = 1;
				gbc_btnChooseColor.gridy = 3;
				panel.add(btnChooseColor, gbc_btnChooseColor);
			}
			{
				JPanel panel_1 = new JPanel();
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.insets = new Insets(0, 0, 5, 0);
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 2;
				gbc_panel_1.gridy = 3;
				panel.add(panel_1, gbc_panel_1);
				{
					
					panel_1.add(edgeColor);
				}
			}
			{
				JLabel lblInnerColor = new JLabel("Inner Color:");
				GridBagConstraints gbc_lblInnerColor = new GridBagConstraints();
				gbc_lblInnerColor.anchor = GridBagConstraints.EAST;
				gbc_lblInnerColor.insets = new Insets(0, 0, 0, 5);
				gbc_lblInnerColor.gridx = 0;
				gbc_lblInnerColor.gridy = 4;
				panel.add(lblInnerColor, gbc_lblInnerColor);
			}
			{
				JButton btnChooseColor_1 = new JButton("Choose Color");
				btnChooseColor_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Color color=JColorChooser.showDialog(null, "Choose inner color", circle.getInnerColor());
						if(color!=null) {
							circle.setInnerColor(color);
							innerColor.setBackground(circle.getInnerColor());
						}
					}
				});
				GridBagConstraints gbc_btnChooseColor_1 = new GridBagConstraints();
				gbc_btnChooseColor_1.insets = new Insets(0, 0, 0, 5);
				gbc_btnChooseColor_1.gridx = 1;
				gbc_btnChooseColor_1.gridy = 4;
				panel.add(btnChooseColor_1, gbc_btnChooseColor_1);
			}
			{
				JPanel panel_1 = new JPanel();
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 2;
				gbc_panel_1.gridy = 4;
				panel.add(panel_1, gbc_panel_1);
				{
					
					panel_1.add(innerColor);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Confirm");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String[]options= {"OK"};
						try {
							int corX=Integer.parseInt(txtCX.getText());
							int corY=Integer.parseInt(txtCY.getText());
							int radius=Integer.parseInt(txtRadius.getText());
							if(corX<1||corY<1||radius<1) {
								JOptionPane.showOptionDialog(null, "Invalid input.", "Error", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
								return;
							}
							circle.setCenter(new Point(corX, corY));
							circle.setRadius(radius);
							dispose();
						} catch(Exception e1) {
							JOptionPane.showOptionDialog(null, "Invalid input.", "Error", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
						}
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						circle=null;
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public Circle getNewCircle() {
		return circle;
	}
	
	public void setNewCircle(Circle circle) {
		this.circle=circle;
		txtCX.setText("" + circle.getCenter().getX());
		txtCY.setText("" + circle.getCenter().getY());
		txtRadius.setText("" + circle.getRadius());
		edgeColor.setBackground(circle.getColor());
		innerColor.setBackground(circle.getInnerColor());
	}

}
