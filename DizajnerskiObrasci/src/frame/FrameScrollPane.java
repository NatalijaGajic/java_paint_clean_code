package frame;

import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class FrameScrollPane extends JScrollPane{
	
	private JList<String> list;
	private DefaultListModel<String> defaultListModel = new DefaultListModel<>();

	public FrameScrollPane() {
		list = new JList<String>();
		list.setBackground(Color.WHITE);
		list.setModel(defaultListModel);
		setViewportView(list);
		setBounds(0, 0, 1000, 500);
	}
	
}