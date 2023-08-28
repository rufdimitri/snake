package rd.builder;

public class Car {
	String color;
	int doors;
	double length;
	double width;
	double height;

	public Car(String color, int doors, double length, double width, double height, double weight, int wheels,
			int driveWheels) {
		super();
		this.color = color;
		this.doors = doors;
		this.length = length;
		this.width = width;
		this.height = height;
		this.weight = weight;
		this.wheels = wheels;
		this.driveWheels = driveWheels;
	}

	public Car() {

	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getDoors() {
		return doors;
	}

	public void setDoors(int doors) {
		this.doors = doors;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getWheels() {
		return wheels;
	}

	public void setWheels(int wheels) {
		this.wheels = wheels;
	}

	public int getDriveWheels() {
		return driveWheels;
	}

	public void setDriveWheels(int driveWheels) {
		this.driveWheels = driveWheels;
	}

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

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

}
