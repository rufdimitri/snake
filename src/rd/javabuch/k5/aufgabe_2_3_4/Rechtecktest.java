package rd.javabuch.k5.aufgabe_2_3_4; //k5 = kapitel 5

import javax.swing.JOptionPane;

public class Rechtecktest {

	public static void main(String[] args) {
		String eingabe;

		eingabe = JOptionPane.showInputDialog("Länge eingeben:");
		double laenge = Double.parseDouble(eingabe);

		eingabe = JOptionPane.showInputDialog("Länge eingeben:");
		double breite = Double.parseDouble(eingabe);

		Rechteck r1 = new Rechteck(laenge, breite);

		System.out.println("Laenge: " + r1.getLaenge() + " Breite: " + r1.getBreite() + " Diagonale: "
				+ r1.getDiagonale() + " Flaeche:" + r1.getFlaeche() + "\nUmfang: " + r1.getUmfang() + " Kurze Seite: "
				+ r1.getKurzeSeite() + " Lange Seite: " + r1.getLangeSeite());

		// Aufgabe 3
		r1.laengeAusgeben(); // locale Variable wird ausgegeben, weil
		// die lokale Variable verhindert Zugriff auf Attribute laenge,
		// weil sie gleichen Namen hat, man kann aber auf Attribut laenge
		// zugreifen, indem man this.laenge nutzt.

		// Aufgabe 4
		System.out.println("Laenge: " + r1.getLaenge());
		r1.laengeVergroessern(5);
		System.out.println("Laenge: " + r1.getLaenge());

		System.out.println("Breite: " + r1.getBreite());
		r1.breiteVergroessern(5);
		System.out.println("Breite: " + r1.getBreite());

		System.out.println("Laenge: " + r1.getLaenge());
		r1.laengeVerkleinern(5);
		System.out.println("Laenge: " + r1.getLaenge());

		System.out.println("Breite: " + r1.getBreite());
		r1.breiteVerkleinern(5);
		System.out.println("Breite: " + r1.getBreite());

	}

}
