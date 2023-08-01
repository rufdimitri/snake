package rd.arrays;

import java.util.Random;

import javax.swing.JOptionPane;

/*
 * 1. Die Elementzahl eines 1 dimensionalen Array soll wären der Laufzeit des Programms eingegeben
 * werden und die Elemente mit zufälligen Integer Zahlen gefüllt werden.
 * Ermitteln Sie nun für das Array den Größten, den Kleinsten und den Durchschnittswert.
 */
public class FortgeschritteneAufgabe_1 {
    public static void main(String[] args) {
        String input;
        input = JOptionPane.showInputDialog(null, "Anzahl der Elementen eingeben: ");
        int elementzahl = Integer.parseInt(input);

        int[] array = new int[elementzahl];
        Random rand = new Random();
        // Array ausfüllen mit Zufälligen Zahlen
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(1000);
        }

        // Array dem Benutzer zeigen
        System.out.print("Array: ");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ", ");
        }
        System.out.println();

        // Ermitteln den Größten, Kleinsten, Durchschnittswert:
        int max = -1;
        int min = -1;
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            if (max == -1 || array[i] > max) {
                max = array[i];
            }
            if (min == -1 || array[i] < min) {
                min = array[i];
            }
            sum = sum + array[i];
        }
        double durchschnittswert = (double) sum / array.length;
        System.out.format("Größter Wert = %d, Kleinster Wert = %d, Durchschnittswert = %.1f", max, min,
                durchschnittswert);
    }

}
