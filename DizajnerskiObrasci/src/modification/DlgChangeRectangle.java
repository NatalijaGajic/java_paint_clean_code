package modification;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Rectangle;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgChangeRectangle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtULX;
	private JTextField txtULY;
	private JTextField txtHeight;
	private JTextField txtWidth;
	private JPanel edgeColor = new JPanel();
	private JPanel innerColor = new JPanel();
	private Rectangle rectangle=new Rectangle();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgChangeRectangle dialog = new DlgChangeRectangle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgChangeRectangle() {
		setModal(true);
		setBounds(100, 100, 354, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblEditRectangle = new JLabel("Edit Rectangle");
			lblEditRectangle.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblEditRectangle, BorderLayout.NORTH);
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{104, 145, 0, 0};
			gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JLabel lblUpperLeftX = new JLabel("Upper Left X:");
				GridBagConstraints gbc_lblUpperLeftX = new GridBagConstraints();
				gbc_lblUpperLeftX.insets = new Insets(0, 0, 5, 5);
				gbc_lblUpperLeftX.anchor = GridBagConstraints.EAST;
				gbc_lblUpperLeftX.gridx = 0;
				gbc_lblUpperLeftX.gridy = 0;
				panel.add(lblUpperLeftX, gbc_lblUpperLeftX);
			}
			{
				txtULX = new JTextField();
				GridBagConstraints gbc_txtULX = new GridBagConstraints();
				gbc_txtULX.gridwidth = 2;
				gbc_txtULX.insets = new Insets(0, 0, 5, 5);
				gbc_txtULX.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtULX.gridx = 1;
				gbc_txtULX.gridy = 0;
				panel.add(txtULX, gbc_txtULX);
				txtULX.setColumns(10);
			}
			{
				JLabel lblUpperLeftY = new JLabel("Upper Left Y:");
				GridBagConstraints gbc_lblUpperLeftY = new GridBagConstraints();
				gbc_lblUpperLeftY.anchor = GridBagConstraints.EAST;
				gbc_lblUpperLeftY.insets = new Insets(0, 0, 5, 5);
				gbc_lblUpperLeftY.gridx = 0;
				gbc_lblUpperLeftY.gridy = 1;
				panel.add(lblUpperLeftY, gbc_lblUpperLeftY);
			}
			{
				txtULY = new JTextField();
				GridBagConstraints gbc_txtULY = new GridBagConstraints();
				gbc_txtULY.gridwidth = 2;
				gbc_txtULY.insets = new Insets(0, 0, 5, 5);
				gbc_txtULY.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtULY.gridx = 1;
				gbc_txtULY.gridy = 1;
				panel.add(txtULY, gbc_txtULY);
				txtULY.setColumns(10);
			}
			{
				JLabel lblHeight = new JLabel("Height:");
				GridBagConstraints gbc_lblHeight = new GridBagConstraints();
				gbc_lblHeight.anchor = GridBagConstraints.EAST;
				gbc_lblHeight.insets = new Insets(0, 0, 5, 5);
				gbc_lblHeight.gridx = 0;
				gbc_lblHeight.gridy = 2;
				panel.add(lblHeight, gbc_lblHeight);
			}
			{
				txtHeight = new JTextField();
				GridBagConstraints gbc_txtHeight = new GridBagConstraints();
				gbc_txtHeight.gridwidth = 2;
				gbc_txtHeight.insets = new Insets(0, 0, 5, 5);
				gbc_txtHeight.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtHeight.gridx = 1;
				gbc_txtHeight.gridy = 2;
				panel.add(txtHeight, gbc_txtHeight);
				txtHeight.setColumns(10);
			}
			{
				JLabel lblWidth = new JLabel("Width:");
				GridBagConstraints gbc_lblWidth = new GridBagConstraints();
				gbc_lblWidth.anchor = GridBagConstraints.EAST;
				gbc_lblWidth.insets = new Insets(0, 0, 5, 5);
				gbc_lblWidth.gridx = 0;
				gbc_lblWidth.gridy = 3;
				panel.add(lblWidth, gbc_lblWidth);
			}
			{
				txtWidth = new JTextField();
				GridBagConstraints gbc_txtWidth = new GridBagConstraints();
				gbc_txtWidth.gridwidth = 2;
				gbc_txtWidth.insets = new Insets(0, 0, 5, 5);
				gbc_txtWidth.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtWidth.gridx = 1;
				gbc_txtWidth.gridy = 3;
				panel.add(txtWidth, gbc_txtWidth);
				txtWidth.setColumns(10);
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
						Color color = JColorChooser.showDialog(null, "Choose edge color", rectangle.getColor());
						if(color!=null) {
							rectangle.setColor(color);
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
						Color color = JColorChooser.showDialog(null, "Choose inner color", rectangle.getInnerColor());
						if(color!=null) {
							rectangle.setInnerColor(color);
							innerColor.setBackground(rectangle.getInnerColor());
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
				JButton btnCorfim = new JButton("Confirm");
				btnCorfim.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String[] options = {"OK"};
						try {
							int x=Integer.parseInt(txtULX.getText());
							int y=Integer.parseInt(txtULY.getText());
							int height=Integer.parseInt(txtHeight.getText());
							int width=Integer.parseInt(txtWidth.getText());
							if(x<1||y<1||height<1||width<1) {
								JOptionPane.showOptionDialog(null, "Invalid input.", "Error", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
								return;
							}
							rectangle.moveOn(x, y);
							rectangle.setHeight(height);
							rectangle.setWidth(width);
							dispose();
						} catch(Exception e1) {
							JOptionPane.showOptionDialog(null, "Invalid input.", "Error", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
						}
					}
				});
				btnCorfim.setActionCommand("OK");
				buttonPane.add(btnCorfim);
				getRootPane().setDefaultButton(btnCorfim);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						rectangle=null;
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public Rectangle getNewRec() {
		return rectangle;
	}
	
	public void setNewRec (Rectangle rectangle) {
		this.rectangle=rectangle;
		txtULX.setText("" + rectangle.getUpperLeftPoint().getX());
		txtULY.setText("" + rectangle.getUpperLeftPoint().getY());
		txtHeight.setText("" + rectangle.getHeight());
		txtWidth.setText("" + rectangle.getWidth());
		edgeColor.setBackground(rectangle.getColor());
		innerColor.setBackground(rectangle.getInnerColor());
	}

}
