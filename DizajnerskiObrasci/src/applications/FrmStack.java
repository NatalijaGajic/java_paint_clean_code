package applications;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import dialogues.DlgRectangle;
import geometry.Rectangle;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.Stack;
import java.awt.event.ActionEvent;

public class FrmStack extends JFrame {

	private JPanel contentPane;
	private DefaultListModel<Rectangle> dlm= new DefaultListModel<Rectangle>();
	private Stack<Rectangle> addRec=new Stack<Rectangle>();
	private JList<Rectangle> list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmStack frame = new FrmStack();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmStack() {
		setTitle("Milunovic Nemanja IT33/2018");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton btnAddRectangle = new JButton("Add Rectangle");
		btnAddRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgRectangle dlgRec = new DlgRectangle();
				dlgRec.setVisible(true);
				if(dlgRec.getRec() != null) {
					addRec.push(dlgRec.getRec());
					dlm.add(0, dlgRec.getRec());
				}
			}
		});
		panel.add(btnAddRectangle);
		
		JButton btnExcludeRectangle = new JButton("Exclude Rectangle");
		btnExcludeRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (addRec.isEmpty()) {
					String[] opcije = {"U redu"};
					JOptionPane.showOptionDialog(null, "Stack is empty!", "Message", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcije, opcije[0]);
					return;
				}
				DlgRectangle dlgRec=new DlgRectangle();
				dlgRec.setRec(addRec.peek());
				dlgRec.setVisible(true);
				if(dlgRec.isCheck()) {
					addRec.pop();
					dlm.remove(0);
				}
				
			}
		});
		panel.add(btnExcludeRectangle);
		
		JLabel lblRectangles = new JLabel("Rectangles");
		lblRectangles.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblRectangles, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		list = new JList<Rectangle>();
		scrollPane.setViewportView(list);
		list.setModel(dlm); //
	}

}
