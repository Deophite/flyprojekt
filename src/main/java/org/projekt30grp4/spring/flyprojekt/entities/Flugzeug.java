package org.projekt30grp4.spring.flyprojekt.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Flugzeug {

    @Id
    private String typ;

    @ManyToOne
    @JoinColumn(name = "H_ID")
    private Hersteller hersteller;

    private int sitze;

    @OneToMany(mappedBy = "flugzeug")
    private List<Fluglinie> fluglinien;

    @OneToMany(mappedBy = "flugzeug")
    private List<Flug> flug;


}
