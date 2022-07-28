package dialogues;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgDonutDraw extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtRadius;
	private JTextField txtIRadius;
	private int radius=0;
	private int innerRadius=0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgDonutDraw dialog = new DlgDonutDraw();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgDonutDraw() {
		setModal(true);
		setBounds(100, 100, 339, 170);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				JLabel label = new JLabel("New label");
				panel.add(label);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{15, 0, 0, 0, -3, 54, 0, 0};
			gbl_panel.rowHeights = new int[]{0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JLabel lblRadius = new JLabel("Radius:");
				GridBagConstraints gbc_lblRadius = new GridBagConstraints();
				gbc_lblRadius.insets = new Insets(0, 0, 5, 5);
				gbc_lblRadius.gridx = 5;
				gbc_lblRadius.gridy = 0;
				panel.add(lblRadius, gbc_lblRadius);
			}
			{
				txtRadius = new JTextField();
				GridBagConstraints gbc_txtRadius = new GridBagConstraints();
				gbc_txtRadius.insets = new Insets(0, 0, 5, 0);
				gbc_txtRadius.gridx = 6;
				gbc_txtRadius.gridy = 0;
				panel.add(txtRadius, gbc_txtRadius);
				txtRadius.setColumns(10);
			}
			{
				JLabel lblInnerRadius = new JLabel("Inner Radius:");
				GridBagConstraints gbc_lblInnerRadius = new GridBagConstraints();
				gbc_lblInnerRadius.insets = new Insets(0, 0, 0, 5);
				gbc_lblInnerRadius.gridx = 5;
				gbc_lblInnerRadius.gridy = 1;
				panel.add(lblInnerRadius, gbc_lblInnerRadius);
			}
			{
				txtIRadius = new JTextField();
				GridBagConstraints gbc_txtIRadius = new GridBagConstraints();
				gbc_txtIRadius.gridx = 6;
				gbc_txtIRadius.gridy = 1;
				panel.add(txtIRadius, gbc_txtIRadius);
				txtIRadius.setColumns(10);
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
							 radius=Integer.parseInt(txtRadius.getText());
							 innerRadius=Integer.parseInt(txtIRadius.getText());
							if(radius<1 || innerRadius <1) {
								JOptionPane.showOptionDialog(null, "Invalid input.", "Error.", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
								return;
							}
							dispose();
						} catch (Exception e1) {
							JOptionPane.showOptionDialog(null, "Invalid input.", "Error.", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
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
						radius=0;
						innerRadius=0;
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public int getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}

}
