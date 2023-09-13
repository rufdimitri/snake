package rd.snake;

import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.Objects;

public class Snake {
	private LinkedList<Segment> segments = new LinkedList<>();
	public Snake.Segment updatedTailHead;

	public double rotationSpeed = Math.toRadians(10);
	public double moveSpeed = 10;
	public double segmentSize = 10;

	public static class Segment {
		private final int segmentId;
		private Point2D.Double position = new Point2D.Double();
		private double rotation; // angle in radian

		private Point2D.Double prevPosition;
		private double prevRotation; // angle in radian
		private Snake snake;

		public Segment(Snake snake, Point2D.Double position, double rotation) {
			super();
			this.segmentId = snake.getSegments().size();
			this.snake = snake;
			this.position.x = position.x;
			this.position.y = position.y;
			this.rotation = rotation;
		}

		public Segment(Segment other) {
			this(other.snake, other.position, other.rotation);
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
			return Objects.equals(position, other.position)
					&& Double.doubleToLongBits(rotation) == Double.doubleToLongBits(other.rotation)
					&& snake == other.snake;
		}

		public boolean positionEquals(Segment other) {
			if (this == other)
				return true;
			if (other == null)
				return false;
			double delta = 0.01;
			return Math.abs(position.x - other.position.x) < delta && Math.abs(position.y - other.position.y) < delta;
		}

		public void setPositionFrom(Segment other) {
			if (other != null) {
				setPosition(other.position.x, other.position.y, other.rotation);
			}
		}

		public void setPosition(Point2D.Double position) {
			setPosition(position.x, position.y);
		}

		public Point2D.Double getPosition() {
			if (position == null) {
				return null;
			}
			// give a new Object, to protect the "position" field from changes
			return new Point2D.Double(position.x, position.y);
		}

		public Point2D.Double getPrevPosition() {
			if (prevPosition == null) {
				return null;
			}
			// give a new Object, to protect the "position" field from changes
			return new Point2D.Double(prevPosition.x, prevPosition.y);
		}

		public void setPosition(double x, double y, double rotation) {
			if (this.position == null) {
				this.position = new Point2D.Double();
			} else {
				if (this.prevPosition == null) {
					this.prevPosition = new Point2D.Double();
				}
				this.prevPosition.x = this.position.x;
				this.prevPosition.y = this.position.y;
				this.prevRotation = this.rotation;
			}
			this.position.x = x;
			this.position.y = y;
			this.rotation = rotation;
		}

		public void setPosition(double x, double y) {
			if (this.position == null) {
				this.position = new Point2D.Double();
			} else {
				if (this.prevPosition == null) {
					this.prevPosition = new Point2D.Double();
				}
				this.prevPosition.x = this.position.x;
				this.prevPosition.y = this.position.y;
			}
			this.position.x = x;
			this.position.y = y;
		}

		public double getRotation() {
			return rotation;
		}

		public double getPrevRotation() {
			return prevRotation;
		}

		public Snake getSnake() {
			return snake;
		}

		public int getSegmentId() {
			return segmentId;
		}
	}

	public void move() {
		final double segmentDistance = segmentSize / 2; // TODO use it to move
		Point2D.Double pos = getHead().getPosition();
		Point2D.Double newPos = new Point2D.Double(pos.x, pos.y - moveSpeed);
		Point2D.Double newPosRotated = GeometryUtil.rotatePoint(newPos, pos, getHead().getRotation());
		getHead().setPosition(newPosRotated);
	}

	public void rotate(double deltaTheta) {
		Segment head = getHead();
		head.prevRotation = head.rotation;
		head.rotation += deltaTheta;
	}

	public void rotateLeft() {
		rotate(-rotationSpeed);
	}

	public void rotateRight() {
		rotate(rotationSpeed);
	}

	public LinkedList<Segment> getSegments() {
		return segments;
	}

	public Segment getHead() {
		return segments.getFirst();
	}

	public void updateTailPosition() {
		// check if position changed
		if (updatedTailHead != null && updatedTailHead.positionEquals(getHead())) {
			return;
		}
		updatedTailHead = new Snake.Segment(getHead());

		Segment prevSegment = null;
		for (Segment segment : segments) {
			if (segment != getHead()) {
				Point2D.Double prevPosition = prevSegment.getPrevPosition();
				if (prevPosition != null) {
					segment.setPosition(prevPosition.x, prevPosition.y, prevSegment.getPrevRotation());
				}
			}
			prevSegment = segment;
		}
	}

	public void addSegments(int count) {
		for (int i = 0; i < count; i++) {
			segments.add(new Segment(getHead()));
		}
	}

}
