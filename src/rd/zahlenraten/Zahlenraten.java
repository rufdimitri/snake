package rd.zahlenraten;

import java.util.Random;

import javax.swing.JOptionPane;

public class Zahlenraten {
    public static void main(String[] args) throws Exception {
        String input;

        input = JOptionPane.showInputDialog(null, "Bitte größte Zahl eingeben:");
        int groessteZahl = Integer.parseInt(input);

        input = JOptionPane.showInputDialog(null, "Anzahl der Versuche eingeben:");
        int anzahlDerVersuche = Integer.parseInt(input);

        Random rand = new Random();
        int dieZahl = rand.nextInt(groessteZahl);

        for (int i = 0; i < anzahlDerVersuche; i++) {
            input = JOptionPane.showInputDialog(null,
                    String.format("Versuch #%d. Bitte Zahl zwischen %d und %d eingeben:", i + 1, 0, groessteZahl));
            int versuchsZahl = Integer.parseInt(input);
            if (versuchsZahl == dieZahl) {
                JOptionPane.showMessageDialog(null, "Herzlichen Glückwunsch! Das ist die richtige Zahl: " + dieZahl);
                System.exit(0);
            }
        }
        JOptionPane.showMessageDialog(null, "Sie haben die Zahl nicht geraten. Die Zahl war " + dieZahl);
    }

}
