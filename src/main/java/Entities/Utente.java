package Entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "utente",
        uniqueConstraints = @UniqueConstraint(name = "uk_tessera", columnNames = "numero_tessera"))
public class Utente {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

    @Column(name = "data_nascita", nullable = false)
    private LocalDate dataNascita;

    @Column(name = "numero_tessera", nullable = false)
    private String numeroTessera;

    //getters & setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCognome() { return cognome; }
    public void setCognome(String cognome) { this.cognome = cognome; }

    public LocalDate getDataNascita() { return dataNascita; }
    public void setDataNascita(LocalDate dataNascita) { this.dataNascita = dataNascita; }

    public String getNumeroTessera() { return numeroTessera; }
    public void setNumeroTessera(String numeroTessera) { this.numeroTessera = numeroTessera; }
}
