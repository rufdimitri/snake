package rd.fahrzeug;

public interface Electro {

	default public void laden(double mengeProzent) {
		if (mengeProzent > 0) {
			if (getLadezustand() + mengeProzent <= 100) {
				setLadezustand(getLadezustand() + mengeProzent);
			} else {
				setLadezustand(100);
			}
		}
	}

	public double getLadezustand();

	public void setLadezustand(double ladezustand);

}
