
package libreria;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import libreria.entidades.Autor;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;
import libreria.servicios.AutorService;
import libreria.servicios.EditorialService;
import libreria.servicios.LibroService;
import persistencia.AutorDAO;
import persistencia.EditorialDAO;
import persistencia.LibroDAO;


public class Libreria {

    
   
    public static void main(String[] args) throws Exception {
        EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();
        Menu m = new Menu();
        LibroService lS = new LibroService();
        AutorService aS = new AutorService();
        EditorialService eS = new EditorialService();
        AutorDAO autorDAO = new AutorDAO();
        EditorialDAO editorialDAO = new EditorialDAO();
        LibroDAO libroDAO = new LibroDAO();
        
        //Creacion de autores
        Autor a1 = new Autor();
        Autor a2 = new Autor();
        Autor a3 = new Autor();
        Autor a4 = new Autor();
        Autor a5 = new Autor();
        Autor a6 = new Autor();

        a1.setNombre("Antón Chéjov");
        autorDAO.crear(a1);
        a2.setNombre("Joseph Conrad");
        autorDAO.crear(a2);
        a3.setNombre("Charles Dickens");
        autorDAO.crear(a3);
        a4.setNombre("Denis Didero");
        autorDAO.crear(a4);
        a5.setNombre("Alfred Doblin");
        autorDAO.crear(a5);
        a6.setNombre("Fiodor Dostoievski");
        autorDAO.crear(a6);
        
        //Creacion de editoriales
        Editorial e1 = new Editorial();
        Editorial e2 = new Editorial();
        Editorial e3 = new Editorial();
        e1.setNombre("Editorial 1");
        editorialDAO.crear(e1);
        e2.setNombre("Editorial 2");
        editorialDAO.crear(e2);
        e3.setNombre("Editorial 3");
        editorialDAO.crear(e3);
        
        //Creacion de libros
        Libro l1 = new Libro("Relatos cortos", 1886, 3, 2, true, a1, e3);
        libroDAO.crear(l1);
        Libro l2 = new Libro("Nostromo", 1904, 1, 1, true, a2, e2);
        libroDAO.crear(l2);
        Libro l3 = new Libro("Grandes Esperanzas", 1861, 1, 1, true, a3, e2);
        libroDAO.crear(l3);
        Libro l4 = new Libro("Jacques el fatalista", 1904, 1, 1, true, a4, e2);
        libroDAO.crear(l4);
        Libro l5 = new Libro("Berlin Alexanderplatz", 1929, 2, 2, true, a5, e3);
        libroDAO.crear(l5);
        Libro l6 = new Libro("Crimen y castigo", 1866, 1, 0, true, a6, e1);
        libroDAO.crear(l6);
        Libro l7 = new Libro("El idiota", 1869, 1, 1, true, a6, e1);
        libroDAO.crear(l7);
        Libro l8 = new Libro("Los endemoniados", 1872, 1, 1, true, a6, e2);
        libroDAO.crear(l8);
        Libro l9 = new Libro("Los hermanos Karamazov", 1880, 1, 1, true, a6, e2);
        libroDAO.crear(l9);
        
        //Menu
        m.menu();
        
        
    }
    
}
