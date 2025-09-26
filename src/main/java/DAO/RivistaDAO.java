package DAO;

import Entities.Rivista;
import jakarta.persistence.EntityManager;

public class RivistaDAO {
    private final EntityManager em;
    public RivistaDAO(EntityManager em) { this.em = em; }

    public void save(Rivista r) {
        em.getTransaction().begin();
        em.persist(r);
        em.getTransaction().commit();
    }
}