package org.projekt30grp4.spring.flyprojekt.upload;

import org.projekt30grp4.spring.flyprojekt.ConverterAnders;
import org.projekt30grp4.spring.flyprojekt.DatenImport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.file.*;

@RestController
@RequestMapping("/upload")
public class UploadController {


    @PostConstruct
    public void init() {
        System.out.println("UploadController wurde geladen!");
    }

    @Autowired
    private DatenImport datenImport;

    @PostMapping
    public ResponseEntity<String> handleFileUpload(@RequestParam("datei") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.ok("<html><body><h3>Upload erfolgreich!</h3></body></html>");
//            return ResponseEntity.badRequest().body("Fehler: Keine Datei hochgeladen.");
        }

        String originalFileName = file.getOriginalFilename();
        if (originalFileName == null || !originalFileName.toLowerCase().endsWith(".xls")) {
            return ResponseEntity.badRequest().body("Fehler: Nur .xls-Dateien sind erlaubt.");
        }

        try {
            Path uploadDir = Paths.get("C:/Users/AAdamskiIdeaProjects/flyprojekt/uploads/dateixls/");
            Files.createDirectories(uploadDir);
            Path inputFile = uploadDir.resolve(originalFileName);
            Files.copy(file.getInputStream(), inputFile, StandardCopyOption.REPLACE_EXISTING);

            String csvPfad1 = ConverterAnders.convertXlsToCsv(inputFile.toString());
            String csvPfad = csvPfad1.replaceAll("/", "\\\\");

            datenImport.importDaten(csvPfad);

            Files.deleteIfExists(Paths.get(csvPfad));
            Files.deleteIfExists(inputFile);

            return ResponseEntity.ok("Upload, Konvertierung und Import erfolgreich.");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Fehler beim Verarbeiten der Datei: " + e.getMessage());
        }
    }

    @GetMapping("/test")
    public String test() {
        return "Controller aktiv!";
    }

}
