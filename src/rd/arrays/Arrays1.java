package rd.arrays;

import java.util.Random;

import javax.swing.JOptionPane;

public class Arrays1 {
    public static void main(String[] args) {
        arrayCopy();
    }

    static void arrayCopy() {
        String input;
        input = JOptionPane.showInputDialog(null, "Arraygröße eingeben: ");
        int groesse = Integer.parseInt(input);

        int[] array = new int[groesse];
        Random rand = new Random();

        // Array ausfüllen mit Zufällige Zahlen
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(100);
        }

        // Array dem Benutzer zeigen
        System.out.print("Array1: ");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ", ");
        }
        System.out.println();

        input = JOptionPane.showInputDialog(null, "Arraygröße für neuen Array eingeben: ");
        int groesse2 = Integer.parseInt(input);
        int[] array2 = new int[groesse2];

        int maxErlaubteGroesse = Math.min(array.length, array2.length);
        // Array kopieren
        for (int i = 0; i < maxErlaubteGroesse; i++) {
            array2[i] = array[i];
        }

        // Array dem Benutzer zeigen
        System.out.print("Array2: ");
        for (int i = 0; i < array2.length; i++) {
            System.out.print(array2[i] + ", ");
        }
        System.out.println();
    }

    static void arrayBasics() {
        int[] array1 = new int[5];
        array1[0] = 10;
        array1[1] = 20;
        array1[2] = 30;
        array1[3] = 40;
        array1[4] = 50;

        System.out.print("array1: ");
        for (int i = 0; i < array1.length; i++) {
            System.out.print(array1[i] + ", ");
        }
        System.out.println();

        // array2
        int[] array2 = { 90, 80, 70, 60 };
        System.out.print("array2: ");
        for (int i = 0; i < array2.length; i++) {
            System.out.print(array2[i] + ", ");
        }
        System.out.println();
    }
}
