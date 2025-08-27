package org.projekt30grp4.spring.flyprojekt;

import org.projekt30grp4.spring.flyprojekt.entities.Fluggesellschaft;
import org.projekt30grp4.spring.flyprojekt.entities.Flughafen;
import org.projekt30grp4.spring.flyprojekt.entities.Fluglinie;
import org.projekt30grp4.spring.flyprojekt.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
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
    FlugRepository flugRepository;
    @Autowired
    FluglinieRepository fluglinieRepository;

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

            }
        }

    }


}
