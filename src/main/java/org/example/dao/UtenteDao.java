package org.example.dao;

import org.example.entities.Utente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class UtenteDao {
    private EntityManagerFactory emf;
    private EntityManager em;

    // Costruttore: inizializza l'EntityManagerFactory e l'EntityManager
    public UtenteDao() {
        emf = Persistence.createEntityManagerFactory("biblioteca");
        em = emf.createEntityManager();
    }

    // Metodo per salvare un utente nel database
    public void save(Utente u) {
        EntityTransaction et = em.getTransaction();
        et.begin();

        em.persist(u);

        et.commit();
    }

    // Metodo per ottenere un utente dato il suo ID (Numero Tessera)
    public Utente getById(int id) {
        return em.find(Utente.class, id);
    }

    // Metodo per eliminare un utente dal database
    public void delete(Utente u) {
        Utente ut = getById(u.getNumeroTessera());

        EntityTransaction et = em.getTransaction();
        et.begin();

        em.remove(ut);

        et.commit();
    }
}
