package rd.fractal;

/**
 *  https://www.youtube.com/watch?v=uc2yok_pLV4
 *  Formula Zn = Z   ^2 + C
 *                n-1
 */
public class Fractal {

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
