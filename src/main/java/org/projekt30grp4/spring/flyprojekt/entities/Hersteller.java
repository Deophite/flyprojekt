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
public class Hersteller {

    @Id
    private int H_ID;
    private String Herstellername;

    @OneToMany(mappedBy = "hersteller")
    private List<Flugzeug> flugzeuge;
}
