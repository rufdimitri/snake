package rd.javabuch.k6.aufgabe_1;

public class Konto {
	private String kontonummer;
	private double kontostand;
	
	public Konto(String kontonummer, double kontostand) {
		this.kontonummer = kontonummer;
		this.kontostand = kontostand;
	}
	
	
	public String getKontonummer() {
		return kontonummer;
	}
	
	public double getKontostand() {
		return kontostand;
	}
	
	public void einzahlen(double betrag) {
		kontostand += betrag;
	}
	
	public void auszahlen(double betrag) {
		kontostand -= betrag;
	}

}
