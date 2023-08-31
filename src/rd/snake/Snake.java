package rd.snake;

import java.awt.geom.Point2D;
import java.util.LinkedList;

public class Snake {
	private LinkedList<Segment> segments = new LinkedList<>();

	public static class Segment {
		public Point2D.Double position;
		public double rotation; // angle in radian

		public Segment(Point2D.Double position, double rotation) {
			super();
			this.position = position;
			this.rotation = rotation;
		}
	}

	public LinkedList<Segment> getSegments() {
		return segments;
	}

	public Segment getHead() {
		return segments.getFirst();
	}

}
