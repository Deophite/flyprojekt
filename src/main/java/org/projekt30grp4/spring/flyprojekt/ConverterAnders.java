package org.projekt30grp4.spring.flyprojekt;

import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

import java.io.File;

public class ConverterAnders {

    // Wird z.B. in DatenImport als Präfix genutzt
    public static String convname = "unbekannt";

    public static String convertXlsToCsv(String inputPfad, String uploadDir) {

        try {
            // Dateiname extrahieren (ohne Endung)
            String dateiname = DateiName(inputPfad);
            convname = dateiname;

            // Zielpfad für .csv festlegen
            String outputPfad = uploadDir + dateiname + ".csv";

            // XLS laden
            Workbook workbook = new Workbook();
            workbook.loadFromFile(inputPfad);

            // Erstes Tabellenblatt auswählen
            Worksheet sheet = workbook.getWorksheets().get(0);

            // Als CSV abspeichern
            sheet.saveToFile(outputPfad, ",");

            return outputPfad;

        } catch (Exception e) {
            throw new RuntimeException("Fehler beim Konvertieren von XLS zu CSV: " + e.getMessage(), e);
        }
    }

    //Methode zum extrahieren des puren Namens aus dem Pfad + Datei.
    public static String DateiName(String inputPfad) {
        File file = new File(inputPfad);
        String name = file.getName(); // z.B. "AA.xls"
        return name.substring(0, name.lastIndexOf('.'));
    }
}