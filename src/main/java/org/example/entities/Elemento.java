package org.example.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "elemento")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamedQueries({
        @NamedQuery(name = "getByAnnoPubblicazione", query = "SELECT e FROM Elemento e WHERE e.annoPubblicazione = :anno"),
        @NamedQuery(name = "getByTitolo", query = "SELECT e FROM Elemento e WHERE e.titolo LIKE :titolo")
})
public class Elemento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int isbn;

    private String titolo;

    @Column (name = "anno_pubblicazione")
    private int annoPubblicazione;

    @Column(name = "numero_pagine")
    private int numeroPagine;

    @OneToMany(mappedBy = "elemento")
    private Set<Prestito> prestiti = new HashSet<>();

    public Set<Prestito> getPrestiti() {
        return prestiti;
    }




    public void setPrestiti(Set<Prestito> prestiti) {
        this.prestiti = prestiti;
    }

    public Elemento() {}

    public Elemento(String titolo, int annoPubblicazione, int numeroPagine) {
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.numeroPagine = numeroPagine;
    }


    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public void setAnnoPubblicazione(int annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    public void setNumeroPagine(int numeroPagine) {
        this.numeroPagine = numeroPagine;
    }

    @Override
    public String toString() {
        return "Elemento{" +
                "isbn='" + isbn + '\'' +
                ", titolo='" + titolo + '\'' +
                ", annoPubblicazione=" + annoPubblicazione +
                ", numeroPagine=" + numeroPagine +
                '}';
    }
}
