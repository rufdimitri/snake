package rd.javabuch.k6.aufgabe_1;

public class Kontotest {

	public static void main(String[] args) {
		Konto konto1 = new Konto("0000000001", 1000);
		System.out.println("Kontonummer: " + konto1.getKontonummer());
		System.out.println("Kontostand: " + konto1.getKontostand());
		
		konto1.einzahlen(500);
		System.out.println("Kontostand: " + konto1.getKontostand());
		
		konto1.auszahlen(750);
		System.out.println("Kontostand: " + konto1.getKontostand());
		
	}

}
