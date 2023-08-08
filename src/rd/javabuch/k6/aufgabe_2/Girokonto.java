package rd.javabuch.k6.aufgabe_2;

import rd.javabuch.k6.aufgabe_1.Konto;

public class Girokonto extends Konto {
	// limit gibt an, welches Kreditlimit dem Kunden für das Überziehen zur
	// Verfügung steht
	private double limit;

	public Girokonto(String kontonummer, double kontostand, double limit) {
		super(kontonummer, kontostand);
		setLimit(limit);
	}

	public double getLimit() {
		return limit;
	}

	public void setLimit(double limit) {
		if (limit > 0) {
			this.limit = limit;
		} else {
			System.out.println("Limit sollte kleiner als 0 sein.");
		}
	}

	@Override
	public void auszahlen(double betrag) {
		if (getKontostand() - betrag >= -limit) {
			super.auszahlen(betrag);
		} else {
			System.out.println(
					"Auszahlung " + betrag + " fehlgeschlagen:\n  Limit überschreitung. Maximaler Auszahlungsbetrag: "
							+ (getKontostand() + limit));
		}
	}

}
