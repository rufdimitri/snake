package rd.electrischeBerechnung;

import javax.swing.JOptionPane;

public class ElectrischeBerechnung {
    public static void main(String[] args) {
        String eingabe;
        eingabe = JOptionPane.showInputDialog(null,
                "Rechentyp eingeben (1 für Reihenschaltung zweier Widerstände; \n2 für Parallelschaltung zweier Widerstände)");
        int rechnenTyp = Integer.parseInt(eingabe);

        eingabe = JOptionPane.showInputDialog(null, "Spannung (V) eingeben: ");
        double U = Double.parseDouble(eingabe);

        eingabe = JOptionPane.showInputDialog(null, "Widerstandswert R1 (\u03A9) eingeben: ");
        double R1 = Double.parseDouble(eingabe);

        eingabe = JOptionPane.showInputDialog(null, "Widerstandswert R2 (\u03A9) eingeben: ");
        double R2 = Double.parseDouble(eingabe);

        if (rechnenTyp == 1) {
            // Reihenschaltung zweier Widerstände
            double Re = R1 + R2;
            double Ig = U / Re;
            double U1 = R1 * Ig;
            double U2 = R2 * Ig;
            double Pg = U * Ig;

            JOptionPane.showMessageDialog(null, String.format(
                    "Berechnete Werte: Re=%.2f\u03A9, Ig=%.2fA, U1=%.2fV, U2=%.2fV, Pg=%.2fW", Re, Ig, U1, U2, Pg));
        } else if (rechnenTyp == 2) {
            // Parallelschaltung zweier Widerstände
            double Re = R1 * R2 / (R1 + R2); // Ohm
            double Ig = U / Re; // Ampere A
            double I1 = U / R1; // (in Ohm Ω)
            double I2 = U / R2; // (in Ohm Ω);
            double Pg = U * Ig; // (in Watt W);

            JOptionPane.showMessageDialog(null,
                    String.format("Berechnete Werte: Re=%.2f\u03A9, Ig=%.2fA, I1=%.2f\u03A9, I2=%.2f\u03A9, Pg=%.2fW",
                            Re, Ig, I1, I2, Pg));
        } else {
            JOptionPane.showMessageDialog(null, "Eingegebenen Rechentyp ist nicht bekannt: " + rechnenTyp);
        }
    }
}
