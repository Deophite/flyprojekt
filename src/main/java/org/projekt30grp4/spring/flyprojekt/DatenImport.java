package org.projekt30grp4.spring.flyprojekt;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import org.projekt30grp4.spring.flyprojekt.entities.*;
import org.projekt30grp4.spring.flyprojekt.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class DatenImport {

    @Autowired
    private HerstellerRepository herstellerRepository;
    @Autowired
    private FlugzeugRepository flugzeugRepository;
    @Autowired
    private FlughafenRepository flughafenRepository;
    @Autowired
    private FluggesellschaftRepository fluggesellschaftRepository;
    @Autowired
    private PassagierRepository passagierRepository;
    @Autowired
    private BuchungRepository buchungRepository;
    @Autowired
    private FlugRepository flugRepository;
    @Autowired
    private FluglinieRepository fluglinieRepository;


    @Transactional
    public void importDaten(String dateiPfad) {

        List<Hersteller> herstellerList = new ArrayList<>();
        List<Flugzeug> flugzeugList = new ArrayList<>();
        List<Fluggesellschaft> fluggesellschaftList = new ArrayList<>();
        List<Passagier> passagierList = new ArrayList<>();
        List<Buchung> buchungList = new ArrayList<>();
        List<Flug> flugList = new ArrayList<>();
        List<Flughafen> flughafenList = new ArrayList<>();
        List<Fluglinie> fluglinieList = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(dateiPfad))) {
            List<String[]> allRows = reader.readAll();

            for (int i = 2; i < allRows.size() - 1; i++) {
                String[] data = allRows.get(i);

                DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("H:mm");
                DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm");

                //Fluggesellschaft
                String fg_id = data[0];
                String fluggesellschaftName = data[1];
                Fluggesellschaft fg = fluggesellschaftRepository.findById(fg_id).orElse(new Fluggesellschaft());
                fg.setFg_ID(fg_id);
                fg.setFluggesellschaftName(fluggesellschaftName);
                fluggesellschaftList.add(fg);

                // FLughafen
                String flughafenName = data[4];
                String stadt = data[6];
                String land = data[5];
                Flughafen fh =  flughafenRepository.findById(flughafenName).orElse(new Flughafen());
                fh.setFlughafenName(flughafenName);
                fh.setStadt(stadt);
                fh.setLand(land);
                flughafenList.add(fh);

                //Fluglinie
                int fl_id = Integer.parseInt(data[3]);
                //String fh_von = data[3]; Das sind beide FK...
                //String fh_nach = data[6];
                LocalTime dauer;
                if (data[10].trim().length() == 4) {
                     dauer = LocalTime.parse(data[10].trim(),formatter1);
                } else {
                     dauer = LocalTime.parse(data[10].trim(),formatter2);
                }
                BigDecimal preis = BigDecimal.valueOf(Double.parseDouble(data[12].replaceAll("\"", "").replaceAll(",",".")));
                Fluglinie fl = fluglinieRepository.findById(fl_id).orElse(new Fluglinie());
                fl.setFl_ID(fl_id);
                fl.setDauer(dauer);
                fl.setPreis(preis);
                fluglinieList.add(fl);

                //Flugzeug
                String typ = data[13];
                int sitze = Integer.parseInt(data[16]);
                Flugzeug fz = flugzeugRepository.findById(typ).orElse(new Flugzeug());
                fz.setTyp(typ);
                fz.setSitze(sitze);
                flugzeugList.add(fz);

                //Buchung  b_id sind nicht einzigartig wir brauche hier noch den prefix vom datei-import
                String b_id =  data[0] + "-" +data[17];  //Hier muss noch der Dateiname rausgezogen werden für prefix
                String datum  = data[18];
                Buchung b = buchungRepository.findById(b_id).orElse(new Buchung());
                b.setB_ID(b_id);
                b.setDatum(datum);
                buchungList.add(b);

                //Passagier
                int p_id = Integer.parseInt(data[19]);
                String anrede = data[20];
                String kompletterName = data[21];
                String plz = data[22];
                String ort  = data[23];
                String strasse = data[24];
                String pland = data[25];
                Passagier p = passagierRepository.findById(p_id).orElse(new Passagier());
                p.setP_ID(p_id);
                p.setAnrede(anrede);
                p.setKompletterName(kompletterName);
                p.setPlz(plz);
                p.setOrt(ort);
                p.setStrasse(strasse);
                p.setLand(pland);
                passagierList.add(p);

                //Hersteller
                String herstellerName = data[14];
                //schaut ob es den herstellerNamen gibt. Dazu die Repository, die h_id läuft mit @GeneratedValue, autoincrement. Kann danach nciht suchen
                Hersteller h = herstellerRepository.findByHerstellerName(herstellerName).orElseGet(() -> {
                    //Wenn es den Namen nicht gibt, wird ein neuer Eintrag erstellt
                    Hersteller h1 = new Hersteller();
                    h1.setHerstellerName(herstellerName);
                    return herstellerRepository.save(h1);
                        });
                h.setHerstellerName(herstellerName);
                herstellerList.add(h);

                //Flug
                String fdate = data[11];
                int bel_sitze = Integer.parseInt(data[15]);
                Flug f = new Flug();
                f.setDatum(fdate);
                f.setBel_sitze(bel_sitze);
                flugList.add(f);

            }

        } catch (IOException e) {
            throw new RuntimeException("Fehler beim Laden der Daten", e);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        } catch (CsvException e) {
            throw new RuntimeException(e);
        } finally {

            fluggesellschaftRepository.saveAll(fluggesellschaftList);
            flughafenRepository.saveAll(flughafenList);
            fluglinieRepository.saveAll(fluglinieList);
            flugzeugRepository.saveAll(flugzeugList);
            buchungRepository.saveAll(buchungList);
            passagierRepository.saveAll(passagierList);
            herstellerRepository.saveAll(herstellerList);
            flugRepository.saveAll(flugList);
        }
    }
}
