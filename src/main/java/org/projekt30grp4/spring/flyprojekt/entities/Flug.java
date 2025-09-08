package org.projekt30grp4.spring.flyprojekt.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Flug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int flug_ID;
    private String datum;
    private int bel_sitze;

    @ManyToOne
    @JoinColumn(name = "typ")
    private Flugzeug flugzeug;

    @ManyToOne
    @JoinColumn(name = "b_ID")
    private Buchung buchung;
}
