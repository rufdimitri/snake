package rd.javabuch.k5.aufgabe_2_3; //k5 = kapitel 5

public class Rechteck {
	private double laenge;
	private double breite;

	public Rechteck() {
		laenge = 0;
		breite = 0;
	}

	public Rechteck(double laenge, double breite) {
		this.laenge = laenge;
		this.breite = breite;
	}

	public void setLaenge(double l) {
		this.laenge = l;
	}

	public void setBreite(double b) {
		this.breite = b;
	}

	public void setSeiten(double l, double b) {
		this.laenge = l;
		this.breite = b;
	}

	public double getLaenge() {
		return this.laenge;
	}

	public double getBreite() {
		return this.breite;
	}

	public double getLangeSeite() {
		if (laenge > breite) {
			return laenge;
		} else {
			return breite;
		}
	}

	public double getKurzeSeite() {
		if (laenge > breite) {
			return breite;
		} else {
			return laenge;
		}
	}

	public double getDiagonale() {
		return Math.sqrt(laenge * laenge + breite * breite);
	}

	public double getFlaeche() {
		return laenge * breite;
	}

	public double getUmfang() {
		return laenge * 2 + breite * 2;
	}

}
