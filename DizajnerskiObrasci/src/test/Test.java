package test;

import java.awt.Color;
import java.util.ArrayList;

import commands.*;
import geometry.Circle;
import geometry.Donut;
import geometry.HexagonAdapter;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import model.DrawingModel;

public class Test {

	
	public static void main(String[] args) {
		Point testPoint = new Point(1, 1, false, Color.BLACK);
		Line testLine = new Line(new Point(1,1), new Point(2,2), false, Color.BLACK);
		Rectangle testRectangle = new Rectangle(new Point(1,1), 40, 50, false, Color.WHITE, Color.BLACK);
		Circle testCircle = new Circle(new Point(1, 1), 10, false, Color.WHITE, Color.BLACK);
		Donut testDonut = new Donut(new Point(1, 1), 10, 5, false, Color.WHITE, Color.BLACK);
		HexagonAdapter testHexagon = new HexagonAdapter(new Point(1,2), 3, Color.WHITE, Color.BLACK);
		
		printSplits(testPoint.toString());
		printSplits(testLine.toString());
		printSplits(testCircle.toString());
		printSplits(testDonut.toString());
		printSplits(testRectangle.toString());
		printSplits(testHexagon.toString());
		
		CmdAdd cmdAdd = new CmdAdd(null, testPoint);
		CmdModify cmdModify = new CmdModify(testPoint, testPoint);
		CmdBringToBack cmdBringToBack = new CmdBringToBack(new DrawingModel(), testPoint);
		CmdBringToFront cmdBringToFront = new CmdBringToFront(new DrawingModel(), testPoint);
		CmdToFront cmdToFront = new CmdToFront(new DrawingModel(), testPoint);
		CmdToBack cmdToBack = new CmdToBack(new DrawingModel(), testPoint);
		CmdSelect cmdSelect = new CmdSelect(null, testPoint);
		CmdDeselect cmdDeselect = new CmdDeselect(null, testPoint);
		ArrayList<Shape> list = new ArrayList<Shape>();
		list.add(testPoint);
		CmdDelete cmdDelete = new CmdDelete(new DrawingModel(), list);
		
		printSplits(cmdAdd.toString());
		printSplits(cmdModify.toString());
		printSplits(cmdBringToBack.toString());
		printSplits(cmdBringToFront.toString());
		printSplits(cmdToFront.toString());
		printSplits(cmdToBack.toString());
		printSplits(cmdSelect.toString());
		printSplits(cmdDeselect.toString());
		printSplits(cmdDelete.toString());
		
	}
	
	private static void printSplits(String line) {
		System.out.println("****************************************");
		String[] splits = line.split("[, =():]");
		for (int i=0; i < splits.length; i++) {
			System.out.println("[" + i + "]:" + splits[i]);
		}
	}

}
