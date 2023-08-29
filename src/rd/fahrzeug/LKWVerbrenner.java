package rd.fahrzeug;

public class LKWVerbrenner extends LKW implements Verbrenner {
	private double tankkapazitaet;

	private double tankzustand;

	@Override
	public void fahren(double strecke) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getTankzustand() {
		return tankzustand;
	}

	@Override
	public void setTankzustand(double tankzustand) {
		this.tankzustand = tankzustand;

	}

	@Override
	public double getTankkapazitaet() {
		return tankkapazitaet;
	}

	@Override
	public void setTankkapazitaet(double tankkapazitaet) {
		this.tankkapazitaet = tankkapazitaet;
	}

}
