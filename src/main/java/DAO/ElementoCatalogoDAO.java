package DAO;

import Entities.ElementoCatalogo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class ElementoCatalogoDAO {
    private final EntityManager em;
    public ElementoCatalogoDAO(EntityManager em) { this.em = em; }

    public void save(ElementoCatalogo e) {
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
    }

    public void removeByIsbn(String isbn) {
        em.getTransaction().begin();
        ElementoCatalogo found = findByIsbn(isbn);
        if (found != null) em.remove(found);
        em.getTransaction().commit();
    }

    public ElementoCatalogo findByIsbn(String isbn) {
        TypedQuery<ElementoCatalogo> q = em.createQuery(
                "select e from ElementoCatalogo e where e.isbn = :isbn", ElementoCatalogo.class);
        q.setParameter("isbn", isbn);
        return q.getResultStream().findFirst().orElse(null);
    }

    public List<ElementoCatalogo> findByAnno(int anno) {
        return em.createQuery(
                        "select e from ElementoCatalogo e where e.annoPubblicazione = :anno", ElementoCatalogo.class)
                .setParameter("anno", anno).getResultList();
    }

    public List<ElementoCatalogo> searchByTitolo(String parte) {
        return em.createQuery(
                "select e from ElementoCatalogo e where lower(e.titolo) like lower(concat('%', :p, '%'))",
                ElementoCatalogo.class).setParameter("p", parte).getResultList();
    }
}