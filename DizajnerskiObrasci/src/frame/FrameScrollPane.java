package frame;

import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class FrameScrollPane extends JScrollPane{

	private static final long serialVersionUID = 1L;
	private JList<String> list;

	public FrameScrollPane() {
		list = new JList<String>();
		list.setBackground(Color.WHITE);
		setViewportView(list);
	}

	public void setDefaultListModel(DefaultListModel<String> defaultListModel) {
		list.setModel(defaultListModel);
	}

}