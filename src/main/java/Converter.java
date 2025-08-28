import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

public class Converter {
    public static void main(String[] args) {



        //Variablen für die Input und Output Dateien. Bei Input muss noch geschaut werden, wie die Datei hinzugefügt wird für Variablen Namen.
        String input = "src/main/dateixls/AA.xls";
        String output = "src/main/dateicsv/" + DateiName(input) + ".xls";

        Workbook workbook = new Workbook();

        //Die xls Datei wird hier geladen, der source woher er die Datei nimmt.
        workbook.loadFromFile(input);

        //Damit nimmt er die gesamte seite(sheet) der xls datei von "get(0)"
        Worksheet sheet = workbook.getWorksheets().get(0);

        //Hier speichert er die vorher ausgelesene Seite als csv Datei im Angegebenen Pfad und namen.
        sheet.saveToFile(output,",");

    }

    //Funktion um den Dateinamen auszulesen.
    public static String DateiName (String input) {

        //Dateiname wird hier aus dem Inputpfad rausgenommen und kann weiter genutzt werden.
        String dateiname = input.substring(input.lastIndexOf("/")+1, input.lastIndexOf("."));

        return dateiname;
    }

}
