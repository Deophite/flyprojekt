package org.projekt30grp4.spring.flyprojekt.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Buchung {

    @Id
    private String b_ID;
    private LocalDate datum;

    @ManyToOne
    @JoinColumn(name = "P_ID")
    private Passagier passagier;

    @OneToMany(mappedBy = "buchung")
    private List<Flug> fluege;
}
