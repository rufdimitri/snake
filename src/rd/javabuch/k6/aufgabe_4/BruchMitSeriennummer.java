package rd.javabuch.k6.aufgabe_4;

import rd.javabuch.k6.aufgabe_3.Bruch;

public class BruchMitSeriennummer extends Bruch {
	private static int anzahl = 0;
	private final int seriennummer = ++anzahl;

	public int getSeriennummer() {
		return seriennummer;
	}

}
