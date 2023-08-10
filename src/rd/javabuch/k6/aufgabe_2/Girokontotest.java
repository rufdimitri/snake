package rd.javabuch.k6.aufgabe_2;

public class Girokontotest {
	public static void main(String args[]) {
		Girokonto gk = new Girokonto("0000000001", 10000.0, 1000.0);
		gk.auszahlen(11000.0);
		System.out.println("Kontostand: " + gk.getKontostand());
		gk.einzahlen(11000.0);
		System.out.println("Kontostand: " + gk.getKontostand());
		gk.auszahlen(11001.0);
		System.out.println("Kontostand: " + gk.getKontostand());
	}
}
