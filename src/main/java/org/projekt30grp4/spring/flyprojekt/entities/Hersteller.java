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
public class Hersteller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int H_ID;
    private String herstellerName;

    @OneToMany(mappedBy = "hersteller")
    private List<Flugzeug> flugzeuge;
}
