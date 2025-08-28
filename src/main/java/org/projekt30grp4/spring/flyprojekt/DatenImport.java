package org.projekt30grp4.spring.flyprojekt;

import org.projekt30grp4.spring.flyprojekt.entities.*;
import org.projekt30grp4.spring.flyprojekt.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

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
        try (BufferedReader br = new BufferedReader(new FileReader(dateiPfad))) {
            String zeile;

            br.readLine();
            br.readLine();

            while ((zeile = br.readLine()) !=null) {
                String[] data = zeile.split(",");

                //Fluggesellschaft
                String fg_id = data[0];
                String fluggesellschaftName = data[1];
                Fluggesellschaft fg = fluggesellschaftRepository.findById(fg_id).orElse(new Fluggesellschaft());
                fg.setFg_ID(fg_id);
                fg.setFluggesellschaftName(fluggesellschaftName);
                fluggesellschaftRepository.save(fg);

                // FLughafen
                String flughafenName = data[3];
                String stadt = data[5];
                String land = data[4];
                Flughafen fh =  flughafenRepository.findById(flughafenName).orElse(new Flughafen());
                fh.setFlughafenName(flughafenName);
                fh.setStadt(stadt);
                fh.setLand(land);

                //Fluglinie
                int fl_id = Integer.parseInt(data[2]);
                //String fh_von = data[3]; Das sind beide FK...
                //String fh_nach = data[6];
                LocalTime dauer = LocalTime.parse(data[9]);
                BigDecimal preis = BigDecimal.valueOf(Double.parseDouble(data[11]));
                Fluglinie fl = fluglinieRepository.findById(fl_id).orElse(new Fluglinie());
                fl.setFl_ID(fl_id);
                fl.setDauer(dauer);
                fl.setPreis(preis);

                //Flugzeug
                String typ = data[12];
                int sitze = Integer.parseInt(data[15]);
                Flugzeug fz = flugzeugRepository.findById(typ).orElse(new Flugzeug());
                fz.setTyp(typ);
                fz.setSitze(sitze);

                //Buchung  b_id sind nicht einzigartig wir brauche hier noch den prefix vom datei-import
                String b_id = filename + data[16];  //Hier muss noch der Dateiname rausgezogen werden für prefix
                LocalDate datum  = LocalDate.parse(data[17]);
                Buchung b = buchungRepository.findById(b_id).orElse(new Buchung());
                b.setB_ID(b_id);
                b.setDatum(datum);

                //Passagier
                int p_id = Integer.parseInt(data[18]);
                String anrede = data[19];
                String kompletterName = data[20];
                String plz = data[21];
                String ort  = data[22];
                String strasse = data[23];
                String pland = data[24];
                Passagier p = passagierRepository.findById(p_id).orElse(new Passagier());
                p.setP_ID(p_id);
                p.setAnrede(anrede);
                p.setKompletterName(kompletterName);
                p.setPlz(plz);
                p.setOrt(ort);
                p.setStrasse(strasse);
                p.setLand(pland);

                //Hersteller
                String herstellerName = data[13];
                Hersteller h = new Hersteller();
                h.setHerstellername(herstellerName);
                herstellerRepository.save(h);

                //Flug
                LocalDate fdate = LocalDate.parse(data[10]);
                int bel_sitze = Integer.parseInt(data[14]);
                Flug f = new Flug();
                f.setDatum(fdate);
                f.setBel_sitze(bel_sitze);
                flugRepository.save(f);

            }
        } catch (IOException e) {
            throw new RuntimeException("Fehler beim Laden der Daten", e);
        }

    }


}
