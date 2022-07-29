package view;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.List;
import javax.swing.JPanel;
import geometry.Shape;
import model.DrawingModel;

/**
 * View 
 * @author Natalija
 *
 */
public class DrawingPanel extends JPanel{
	
	private DrawingModel model;

	public DrawingPanel() {
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

