package org.example.dao;

import org.example.entities.Prestito;

import javax.persistence.*;
import java.util.List;

public class PrestitoDao {
    private EntityManagerFactory emf;
    private EntityManager em;


    public PrestitoDao(){
        emf = Persistence.createEntityManagerFactory("biblioteca");
        em = emf.createEntityManager();
    }


    public void save(Prestito p){
        EntityTransaction et = em.getTransaction();
        et.begin();

        em.persist(p);

        et.commit();
    }

    public Prestito getById(Long id){
        return em.find(Prestito.class, id);
    }

    public void delete(Prestito p){
        Prestito pt = getById(p.getId());

        EntityTransaction et = em.getTransaction();
        et.begin();

        em.remove(pt);

        et.commit();
    }


    public List<Prestito> getPrestitiByNumeroTessera(int numeroTessera) {
        TypedQuery<Prestito> q = em.createNamedQuery("getPrestitiByNumeroTessera", Prestito.class);
        q.setParameter("numeroTessera", numeroTessera);
        return q.getResultList();
    }



    public List<Prestito> getPrestitiScadutiNonRestituiti() {
        TypedQuery<Prestito> q = em.createNamedQuery("getPrestitiScadutiNonRestituiti", Prestito.class);
        return q.getResultList();
    }
}
