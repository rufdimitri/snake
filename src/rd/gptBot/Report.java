package rd.gptBot;

//Report Beispiel (Report.txt)
//Abc
//**************
//
//“Vertreter-ID | Vertreter-Name | Auftrags-Nr | Umsatz”
//“--------------------------------------------------”
//101 | Alice | 1001 | 5000.0
//1002 | 3000.0
//1003 | 4000.0
//102 | Bob | 1004 | 6000.0
//1005 | 2000.0
//103 | Charlie | 1006 | 7000.0
//1007 | 8000.0
//1008 | 9000.0


// Importieren Sie die Klassen, die wir benötigen 
import java.util.ArrayList;
import java.io.PrintWriter;

// Definieren Sie eine Klasse für einen Vertreter 
class Vertreter {
    // Definieren Sie die Attribute eines Vertreters
    int id; // Die ID des Vertreters
    String name; // Der Name des Vertreters
    ArrayList<Auftrag> aufträge; // Die Liste der Aufträge des Vertreters

    // Definieren Sie einen Konstruktor für einen Vertreter
    public Vertreter(int id, String name) {
        // Weisen Sie die Parameter den Attributen zu
        this.id = id;
        this.name = name;
        // Initialisieren Sie die Liste der Aufträge als eine leere Liste
        this.aufträge = new ArrayList<Auftrag>();
    }

    // Definieren Sie eine Methode, um einen Auftrag zu der Liste hinzuzufügen
    public void addAuftrag(Auftrag auftrag) {
        // Fügen Sie den Auftrag zu der Liste hinzu
        this.aufträge.add(auftrag);
    }

    // Definieren Sie eine Methode, um die ID des Vertreters zurückzugeben
    public int getID() {
        // Geben Sie die ID zurück
        return this.id;
    }

    // Definieren Sie eine Methode, um den Namen des Vertreters zurückzugeben
    public String getName() {
        // Geben Sie den Namen zurück
        return this.name;
    }

    // Definieren Sie eine Methode, um die Liste der Aufträge zurückzugeben
    public ArrayList<Auftrag> getAufträge() {
        // Geben Sie die Liste zurück
        return this.aufträge;
    }

    // Definieren Sie eine Methode, um die Anzahl der Aufträge zurückzugeben
    public int getAnzahlAufträge() {
        // Geben Sie die Größe der Liste zurück
        return this.aufträge.size();
    }
}

// Definieren Sie eine Klasse für einen Auftrag
class Auftrag {
    // Definieren Sie die Attribute eines Auftrags
    int nummer; // Die Nummer des Auftrags
    double umsatz; // Der Umsatz des Auftrags

    // Definieren Sie einen Konstruktor für einen Auftrag
    public Auftrag(int nummer, double umsatz) {
        // Weisen Sie die Parameter den Attributen zu
        this.nummer = nummer;
        this.umsatz = umsatz;
    }

    // Definieren Sie eine Methode, um die Nummer des Auftrags zurückzugeben
    public int getNummer() {
        // Geben Sie die Nummer zurück
        return this.nummer;
    }

    // Definieren Sie eine Methode, um den Umsatz des Auftrags zurückzugeben
    public double getUmsatz() {
        // Geben Sie den Umsatz zurück
        return this.umsatz;
    }
}

// Definieren Sie eine Klasse für das Hauptprogramm
public class Report {

    // Definieren Sie eine Methode, um das Firmenlogo zu drucken
    public static void druckeFirmenlogo(PrintWriter writer) {
        // Drucken Sie das Firmenlogo mit dem Writer-Objekt
        writer.println("Abc");
        writer.println("**************");
        writer.println();
    }

    // Definieren Sie eine Methode, um den Tabellenkopf zu drucken
    public static void druckeTabellenkopf(PrintWriter writer) {
        // Drucken Sie den Tabellenkopf mit dem Writer-Objekt
        writer.println("“Vertreter-ID | Vertreter-Name | Auftrags-Nr | Umsatz”");
        writer.println("“--------------------------------------------------”");
    }

    // Definieren Sie eine Methode, um einen Vertreter zu drucken
    public static void druckeVertreter(PrintWriter writer, Vertreter vertreter) {
        // Drucken Sie die ID und den Namen des Vertreters mit dem Writer-Objekt
        writer.print(vertreter.getID());
        writer.print(" | ");
        writer.print(vertreter.getName());
        writer.print(" | ");
    }

    // Definieren Sie eine Methode, um einen Auftrag zu drucken
    public static void druckeAuftrag(PrintWriter writer, Auftrag auftrag) {
        // Drucken Sie die Nummer und den Umsatz des Auftrags mit dem Writer-Objekt
        writer.print(auftrag.getNummer());
        writer.print(" | ");
        writer.println(auftrag.getUmsatz());
    }

    // Definieren Sie die Hauptmethode
    public static void main(String[] args) {

        // Initialisieren Sie einige Variablen
        int seite = 1; // Die aktuelle Seite
        int zeile = 1; // Die aktuelle Zeile
        ArrayList<Vertreter> vertreter = new ArrayList<Vertreter>(); // Die Liste aller Vertreter
        int anzahlVertreter; // Die Anzahl der Vertreter
        int index = 0; // Der Index des aktuellen Vertreters

        // Erstellen Sie ein Writer-Objekt, um in eine Datei zu schreiben
        try {
            PrintWriter writer = new PrintWriter("report.txt");

            // Fügen Sie einige Vertreter und Aufträge zu der Liste hinzu (Sie können diese
            // Daten ändern oder aus einer anderen Quelle lesen)
            Vertreter v1 = new Vertreter(101, "Alice");
            v1.addAuftrag(new Auftrag(1001, 5000.0));
            v1.addAuftrag(new Auftrag(1002, 3000.0));
            v1.addAuftrag(new Auftrag(1003, 4000.0));
            vertreter.add(v1);

            Vertreter v2 = new Vertreter(102, "Bob");
            v2.addAuftrag(new Auftrag(1004, 6000.0));
            v2.addAuftrag(new Auftrag(1005, 2000.0));
            vertreter.add(v2);

            Vertreter v3 = new Vertreter(103, "Charlie");
            v3.addAuftrag(new Auftrag(1006, 7000.0));
            v3.addAuftrag(new Auftrag(1007, 8000.0));
            v3.addAuftrag(new Auftrag(1008, 9000.0));
            vertreter.add(v3);

            // Holen Sie sich die Anzahl der Vertreter
            anzahlVertreter = vertreter.size();

            // Drucken Sie das Firmenlogo und den Tabellenkopf auf der ersten Seite
            druckeFirmenlogo(writer);
            druckeTabellenkopf(writer);

            // Beginnen Sie eine Schleife über alle Vertreter
            while (index < anzahlVertreter) {

                // Holen Sie sich den aktuellen Vertreter aus der Liste
                Vertreter v = vertreter.get(index);

                // Holen Sie sich die Liste der Aufträge des aktuellen Vertreters
                ArrayList<Auftrag> aufträge = v.getAufträge();

                // Holen Sie sich die Anzahl der Aufträge des aktuellen Vertreters
                int anzahlAufträge = aufträge.size();

                // Initialisieren Sie einen Zähler für die Aufträge
                int zähler = 0;

                // Drucken Sie den aktuellen Vertreter auf der nächsten Zeile
                zeile++;
                druckeVertreter(writer, v);

                // Beginnen Sie eine Schleife über alle Aufträge des aktuellen Vertreters
                while (zähler < anzahlAufträge) {

                    // Holen Sie sich den aktuellen Auftrag aus der Liste
                    Auftrag a = aufträge.get(zähler);

                    // Drucken Sie den aktuellen Auftrag auf der nächsten Zeile
                    zeile++;
                    druckeAuftrag(writer, a);

                    // Überprüfen Sie, ob die Seite voll ist
                    if ((seite == 1 && zeile == 20) || (seite > 1 && zeile == 40)) {

                        // Erhöhen Sie die Seitenzahl um eins
                        seite++;

                        // Setzen Sie die Zeilennummer auf eins zurück
                        zeile = 1;

                        // Drucken Sie den Tabellenkopf und den aktuellen Vertreter auf der neuen Seite
                        druckeTabellenkopf(writer);
                        druckeVertreter(writer, v);
                    }

                    // Erhöhen Sie den Zähler um eins
                    zähler++;
                }

                // Erhöhen Sie den Index um eins
                index++;
            }

            // Schließen Sie das Writer-Objekt
            writer.close();

        } catch (Exception e) {
            // Behandeln Sie mögliche Ausnahmen
            System.out.println(e.getMessage());
        }

    }
}
