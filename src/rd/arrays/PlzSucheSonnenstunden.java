package rd.arrays;

import java.util.Random;

import javax.swing.JOptionPane;

public class PlzSucheSonnenstunden {
    public static void main(String[] args) {
        int[][] sonnenstunden;

        // Daten generieren
        int plzMin = 10000;
        int plzMax = 99999;
        int plzAnzahl = 10;
        int stdMin = 100;
        int stdMax = 1500;
        Random rand = new Random(1); // 1 = für die gleiche Züfallszahlen jedes Mal

        int spaltenAnzahl = 2;
        sonnenstunden = new int[plzAnzahl][spaltenAnzahl];
        int plzBereich = (plzMax - plzMin) / plzAnzahl;
        for (int i = 0; i < sonnenstunden.length; i++) {
            // PLZ aufsteigend generieren
            sonnenstunden[i][0] = rand.nextInt(i * plzBereich, (i + 1) * plzBereich) + plzMin;
            // Sonnenstunden generieren
            sonnenstunden[i][1] = rand.nextInt(stdMin, stdMax);
        }
        // generierte Daten anzeigen;
        System.out.println("# \t PLZ \t Sonnenstunden");
        for (int i = 0; i < sonnenstunden.length; i++) {
            System.out.print(i);
            System.out.print("\t");
            System.out.print(sonnenstunden[i][0]);
            System.out.print("\t");
            System.out.print(sonnenstunden[i][1]);
            System.out.println();
        }

        // function holeSonnenstunden():
        // Benutzer nach PLZ Fragen:
        String eingabe = JOptionPane.showInputDialog("Bitte PLZ eingeben: ");
        int plzGesucht = Integer.parseInt(eingabe);
        System.out.println("Gesuchte PLZ: " + plzGesucht);
        // nach plz suchen:
        int i = 0;
        int iMin = 0;
        int iMax = sonnenstunden.length - 1;
        int plz;
        while (iMax != iMin) {
            i = (iMax + iMin) / 2; // nehmen ein Wert in der Mitte
            System.out.format("iMin=%2d \t iMax=%2d \t i=%2d \n", iMin, iMax, i);
            plz = sonnenstunden[i][0];
            if (plz > plzGesucht) {
                iMax = i - 1;
                if (iMax < iMin) { // korrigieren, falls wir zu weit gegangen sind
                    iMax = iMin;
                }
            } else if (plz < plzGesucht) {
                iMin = i + 1;
                if (iMin > iMax) { // korrigieren, falls wir zu weit gegangen sind
                    iMin = iMax;
                }
            } else { // ist gleich, gefunden
                iMax = i;
                iMin = i;
            }
        }
        // in iMin/iMax ist index von nächststehenden PLZ gespeichert, wir benutzen es
        // weiter
        i = iMin;
        boolean gefunden = sonnenstunden[i][0] == plzGesucht;
        if (gefunden) {
            JOptionPane.showMessageDialog(null,
                    "Für gesuchte PLZ: " + plzGesucht + " ist folgende Sonnenstundenwert gefunden: "
                            + sonnenstunden[i][1]);
        } else {
            // speichern index in i
            while (i >= 0 && sonnenstunden[i][0] > plzGesucht) { // ist nächststehende PLZ größer als gesuchte PLZ
                i--;
            }
            if (i >= 0) { // falls i kleiner als 0 ist, bedeutet das, das nächststehende kleinere PLZ in
                          // array nicht gefunden wurde.
                JOptionPane.showMessageDialog(null,
                        "Gesuchte PLZ: " + plzGesucht + " ist nicht gefunden, aber für nächststehende kleinere PLZ: "
                                + sonnenstunden[i][0] + " ist folgenden Sonnenstundenwert gefunden: "
                                + sonnenstunden[i][1]);
            } else { // angegebene PLZ ist kleiner als alle PLZ in array
                JOptionPane.showMessageDialog(null, "Ergebnis: -1 (Eingegebene PLZ ist kleiner als ale PLZ in array)");
            }
        }
    }
}
