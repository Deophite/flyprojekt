package org.projekt30grp4.spring.flyprojekt;

import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

public class Converter {

    public static String input = "";
    //Nutzung in Datenimport für Prefix
    public static String convname = DateiName(input);

    public static String Convert(String input) {

        //Variablen für die Input und Output Dateien. Bei Input muss noch geschaut werden, wie die Datei hinzugefügt wird für Variablen Namen.
        String output = "uploads/dateicsv" + convname + ".csv";

        try {
            Workbook workbook = new Workbook();

            //Die xls Datei wird hier geladen, der source woher er die Datei nimmt.
            workbook.loadFromFile(input);

            //Damit nimmt er die gesamte seite(sheet) der xls datei von "get(0)"
            Worksheet sheet = workbook.getWorksheets().get(0);

            //Hier speichert er die vorher ausgelesene Seite als csv Datei im Angegebenen Pfad und namen.
            sheet.saveToFile(output, ",");

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.err.println("Datei nicht gefunden.");
        }

        return output;
    }

    //Funktion um den Dateinamen auszulesen.
    public static String DateiName(String input) {

        //Dateiname wird hier aus dem Inputpfad rausgenommen und kann weiter genutzt werden.
        String dateiname = input.substring(input.lastIndexOf("/")+1, input.lastIndexOf("."));

        return dateiname;
    }



}
