
package persistencia;

import java.util.List;
import libreria.entidades.Libro;

public class LibroDAO extends DAO {
    
    public void crear(Libro libro) throws Exception{
        try {
            em.getTransaction().begin();
            em.persist(libro);
            em.getTransaction().commit();
        } catch (Exception e) {
            try {
                em.getTransaction().rollback();
            } catch (Exception ex) {
                throw new Exception("Error haciendo un rollback");
            }
            throw new Exception("Error al persitir un libro");
        }
    }
    
    public void modificar(Libro libro) throws Exception{
        try {
            em.getTransaction().begin();
            em.merge(libro);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Error al modificar el libro");
        }
    }
    
    public void eliminar(Libro libro) throws Exception{
        try {
            em.getTransaction().begin();
            em.remove(libro);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Error al eliminar libro");
        }
    }
    
    public Libro buscarPorIsnb(Long isnb){        
            Libro libro = em.find(Libro.class, isnb);
        return libro;
    }
    
    public Libro buscarPorTitulo(String titulo) throws Exception {
        try {
            Libro libro = (Libro) em.createQuery("SELECT d "
                + " FROM Libro d"
                + " WHERE d.titulo LIKE :titulo").
                setParameter("titulo", titulo).
                getSingleResult();
        return libro;
        } catch (Exception e) {
            System.out.println("No se encontro el libro");
            return null;
        }
    }
    
    public List<Libro> buscarPorAutor(String autor){
        try {
            List<Libro> libros = em.createQuery("SELECT d "
                    + " FROM Libro d"
                    + " WHERE d.autor.nombre LIKE :autor").
                    setParameter("autor", autor).
                    getResultList();
            return libros;        
        } catch (Exception e) {
            System.out.println("No se encontro el autor");
            return null;
        }
    }
    
    public List<Libro> buscarPorEditorial(String editorial){
        try {
            List<Libro> libros = em.createQuery("SELECT d "
                    + " FROM Libro d"
                    + " WHERE d.editorial.nombre LIKE :editorial").
                    setParameter("editorial", editorial).
                    getResultList();
            return libros;
        } catch (Exception e) {
            System.out.println("No se encontro la editorial");
            return null;
        }
    }
    
}
