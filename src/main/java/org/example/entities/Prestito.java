package org.example.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="prestito")
@NamedQueries({
        @NamedQuery(name = "getPrestitiByNumeroTessera",
                    query = "SELECT p FROM Prestito p WHERE p.utente.numeroTessera = :numeroTessera"),
        @NamedQuery(
                name = "getPrestitiByIsbn",
                query = "SELECT p FROM Prestito p WHERE p.elemento.isbn = :isbn"),
        @NamedQuery( name = "getPrestitiScadutiNonRestituiti",
                     query = "SELECT p FROM Prestito p WHERE p.dataRestituzionePrevista < CURRENT_DATE AND p.dataRestituzioneEffettiva IS NULL")
})
public class Prestito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "elemento_isbn")
    private Elemento elemento;

    @ManyToOne
    @JoinColumn(name = "utente_tessera")
    private Utente utente;

    @Column(name = "data_inizio_prestito")
    private LocalDate dataInizioPrestito;

    @Column(name = "data_restituzione_prevista")
    private LocalDate dataRestituzionePrevista;

    @Column(name = "data_restituzione_effettiva")
    private LocalDate dataRestituzioneEffettiva;

    public Prestito() {
    }

    public Prestito(Elemento elemento, Utente utente, LocalDate dataInizioPrestito, LocalDate dataRestituzionePrevista, LocalDate dataRestituzioneEffettiva) {
        this.elemento = elemento;
        this.utente = utente;
        this.dataInizioPrestito = dataInizioPrestito;
        this.dataRestituzionePrevista = dataRestituzionePrevista;
        this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Elemento getElemento() {
        return elemento;
    }

    public void setElemento(Elemento elemento) {
        this.elemento = elemento;
    }

    public LocalDate getDataInizioPrestito() {
        return dataInizioPrestito;
    }

    public void setDataInizioPrestito(LocalDate dataInizioPrestito) {
        this.dataInizioPrestito = dataInizioPrestito;
    }

    public LocalDate getDataRestituzionePrevista() {
        return dataRestituzionePrevista;
    }

    public void setDataRestituzionePrevista(LocalDate dataRestituzionePrevista) {
        this.dataRestituzionePrevista = dataRestituzionePrevista;
    }

    public LocalDate getDataRestituzioneEffettiva() {
        return dataRestituzioneEffettiva;
    }

    public void setDataRestituzioneEffettiva(LocalDate dataRestituzioneEffettiva) {
        this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
    }


    @Override
    public String toString() {
        String tipoDescrizione = (elemento.getTitolo() != null) ? elemento.getTitolo() : "N/A";
        String dataRestituzioneEffettivaDescrizione = (dataRestituzioneEffettiva != null) ? dataRestituzioneEffettiva.toString() : "Non restituito";

        return "Prestito{" +
                "id=" + id +
                ", titolo='" + tipoDescrizione + '\'' +
                "isbn"+ elemento.getIsbn()+
                ", dataInizioPrestito=" + dataInizioPrestito +
                ", dataRestituzionePrevista=" + dataRestituzionePrevista +
                ", dataRestituzioneEffettiva=" + dataRestituzioneEffettivaDescrizione +
                '}';
    }



}
