package rd.fortgeschrittene_aufgaben;

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
        
        //zusätzliche Spalte für Speichern des Durchschnittswertes
        //Durchschnittswert ist Double, aber wird als Integer gespeichert multipliziert bei 100 (für 2 nachkommastellen)
        //Beispiel Durchschnittwert = 10,53 wird als integer 1053 gespeichert
        //Durchschnittwert wird in der ersten Spalte gespechert
        spaltenAnz += 1; 

        int[][] array = new int[zeilenAnz][spaltenAnz];

        // Array ausfüllen mit Zufälligen Zahlen
        Random rand = new Random();
        for (int z = 0; z < zeilenAnz; z++) {
            for (int s = 1; s < spaltenAnz; s++) {
                array[z][s] = rand.nextInt(1000);
            }
        }
        arrayPrint("Generiertes Array: ", array);

        //Durchschnittswert berechnen
        int sum = 0;
        double durchschnitt;
        for (int z = 0; z < zeilenAnz; z++) {
            sum = 0;
            for (int s = 1; s < spaltenAnz; s++) { //starten mit 1, weil 0 ist Durchschnittswert
                sum += array[z][s];
            }
            durchschnitt = (double) sum / (spaltenAnz-1);
            array[z][0] = (int)Math.round(durchschnitt * 100);
        }
        arrayPrint("Durchschnitt berechnet: ", array);

        //sortieren des Arrays nach Durchschnittswert
        int[] buffer = new int[spaltenAnz];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) { //-1, denn wir nutzen j+1 index
                if (array[j][0] > array[j+1][0]) { //Vergleichen Durchschnittswert und tauschen, falls notwendig
                    arrayCopy(buffer, array[j]);
                    arrayCopy(array[j], array[j+1]);
                    arrayCopy(array[j+1], buffer);
                }
            }
        }
        arrayPrint("Sortiert: ", array);
    }

    static void arrayPrint(String title, int[][] array) {
        String formatS = "%15s";
        String formatD = "%15d";
        String formatF = "%15.2f";
        // Array anzeigen
        System.out.println(title);
        for (int z = 0; z < array.length+1; z++) { //+1 für Tabellenkopf
            for (int s = 0; s < array[0].length; s++) {
                if (z == 0) { // Tabellenkopf                    
                    if (s == 0) {
                        System.out.format(formatS, "Durchschnitt");
                    } else if (s == 1) {
                        System.out.format(formatS, "Daten");
                    }
                } else {
                    if (s == 0) { //Durchschnitt
                        double durchschnitt = (double)array[z-1][s] / 100;
                        System.out.format(formatF, durchschnitt);
                    } else {
                        System.out.format(formatD, array[z-1][s]);
                    }
                }
                
            }
            System.out.println();
        }
        System.out.println();
    }

    static void arrayCopy(int[] toArray, int[] fromArray) {
        for (int i = 0; i < toArray.length; i++) {
            toArray[i] = fromArray[i];
        }
    }

}
