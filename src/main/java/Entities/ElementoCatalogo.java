package Entities;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "elemento_catalogo",
        uniqueConstraints = @UniqueConstraint(name = "uk_isbn", columnNames = "isbn"))
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public abstract class ElementoCatalogo {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, length = 20, unique = true)
    private String isbn;

    @Column(nullable = false)
    private String titolo;

    @Column(name = "anno_pubblicazione", nullable = false)
    private int annoPubblicazione;

    @Column(name = "numero_pagine", nullable = false)
    private int numeroPagine;

    //getters & setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getTitolo() { return titolo; }
    public void setTitolo(String titolo) { this.titolo = titolo; }

    public int getAnnoPubblicazione() { return annoPubblicazione; }
    public void setAnnoPubblicazione(int annoPubblicazione) { this.annoPubblicazione = annoPubblicazione; }

    public int getNumeroPagine() { return numeroPagine; }
    public void setNumeroPagine(int numeroPagine) { this.numeroPagine = numeroPagine; }
}