package DAO;

import Entities.Libro;
import jakarta.persistence.EntityManager;
import java.util.List;

public class LibroDAO {
    private final EntityManager em;
    public LibroDAO(EntityManager em) { this.em = em; }

    public void save(Libro l) {
        em.getTransaction().begin();
        em.persist(l);
        em.getTransaction().commit();
    }

    public List<Libro> findByAutore(String autore) {
        return em.createQuery("select l from Libro l where lower(l.autore) = lower(:a)", Libro.class)
                .setParameter("a", autore).getResultList();
    }
}