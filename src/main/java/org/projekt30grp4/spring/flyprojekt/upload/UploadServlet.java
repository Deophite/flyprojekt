package org.projekt30grp4.spring.flyprojekt.upload;


import org.projekt30grp4.spring.flyprojekt.ConverterAnders;
import org.projekt30grp4.spring.flyprojekt.DatenImport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.*;

@WebServlet("/")
@MultipartConfig
public class UploadServlet extends HttpServlet {

    @Autowired
    private DatenImport datenImport;

    @Override
    public void init()  {
        // Aktiviert Spring Autowiring im Servlet-Kontext
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("multipart/form-data");

        Part filePart = request.getPart("datei");
        if (filePart == null || filePart.getSize() == 0) {
            response.getWriter().println("Fehler: Keine Datei hochgeladen.");
            return;
        }

        // Nur .xls zulassen
        String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        if (!originalFileName.toLowerCase().endsWith(".xls")) {
            request.setAttribute("fehler", "Fehler: Es sind nur .xls Dateien erlaubt.");
            request.getRequestDispatcher("/uploadresult.jsp").forward(request, response);
            return;
        }

        String userHome = System.getProperty("user.home");
        Path uploadDir = Paths.get(userHome, "upload");

        try {
            Files.createDirectories(uploadDir);
            System.out.println("Directory already exists: " + uploadDir.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Fehler: " + e.getMessage());
        }



       Path inputFile = uploadDir.resolve(originalFileName);


        // Datei speichern
        try (InputStream inputStream = filePart.getInputStream()) {
            Files.copy(inputStream, inputFile, StandardCopyOption.REPLACE_EXISTING);
        }
// .............................. KONVERTIERUNG...........................................................
        // Konvertierung und Import
        try {
            String csvPfad = ConverterAnders.convertXlsToCsv(inputFile.toString(), uploadDir.toString());
            datenImport.importDaten(csvPfad);

            // Aufräumen
            Files.deleteIfExists(Paths.get(csvPfad));
            Files.deleteIfExists(inputFile);
            // Nachdem es geklappt
            response.getWriter().println("Upload, Konvertierung und Import erfolgreich.");

        } catch (Exception e) {

            response.getWriter().println("Fehler beim Verarbeiten der Datei: " + e.getMessage());
        }
    }


}