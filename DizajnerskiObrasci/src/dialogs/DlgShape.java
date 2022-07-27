package dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Point;

import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public abstract class DlgShape extends JDialog implements Dialog {
	
	private JPanel contentPanel;
	private JPanel btnPanel;
	private Color borderColor;
	private JButton btnOk;
	private JButton btnCancel;
	private KeyAdapter inputListener;
	private boolean accepted;
	private JLabel lblXCoord;
	private JLabel lblYCoord;
	private JTextField txtXCoord;
	private JTextField txtYCoord;
	private JButton btnBorderColor;
	private JTextField txtBorderColor;

	public DlgShape() {
		contentPanel = new JPanel();
		lblXCoord = new JLabel("X coordinate:");
		txtXCoord = new JTextField();
		lblYCoord = new JLabel("Y coordinate:");
		txtYCoord = new JTextField();
		btnBorderColor = new JButton("Border color");
		txtBorderColor = new JTextField();
		btnOk = new JButton("OK");
		btnCancel = new JButton("Cancel");
		borderColor = Color.BLACK;
		initializeCharListener();
		buildDialog();
		addBtnBorderColorListener();
		addBtnOkListener();
		addBtnCancelListener();
	}

	private void initializeCharListener() {
		inputListener = new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent event) {
				preventInvalidChar(event);
			}
		};
	}

	private void preventInvalidChar(KeyEvent event) {
		char charInput = event.getKeyChar();

		if (isCharInvalid(charInput)) {
			getToolkit().beep();
			event.consume();
		}
	}

	private boolean isCharInvalid(char charInput) {
		return charInput < '0' || charInput > '9' || charInput == KeyEvent.VK_BACK_SPACE
				|| charInput == KeyEvent.VK_DELETE;
	}

	public void buildDialog() {
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		btnPanel = new JPanel();
		btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(btnPanel, BorderLayout.SOUTH);
		
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		
		GridBagConstraints gbc_lblXCoord = new GridBagConstraints();
		gbc_lblXCoord.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblXCoord.insets = new Insets(0, 0, 5, 5);
		gbc_lblXCoord.gridx = 1;
		gbc_lblXCoord.gridy = 0;
		contentPanel.add(lblXCoord, gbc_lblXCoord);
		
		GridBagConstraints gbc_txtXCoord = new GridBagConstraints();
		gbc_txtXCoord.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtXCoord.insets = new Insets(0, 0, 5, 0);
		gbc_txtXCoord.gridx = 5;
		gbc_txtXCoord.gridy = 0;
		contentPanel.add(txtXCoord, gbc_txtXCoord);
		txtXCoord.setColumns(12);
		txtXCoord.addKeyListener(inputListener);
		
		GridBagConstraints gbc_lblYCoord = new GridBagConstraints();
		gbc_lblYCoord.fill = GridBagConstraints.BOTH;
		gbc_lblYCoord.insets = new Insets(0, 0, 5, 5);
		gbc_lblYCoord.gridx = 1;
		gbc_lblYCoord.gridy = 1;
		contentPanel.add(lblYCoord, gbc_lblYCoord);
		
		GridBagConstraints gbc_txtYCoord = new GridBagConstraints();
		gbc_txtYCoord.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtYCoord.insets = new Insets(0, 0, 5, 0);
		gbc_txtYCoord.gridx = 5;
		gbc_txtYCoord.gridy = 1;
		contentPanel.add(txtYCoord, gbc_txtYCoord);
		txtYCoord.setColumns(12);
		txtYCoord.addKeyListener(inputListener);
		
		GridBagConstraints gbc_btnBorderColor = new GridBagConstraints();
		gbc_btnBorderColor.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBorderColor.insets = new Insets(0, 0, 0, 5);
		gbc_btnBorderColor.gridx = 1;
		gbc_btnBorderColor.gridy = 2;
		contentPanel.add(btnBorderColor, gbc_btnBorderColor);
		
		txtBorderColor.setEditable(false);
		txtBorderColor.setBackground(Color.BLACK);
		GridBagConstraints gbc_txtBorderColor = new GridBagConstraints();
		gbc_txtBorderColor.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBorderColor.gridx = 5;
		gbc_txtBorderColor.gridy = 2;
		contentPanel.add(txtBorderColor, gbc_txtBorderColor);
		txtBorderColor.setColumns(12);

		btnOk.setActionCommand("OK");
		btnPanel.add(btnOk);
		getRootPane().setDefaultButton(btnOk);

		btnCancel.setActionCommand("Cancel");
		btnPanel.add(btnCancel);
		
	}

	private void addBtnBorderColorListener() {
		btnBorderColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				borderColor = JColorChooser.showDialog(getContentPane(), "Choose border color", borderColor);
				txtBorderColor.setBackground(borderColor);
			}
		});
	}

	private void addBtnOkListener() {
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (!areAllFieldsFilled())
					JOptionPane.showMessageDialog(new JFrame(), "You have not filled in all the fields, try again!",
							"Error!", JOptionPane.ERROR_MESSAGE);
				else if (!areValuesValid())
					JOptionPane.showMessageDialog(new JFrame(), "Values are invalid, try again!", "Error!",
							JOptionPane.ERROR_MESSAGE);
				else {
					setAccepted(true);
					setVisible(false);
				}
			}
		});
	}

	private void addBtnCancelListener() {
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				dispose();
			}
		});
	}

	@Override
	public void setCreateDialogFields(Point point) {
		String xCoord = String.valueOf(point.getX());
		String yCoord = String.valueOf(point.getY());
		getTxtXCoord().setText(xCoord);
		getTxtYCoord().setText(yCoord);	
	}
	
	
	public JPanel getContentPanel() {
		return contentPanel;
	}

	public KeyAdapter getInputListener() {
		return inputListener;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public boolean isAccepted() {
		return accepted;
	}
	
	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public JTextField getTxtXCoord() {
		return txtXCoord;
	}

	public void setTxtXCoord(JTextField txtXCoord) {
		this.txtXCoord = txtXCoord;
	}

	public JTextField getTxtYCoord() {
		return txtYCoord;
	}

	public void setTxtYCoord(JTextField txtYCoord) {
		this.txtYCoord = txtYCoord;
	}

	public JTextField getTxtBorderColor() {
		return txtBorderColor;
	}

	public void setTxtBorderColor(JTextField txtBorderColor) {
		this.txtBorderColor = txtBorderColor;
	}

	public JButton getBtnBorderColor() {
		return btnBorderColor;
	}

	public JButton getBtnOk() {
		return btnOk;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}
	
	
	
	
	
	
}
