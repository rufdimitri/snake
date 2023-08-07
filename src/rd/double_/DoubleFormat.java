package rd.double_;

public class DoubleFormat {

	public static void main(String[] args) {
		double d = 1.23;
		System.out.println(d);
		System.out.format("%f \n", d);
		System.out.format("%.0f \n", d);
		System.out.format("%0d \n", d);

	}

}
