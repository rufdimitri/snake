package rd.snake;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *  Contains all entities and methods to manipulate them
 */
public class World {
	private static Random random = new Random();
	public Snake snake;
	public List<Food> food = new ArrayList<>();
	public int width, height;
	public Point2D.Double center;

	public World(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		center = new Point2D.Double(width / 2, height / 2);
	}

	public void createEntities() {
		// Create Snake
		snake = new Snake();
		snake.getSegments().add(new Snake.Segment(snake, center, 0)); // manually add head in center of World
		snake.segmentSize = 20;
		snake.addSegments(10); // add other segments automatically

		// Generate Food
		addRandomFood(100);
	}

	public void addRandomFood(int count) {
		for (int i = 0; i < count; i++) {
			this.food.add(new Food(getRandomLocation(Food.sizeFromEnergy(Food.ENERGY_MAX))));
		}
	}

	public Point2D.Double getRandomLocation(double borderWidth) {
		double x = random.nextDouble(0 + borderWidth, width - borderWidth);
		double y = random.nextDouble(0 + borderWidth, height - borderWidth);
		return new Point2D.Double(x, y);
	}
}
