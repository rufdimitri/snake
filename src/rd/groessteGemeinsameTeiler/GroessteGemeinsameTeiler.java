package rd.groessteGemeinsameTeiler;

import javax.swing.JOptionPane;

public class GroessteGemeinsameTeiler {
    public static void main(String[] args) {
        String eingabe;
        eingabe = JOptionPane.showInputDialog(null,
                "Bitte N eingeben: ");
        int n = Integer.parseInt(eingabe);

        eingabe = JOptionPane.showInputDialog(null,
                "Bitte M eingeben: ");
        int m = Integer.parseInt(eingabe);

        int zaehler = 0;
        while (m != n) {
            zaehler++;
            if (m > n) {
                m -= n;
            } else {
                n -= m;
            }
        }

        JOptionPane.showMessageDialog(null, String.format("Teiler: %d, SchleifeZähler: %d", m, zaehler));
    }

}
