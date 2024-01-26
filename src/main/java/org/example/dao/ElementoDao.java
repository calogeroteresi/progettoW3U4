package org.example.dao;

import org.example.entities.Elemento;
import org.example.entities.Libro;

import javax.persistence.*;
import java.util.List;

public class ElementoDao {
    private EntityManagerFactory emf;
    private EntityManager em;

    // Costruttore: inizializza l'EntityManagerFactory e l'EntityManager
    public ElementoDao() {
        emf = Persistence.createEntityManagerFactory("biblioteca");
        em = emf.createEntityManager();
    }

    // Metodo per salvare un elemento nel database
    public void save(Elemento e) {
        EntityTransaction et = em.getTransaction();
        et.begin();

        em.persist(e);

        et.commit();
    }

    // Metodo per ottenere un elemento dato il suo ISBN
    public Elemento getById(int isbn) {
        return em.find(Elemento.class, isbn);
    }

    // Metodo per eliminare un elemento dal database
    public void delete(Elemento e) {
        Elemento ev = getById(e.getIsbn());

        EntityTransaction et = em.getTransaction();
        et.begin();

        em.remove(ev);

        et.commit();
    }

    // Metodo per chiudere l'EntityManager e l'EntityManagerFactory
    public void close() {
        em.close();
        emf.close();
    }

    // Metodo per ottenere una lista di elementi dato l'anno di pubblicazione
    public List<?> getByAnnoPubblicazione(int anno) {
        Query q = em.createNamedQuery("getByAnnoPubblicazione");
        q.setParameter("anno", anno);
        return q.getResultList();
    }

    // Metodo per ottenere una lista di elementi dato il titolo
    public List<Elemento> getByTitolo(String titolo) {
        TypedQuery<Elemento> q = em.createNamedQuery("getByTitolo", Elemento.class);
        q.setParameter("titolo", "%" + titolo + "%");
        return q.getResultList();
    }

    // Metodo per ottenere una lista di libri dato l'autore
    public List<Libro> getByAutore(String autore) {
        TypedQuery<Libro> query = em.createNamedQuery("getByAutore", Libro.class);
        query.setParameter("autore", autore);
        return query.getResultList();
    }
}
