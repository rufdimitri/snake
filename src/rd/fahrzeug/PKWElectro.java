package rd.fahrzeug;

public class PKWElectro extends PKW implements Electro {

	private double ladezustand;

	@Override
	public double getLadezustand() {
		return ladezustand;
	}

	@Override
	public void setLadezustand(double ladezustand) {
		this.ladezustand = ladezustand;
	}

	@Override
	public void fahren(double strecke) {
		// TODO Auto-generated method stub

	}

}
