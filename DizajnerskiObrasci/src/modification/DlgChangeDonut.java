package modification;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Donut;
import geometry.Point;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgChangeDonut extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCeX;
	private JTextField txtCeY;
	private JTextField txtRad;
	private JTextField txtIRad;
	private Donut donut = new Donut();
	private JPanel edgeColor = new JPanel();
	private JPanel innerColor = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgChangeDonut dialog = new DlgChangeDonut();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgChangeDonut() {
		setModal(true);
		setBounds(100, 100, 313, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				JLabel lblEditDonut = new JLabel("Edit Donut");
				panel.add(lblEditDonut);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{97, 0, 0, 0};
			gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
				txtCeX = new JTextField();
				GridBagConstraints gbc_txtCeX = new GridBagConstraints();
				gbc_txtCeX.gridwidth = 2;
				gbc_txtCeX.insets = new Insets(0, 0, 5, 5);
				gbc_txtCeX.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtCeX.gridx = 1;
				gbc_txtCeX.gridy = 0;
				panel.add(txtCeX, gbc_txtCeX);
				txtCeX.setColumns(10);
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
				txtCeY = new JTextField();
				GridBagConstraints gbc_txtCeY = new GridBagConstraints();
				gbc_txtCeY.gridwidth = 2;
				gbc_txtCeY.insets = new Insets(0, 0, 5, 5);
				gbc_txtCeY.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtCeY.gridx = 1;
				gbc_txtCeY.gridy = 1;
				panel.add(txtCeY, gbc_txtCeY);
				txtCeY.setColumns(10);
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
				txtRad = new JTextField();
				GridBagConstraints gbc_txtRad = new GridBagConstraints();
				gbc_txtRad.gridwidth = 2;
				gbc_txtRad.insets = new Insets(0, 0, 5, 5);
				gbc_txtRad.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtRad.gridx = 1;
				gbc_txtRad.gridy = 2;
				panel.add(txtRad, gbc_txtRad);
				txtRad.setColumns(10);
			}
			{
				JLabel lblInnerRadius = new JLabel("Inner Radius:");
				GridBagConstraints gbc_lblInnerRadius = new GridBagConstraints();
				gbc_lblInnerRadius.anchor = GridBagConstraints.EAST;
				gbc_lblInnerRadius.insets = new Insets(0, 0, 5, 5);
				gbc_lblInnerRadius.gridx = 0;
				gbc_lblInnerRadius.gridy = 3;
				panel.add(lblInnerRadius, gbc_lblInnerRadius);
			}
			{
				txtIRad = new JTextField();
				GridBagConstraints gbc_txtIRad = new GridBagConstraints();
				gbc_txtIRad.gridwidth = 2;
				gbc_txtIRad.insets = new Insets(0, 0, 5, 5);
				gbc_txtIRad.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtIRad.gridx = 1;
				gbc_txtIRad.gridy = 3;
				panel.add(txtIRad, gbc_txtIRad);
				txtIRad.setColumns(10);
			}
			{
				JLabel lblEdgeColor = new JLabel("Edge Color:");
				GridBagConstraints gbc_lblEdgeColor = new GridBagConstraints();
				gbc_lblEdgeColor.anchor = GridBagConstraints.EAST;
				gbc_lblEdgeColor.insets = new Insets(0, 0, 5, 5);
				gbc_lblEdgeColor.gridx = 0;
				gbc_lblEdgeColor.gridy = 4;
				panel.add(lblEdgeColor, gbc_lblEdgeColor);
			}
			{
				JButton btnChooseColor = new JButton("Choose Color");
				btnChooseColor.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Color color=JColorChooser.showDialog(null, "Choose edge color", donut.getColor());
						if(color!=null) {
							donut.setColor(color);
							edgeColor.setBackground(color);
						}
					}
				});
				GridBagConstraints gbc_btnChooseColor = new GridBagConstraints();
				gbc_btnChooseColor.anchor = GridBagConstraints.WEST;
				gbc_btnChooseColor.insets = new Insets(0, 0, 5, 5);
				gbc_btnChooseColor.gridx = 1;
				gbc_btnChooseColor.gridy = 4;
				panel.add(btnChooseColor, gbc_btnChooseColor);
			}
			{
				JPanel panel_1 = new JPanel();
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.insets = new Insets(0, 0, 5, 0);
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 2;
				gbc_panel_1.gridy = 4;
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
				gbc_lblInnerColor.gridy = 5;
				panel.add(lblInnerColor, gbc_lblInnerColor);
			}
			{
				JButton btnChooseColor_1 = new JButton("Choose Color");
				btnChooseColor_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Color color=JColorChooser.showDialog(null, "Choose inner color", donut.getInnerColor());
						if(color!=null) {
							donut.setInnerColor(color);
							innerColor.setBackground(donut.getInnerColor());
						}
					}
				});
				GridBagConstraints gbc_btnChooseColor_1 = new GridBagConstraints();
				gbc_btnChooseColor_1.insets = new Insets(0, 0, 0, 5);
				gbc_btnChooseColor_1.anchor = GridBagConstraints.WEST;
				gbc_btnChooseColor_1.gridx = 1;
				gbc_btnChooseColor_1.gridy = 5;
				panel.add(btnChooseColor_1, gbc_btnChooseColor_1);
			}
			{
				JPanel panel_1 = new JPanel();
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 2;
				gbc_panel_1.gridy = 5;
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
							int cX=Integer.parseInt(txtCeX.getText());
							int cY=Integer.parseInt(txtCeY.getText());
							int radiusA=Integer.parseInt(txtRad.getText());
							int innerRad=Integer.parseInt(txtIRad.getText());
							if(cX<1||cY<1||radiusA<1||innerRad<1) {
								JOptionPane.showOptionDialog(null, "Invalid input.", "Error", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
								return;
							}
							donut.setCenter(new Point(cX, cY));
							donut.setRadius(radiusA);
							donut.setInnerRadius(innerRad);
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
						donut=null;
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public Donut getNewDonut() {
		return donut;
	}
	
	public void setNewDonut(Donut donut) {
		this.donut=donut;
		txtCeX.setText("" + donut.getCenter().getX());
		txtCeY.setText("" + donut.getCenter().getY());
		txtRad.setText("" + donut.getRadius());
		txtIRad.setText("" + donut.getInnerRadius());
		edgeColor.setBackground(donut.getColor());
		innerColor.setBackground(donut.getInnerColor());
	}


}
