package rd.ggtEuklid;

import javax.swing.JOptionPane;

public class GgtEuklid {
    public static void main(String[] args) {
        String eingabe;
        eingabe = JOptionPane.showInputDialog(null,
                "Bitte N eingeben: ");
        int n = Integer.parseInt(eingabe);

        eingabe = JOptionPane.showInputDialog(null,
                "Bitte M eingeben: ");
        int m = Integer.parseInt(eingabe);

        int rest = m % n;

        while (rest > 0) {
            m = n;
            n = rest;
            rest = m % n;
        }

        JOptionPane.showMessageDialog(null, "n = " + n);
    }
}
