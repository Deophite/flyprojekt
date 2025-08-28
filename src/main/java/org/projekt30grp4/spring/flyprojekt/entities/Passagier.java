package org.projekt30grp4.spring.flyprojekt.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Passagier {

    @Id
    private int p_ID;
    private String anrede;
    private String kompletterName;
    private String plz;
    private String ort;
    private String Strasse;
    private String land;

    @OneToMany(mappedBy = "Passagier")
    private List<Buchung> buchungen;

}
