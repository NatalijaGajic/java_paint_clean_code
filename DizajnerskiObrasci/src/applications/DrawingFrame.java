package applications;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import controllers.DrawingController;
import dialogs.*;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import modification.DlgChangeCircle;
import modification.DlgChangeDonut;
import modification.DlgChangeLine;
import modification.DlgChangePoint;
import modification.DlgChangeRectangle;

public class DrawingFrame extends JFrame {

	private JPanel contentPane;
	private JPanel pnlNorth;
	private JPanel pnlSouth;
	private JPanel pnlColors;
	private JPanel pnlActiveEdgeColor;
	private JPanel pnlActiveInnerColor;
	private JToggleButton tglbtnDraw;
	private JToggleButton tglbtnSelect;
	private JToggleButton tglbtnPoint;
	private JToggleButton tglbtnLine;
	private JToggleButton tglbtnRectangle;
	private JToggleButton tglbtnCircle;
	private JToggleButton tglbtnDonut;
	private JButton btnActiveEdgeColor;
	private JButton btnActiveInnerColor;
	private JButton btnModify;
	private JButton btnDelete;
	private JButton btnExit;
	private final ButtonGroup btnShapes = new ButtonGroup();
	private final ButtonGroup btnMode = new ButtonGroup();

	private DrawingPanel view = new DrawingPanel();
	private DrawingModel model = new DrawingModel();
	private DrawingController controller = new DrawingController(model, this);

	private Color activeEdgeColor = Color.BLACK;
	private Color activeInnerColor = Color.WHITE;
	
	private Point startPoint;
	private Shape selected;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					DrawingFrame frame = new DrawingFrame();
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
	public DrawingFrame() {
		contentPane = new JPanel();
		pnlNorth = new JPanel();
		pnlSouth = new JPanel();
		pnlColors = new JPanel();
		pnlActiveEdgeColor = new JPanel();
		pnlActiveInnerColor = new JPanel();
		tglbtnDraw = new JToggleButton("Draw");
		tglbtnSelect = new JToggleButton("Select");
		tglbtnPoint = new JToggleButton("Point");
		tglbtnLine = new JToggleButton("Line");
		tglbtnRectangle = new JToggleButton("Rectangle");
		tglbtnCircle = new JToggleButton("Circle");
		tglbtnDonut = new JToggleButton("Donut");
		btnActiveEdgeColor = new JButton("Active Edge Color");
		btnActiveInnerColor = new JButton("Active Inner Color");
		btnModify = new JButton("Modify");
		btnDelete = new JButton("Delete");
		btnExit = new JButton("Exit");
		addBtnActiveEdgeColorListener();
		addBtnActiveInnerColorListener();
		addBtnModifyListener();
		addBtnDeleteListener();
		addBtnExitListener();
		addViewListener();
		buildFrame();
		
		view.setModel(model);
		view.setBackground(Color.WHITE);
		contentPane.add(view, BorderLayout.CENTER);
		
	}
	
	private void buildFrame() {
		setTitle("Dizajnerski obrasci");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 728, 424);
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		contentPane.add(pnlNorth, BorderLayout.NORTH);
		contentPane.add(pnlSouth, BorderLayout.SOUTH);
		
		pnlNorth.add(tglbtnPoint);
		btnShapes.add(tglbtnPoint);
		
		pnlNorth.add(tglbtnLine);
		btnShapes.add(tglbtnLine);
		
		pnlNorth.add(tglbtnRectangle);
		btnShapes.add(tglbtnRectangle);
		
		pnlNorth.add(tglbtnCircle);
		btnShapes.add(tglbtnCircle);
		
		pnlNorth.add(tglbtnDonut);
		btnShapes.add(tglbtnDonut);
		
		pnlNorth.add(pnlColors);
		
		pnlColors.add(btnActiveEdgeColor);
		pnlActiveEdgeColor.setBackground(activeEdgeColor);
		pnlColors.add(pnlActiveEdgeColor);
		
		pnlColors.add(btnActiveInnerColor);
		pnlActiveInnerColor.setBackground(activeInnerColor);
		pnlColors.add(pnlActiveInnerColor);

		pnlSouth.add(tglbtnDraw);
		btnMode.add(tglbtnDraw);
		
		pnlSouth.add(tglbtnSelect);
		btnMode.add(tglbtnSelect);

		pnlSouth.add(btnModify);

		pnlSouth.add(btnDelete);
		
		pnlSouth.add(btnExit);

		pnlNorth.repaint();
	}
	
	private void addBtnActiveEdgeColorListener() {
		btnActiveEdgeColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				activeEdgeColor = JColorChooser.showDialog(null, "Choose edge color", Color.BLACK);
				if (activeEdgeColor != null) {
					pnlActiveEdgeColor.setBackground(activeEdgeColor);
				}
			}
		});
	}
	
	private void addBtnActiveInnerColorListener() {
		btnActiveInnerColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				activeInnerColor = JColorChooser.showDialog(null, "Choose inner color", Color.WHITE);
				if (activeInnerColor != null) {
					pnlActiveInnerColor.setBackground(activeInnerColor);
				}
			}
		});
	}
	
	private void addBtnModifyListener() {
		btnModify.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selected != null) {
					if (selected instanceof Point) {
						Point point = (Point) selected;
						DlgPoint dlg = new DlgPoint();
						dlg.setModifyDialogFields(point);
						dlg.setVisible(true);
						controller.modifyShapeIfAccepted(dlg, point);
					} else if (selected instanceof Line) {
						Line line = (Line) selected;
						DlgLine dlg = new DlgLine();
						dlg.setModifyDialogFields(line);
						dlg.setVisible(true);
						controller.modifyShapeIfAccepted(dlg, line);
					} else if (selected instanceof Rectangle) {
						Rectangle rectangle = (Rectangle) selected;
						DlgRectangle dlg = new DlgRectangle();
						dlg.setModifyDialogFields(rectangle);
						dlg.setVisible(true);
						controller.modifyShapeIfAccepted(dlg, rectangle);
					} else if (selected instanceof Donut) {
						Donut donut = (Donut) selected;
						DlgDonut dlg = new DlgDonut();
						dlg.setModifyDialogFields(donut);
						dlg.setVisible(true);
						controller.modifyShapeIfAccepted(dlg, donut);
					} else if (selected instanceof Circle) {
						Circle circle = (Circle) selected;
						DlgCircle dlg = new DlgCircle();
						dlg.setModifyDialogFields(circle);
						dlg.setVisible(true);
						controller.modifyShapeIfAccepted(dlg, circle);
					}
				}
				view.repaint();
			}
		});
	}
	
	private void addBtnDeleteListener() {
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] options = { "Yes", "No" };
				if (selected != null) {
					int option = JOptionPane.showOptionDialog(null, "Are you sure?", "WARNING!", JOptionPane.OK_OPTION,
							JOptionPane.ERROR_MESSAGE, null, options, options[0]);
					if (option == 0) {
						view.getShapes().remove(selected);
						view.repaint();
					}
				}
			}
		});
	}
	
	private void addBtnExitListener() {
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
	
	private void addViewListener() {
		view.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				thisMouseClicked(arg0);
			}
		});
	}
	
	private void thisMouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		if(tglbtnSelect.isSelected()) {
			selected = null;
			Iterator<Shape> it = model.getShapes().iterator();
			while(it.hasNext()) {
				Shape shape = it.next();
				shape.setSelected(false);
				if(shape.contains(e.getX(), e.getY()))
					selected = shape;
			}
			
			if (selected != null) {
				selected.setSelected(true);
			}
			
		} else if (tglbtnDraw.isSelected()) {
			Iterator<Shape> it = model.getShapes().iterator();
			while(it.hasNext()) {
				Shape shape = it.next();
				shape.setSelected(false);
			}
			
			Point point = new Point(x, y);
			
			if (tglbtnPoint.isSelected()) {
				DlgPoint dlgPoint = new DlgPoint();
				dlgPoint.setCreateDialogFields(point, activeEdgeColor);
				dlgPoint.setVisible(true);
				controller.addShapeIfAccepted(dlgPoint);
			} else if (tglbtnLine.isSelected()) {
				if (startPoint == null)
					startPoint = new Point(x, y);
				else {
					DlgLine dlgLine = new DlgLine();
					dlgLine.setCreateDialogFields(startPoint, point, activeEdgeColor);
					dlgLine.setVisible(true);
					controller.addShapeIfAccepted(dlgLine);
					startPoint = null;
				}
			} else if (tglbtnRectangle.isSelected()) {
				DlgRectangle dlgRect = new DlgRectangle();
				dlgRect.setCreateDialogFields(point, activeEdgeColor, activeInnerColor);
				dlgRect.setVisible(true);
				controller.addShapeIfAccepted(dlgRect);
			} else if (tglbtnCircle.isSelected()) {
				DlgCircle dlgCircle = new DlgCircle();
				dlgCircle.setCreateDialogFields(point, activeEdgeColor, activeInnerColor);
				dlgCircle.setVisible(true);
				controller.addShapeIfAccepted(dlgCircle);
			} else if (tglbtnDonut.isSelected()) {
				DlgDonut dlgDonut = new DlgDonut();
				dlgDonut.setCreateDialogFields(point, activeEdgeColor, activeInnerColor);
				dlgDonut.setVisible(true);
				controller.addShapeIfAccepted(dlgDonut);
			}
		}
		repaint();
	}

	public JToggleButton getTglbtnSelect() {
		return tglbtnSelect;
	}

	public void setTglbtnSelect(JToggleButton tglbtnSelect) {
		this.tglbtnSelect = tglbtnSelect;
	}

	public JToggleButton getTglbtnPoint() {
		return tglbtnPoint;
	}

	public void setTglbtnPoint(JToggleButton tglbtnPoint) {
		this.tglbtnPoint = tglbtnPoint;
	}

	public JToggleButton getTglbtnLine() {
		return tglbtnLine;
	}

	public void setTglbtnLine(JToggleButton tglbtnLine) {
		this.tglbtnLine = tglbtnLine;
	}

	public JToggleButton getTglbtnRectangle() {
		return tglbtnRectangle;
	}

	public void setTglbtnRectangle(JToggleButton tglbtnRectangle) {
		this.tglbtnRectangle = tglbtnRectangle;
	}

	public JToggleButton getTglbtnCircle() {
		return tglbtnCircle;
	}

	public void setTglbtnCircle(JToggleButton tglbtnCircle) {
		this.tglbtnCircle = tglbtnCircle;
	}

	public JToggleButton getTglbtnDonut() {
		return tglbtnDonut;
	}

	public void setTglbtnDonut(JToggleButton tglbtnDonut) {
		this.tglbtnDonut = tglbtnDonut;
	}

	public ButtonGroup getBtnShapes() {
		return btnShapes;
	}

	public Color getActiveEdgeColor() {
		return activeEdgeColor;
	}

	public void setActiveEdgeColor(Color activeEdgeColor) {
		this.activeEdgeColor = activeEdgeColor;
	}

	public Color getActiveInnerColor() {
		return activeInnerColor;
	}

	public void setActiveInnerColor(Color activeInnerColor) {
		this.activeInnerColor = activeInnerColor;
	}

	public ButtonGroup getBtnMode() {
		return btnMode;
	}

	public JToggleButton getTglbtnDraw() {
		return tglbtnDraw;
	}

	public void setTglbtnDraw(JToggleButton tglbtnDraw) {
		this.tglbtnDraw = tglbtnDraw;
	}

	public DrawingPanel getView() {
		return view;
	}

	public void setView(DrawingPanel view) {
		this.view = view;
	}

	public void setController(DrawingController controller) {
		this.controller = controller;
	}
	
	

}
