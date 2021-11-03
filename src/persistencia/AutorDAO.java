
package persistencia;

import libreria.Menu;
import libreria.entidades.Autor;


public class AutorDAO extends DAO {
    
    public void crear(Autor autor) throws Exception{
        try {
            em.getTransaction().begin();
            em.persist(autor);
            em.getTransaction().commit();
        } catch (Exception e) {
            try {
                em.getTransaction().rollback();
            } catch (Exception ex) {
                throw new Exception("Error haciendo un rollback");
            }
            throw new Exception("Error al persitir un autor");
        }
    }
    
    public void modificar(Autor autor) throws Exception{
        try {
            em.getTransaction().begin();
            em.merge(autor);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Error al modificar el autor");
        }
    }
    
    public void eliminar(Autor autor) throws Exception{
        try {
            em.getTransaction().begin();
            em.remove(autor);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Error al eliminar autor");
        }
    }
    
    public Autor buscarPorId(Integer id){
        return em.find(Autor.class, id);
    }
    
    
    public Autor buscarPorNombre(String nombre) throws Exception {
        try {
            Autor autor = (Autor) em.createQuery("SELECT d "
                + " FROM Autor d"
                + " WHERE d.nombre LIKE :nombre").
                setParameter("nombre", nombre).
                getSingleResult();
        
        return autor;
        } catch (Exception e) {
            return null;
        }
       
    }

    
}
