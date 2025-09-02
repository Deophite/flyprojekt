package org.projekt30grp4.spring.flyprojekt;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class test {
    public static void main(String[] args) {

        Button start db inject

        Converteralt.Convert();

        datei löschen

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("org.projekt30grp4.spring");
        context.refresh();

        DatenImport importer = context.getBean(DatenImport.class);

        importer.importDaten("src/main/dateicsv/" + Converteralt.convname + ".csv");

        datei löschen

        context.close();

        System.out.println("Das Daten wurde erfolgreich gefunden und weitergeschickt.");
    }
}
