package DAO;

import Entities.Prestito;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class PrestitoDAO {
    private final EntityManager em;
    public PrestitoDAO(EntityManager em) { this.em = em; }

    public Prestito save(Prestito p) {
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
        return p;
    }

    public Prestito restituisci(UUID prestitoId) {
        em.getTransaction().begin();
        Prestito p = em.find(Prestito.class, prestitoId);
        if (p != null && p.getDataRestituzioneEffettiva() == null) {
            p.setDataRestituzioneEffettiva(LocalDate.now());
        }
        em.getTransaction().commit();
        return p;
    }

    public List<Prestito> prestitiAttiviPerTessera(String numeroTessera) {
        return em.createQuery(
                "select p from Prestito p where p.utente.numeroTessera = :t and p.dataRestituzioneEffettiva is null",
                Prestito.class).setParameter("t", numeroTessera).getResultList();
    }

    public List<Prestito> prestitiScadutiNonRestituiti() {
        return em.createQuery(
                "select p from Prestito p where p.dataRestituzioneEffettiva is null and p.dataRestituzionePrevista < :oggi",
                Prestito.class).setParameter("oggi", LocalDate.now()).getResultList();
    }
}