package rd.fractal;

/**
 * Represents a 2D-Point
 */
public class Point {
	double x;
	double y;

	public Point(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	public void add(double value) {
		x += value;
		y += value;
	}

	public void add(Point p) {
		x = x + p.x;
		y = y + p.y;
	}

}
