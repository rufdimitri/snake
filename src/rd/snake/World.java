package rd.snake;

import java.awt.geom.Point2D;
import java.util.List;

/**
 *  Contains all entities and methods to manipulate them
 */
public class World {
	public Snake snake;
	public List<Food> food;
	public int width, height;
	public Point2D.Double center;

	public World(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		center = new Point2D.Double(width / 2, height / 2);
	}

	public void createEntities() {
		snake = new Snake();
		snake.getSegments().add(new Snake.Segment(snake, center, 0)); // manually add head in center of World
		snake.segmentSize = 20;
		snake.addSegments(10); // add other segments automatically
	}
}
