package rd.fahrzeug;

public class Main {
	public static void main(String[] args) {
		LKWVerbrenner brenA = new LKWVerbrenner();
		brenA.setTankkapazitaet(200);
		brenA.setTankzustand(180);
		brenA.tanken(25);
		System.out.print("brenA: ");
		System.out.println(brenA.getTankzustand());

		LKWVerbrenner brenB = new LKWVerbrenner();
		brenB.setTankkapazitaet(50);
		brenB.setTankzustand(40);
		brenB.tanken(-50);
		System.out.print("brenB: ");
		System.out.println(brenB.getTankzustand());

		LKWVerbrenner brenC = new LKWVerbrenner();
		brenC.setTankkapazitaet(80);
		brenC.setTankzustand(40);
		brenC.tanken(0);
		System.out.print("brenC: ");
		System.out.println(brenC.getTankzustand());

		PKWElectro eleA = new PKWElectro();
		eleA.setLadezustand(40);
		eleA.laden(99);
		System.out.print("eleA: ");
		System.out.println(eleA.getLadezustand());

		PKWElectro eleB = new PKWElectro();
		eleB.setLadezustand(20);
		eleB.laden(-200);
		System.out.print("eleB: ");
		System.out.println(eleB.getLadezustand());
	}
}
