package rd.javabuch.k6.aufgabe_3;

public class Bruchtest {

	public static void main(String[] args) {
		Bruch a = new Bruch(3, 4);
		Bruch b = new Bruch(19, 8);
		Bruch c = a.addiere(b);
		Bruch d = a.subtrahiere(b);
		a.ausgeben();
		b.ausgeben();
		c.ausgeben();

		System.out.println();
		d.ausgeben();
		d.kuerzen();
		d.ausgeben();
	}

}
