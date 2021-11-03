
package persistencia;

import libreria.entidades.Autor;
import libreria.entidades.Editorial;


public class EditorialDAO extends DAO {
           public void crear(Editorial editorial) throws Exception{
        try {
            em.getTransaction().begin();
            em.persist(editorial);
            em.getTransaction().commit();
        } catch (Exception e) {
            try {
                em.getTransaction().rollback();
            } catch (Exception ex) {
                throw new Exception("Error haciendo un rollback");
            }
            throw new Exception("Error al persitir un editorial");
        }
    }
   
    public void modificar(Editorial editorial) throws Exception{
        try {
            em.getTransaction().begin();
            em.merge(editorial);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Error al modificar la editorial");
        }
    }
    
    public void eliminar(Editorial editorial) throws Exception{
        try {
            em.getTransaction().begin();
            em.remove(editorial);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Error al eliminar editorial");
        }
    }
    
    public Editorial buscarPorId(Integer id){
        return em.find(Editorial.class, id);
    } 
    
     
    public Editorial buscarPorNombre(String nombre) throws Exception {
        try {
            Editorial editorial = (Editorial) em.createQuery("SELECT d "
                + " FROM Editorial d"
                + " WHERE d.nombre LIKE :nombre").
                setParameter("nombre", nombre).
                getSingleResult();
        return editorial;
        } catch (Exception e) {
            return null;
        }
       
    }

}
