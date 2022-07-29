package frame;

import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

public class FrameShapeToolBar extends JToolBar{

	
	private JToggleButton tglbtnPoint;
	private JToggleButton tglbtnLine;
	private JToggleButton tglbtnRectangle;
	private JToggleButton tglbtnCircle;
	private JToggleButton tglbtnDonut;
	private JToggleButton tglbtnHexagon;
	
	private final ButtonGroup btnShapes = new ButtonGroup();
	
	public FrameShapeToolBar() {
		createToggleButtons();
		addToggleButtonsToToolBar();
		addToggleButtonsToButtonGroup();
	}
	
	private void createToggleButtons() {
		tglbtnPoint = new JToggleButton("     Point    ");
		tglbtnLine = new JToggleButton("     Line    ");
		tglbtnRectangle = new JToggleButton("Rectangle");
		tglbtnCircle = new JToggleButton("   Circle   ");
		tglbtnDonut = new JToggleButton("   Donut   ");
		tglbtnHexagon = new JToggleButton(" Hexagon ");
	}
	
	private void addToggleButtonsToToolBar() {
		add(tglbtnPoint);
		add(tglbtnLine);
		add(tglbtnRectangle);
		add(tglbtnCircle);
		add(tglbtnDonut);
		add(tglbtnHexagon);
	}
	
	private void addToggleButtonsToButtonGroup() {
		btnShapes.add(tglbtnPoint);
		btnShapes.add(tglbtnLine);
		btnShapes.add(tglbtnRectangle);
		btnShapes.add(tglbtnCircle);
		btnShapes.add(tglbtnDonut);
		btnShapes.add(tglbtnHexagon);
	}
	
	public boolean isTlgBtnPointSelected() {
		return tglbtnPoint.isSelected();
	}
	
	public boolean isTlgBtnLineSelected() {
		return tglbtnLine.isSelected();
	}
	
	public boolean isTlgBtnCircleSelected() {
		return tglbtnCircle.isSelected();
	}
	
	public boolean isTlgBtnRectangleSelected() {
		return tglbtnRectangle.isSelected();
	}
	
	public boolean isTlgBtnDonutSelected() {
		return tglbtnDonut.isSelected();
	}
	
	public boolean isTlgBtnHexagonSelected() {
		return tglbtnHexagon.isSelected();
	}
	
}