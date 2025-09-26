package ChristianBottini;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

//creato per avere una sola volta EntityManagerFactory e poi usare EntityManager quando serve
public class JPAUtil {
    private static EntityManagerFactory emf;

    public static synchronized EntityManagerFactory getEmf() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("BibliotecaPU");
        }
        return emf;
    }

    public static synchronized void close() {
        if (emf != null && emf.isOpen()) emf.close();
    }
}