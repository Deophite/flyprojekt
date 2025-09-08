package org.projekt30grp4.spring.flyprojekt;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class test {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("org.projekt30grp4.spring");
        context.refresh();

        DatenImport importer = context.getBean(DatenImport.class);

        importer.importDaten("src/main/dateicsv/AA.csv");

        context.close();
    }
}
