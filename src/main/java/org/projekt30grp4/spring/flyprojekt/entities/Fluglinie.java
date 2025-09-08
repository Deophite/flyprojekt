package org.projekt30grp4.spring.flyprojekt.entities;

import com.sun.xml.internal.ws.resources.UtilMessages;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fluglinie {

    @Id
    private int fl_ID;

    @ManyToOne
    @JoinColumn(name = "Fh_Von")
    private Flughafen startFlughafen;

    @ManyToOne
    @JoinColumn(name = "Fh_Nach")
    private Flughafen zielFlughafen;

    private LocalTime dauer;
    private BigDecimal preis;

    @ManyToOne
    @JoinColumn(name = "typ")
    private Flugzeug flugzeug;

    @ManyToOne
    @JoinColumn(name = "fg_ID")
    private Fluggesellschaft fluggesellschaft;

}
