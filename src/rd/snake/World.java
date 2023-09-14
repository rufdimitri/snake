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
		snake = new Snake();
		snake.getSegments().add(new Snake.Segment(snake, center, 0)); // manually add head in center of World
		snake.segmentSize = 20;
		snake.addSegments(10); // add other segments automatically
	}

	public void addRandomFood(int count) {
		for (int i = 0; i < count; i++) {
			this.food.add(createRandomFood());
		}
	}

	public Food createRandomFood() {
		int energy = random.nextInt(Food.ENERGY_MIN, Food.ENERGY_MAX);
		double x = random.nextDouble(width);
		double y = random.nextDouble(height);
		Point2D.Double location = new Point2D.Double(x, y);
		return new Food(location, energy);
	}
}
