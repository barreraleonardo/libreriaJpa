
package persistencia;

import java.util.List;
import libreria.entidades.Cliente;


public class ClienteDAO extends DAO {
    
    
    public void crear(Cliente cliente) throws Exception{
        try {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            try {
                em.getTransaction().rollback();
            } catch (Exception ex) {
                throw new Exception("Error al hacer rollback");
            }
            throw new Exception("Error al persistir al cliente");
        }
    }
    
    
    public void modificar(Cliente cliente) throws Exception{
        try {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Error al modificar cliente");
        }
    }
    
    
    public void eliminar(Cliente cliente) throws Exception{
        try {
            em.getTransaction().begin();
            em.remove(cliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Error al eliminar cliente");
        }
    }
    
    
    public Cliente buscarPorId(Integer id){
        return em.find(Cliente.class, id);
    }
    
    
    public List<Cliente> buscarPorNombre(String nombre){
        try {
            List<Cliente>clientes = em.createQuery("SELECT d "
                    + " FROM Cliente d"
                    + " WHERE d.nombre LIKE :nombre").
                    setParameter("nombre", nombre).
                    getResultList();
            return clientes;
        } catch (Exception e) {
            return null;
        }
    }
}
