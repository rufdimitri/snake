package rd.builder;

public class Main {

	public static void main(String[] args) {
		Car car1 = Car.newCar().color("red");
		Car car2 = Car.newCar()
				.color("green")
				.doors(3)
				.weight(1500)
				.length(5.5)
				.width(2.0)
				.height(1.5)
				.wheels(4)
				.driveWheels(2);

		System.out.println(car1);
		System.out.println(car2);
	}

}
