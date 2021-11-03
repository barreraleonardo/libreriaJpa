
package persistencia;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;


public abstract class DAO {
    protected EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();
}
