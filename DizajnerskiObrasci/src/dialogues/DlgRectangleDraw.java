package dialogues;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgRectangleDraw extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtHeight;
	private JTextField txtWidth;
	private int heightR=0;
	private int widthR=0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgRectangleDraw dialog = new DlgRectangleDraw();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgRectangleDraw() {
		setModal(true);
		setBounds(100, 100, 364, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				JLabel lblNewRectangle = new JLabel("New Rectangle");
				panel.add(lblNewRectangle);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, BorderLayout.NORTH);
				{
					JLabel lblNewLabel = new JLabel("Height:");
					lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
					panel_1.add(lblNewLabel);
				}
				{
					txtHeight = new JTextField();
					txtHeight.setHorizontalAlignment(SwingConstants.CENTER);
					panel_1.add(txtHeight);
					txtHeight.setColumns(10);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, BorderLayout.CENTER);
				{
					JLabel lblWidth = new JLabel("Width:");
					lblWidth.setHorizontalAlignment(SwingConstants.CENTER);
					panel_1.add(lblWidth);
				}
				{
					txtWidth = new JTextField();
					txtWidth.setHorizontalAlignment(SwingConstants.CENTER);
					panel_1.add(txtWidth);
					txtWidth.setColumns(10);
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
							 heightR=Integer.parseInt(txtHeight.getText());
							 widthR=Integer.parseInt(txtWidth.getText());
							if(heightR<1 || widthR<1) {
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
						heightR=0;
						widthR=0;
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	public int getHeightR() {
		return heightR;
	}

	public void setHeightR(int heightR) {
		this.heightR = heightR;
	}

	public int getWidthR() {
		return widthR;
	}

	public void setWidthR(int widthR) {
		this.widthR = widthR;
	}
	
	

}
