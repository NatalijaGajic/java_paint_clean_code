package views;

import java.awt.Graphics;
import java.util.Iterator;
import javax.swing.JPanel;
import geometry.DrawingModel;
import geometry.Shape;

public class DrawingView extends JPanel {

	private DrawingModel model;

	
	public void setModel(DrawingModel model) {
		this.model = model;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Iterator<Shape> it = model.getShapes().iterator();	
		while(it.hasNext()) {
			it.next().draw(g);
		}
	}	
}
