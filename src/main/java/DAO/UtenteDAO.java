package DAO;

import Entities.Utente;
import jakarta.persistence.EntityManager;

public class UtenteDAO {
    private final EntityManager em;
    public UtenteDAO(EntityManager em) { this.em = em; }

    public void save(Utente u) {
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
    }

    public Utente findByNumeroTessera(String nt) {
        return em.createQuery("select u from Utente u where u.numeroTessera = :nt", Utente.class)
                .setParameter("nt", nt).getResultStream().findFirst().orElse(null);
    }
}
