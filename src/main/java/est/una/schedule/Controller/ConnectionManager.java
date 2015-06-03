package est.una.schedule.Controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by geykel on 02/06/2015.
 */
public class ConnectionManager {
    private static EntityManagerFactory emf = null;

    public ConnectionManager() {}

    private static synchronized void init() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("schedule_db");
        }
    }

    public static synchronized void close() {
        if (emf != null) {
            emf.close();
            emf = null;
        }
    }

    public static EntityManager getNewEntityManager() {
        init();
        return emf.createEntityManager();
    }
}