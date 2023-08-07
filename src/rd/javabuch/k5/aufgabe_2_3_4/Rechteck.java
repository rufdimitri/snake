package rd.javabuch.k5.aufgabe_2_3_4; //k5 = kapitel 5

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
		System.out.format("Constructor(%s, %s)\n", String.valueOf(laenge), String.valueOf(breite));
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

	/**
	 * Aufgabe 3
	 */
	void laengeAusgeben() {
		double laenge = 5.4;
		System.out.println("Länge: " + laenge);
	}

	/**
	 * Aufgabe 4
	 */
	void laengeVergroessern(double l) {
		laenge += l;
		System.out.println("laengeVergroessern(" + l + ")");
	}

	/**
	 * Aufgabe 4
	 */
	void breiteVergroessern(double b) {
		breite += b;
		System.out.println("breiteVergroessern(" + b + ")");
	}

	/**
	 * Aufgabe 4
	 */
	void laengeVerkleinern(double l) {
		laenge -= l;
		System.out.println("laengeVerkleinern(" + l + ")");
	}

	/**
	 * Aufgabe 4
	 */
	void breiteVerkleinern(double b) {
		breite -= b;
		System.out.println("breiteVerkleinern(" + b + ")");
	}

}
