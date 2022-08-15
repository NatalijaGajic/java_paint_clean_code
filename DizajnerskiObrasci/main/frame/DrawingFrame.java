package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import controllers.*;
import geometry.*;
import view.DrawingPanel;

public class DrawingFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnlWest;
	private JPanel pnlNorth;
	private JPanel pnlSouth;
	private FrameScrollPane scrollPane;
	private FrameShapeToolBar shapeToolBar;
	private FrameColorToolBar colorToolBar;
	private FrameDrawingOptionsToolBar drawingOptionsToolBar;
	private FrameCommandOptionsToolBar commandOptionsToolBar;
	private FrameMenuBar menuBar;
	private DrawingPanel view;
	private DrawingController drawingController;
	
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
		drawingOptionsToolBar = new FrameDrawingOptionsToolBar();
		commandOptionsToolBar = new FrameCommandOptionsToolBar();
		shapeToolBar = new FrameShapeToolBar();
		colorToolBar = new FrameColorToolBar();
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
		pnlWest.add(drawingOptionsToolBar);
		pnlWest.add(commandOptionsToolBar);
		
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
		
		if(drawingOptionsToolBar.isTglBtnSelectSelected()){
			drawingController.selectOrDeselectShape(clickedPoint);
		} else if (drawingOptionsToolBar.isTglBtnDrawSelected()) {
			if (shapeToolBar.isTlgBtnPointSelected()) {
				drawingController.drawPointIfAccepted(clickedPoint);
			} else if (shapeToolBar.isTlgBtnLineSelected()) {
				drawingController.drawLineIfAccepted(clickedPoint);
			} else if (shapeToolBar.isTlgBtnRectangleSelected()) {
				drawingController.drawRectangleIfAccepted(clickedPoint);
			} else if (shapeToolBar.isTlgBtnCircleSelected()) {
				drawingController.drawCircleIfAccepted(clickedPoint);
			} else if (shapeToolBar.isTlgBtnDonutSelected()) {
				drawingController.drawDonutIfAccepted(clickedPoint);
			} else if (shapeToolBar.isTlgBtnHexagonSelected()) {
				drawingController.drawHexagonIfAccepted(clickedPoint);
			}
		}
	}

	public DrawingPanel getView() {
		return view;
	}

	public DrawingController getDrawingController() {
		return drawingController;
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
	
	public FrameDrawingOptionsToolBar getDrawingOptionsToolBar() {
		return drawingOptionsToolBar;
	}

	public FrameCommandOptionsToolBar getCommandOptionsToolBar() {
		return commandOptionsToolBar;
	}

	public FrameMenuBar getFrameMenuBar() {
		return menuBar;
	}
	
	public void setDrawingController(DrawingController drawingController) {
		this.drawingController = drawingController;
		setDrawingControllerForToolBars();
	}
	
	private void setDrawingControllerForToolBars() {
		colorToolBar.setDrawingController(drawingController);
		drawingOptionsToolBar.setDrawingController(drawingController);
	}
	
	public void setButtonsControllerForOptionsToolBar(ButtonsController buttonsController) {
		drawingOptionsToolBar.setButtonsController(buttonsController);
		commandOptionsToolBar.setButtonsController(buttonsController);
	}
	
	public void setDefaultListModelForScrollPane(DefaultListModel<String> defaultListModel) {
		scrollPane.setDefaultListModel(defaultListModel);
	}
	
	public void setFileControllerForMenuBar(FileController fileController) {
		menuBar.setFileController(fileController);
	}
	
	public void setCommandControllerForOptionsToolBar(CommandController commandController) {
		commandOptionsToolBar.setCommandController(commandController);
	}
}
