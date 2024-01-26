package org.example.dao;

import org.example.entities.Prestito;

import javax.persistence.*;
import java.util.List;

public class PrestitoDao {
    private EntityManagerFactory emf;
    private EntityManager em;

    // Costruttore: inizializza l'EntityManagerFactory e l'EntityManager
    public PrestitoDao() {
        emf = Persistence.createEntityManagerFactory("biblioteca");
        em = emf.createEntityManager();
    }

    // Metodo per salvare un prestito nel database
    public void save(Prestito p) {
        EntityTransaction et = em.getTransaction();
        et.begin();

        // Verifica se il libro è presente nella lista dei prestiti
        int isbnDelLibro = p.getElemento().getIsbn();
        List<Prestito> prestitiDelLibro = getPrestitiByIsbn(isbnDelLibro);

        // Verifica se il libro è disponibile se la data di inizio prestito del nuovo prestito
        // è posteriore alla data di restituzione di eventuali prestiti precendenti collegati al libro

        boolean isLibroDisponibile = prestitiDelLibro.stream()
                .filter(prestito -> prestito.getDataRestituzioneEffettiva() != null)
                .allMatch(prestito -> prestito.getDataRestituzioneEffettiva().isBefore(p.getDataInizioPrestito()));

        if (isLibroDisponibile) {
            em.persist(p);
            System.out.println("Prestito salvato con successo.");
        } else {
            System.out.println("Il libro con ISBN " + isbnDelLibro + " non è disponibile per un nuovo prestito.");
        }

        et.commit();
    }

    // Metodo per ottenere una lista di prestiti dato l'ISBN di un libro
    public List<Prestito> getPrestitiByIsbn(int isbn) {
        TypedQuery<Prestito> q = em.createNamedQuery("getPrestitiByIsbn", Prestito.class);
        q.setParameter("isbn", isbn);
        return q.getResultList();
    }

    // Metodo per ottenere un prestito dato il suo ID
    public Prestito getById(Long id) {
        return em.find(Prestito.class, id);
    }

    // Metodo per eliminare un prestito dal database
    public void delete(Prestito p) {
        Prestito pt = getById(p.getId());

        EntityTransaction et = em.getTransaction();
        et.begin();

        em.remove(pt);

        et.commit();
    }

    // Metodo per ottenere una lista di prestiti dato il numero di tessera
    public List<Prestito> getPrestitiByNumeroTessera(int numeroTessera) {
        TypedQuery<Prestito> q = em.createNamedQuery("getPrestitiByNumeroTessera", Prestito.class);
        q.setParameter("numeroTessera", numeroTessera);
        return q.getResultList();
    }

    // Metodo per ottenere una lista di prestiti scaduti e non restituiti
    public List<Prestito> getPrestitiScadutiNonRestituiti() {
        TypedQuery<Prestito> q = em.createNamedQuery("getPrestitiScadutiNonRestituiti", Prestito.class);
        return q.getResultList();
    }
}
