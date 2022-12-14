package view;

import java.awt.*;
import java.util.Iterator;
import java.util.List;
import javax.swing.JPanel;
import geometry.Shape;
import model.DrawingModel;

public class DrawingPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private DrawingModel model;

	public DrawingPanel() {
		setBackground(Color.WHITE);
	}
		 
	public void paint(Graphics g) {
		super.paint(g);
		Iterator<Shape> it = model.getShapes().iterator();
		while(it.hasNext())
		{
			it.next().draw(g);
		}
	}
	
	public List<Shape> getShapes() {
		return model.getShapes();
	}
	
	public void setModel(DrawingModel model) {
		this.model = model;
	}
	
	}

