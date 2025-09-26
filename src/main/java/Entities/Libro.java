package Entities;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("LIBRO")
public class Libro extends ElementoCatalogo {
    @Column
    private String autore;
    @Column
    private String genere;

    //getters & setters
    public String getAutore() { return autore; }
    public void setAutore(String autore) { this.autore = autore; }

    public String getGenere() { return genere; }
    public void setGenere(String genere) { this.genere = genere; }
}
