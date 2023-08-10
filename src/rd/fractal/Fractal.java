package rd.fractal;

import java.util.LinkedList;
import java.util.Random;

/**
 *  https://www.youtube.com/watch?v=uc2yok_pLV4
 *  Formula Zn = Z   ^2 + C
 *                n-1
 */
public class Fractal {
	public final LinkedList<Point> points = new LinkedList<>();
	private final Random random = new Random();
	private final Point constant = new Point(0.12, -0.14);

	public void calcStep() {
		if (points.isEmpty()) {
			points.add(random());
		} else {
			Point next = computeNext(points.getLast(), constant);
			System.out.format("lx = %3.2f ly = %3.2f\n", points.getLast().x, points.getLast().y);
			System.out.format(" x = %3.2f  y = %3.2f\n", next.x, next.y);
			points.add(next);
//			points.add(random());
		}
	}

	private Point random() {
		double scale = 1;
		return new Point(random.nextDouble(scale) - scale / 2, random.nextDouble(scale) - scale / 2);
	}

	/** Compute Zn²+C
	 * 
	 * @param current
	 * @param constant
	 * @return
	 */
	public Point computeNext(Point current, Point constant) {
		// Zn²
		final double zr = current.x * current.x - current.y * current.y;
		final double zi = 2.0 * current.x * current.y;
		Point point = new Point(zr, zi);

		// Add constant
		point.add(constant);
		return point;
	}

	public double mod2(Point p) {
		return p.x * p.x + p.y * p.y;
	}

}
