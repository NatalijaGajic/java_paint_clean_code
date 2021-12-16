package applications;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Rectangle;
import dialogues.DlgRectangle;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;

public class FrmSort extends JFrame {

	private JPanel contentPane;
	private ArrayList<Rectangle> addRec=new ArrayList<Rectangle>();
	private JList<Rectangle> list;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmSort frame = new FrmSort();
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
	public FrmSort() {
		setTitle("Milunovic Nemanja IT33/2018");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JButton btnAddRectangle = new JButton("Add Rectangle");
		btnAddRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgRectangle dlgRec=new DlgRectangle();
				dlgRec.setVisible(true);
				if(dlgRec.getRec()!=null) {
					
					addRec.add(dlgRec.getRec());
				}
				addRec.sort(null);
				list.setModel(fillJList());
			}
		});
		panel.add(btnAddRectangle);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		list = new JList<Rectangle>();
		scrollPane.setViewportView(list);
		
	}
	
	private DefaultListModel<Rectangle> fillJList(){
		Iterator<Rectangle> iter=addRec.iterator();
		DefaultListModel<Rectangle>dlm=new DefaultListModel<Rectangle>();
		while(iter.hasNext()) {
			dlm.addElement(iter.next());
		}
		return dlm;
	}

}
