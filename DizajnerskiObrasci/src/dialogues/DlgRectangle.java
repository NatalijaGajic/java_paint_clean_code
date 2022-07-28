package dialogues;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Point;
import geometry.Rectangle;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgRectangle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCX;
	private JTextField txtCY;
	private JTextField txtHeight;
	private JTextField txtWidth;
	private Rectangle rectangle;
	
	boolean check;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgRectangle dialog = new DlgRectangle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgRectangle() {
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{89, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblCoordinateX = new JLabel("Coordinate X:");
			GridBagConstraints gbc_lblCoordinateX = new GridBagConstraints();
			gbc_lblCoordinateX.insets = new Insets(0, 0, 5, 5);
			gbc_lblCoordinateX.anchor = GridBagConstraints.EAST;
			gbc_lblCoordinateX.gridx = 0;
			gbc_lblCoordinateX.gridy = 0;
			contentPanel.add(lblCoordinateX, gbc_lblCoordinateX);
		}
		{
			txtCX = new JTextField();
			GridBagConstraints gbc_txtCX = new GridBagConstraints();
			gbc_txtCX.insets = new Insets(0, 0, 5, 0);
			gbc_txtCX.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtCX.gridx = 1;
			gbc_txtCX.gridy = 0;
			contentPanel.add(txtCX, gbc_txtCX);
			txtCX.setColumns(10);
		}
		{
			JLabel lblCoordinateY = new JLabel("Coordinate Y:");
			GridBagConstraints gbc_lblCoordinateY = new GridBagConstraints();
			gbc_lblCoordinateY.anchor = GridBagConstraints.EAST;
			gbc_lblCoordinateY.insets = new Insets(0, 0, 5, 5);
			gbc_lblCoordinateY.gridx = 0;
			gbc_lblCoordinateY.gridy = 1;
			contentPanel.add(lblCoordinateY, gbc_lblCoordinateY);
		}
		{
			txtCY = new JTextField();
			GridBagConstraints gbc_txtCY = new GridBagConstraints();
			gbc_txtCY.insets = new Insets(0, 0, 5, 0);
			gbc_txtCY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtCY.gridx = 1;
			gbc_txtCY.gridy = 1;
			contentPanel.add(txtCY, gbc_txtCY);
			txtCY.setColumns(10);
		}
		{
			JLabel lblHeight = new JLabel("Height:");
			GridBagConstraints gbc_lblHeight = new GridBagConstraints();
			gbc_lblHeight.anchor = GridBagConstraints.EAST;
			gbc_lblHeight.insets = new Insets(0, 0, 5, 5);
			gbc_lblHeight.gridx = 0;
			gbc_lblHeight.gridy = 2;
			contentPanel.add(lblHeight, gbc_lblHeight);
		}
		{
			txtHeight = new JTextField();
			GridBagConstraints gbc_txtHeight = new GridBagConstraints();
			gbc_txtHeight.insets = new Insets(0, 0, 5, 0);
			gbc_txtHeight.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtHeight.gridx = 1;
			gbc_txtHeight.gridy = 2;
			contentPanel.add(txtHeight, gbc_txtHeight);
			txtHeight.setColumns(10);
		}
		{
			JLabel lblWidth = new JLabel("Width:");
			GridBagConstraints gbc_lblWidth = new GridBagConstraints();
			gbc_lblWidth.anchor = GridBagConstraints.EAST;
			gbc_lblWidth.insets = new Insets(0, 0, 0, 5);
			gbc_lblWidth.gridx = 0;
			gbc_lblWidth.gridy = 3;
			contentPanel.add(lblWidth, gbc_lblWidth);
		}
		{
			txtWidth = new JTextField();
			GridBagConstraints gbc_txtWidth = new GridBagConstraints();
			gbc_txtWidth.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtWidth.gridx = 1;
			gbc_txtWidth.gridy = 3;
			contentPanel.add(txtWidth, gbc_txtWidth);
			txtWidth.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						check=true;
						String[] option= {"OK"};
						try {
							int x=Integer.parseInt(txtCX.getText());
							int y=Integer.parseInt(txtCY.getText());
							int height=Integer.parseInt(txtHeight.getText());
							int width=Integer.parseInt(txtWidth.getText());
							if(x<1 || y<1 || height<1 || width<1) {
								JOptionPane.showOptionDialog(null, "Error entering!", "Error", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, option, option[0]);
								return;
							}
								rectangle=new Rectangle(new Point(x,y), height, width);
								dispose();

						} catch (Exception e1) {
							JOptionPane.showOptionDialog(null, "Error entering!", "Error", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, option, option[0]);
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
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public Rectangle getRec() {
		return rectangle;
	}
	public void setRec(Rectangle rectangle) {
		this.rectangle = rectangle;
		txtCX.setText("" + rectangle.getUpperLeftPoint().getX());
		txtCY.setText("" + rectangle.getUpperLeftPoint().getY());
		txtHeight.setText("" + rectangle.getHeight());
		txtWidth.setText("" + rectangle.getWidth());
	}
	
	public boolean isCheck(){
		return check;
	}

}
