package dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Line;
import geometry.Point;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgLine extends JDialog{

	private final JPanel contentPanel = new JPanel();
	private JTextField txtX1;
	private JTextField txtY1;
	private JTextField txtX2;
	private JTextField txtY2;
	private Line line=new Line();
	JPanel panel_2 = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgLine dialog = new DlgLine();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgLine() {
		setModal(true);
		setBounds(100, 100, 263, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblEditLine = new JLabel("Edit Line");
			lblEditLine.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblEditLine, BorderLayout.NORTH);
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{65, 0, 0, 0};
			gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JLabel lblX = new JLabel("X1:");
				GridBagConstraints gbc_lblX = new GridBagConstraints();
				gbc_lblX.insets = new Insets(0, 0, 5, 5);
				gbc_lblX.anchor = GridBagConstraints.EAST;
				gbc_lblX.gridx = 0;
				gbc_lblX.gridy = 0;
				panel.add(lblX, gbc_lblX);
			}
			{
				txtX1 = new JTextField();
				GridBagConstraints gbc_txtX1 = new GridBagConstraints();
				gbc_txtX1.gridwidth = 2;
				gbc_txtX1.insets = new Insets(0, 0, 5, 5);
				gbc_txtX1.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtX1.gridx = 1;
				gbc_txtX1.gridy = 0;
				panel.add(txtX1, gbc_txtX1);
				txtX1.setColumns(10);
			}
			{
				JLabel lblY = new JLabel("Y1:");
				GridBagConstraints gbc_lblY = new GridBagConstraints();
				gbc_lblY.anchor = GridBagConstraints.EAST;
				gbc_lblY.insets = new Insets(0, 0, 5, 5);
				gbc_lblY.gridx = 0;
				gbc_lblY.gridy = 1;
				panel.add(lblY, gbc_lblY);
			}
			{
				txtY1 = new JTextField();
				GridBagConstraints gbc_txtY1 = new GridBagConstraints();
				gbc_txtY1.gridwidth = 2;
				gbc_txtY1.insets = new Insets(0, 0, 5, 5);
				gbc_txtY1.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtY1.gridx = 1;
				gbc_txtY1.gridy = 1;
				panel.add(txtY1, gbc_txtY1);
				txtY1.setColumns(10);
			}
			{
				JLabel lblX_1 = new JLabel("X2:");
				GridBagConstraints gbc_lblX_1 = new GridBagConstraints();
				gbc_lblX_1.anchor = GridBagConstraints.EAST;
				gbc_lblX_1.insets = new Insets(0, 0, 5, 5);
				gbc_lblX_1.gridx = 0;
				gbc_lblX_1.gridy = 2;
				panel.add(lblX_1, gbc_lblX_1);
			}
			{
				txtX2 = new JTextField();
				GridBagConstraints gbc_txtX2 = new GridBagConstraints();
				gbc_txtX2.gridwidth = 2;
				gbc_txtX2.insets = new Insets(0, 0, 5, 5);
				gbc_txtX2.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtX2.gridx = 1;
				gbc_txtX2.gridy = 2;
				panel.add(txtX2, gbc_txtX2);
				txtX2.setColumns(10);
			}
			{
				JLabel lblY_1 = new JLabel("Y2:");
				GridBagConstraints gbc_lblY_1 = new GridBagConstraints();
				gbc_lblY_1.anchor = GridBagConstraints.EAST;
				gbc_lblY_1.insets = new Insets(0, 0, 5, 5);
				gbc_lblY_1.gridx = 0;
				gbc_lblY_1.gridy = 3;
				panel.add(lblY_1, gbc_lblY_1);
			}
			{
				txtY2 = new JTextField();
				GridBagConstraints gbc_txtY2 = new GridBagConstraints();
				gbc_txtY2.gridwidth = 2;
				gbc_txtY2.insets = new Insets(0, 0, 5, 5);
				gbc_txtY2.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtY2.gridx = 1;
				gbc_txtY2.gridy = 3;
				panel.add(txtY2, gbc_txtY2);
				txtY2.setColumns(10);
			}
			{
				JLabel lblColor = new JLabel("Color:");
				GridBagConstraints gbc_lblColor = new GridBagConstraints();
				gbc_lblColor.anchor = GridBagConstraints.EAST;
				gbc_lblColor.insets = new Insets(0, 0, 0, 5);
				gbc_lblColor.gridx = 0;
				gbc_lblColor.gridy = 4;
				panel.add(lblColor, gbc_lblColor);
			}
			{
				JButton btnChooseColor = new JButton("Choose Color");
				btnChooseColor.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Color color=JColorChooser.showDialog(null, "Choose color", line.getColor());
						if (color!=null) {
							line.setColor(color);
							panel_2.setBackground(color);
						}
					}
				});
				GridBagConstraints gbc_btnChooseColor = new GridBagConstraints();
				gbc_btnChooseColor.insets = new Insets(0, 0, 0, 5);
				gbc_btnChooseColor.gridx = 1;
				gbc_btnChooseColor.gridy = 4;
				panel.add(btnChooseColor, gbc_btnChooseColor);
			}
			{
				JPanel panel_1 = new JPanel();
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 2;
				gbc_panel_1.gridy = 4;
				panel.add(panel_1, gbc_panel_1);
				{
					panel_1.add(panel_2);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnConfirm = new JButton("Confirm");
				btnConfirm.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						String[] options = {"OK"};
						try {
							int firstX=Integer.parseInt(txtX1.getText());
							int firstY=Integer.parseInt(txtY1.getText());
							int secondX=Integer.parseInt(txtX2.getText());
							int secondY=Integer.parseInt(txtY2.getText());
							if(firstX<1 || firstY<1 || secondX<1 || secondY<1) {
								JOptionPane.showOptionDialog(null, "Invalid input.", "Error", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
								return;
							}
							line.setStartPoint(new Point(firstX,firstY)); 
							line.setEndPoint(new Point(secondX, secondY));
							dispose();
						} catch (Exception e1) {
							JOptionPane.showOptionDialog(null, "Invalid input.", "Error", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
						}
					}
				});
				btnConfirm.setActionCommand("OK");
				buttonPane.add(btnConfirm);
				getRootPane().setDefaultButton(btnConfirm);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						line=null;
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
		txtX1.setText("" + line.getStartPoint().getX());
		txtY1.setText("" + line.getStartPoint().getY());
		txtX2.setText("" + line.getEndPoint().getX());
		txtY2.setText("" + line.getEndPoint().getY());
		panel_2.setBackground(line.getColor());
	}

}
