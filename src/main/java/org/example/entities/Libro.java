package org.example.entities;


import javax.persistence.*;

@Entity
@Table(name = "libro")
@NamedQuery(
        name = "getByAutore",
        query = "SELECT l FROM Libro l WHERE l.autore = :autore"
)
public class Libro extends Elemento{

    private String autore;

    @Enumerated(EnumType.STRING)
    private Genere genere;

    public Libro() {}

    public Libro(String titolo, int annoPubblicazione, int numeroPagine, String autore, Genere genere) {
        super(titolo, annoPubblicazione, numeroPagine);
        this.autore = autore;
        this.genere = genere;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public Genere getGenere() {
        return genere;
    }

    public void setGenere(Genere genere) {
        this.genere = genere;
    }

    public String toString() {
        return "Libro{" +
                "titolo='" + getTitolo() + '\'' +
                ", autore='" + autore + '\'' +
                ", genere=" + genere +
                ", annoPubblicazione=" + getAnnoPubblicazione() +
                ", numeroPagine=" + getNumeroPagine() +
                ", isbn='" + getIsbn() + '\'' +
                '}';
    }
}
