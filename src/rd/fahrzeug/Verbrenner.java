package rd.fahrzeug;

public interface Verbrenner {

	default public void tanken(double mengeLiter) {
		if (mengeLiter > 0) {
			if (getTankzustand() + mengeLiter <= getTankkapazitaet()) {
				setTankzustand(getTankzustand() + mengeLiter);
			} else {
				setTankzustand(getTankkapazitaet());
			}
		}
	}

	public double getTankzustand();

	public void setTankzustand(double tankzustand);

	public double getTankkapazitaet();

	public void setTankkapazitaet(double tankkapazitaet);

}
