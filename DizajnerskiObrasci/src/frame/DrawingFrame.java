package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllers.DrawingController;
import dialogs.DlgCircle;
import dialogs.DlgDonut;
import dialogs.DlgLine;
import dialogs.DlgPoint;
import dialogs.DlgRectangle;
import geometry.Point;
import geometry.Shape;
import logger.LogWriter;
import model.DrawingModel;
import view.DrawingPanel;

public class DrawingFrame extends JFrame {

	private JPanel contentPane;
	private JPanel pnlWest;
	private JPanel pnlNorth;
	private JPanel pnlSouth;

	private FrameScrollPane scrollPane;
	private FrameShapeToolBar shapeToolBar;
	private FrameColorToolBar colorToolBar;
	private FrameOptionsToolBar optionsToolBar;
	private FrameMenuBar menuBar;

	private DrawingPanel view;
	private DrawingController controller;
	
	
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
		view = new DrawingPanel();
		createPanels();
		createToolBars();
		scrollPane = new FrameScrollPane();
		menuBar = new FrameMenuBar();
		addViewListener();
		buildFrame();
				
	}
	
	private void createPanels() {
		contentPane = new JPanel();
		pnlNorth = new JPanel();
		pnlSouth = new JPanel();
		pnlWest = new JPanel();
	}
	
	private void createToolBars() {
		optionsToolBar = new FrameOptionsToolBar();
		shapeToolBar = new FrameShapeToolBar();
		colorToolBar = new FrameColorToolBar();
		shapeToolBar = new FrameShapeToolBar();
	}
	
	private void buildFrame() {
		setTitle("Dizajnerski obrasci");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 585);
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		contentPane.add(pnlNorth, BorderLayout.NORTH);
		contentPane.add(pnlSouth, BorderLayout.SOUTH);
		contentPane.add(pnlWest, BorderLayout.WEST);
		contentPane.add(view, BorderLayout.CENTER);
		
		pnlNorth.add(menuBar);
		pnlNorth.add(shapeToolBar);
		pnlNorth.add(colorToolBar);
		
		pnlWest.setLayout(new BoxLayout(pnlWest, BoxLayout.Y_AXIS));
		pnlWest.add(optionsToolBar);
		
		pnlSouth.setLayout(new BorderLayout(0, 0));
		pnlSouth.add(scrollPane);
		
	}
	

	private void addViewListener() {
		view.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				handleMouseClicked(arg0);
			}
		});
	}
	
	public void handleMouseClicked(MouseEvent click) {
		int x = click.getX();
		int y = click.getY();
		Point clickedPoint = new Point(x, y);
		
		if(optionsToolBar.isTglBtnSelectSelected()){
			controller.selectOrDeselectShape(clickedPoint);
		} else if (optionsToolBar.isTglBtnDrawSelected()) {
			if (shapeToolBar.isTlgBtnPointSelected()) {
				controller.drawPointIfAccepted(clickedPoint);
			} else if (shapeToolBar.isTlgBtnLineSelected()) {
				controller.drawLineIfAccepted(clickedPoint);
			} else if (shapeToolBar.isTlgBtnRectangleSelected()) {
				controller.drawRectangleIfAccepted(clickedPoint);
			} else if (shapeToolBar.isTlgBtnCircleSelected()) {
				controller.drawCircleIfAccepted(clickedPoint);
			} else if (shapeToolBar.isTlgBtnDonutSelected()) {
				controller.drawDonutIfAccepted(clickedPoint);
			} else if (shapeToolBar.isTlgBtnHexagonSelected()) {
				controller.drawHexagonIfAccepted(clickedPoint);
			}
		}
		view.repaint(); //TODO: controller should update view
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

	public FrameScrollPane getScrollPane() {
		return scrollPane;
	}

	public FrameShapeToolBar getShapeToolBar() {
		return shapeToolBar;
	}

	public FrameColorToolBar getColorToolBar() {
		return colorToolBar;
	}

	public FrameOptionsToolBar getOptionsToolBar() {
		return optionsToolBar;
	}
	
	public void setControllerForToolBars(DrawingController controller) {
		colorToolBar.setController(controller);
		optionsToolBar.setController(controller);
	}
	
	public void setDefaultListModelForScrollPane(DefaultListModel<String> defaultListModel) {
		scrollPane.setDefaultListModel(defaultListModel);
	}
	
	
}
