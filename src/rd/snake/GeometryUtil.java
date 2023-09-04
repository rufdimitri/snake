package rd.snake;

import java.awt.geom.Point2D;

public class GeometryUtil {
	// A method that takes two points (p and o) and an angle (theta) in radians, and
	// returns a new point that is the result of rotating p around o by theta
	static public Point2D.Double rotatePoint(Point2D.Double p, Point2D.Double anchorPoint, double theta) {
		// Calculate the difference between the coordinates of p and o
		double dx = p.x - anchorPoint.x;
		double dy = p.y - anchorPoint.y;

		// Apply the rotation matrix formula to get the new coordinates of p'
		double x = Math.cos(theta) * dx - Math.sin(theta) * dy + anchorPoint.x;
		double y = Math.sin(theta) * dx + Math.cos(theta) * dy + anchorPoint.y;

		// Create and return a new point with the new coordinates
		return new Point2D.Double(x, y);
	}
}
