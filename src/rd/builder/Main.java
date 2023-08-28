package rd.builder;

public class Main {

	public static void main(String[] args) {
		// Constructor
		// Car(String color, int doors, double length, double width, double height,
		// double weight, int wheels, int driveWheels)
		Car car0 = new Car("green", 4, 4.0, 2.0, 1.6, 1200, 4, 2);

		// Builder
		Car car1 = Car.newCar().color("red");

		// @formatter:off
		Car car2 = Car.newCar()
				.color("green")
				.doors(3)
				.weight(1500)
				.length(5.5)
				.width(2.0)
				.height(1.5)
				.wheels(4)
				.driveWheels(2);
		// @formatter:on

		// Setter
		Car car3 = new Car();
		car3.setColor("blue");
		car3.setDoors(5);
		car3.setWeight(2000);
		car3.setLength(6.0);
		car3.setWidth(2.0);
		car3.setHeight(1.5);
		car3.setWheels(4);
		car3.setDriveWheels(4);

		System.out.println(car0);
		System.out.println(car1);
		System.out.println(car2);
		System.out.println(car3);

	}

}
