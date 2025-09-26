package Entities;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("RIVISTA")
public class Rivista extends ElementoCatalogo {
    @Enumerated(EnumType.STRING)
    @Column
    private Periodicita periodicita;

    //getters & setters
    public Periodicita getPeriodicita() { return periodicita; }
    public void setPeriodicita(Periodicita periodicita) { this.periodicita = periodicita; }
}