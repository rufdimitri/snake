package rd.arrays;

import java.util.Random;

import javax.swing.JOptionPane;

/*
 * 2 D Array Zeilen sortieren
 * Die Zeilen- und die Spaltenzahl eines 2 D Array soll während der Laufzeit des Programms
 * eingegeben werden und dann das Array mit Zufallszahlen gefüllt werden. Sortiere Sie dann die
 * Zeilen des Array aufsteigend nach dem Durchschnittswert.
 * Tipp: Wenn Ihr Array eine Zeile mehr hat, können Sie mit dieser Zeile den Dreiecktausch
 * vornehmen.
 */
public class FortgeschritteneAufgabe_3 {
    public static void main(String[] args) {
        String input;
        input = JOptionPane.showInputDialog(null, "Anzahl der Zeilen eingeben: ");
        int zeilenAnz = Integer.parseInt(input);

        input = JOptionPane.showInputDialog(null, "Anzahl der Spalten eingeben: ");
        int spaltenAnz = Integer.parseInt(input);

        int[][] array = new int[zeilenAnz][spaltenAnz];
        int[] buffer = new int[spaltenAnz];
        Random rand = new Random();
        // Array ausfüllen mit Zufälligen Zahlen
        for (int i = 0; i < zeilenAnz; i++) {
            for (int j = 0; j < spaltenAnz; j++) {
                array[i][j] = rand.nextInt(1000);
            }
        }

        String formatS = "%15s";
        String formatD = "%15d";
        // Array dem Benutzer zeigen
        System.out.println("Array: ");
        for (int z = 0; z <= zeilenAnz; z++) {
            for (int s = 0; s <= spaltenAnz; s++) {
                if (z == 0 && s > 0) {
                    // Kopf
                    if (s == 1) {
                        System.out.format(formatS, "Daten");
                    } else if (s < spaltenAnz) {
                        System.out.format(formatS, "");
                    } else {
                        System.out.format(formatS, "Durchschnitt");
                    }
                }
                System.out.format(formatD, array[z - 1][s - 1]);
            }
            System.out.println();
        }
        System.out.println();

    }

}
