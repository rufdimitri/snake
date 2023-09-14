package rd.snake;

import java.awt.geom.Point2D;

public class Food {
	Point2D.Double location;
	int energy;

	public Food(Point2D.Double location, int energy) {
		super();
		this.location = location;
		this.energy = energy;
	}

}
