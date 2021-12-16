package geometry;

import java.util.Arrays;
import java.util.HashMap;

public class Test {
	
public static void main(String[] args) {
		
		Point p = new Point();
		p.setX(10);
		p.setY(20);
		
		double rez = p.distance(50, 70);
		System.out.println("Distance is = " + rez);
		
		Point p1 = new Point();
		p1.setX(15);
		p1.setY(27);
		
		// 1. Inicijalizovati x koordinatu tacke p
		// na vrednost y koordinate tacke p1
		p.setX(p1.getY());
		System.out.println("X of point p is " + p.getX());
		// 2. Postaviti za pocetnu tacku linije l1 tacku p, a
		// za krajnju tacku linije l1 tacku p1
		Line l1 = new Line();
		l1.setStartPoint(p);
		l1.setEndPoint(p1);
		// 3. Postaviti y koordinatu krajnje tacke l1 na 23
		l1.getEndPoint().setY(23);
		System.out.println("Y of end point of line l1 is " + l1.getEndPoint().getY());
		// 4. Inicijalizovati x koordinatu pocetne tacke linije l1
		// na vrednost y koordinate krajnje tacke linije l1
		l1.getStartPoint().setX(l1.getEndPoint().getX());
		System.out.println("x of start point of l1 is " + l1.getStartPoint().getX());
		// 5. Postaviti x koordinatu krajnje tacke l1 na vrednost
		// duzine linije l1 umanjene za vrednost zbira x i y
		// koordinate pocetne tacke linije
		l1.getEndPoint().setX((int) (l1.length() - (l1.getStartPoint().getX() + l1.getStartPoint().getY())));
		System.out.println("x of end point of l1 is " + l1.getEndPoint().getX());
		// 6. Postaviti x koordinatu tacke gore levo pravougaonika
		// r1 na vrednost 10 i y koordinatu na vrednost 15
		Rectangle r1 = new Rectangle();
		r1.getUpperLeftPoint().setX(10); 
		r1.getUpperLeftPoint().setY(15);
		System.out.println("X of upper left point of r1 is " + r1.getUpperLeftPoint().getX() + '\n'
				+ "Y of upper left point of r1 is " + r1.getUpperLeftPoint().getY());
		// 7. Postaviti centar kruga c1 na koordinate tacke
		// gore levo od r1
		Circle c1 = new Circle();
		c1.setCenter(r1.getUpperLeftPoint());
		// 8. Postaviti x koordinatu centra kruga na vrednost razlike
		// povrsine pravougaonika i y koordinate pocetne tacke linije l1
		c1.getCenter().setX( (int) r1.area() - l1.getStartPoint().getY());
		System.out.println("x of circle c1 is " + c1.getCenter().getX());
		
		Point p3 = new Point(10,20);
		Point p4 = new Point(30, 30);
		
		Line lin1 = new Line(p3, p4);
		
		Circle c2 = new Circle(p3, 10);
		
		Rectangle r2 = new Rectangle(p3, 10, 20);
		
		System.out.println(c1.getCenter().getX());
		
		System.out.println(p3);
		
		int a = 10;
		int b = 10;
		
		System.out.println(a == b);
		
		Point p5 = new Point(10, 20);
		Point p6 = new Point(10, 20);
		
		System.out.println(p5 == p6);
		
		//p6 = p5;
		
		System.out.println(p5 == p6);
		
		//p6.setX(20);
		
		System.out.println(p6);
		System.out.println(p5);
		
		System.out.println(p5.equals(p6));
		
		String s1 = "pera";
		
		String s2 = "pera";
		
		System.out.println(s1 == s2);
		
		String s3 = new String("pera");
		String s4 = new String("pera");
		
		System.out.println(s3 == s4);
		
		System.out.println(s3.equals(s4));
		
		Donut d = new Donut(new Point(100, 200), 60, 30);
		System.out.println(d.toString());
		System.out.println(d.equals(p6));
		System.out.println(d.contains(500, 600));
		System.out.println(d.area());
		
		// dinamicko povezivanje
		Circle c3 = new Donut(new Point(10, 10), 50, 30);
		
		System.out.println(p1.compareTo(p3));
		
		// Niz i sortiranje niza
		Point p9 = new Point(5, 5);
		Point p7 = new Point(10, 10);
		Point p8 = new Point(2, 2);
		Point p10 = new Point(15, 15);
		
		Point[] tacke = {p9, p7, p8, p10};
		
		System.out.println("Nesortiran niz tacaka");
		for (int i = 0; i < tacke.length; i++) {
			System.out.println(tacke[i]);
		}
		
		Arrays.sort(tacke);
		
		System.out.println("Sortiran niz tacaka");
		for (int i = 0; i < tacke.length; i++) {
			System.out.println(tacke[i]);
		}
		
		// HashMap-e
		HashMap<String, Shape> map = new HashMap<String, Shape>();
		map.put("point", p1);
		map.put("rectangle", r1);
		map.put("Point", p3);
		
		System.out.println("Point from map is: " + map.get("point"));
		System.out.println("Point from map is: " + map.get("Point"));
		
		Point p2 = new Point(200,200);
		map.put("point", p2);
		System.out.println("Point from map is: " + map.get("point"));
		
		Circle c5 = new Circle(p7, 20);
		
		try {
			c5.setRadius(-50);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Ja se uvek izvrsavam");
		}
		
	}

}
