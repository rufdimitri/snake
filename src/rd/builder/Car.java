package rd.builder;

public class Car {
	String color;
	int doors;
	double length;
	double width;
	double height;
	double weight;
	int wheels;
	int driveWheels;

	public Car color(String color) {
		this.color = color;
		return this;
	}

	public Car length(double length) {
		this.length = length;
		return this;
	}

	public Car doors(int doors) {
		this.doors = doors;
		return this;
	}

	public Car width(double width) {
		this.width = width;
		return this;
	}

	public Car height(double height) {
		this.height = height;
		return this;
	}

	public Car weight(double weight) {
		this.weight = weight;
		return this;
	}

	public Car wheels(int wheels) {
		this.wheels = wheels;
		return this;
	}

	public Car driveWheels(int driveWheels) {
		this.driveWheels = driveWheels;
		return this;
	}

	public static Car newCar() {
		return new Car();
	}

	@Override
	public String toString() {
		return "Car [color=" + color + ", doors=" + doors + ", length=" + length + ", width=" + width + ", height="
				+ height + ", weight=" + weight + ", wheels=" + wheels + ", driveWheels=" + driveWheels + "]";
	}

}
