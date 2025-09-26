package ChristianBottini;

import DAO.*;
import Entities.*;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;

public class Application {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEmf().createEntityManager();

        try {
            UtenteDAO utenteDAO = new UtenteDAO(em);
            ElementoCatalogoDAO elementoDAO = new ElementoCatalogoDAO(em);
            LibroDAO libroDAO = new LibroDAO(em);
            RivistaDAO rivistaDAO = new RivistaDAO(em);
            PrestitoDAO prestitoDAO = new PrestitoDAO(em);

            //creo utente
            Utente u = new Utente();
            u.setNome("Roberto");
            u.setCognome("Baggio");
            u.setDataNascita(LocalDate.of(1974, 5, 12));
            u.setNumeroTessera("T321");
            utenteDAO.save(u);

            //creo un libro e una rivista
            Libro l = new Libro();
            l.setIsbn("9999900000001");
            l.setTitolo("Il titolo");
            l.setAnnoPubblicazione(1990);
            l.setNumeroPagine(700);
            l.setAutore("Umberto Foscolo");
            l.setGenere("La via del codice");
            libroDAO.save(l);

            Rivista r = new Rivista();
            r.setIsbn("987654332101234");
            r.setTitolo("La Gazzetta dello sport");
            r.setAnnoPubblicazione(2025);
            r.setNumeroPagine(99);
            r.setPeriodicita(Periodicita.SETTIMANALE);
            rivistaDAO.save(r);

            //ricerche
            System.out.println("Ricerca ISBN 9999900000001 -> " + (elementoDAO.findByIsbn("9999900000001") != null));
            System.out.println("Libri di Umberto Foscolo: " + libroDAO.findByAutore("Umberto Foscolo").size());
            System.out.println("Per anno 1990: " + elementoDAO.findByAnno(1980).size());
            System.out.println("Per titolo 'rosa': " + elementoDAO.searchByTitolo("rosa").size());

            //avvia prestito
            Prestito p = new Prestito();
            p.setUtente(u);
            p.setElemento(l);
            prestitoDAO.save(p);
            System.out.println("Prestiti attivi per T321: " + prestitoDAO.prestitiAttiviPerTessera("T321").size());

            //prestiti scaduti
            System.out.println("Prestiti scaduti: " + prestitoDAO.prestitiScadutiNonRestituiti().size());

            //restituzione
            prestitoDAO.restituisci(p.getId());
            System.out.println("Prestiti attivi post-restituzione: " + prestitoDAO.prestitiAttiviPerTessera("T321").size());

            //rimozione per ISBN
            elementoDAO.removeByIsbn("987654332101234"); //rimuove la rivista

        } finally {
            em.close();
            JPAUtil.close();
        }
    }
}
