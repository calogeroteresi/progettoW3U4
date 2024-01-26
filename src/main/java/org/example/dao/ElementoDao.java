package org.example.dao;

import org.example.entities.Elemento;
import org.example.entities.Libro;

import javax.persistence.*;
import java.util.List;

public class ElementoDao {
    private EntityManagerFactory emf;
    private EntityManager em;


    public ElementoDao(){
        emf = Persistence.createEntityManagerFactory("biblioteca");
        em = emf.createEntityManager();
    }


    public void save(Elemento e){
        EntityTransaction et = em.getTransaction();
        et.begin();

        em.persist(e);

        et.commit();
    }

    public Elemento getById(int isbn){
        return em.find(Elemento.class, isbn);
    }

    public void delete(Elemento e){
        Elemento ev = getById(e.getIsbn());

        EntityTransaction et = em.getTransaction();
        et.begin();

        em.remove(ev);

        et.commit();
    }

    public void close(){
        em.close();
        emf.close();
    }

    public List<?> getByAnnoPubblicazione(int anno){
        Query q = em.createNamedQuery("getByAnnoPubblicazione");
        q.setParameter("anno", anno );
        return q.getResultList();
    }

    public List<Elemento> getByTitolo(String titolo) {
        TypedQuery<Elemento> q = em.createNamedQuery("getByTitolo", Elemento.class);
        q.setParameter("titolo", "%" + titolo + "%");
        return q.getResultList();
    }


    public List<Libro> getByAutore(String autore) {
        TypedQuery<Libro> query = em.createNamedQuery("getByAutore", Libro.class);
        query.setParameter("autore", autore);
        return query.getResultList();
    }





}


