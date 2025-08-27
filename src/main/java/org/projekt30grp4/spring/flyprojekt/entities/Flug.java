package org.projekt30grp4.spring.flyprojekt.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Flug {

    @Id
    private int flug_ID;
    private Date datum;
    private int bel_sitze;

    @ManyToOne
    @JoinColumn(name = "Typ")
    private Flugzeug flugzeug;

    @ManyToOne
    @JoinColumn(name = "B_ID")
    private Buchung buchung;
}
