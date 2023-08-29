package rd.fahrzeug;

/**
 *  Top Klasse Projekts
 */
public abstract class Fahrzeug {
	/** <b>anzahl</b> zählt anzahl der Fahrzeuge */
	private static int anzahl = 0;
	private int fahrzeugId = ++anzahl;

	abstract public void fahren(double strecke);

//	public abstract void neuesFahrzeug();

	public int getFahrzeugId() {
		return fahrzeugId;
	}

}
