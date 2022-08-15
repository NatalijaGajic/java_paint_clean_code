package dialogs;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;
import geometry.Point;
import javax.swing.JTextField;

public abstract class DlgShape extends JDialog implements Dialog {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	private JPanel pnlEdgeColor;
	private JPanel pnlButton; 
	private JPanel panel;
	private JButton btnOk;
	private JButton btnCancel;
	private JLabel lblXCoord;
	private JLabel lblYCoord;
	private JTextField txtXCoord;
	private JTextField txtYCoord;
	private JButton btnEdgeColor;
	private Color edgeColor;
	private boolean accepted;
	private KeyAdapter inputListener;


	public DlgShape() {
		
		contentPanel = new JPanel();
		pnlEdgeColor = new JPanel();
		pnlButton = new JPanel();
		panel = new JPanel();
		lblXCoord = new JLabel("X coordinate:");
		txtXCoord = new JTextField();
		lblYCoord = new JLabel("Y coordinate:");
		txtYCoord = new JTextField();
		btnEdgeColor = new JButton("Choose color");
		btnOk = new JButton("OK");
		btnCancel = new JButton("Cancel");
		edgeColor = Color.BLACK;
		initializeCharListener();
		buildDialog();
		addBtnEdgeColorListener();
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
		setBounds(100, 100, 354, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		contentPanel.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{104, 145, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		{
			
			GridBagConstraints gbc_lblXCoord = new GridBagConstraints();
			gbc_lblXCoord.insets = new Insets(0, 0, 5, 5);
			gbc_lblXCoord.anchor = GridBagConstraints.EAST;
			gbc_lblXCoord.gridx = 0;
			gbc_lblXCoord.gridy = 0;
			panel.add(lblXCoord, gbc_lblXCoord);
			
		}
		{
			GridBagConstraints gbc_txtXCoord = new GridBagConstraints();
			gbc_txtXCoord.gridwidth = 2;
			gbc_txtXCoord.insets = new Insets(0, 0, 5, 5);
			gbc_txtXCoord.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtXCoord.gridx = 1;
			gbc_txtXCoord.gridy = 0;
			panel.add(txtXCoord, gbc_txtXCoord);
			txtXCoord.setColumns(10);
			txtXCoord.addKeyListener(inputListener);
			
		}
		{
			GridBagConstraints gbc_lblYCoord = new GridBagConstraints();
			gbc_lblYCoord.anchor = GridBagConstraints.EAST;
			gbc_lblYCoord.insets = new Insets(0, 0, 5, 5);
			gbc_lblYCoord.gridx = 0;
			gbc_lblYCoord.gridy = 1;
			panel.add(lblYCoord, gbc_lblYCoord);
			
		}
		{
			
			GridBagConstraints gbc_txtYCoord = new GridBagConstraints();
			gbc_txtYCoord.gridwidth = 2;
			gbc_txtYCoord.insets = new Insets(0, 0, 5, 5);
			gbc_txtYCoord.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtYCoord.gridx = 1;
			gbc_txtYCoord.gridy = 1;
			panel.add(txtYCoord, gbc_txtYCoord);
			txtYCoord.setColumns(10);
			txtYCoord.addKeyListener(inputListener);
			
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
			
			GridBagConstraints gbc_btnEdgeColor = new GridBagConstraints();
			gbc_btnEdgeColor.anchor = GridBagConstraints.WEST;
			gbc_btnEdgeColor.insets = new Insets(0, 0, 5, 5);
			gbc_btnEdgeColor.gridx = 1;
			gbc_btnEdgeColor.gridy = 4;
			panel.add(btnEdgeColor, gbc_btnEdgeColor);
			
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
				panel_1.add(pnlEdgeColor);
			}
		}
		
		pnlButton.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(pnlButton, BorderLayout.SOUTH);

		btnOk.setActionCommand("OK");
		pnlButton.add(btnOk);
		getRootPane().setDefaultButton(btnOk);

		btnCancel.setActionCommand("Cancel");
		pnlButton.add(btnCancel);
		
	}

	private void addBtnEdgeColorListener() {
		btnEdgeColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				Color chosenColor = JColorChooser.showDialog(getContentPane(), "Choose edge color", edgeColor);
				if(chosenColor != null) {
					edgeColor = chosenColor;
					pnlEdgeColor.setBackground(edgeColor);
				}
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
	public void setCreateDialogFields(Point point, Color edgeColor) {
		String xCoord = String.valueOf(point.getX());
		getTxtXCoord().setText(xCoord);
		
		String yCoord = String.valueOf(point.getY());
		getTxtYCoord().setText(yCoord);
		
		getPnlEdgeColor().setBackground(edgeColor);
		setEdgeColor(edgeColor);
	}
	
	
	public JPanel getContentPanel() {
		return contentPanel;
	}

	public KeyAdapter getInputListener() {
		return inputListener;
	}

	public Color getEdgeColor() {
		return edgeColor;
	}

	public void setEdgeColor(Color edgeColor) {
		this.edgeColor = edgeColor;
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

	public JButton getBtnOk() {
		return btnOk;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}

	public JPanel getPnlEdgeColor() {
		return pnlEdgeColor;
	}

	public JPanel getPanel() {
		return panel;
	}

	public JButton getBtnEdgeColor() {
		return btnEdgeColor;
	}

	
}
