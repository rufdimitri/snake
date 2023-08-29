package rd.fahrzeug;

public class Elektroroller extends Roller implements Electro {

	@Override
	public void fahren(double strecke) {
		// TODO Auto-generated method stub

	}

	private double ladezustand;

	@Override
	public double getLadezustand() {
		return ladezustand;
	}

	@Override
	public void setLadezustand(double ladezustand) {
		this.ladezustand = ladezustand;
	}

}
