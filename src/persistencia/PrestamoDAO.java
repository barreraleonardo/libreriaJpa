
package persistencia;

import java.util.Date;
import java.util.List;
import libreria.entidades.Cliente;
import libreria.entidades.Libro;
import libreria.entidades.Prestamo;


public class PrestamoDAO extends DAO {
    
    public void crear(Prestamo prestamo) throws Exception{
        try {
            em.getTransaction().begin();
            em.persist(prestamo);
            em.getTransaction().commit();
        } catch (Exception e) {
            try {
                em.getTransaction().rollback();
            } catch (Exception ex) {
                throw new Exception("Error al hacer rollback");
            }
        }
        throw new Exception("Error al persistir al cliente");
    }
    
    
    public void modificar(Prestamo prestamo) throws Exception{
        try {
            em.getTransaction().begin();
            em.merge(prestamo);
            em.getTransaction().commit();
        } catch (Exception e) {
            
            throw new Exception("Error al modificar prestamo");
        }
    }
    
    
    public void eliminar(Prestamo prestamo) throws Exception{
        try {
            em.getTransaction().begin();
            em.remove(prestamo);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Error al eliminar prestamo");
        }
    }
    
    
    public Prestamo buscarPorId(Integer id) throws Exception{
        try {
            return em.find(Prestamo.class, id);
        } catch (Exception e) {
            throw new Exception("No se encontro el prestamo");
        }
    }
    
    public List<Prestamo> buscarPorFechaPrestamo(Date fecha) throws Exception{
        try {
            List<Prestamo> prestamos = em.createQuery("SELECT d "
                + " FROM Prestamo d"
                + " WHERE d.fechaPrestamo LIKE :fecha").
                setParameter("fecha", fecha).getResultList();
            return prestamos;
        } catch (Exception e) {
            throw new Exception("No se encontraron resultados");
        }
    }
    
    public List<Prestamo> buscarPorFechaDevolucion(Date fecha) throws Exception{
        try {
            List<Prestamo> prestamos = em.createQuery("SELECT d "
                + " FROM Prestamo d"
                + " WHERE d.fechaDevolucion LIKE :fecha").
                setParameter("fecha", fecha).getResultList();
            return prestamos;
        } catch (Exception e) {
            throw new Exception("No se encontraron resultados");
        }
    }
    
    public List<Prestamo> buscarPorLibro(Libro libro) throws Exception{
        try {
            List<Prestamo> prestamos = em.createQuery("SELECT d "
                + " FROM Prestamo d"
                + " WHERE d.libro LIKE :libro").
                setParameter("libro", libro).
                getResultList();
            return prestamos;
        } catch (Exception e) {
            throw new Exception("No se encontraron resultados");
        }
    }
    
    public List<Prestamo> buscarPorCliente(Cliente cliente) throws Exception{
        try {
            List<Prestamo> prestamos = em.createQuery("SELECT d "
                + " FROM Prestamo d"
                + " WHERE d.cliente LIKE :cliente").
                setParameter("cliente", cliente).getResultList();
            return prestamos;
        } catch (Exception e) {
            throw new Exception("No se encontraron resultados");
        }
    }
    
}
