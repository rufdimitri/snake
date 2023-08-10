package rd.javabuch.k6.aufgabe_3;

public class Bruch {
	private int zaehler;
	private int nenner;

	Bruch() {
		zaehler = 0;
		nenner = 1;
	}

	Bruch(int x) {
		zaehler = x;
		nenner = 1;
	}

	Bruch(int zaehler_, int nenner_) {
		zaehler = zaehler_;
		nenner = nenner_;
	}

	int getZaehler() {
		return zaehler;
	}

	int getNenner() {
		return nenner;
	}

	void setZaehler(int z) {
		zaehler = z;
	}

	void setNenner(int n) {
		nenner = n;
	}

	void ausgeben() {
		System.out.println(zaehler + "/" + nenner);
	}

	String Bruchstring() {
		return zaehler + "/" + nenner;
	}

	void kuerzen() {
		int m, n, r; // lokale Variablen
		m = Math.abs(zaehler);
		n = Math.abs(nenner);
		r = m % n;
		while (r > 0) { // Berechnung des ggT
			m = n;
			n = r;
			r = m % n;
		}
		zaehler /= n; // in n steht jetzt der ggT6.5 Übungsaufgaben
		nenner /= n;
	}

	void gekuerztausgeben() {
		kuerzen();
		ausgeben();
	}

	boolean equals(Bruch x) {
		Bruch a = new Bruch(this.zaehler, this.nenner);
		Bruch b = new Bruch(x.zaehler, x.nenner);
		a.kuerzen();
		b.kuerzen();
		if ((a.zaehler == b.zaehler) && (a.nenner == b.nenner)) {
			return true;
		} else {
			return false;
		}
	}

	Bruch addiere(Bruch b) {
		int neuerNenner = this.nenner * b.nenner;
		int neuerZaehler = this.zaehler * b.nenner + b.zaehler * this.nenner;
		Bruch neuerBruch = new Bruch(neuerZaehler, neuerNenner);
		return neuerBruch;
	}

	Bruch subtrahiere(Bruch b) {
		int neuerNenner = this.nenner * b.nenner;
		int neuerZaehler = this.zaehler * b.nenner - b.zaehler * this.nenner;
		Bruch neuerBruch = new Bruch(neuerZaehler, neuerNenner);
		return neuerBruch;
	}

	double dezimalwert() {
		double dezimalWert = 1.0 * zaehler / nenner;
		return dezimalWert;
	}
}
