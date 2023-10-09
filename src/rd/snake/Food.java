package rd.snake;

import java.awt.geom.Point2D;
import java.util.Random;

public class Food {
	Point2D.Double position;
	private static Random random = new Random();
	int energy;
	double size;
	public static final int ENERGY_MIN = 10;
	public static final int ENERGY_MAX = 100;

	public Food(Point2D.Double location, int energy) {
		this.position = location;
		this.energy = energy;
		this.size = sizeFromEnergy(energy);
	}

	/**
	 * Create Food with random energy value
	 * @param position
	 */
	public Food(Point2D.Double position) {
		this(position, random.nextInt(ENERGY_MIN, ENERGY_MAX + 1));
	}

	static public double sizeFromEnergy(int energy) {
		final int foodMinSize = 5;
		return Math.max(foodMinSize, energy * 0.2);
	}

}
