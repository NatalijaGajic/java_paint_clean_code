package applications;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dialogues.DlgCircleDraw;
import dialogues.DlgDonutDraw;
import dialogues.DlgRectangleDraw;
import geometry.Circle;
import geometry.Donut;
import geometry.DrawingModel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;

public class DrawingPanel extends JPanel{
	
	private FrmDrawing frame;
	private DrawingModel model;
	private Point startPoint;
	private Shape selected;
	
	public DrawingPanel(FrmDrawing frame) {
		//super.paint(g);
		setBackground(Color.WHITE);
		this.frame=frame;
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				thisMouseClicked(arg0);
			}
		});
	}
	
	protected void thisMouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		
		if(frame.getTglbtnSelect().isSelected()) {
			selected=null;
			Iterator<Shape> it=model.getShapes().iterator();
			while(it.hasNext()) {
				
				Shape shape=it.next();
				shape.setSelected(false);
				if(shape.contains(e.getX(), e.getY()))
					selected=shape;
			}
			
			if (selected!=null) {
				selected.setSelected(true);
			}
			
		} else if (frame.getTglbtnDraw().isSelected()) {
				Iterator<Shape> it=model.getShapes().iterator();
				while(it.hasNext()) {
					Shape shape=it.next();
					shape.setSelected(false);
				}
			
				 if (frame.getTglbtnPoint().isSelected()) {
				model.add(new Point(x, y, frame.getActiveColor()));
			}else if(frame.getTglbtnLine().isSelected()) {
				if(startPoint==null)
					startPoint=new Point(x, y);
				else {
					model.add(new Line(startPoint, new Point(x, y), frame.getActiveColor()));
					startPoint=null;
				}
			}else if (frame.getTglbtnRectangle().isSelected()) {
				DlgRectangleDraw dlg=new DlgRectangleDraw();
				dlg.setVisible(true);
					model.add(new Rectangle(new Point (x,y), dlg.getHeightR(), dlg.getWidthR(), frame.getActiveColor(), frame.getActiveInnerColor()));
	
			}else if (frame.getTglbtnCircle().isSelected()) {
				DlgCircleDraw dlg=new DlgCircleDraw();
				dlg.setVisible(true);
				model.add(new Circle(new Point(x, y),dlg.getRadius(), frame.getActiveColor(), frame.getActiveInnerColor()));
				
			} else if (frame.getTglbtnDonut().isSelected()) {
				DlgDonutDraw dlg = new DlgDonutDraw();
				dlg.setVisible(true);
				
				model.add(new Donut(new Point(x, y), dlg.getRadius(), dlg.getInnerRadius(), frame.getActiveColor(), frame.getActiveInnerColor()));
		
			}
		}
		repaint();
		
	}
		
		 
	public void paint(Graphics g) {
		super.paint(g);
		Iterator<Shape> it=model.getShapes().iterator();
		while(it.hasNext())
		{
			it.next().draw(g);
			/*
			 Shape s=it.next();
			if(s instanceof Point) {
				((Point)s).draw(g);
			} else if (s instanceof Line) {
				((Line)s).draw(g);
			} else if (s instanceof Rectangle) {
				((Rectangle)s).draw(g);
			} else if (s instanceof Circle) {
				((Circle)s).draw(g);
			} else if (s instanceof Donut) {
				((Donut)s).draw(g);
			} 
			 */
		}
	}
	
	public Shape getSelected() {
		return selected;
	}
	
	public List<Shape> getShapes() {
		return model.getShapes();
	}
	
	public void setSelected(Shape selected) {
		this.selected=selected;
	}
	
	public void setModel(DrawingModel model) {
		this.model = model;
	}
	
	}

