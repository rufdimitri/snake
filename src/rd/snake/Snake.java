package rd.snake;

import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.Objects;

public class Snake {
	private LinkedList<Segment> segments = new LinkedList<>();
	public Snake.Segment prevHeadPosition;

	public static class Segment {
		public Point2D.Double position;
		public double rotation; // angle in radian

		public Segment(Point2D.Double position, double rotation) {
			super();
			this.position = position;
			this.rotation = rotation;
		}

		@Override
		public int hashCode() {
			return Objects.hash(position, rotation);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Segment other = (Segment) obj;
			return position.x - other.position.x < 0.01 && position.y - other.position.y < 0.01
					&& rotation - other.rotation < 0.01;
		}

	}

	public LinkedList<Segment> getSegments() {
		return segments;
	}

	public Segment getHead() {
		return segments.getFirst();
	}

	public void updateTailPosition() {
		if (prevHeadPosition != null && prevHeadPosition.position.x == getHead().position.x
				&& prevPosition.y == getHead().position.y) {
			return;
		}
		Segment prevSegment = null;
		for (Segment segment : segments) {
			if (prevSegment == null) {
				prevSegment = segment;
			} else if (prevSegment == getHead()) {
				segment.position.x = prevPosition.x;
				segment.position.y = prevPosition.y;
			} else {
				segment.position.x = prevSegment.position.x;
				segment.position.y = prevSegment.position.y;
			}
		}
		prevPosition = new Point2D.Double(getHead().position.x, getHead().position.y);
	}

	public void addSegments(int count) {
		for (int i = 0; i < count; i++) {
			Point2D.Double position = new Point2D.Double(getHead().position.x, getHead().position.y);
			double rotation = getHead().rotation;
			segments.add(new Segment(position, rotation));
		}
	}

}
