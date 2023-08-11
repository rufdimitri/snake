package rd.notenberechnung_pruefung;

public class Notenberechnung {

	public static int berechnen(int ap1Punkte, int ap21Punkte, int ap22Punkte, int ap23Punkte, int ap2DokuPunkte,
			int ap2FachGespraechPunkte) {

		final int mangelhaft = 30; // kleiner als mangelhaft ist ungenügend
		final int ausreichend = 50;

		boolean bestanden = true;

		if (ap1Punkte < mangelhaft) {
			bestanden = false;
		}

		int ap2AnzahlBestanden = 0;
		if (ap21Punkte >= ausreichend) {
			ap2AnzahlBestanden++;
		} else if (ap21Punkte < mangelhaft) {
			bestanden = false;
		}
		if (ap22Punkte >= ausreichend) {
			ap2AnzahlBestanden++;
		} else if (ap22Punkte < mangelhaft) {
			bestanden = false;
		}
		if (ap23Punkte >= ausreichend) {
			ap2AnzahlBestanden++;
		} else if (ap23Punkte < mangelhaft) {
			bestanden = false;
		}
		if (ap2DokuPunkte >= ausreichend) {
			ap2AnzahlBestanden++;
		} else if (ap2DokuPunkte < mangelhaft) {
			bestanden = false;
		}
		if (ap2FachGespraechPunkte >= ausreichend) {
			ap2AnzahlBestanden++;
		} else if (ap2FachGespraechPunkte < mangelhaft) {
			bestanden = false;
		}
		if (ap2AnzahlBestanden < 3) {
			bestanden = false;
		}
		double punkteGesamt = ap1Punkte * 0.2 + ap21Punkte * 0.1 + ap22Punkte * 0.1 + ap23Punkte * 0.1
				+ ap2DokuPunkte * 0.25 + ap2FachGespraechPunkte * 0.25;

		if (punkteGesamt < ausreichend) {
			bestanden = false;
		}
		if (bestanden) {
			return (int) Math.round(punkteGesamt);
		} else {
			return 0;
		}
	}

	public static Note ueberpruefen(int ap1Punkte, int ap21Punkte, int ap22Punkte, int ap23Punkte, int ap2DokuPunkte,
			int ap2FachGespraechPunkte, int nachpruefungPunkte) {

		int ergebnis = berechnen(ap1Punkte, ap21Punkte, ap22Punkte, ap23Punkte, ap2DokuPunkte, ap2FachGespraechPunkte);
		if (ergebnis == 0) {
			int ap21PunkteNachpruefung = (int) Math.round((ap21Punkte * 2 + nachpruefungPunkte * 1) / 3);
			int ergebnisNachpruefung1 = berechnen(ap1Punkte, ap21PunkteNachpruefung, ap22Punkte, ap23Punkte,
					ap2DokuPunkte, ap2FachGespraechPunkte);

			if (ergebnisNachpruefung1 > 0) {
				return new Note(false, 0, true, ergebnisNachpruefung1);
			}

			int ap22PunkteNachpruefung = (int) Math.round((ap22Punkte * 2 + nachpruefungPunkte * 1) / 3);
			int ergebnisNachpruefung2 = berechnen(ap1Punkte, ap21Punkte, ap22PunkteNachpruefung, ap23Punkte,
					ap2DokuPunkte, ap2FachGespraechPunkte);

			if (ergebnisNachpruefung2 > 0) {
				return new Note(false, 0, true, ergebnisNachpruefung2);
			}

			int ap23PunkteNachpruefung = (int) Math.round((ap23Punkte * 2 + nachpruefungPunkte * 1) / 3);
			int ergebnisNachpruefung3 = berechnen(ap1Punkte, ap21Punkte, ap22Punkte, ap23PunkteNachpruefung,
					ap2DokuPunkte, ap2FachGespraechPunkte);

			if (ergebnisNachpruefung3 > 0) {
				return new Note(false, 0, true, ergebnisNachpruefung3);
			}

			return new Note(false, 0, false, 0);

		} else {
			return new Note(true, ergebnis, false, 0);
		}
	}

	public static void main(String[] args) {
		Note note;

		note = ueberpruefen(50, 50, 50, 50, 50, 50, 100);
		System.out.println(note);

		note = ueberpruefen(45, 45, 45, 45, 45, 45, 100);
		System.out.println(note);

		note = ueberpruefen(45, 45, 45, 60, 60, 60, 100);
		System.out.println(note);

		note = ueberpruefen(80, 75, 80, 85, 60, 60, 100);
		System.out.println(note);

		note = ueberpruefen(45, 45, 45, 90, 80, 80, 100);
		System.out.println(note);

		note = ueberpruefen(45, 45, 45, 45, 85, 85, 100);
		System.out.println(note);

		note = ueberpruefen(29, 90, 90, 90, 90, 90, 100);
		System.out.println(note);

		note = ueberpruefen(90, 29, 90, 90, 90, 90, 100);
		System.out.println(note);

		note = ueberpruefen(50, 49, 48, 47, 80, 80, 60);
		System.out.println(note);
	}

}
