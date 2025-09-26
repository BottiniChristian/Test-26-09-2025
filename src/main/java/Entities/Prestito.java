package Entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "prestito")
public class Prestito {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "utente_id")
    private Utente utente;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "elemento_id")
    private ElementoCatalogo elemento;

    @Column(name = "data_inizio_prestito", nullable = false)
    private LocalDate dataInizioPrestito;

    @Column(name = "data_restituzione_prevista", nullable = false)
    private LocalDate dataRestituzionePrevista;

    @Column(name = "data_restituzione_effettiva")
    private LocalDate dataRestituzioneEffettiva;

    @PrePersist
    void onCreate() {
        if (dataInizioPrestito == null) dataInizioPrestito = LocalDate.now();
        if (dataRestituzionePrevista == null) dataRestituzionePrevista = dataInizioPrestito.plusDays(30);
    }

    //getters & setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Utente getUtente() { return utente; }
    public void setUtente(Utente utente) { this.utente = utente; }

    public ElementoCatalogo getElemento() { return elemento; }
    public void setElemento(ElementoCatalogo elemento) { this.elemento = elemento; }

    public LocalDate getDataInizioPrestito() { return dataInizioPrestito; }
    public void setDataInizioPrestito(LocalDate dataInizioPrestito) { this.dataInizioPrestito = dataInizioPrestito; }

    public LocalDate getDataRestituzionePrevista() { return dataRestituzionePrevista; }
    public void setDataRestituzionePrevista(LocalDate dataRestituzionePrevista) { this.dataRestituzionePrevista = dataRestituzionePrevista; }

    public LocalDate getDataRestituzioneEffettiva() { return dataRestituzioneEffettiva; }
    public void setDataRestituzioneEffettiva(LocalDate dataRestituzioneEffettiva) { this.dataRestituzioneEffettiva = dataRestituzioneEffettiva; }
}