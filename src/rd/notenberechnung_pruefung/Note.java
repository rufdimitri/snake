package rd.notenberechnung_pruefung;

public class Note {
	boolean bestanden;
	int gesamtPunkte;
	boolean nachpruefung;
	int gesamtPunkteMitNachpruefung;

	public Note(boolean bestanden, int gesamtPunkte, boolean nachpruefung, int gesamtPunkteMitNachpruefung) {
		super();
		this.bestanden = bestanden;
		this.gesamtPunkte = gesamtPunkte;
		this.nachpruefung = nachpruefung;
		this.gesamtPunkteMitNachpruefung = gesamtPunkteMitNachpruefung;
	}

	@Override
	public String toString() {
		return "Note [bestanden=" + bestanden + ", gesamtPunkte=" + gesamtPunkte + ", nachpruefung=" + nachpruefung
				+ ", gesamtPunkteMitNachpruefung=" + gesamtPunkteMitNachpruefung + "]";
	}

}
