
package libreria.servicios;

import java.util.List;
import java.util.Scanner;
import libreria.entidades.Autor;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;
import persistencia.LibroDAO;

public class LibroService {
    Scanner sc = new Scanner(System.in).useDelimiter("\n");
    AutorService aS = new AutorService();
    EditorialService eS = new EditorialService();
    private final LibroDAO dao;
    public LibroService(){
        this.dao = new LibroDAO();
    }
    public void crear() throws Exception{
        try {
            Libro libro =  new Libro();
            System.out.println("Ingrese título del libro");
            libro.setTitulo(sc.next());
            System.out.println("Ingrese año de publicación");
            libro.setAnio(sc.nextInt());
            System.out.println("Ingrese autor");
            String nombre = sc.next();
            Autor autor = aS.buscarPorNombre(nombre);
            if (autor != null) {
                libro.setAutor(autor);
            }else{
                aS.crear(nombre);
               libro.setAutor(aS.buscarPorNombre(nombre)); 
            }
            System.out.println("Ingrese cantidad de ejemplares");
            libro.setEjemplares(sc.nextInt());
            libro.setEjemplaresRestantes(libro.getEjemplares() - libro.getEjemplaresPrestados());
            System.out.println("Ingrese editorial");
            String nombreEditorial = sc.next();
            Editorial editorial = eS.buscarPorNombre(nombreEditorial);
            if (editorial != null) {
                libro.setEditorial(editorial);
            }else{
                eS.crear(nombreEditorial);
                libro.setEditorial(eS.buscarPorNombre(nombreEditorial));
            }
            dao.crear(libro);
 
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Libro buscarPorIsbn(long isbn){
        try {
            Libro libro = dao.buscarPorIsnb(isbn);
        System.out.println("Nombre: " + libro.getTitulo());
        System.out.println("Autor: "+ libro.getAutor().getNombre());
        System.out.println("Año de publicación: " + libro.getAnio());
        System.out.println("Cantidad de ejemplares: " + libro.getEjemplares());
        System.out.println("Cantidad de ejemplares prestados: " + libro.getEjemplaresPrestados());
        System.out.println("Cantidad de ejemplares disponibles: " + libro.getEjemplaresRestantes());
        return libro;
        } catch (Exception e) {
            System.out.println("Libro no encontrado");
            return null;
        }
        
    }
    
    public Libro buscarPorTitulo(String titulo) throws Exception {
        try {
            if (titulo == null) {
                throw new Exception("Debe indicar un título");
            }
            Libro libro = dao.buscarPorTitulo(titulo);
            return libro;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void buscarPorAutor(String autor) throws Exception{
        
        try {
            if (autor == null) {
                throw new Exception("Debe indicar un nombre de autor");
            }
            List<Libro> libros = dao.buscarPorAutor(autor);
            System.out.println("");
            System.out.println("     " + libros.size() + " Libros encontrados del autor: " + autor);
            System.out.println("");
            System.out.println("------------------------------------------------------------------------");
            for (Libro libro : libros) {
            System.out.println("Nombre: " + libro.getTitulo());
            System.out.println("Autor: " + libro.getAutor().getNombre());
            System.out.println("Año de publicación: " + libro.getAnio());
            System.out.println("Cantidad de ejemplares: " + libro.getEjemplares());
            System.out.println("Cantidad de ejemplares prestados: " + libro.getEjemplaresPrestados());
            System.out.println("Cantidad de ejemplares disponibles: " + libro.getEjemplaresRestantes());
            
            System.out.println("------------------------------------------------------------------------");
            }
           
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void buscarPorEditorial(String editorial) throws Exception{
        try {
            if (editorial == null) {
                System.out.println("Debe indicar una editorial");
            }
            List<Libro> libros = dao.buscarPorEditorial(editorial);
            System.out.println("");
            System.out.println("     " + libros.size() + " Libros encontrados de editorial: " + editorial);
            System.out.println("");
            System.out.println("------------------------------------------------------------------------");
            for (Libro libro : libros) {
            System.out.println("Nombre: " + libro.getTitulo());
            System.out.println("Autor: " + libro.getAutor().getNombre());
            System.out.println("Año de publicación: " + libro.getAnio());
            System.out.println("Cantidad de ejemplares: " + libro.getEjemplares());
            System.out.println("Cantidad de ejemplares prestados: " + libro.getEjemplaresPrestados());
            System.out.println("Cantidad de ejemplares disponibles: " + libro.getEjemplaresRestantes());
            
            System.out.println("------------------------------------------------------------------------");
            }
        } catch (Exception e) {
                throw e;
                }
        
    }
    
    public void modificarLibro(String nombre) throws Exception{
        try {
            if (nombre == null) {
                throw new Exception("Debe indicar un titulo");
            }
            Libro libro = buscarPorTitulo(nombre);
            if (libro != null) {
                int opc;
                do {
                    System.out.println("----------¿Que desea editar?----------"
                        + "\n1-Todo"
                        + "\n2-Titulo"
                        + "\n3-Año"
                        + "\n4-Cantidad de ejemplares"
                        + "\n5-Cantidad de ejemplares prestados"
                        + "\n6-Cantidad de ejemplares disponibles"
                        + "\n7-Autor"
                        + "\n8-Editorial"
                        + "\n9-Alta"
                        + "\n10-Salir");
                opc=sc.nextInt();
                switch(opc){
                    case 1:
                        System.out.println("----------Editar todo----------");
                        modificarTodo(libro);
                        break;
                    case 2:
                        System.out.println("----------Edición de título----------");
                        modificarTitulo(libro);
                        break;
                    case 3:
                        System.out.println("----------Edición de año----------");
                        modificarAnio(libro);
                        break;
                    case 4:
                        System.out.println("----------Edición cantidad ejemplares----------");
                        modificarEjemplares(libro);
                        break;
                    case 5:
                        System.out.println("----------Edición cantidad prestados----------");
                        modificarPrestados(libro);
                        modificarDisponibles(libro);
                        break;
                    case 6:
                        System.out.println("----------Las cantidades disponibles se calculan solas----------"); 
                        break;
                    case 7:
                        System.out.println("----------Edición de autor----------");
                        modificarAutor(libro);
                        break;
                    case 8:
                        System.out.println("----------Edición de editorial----------");
                        modificarEditorial(libro);
                        break;
                    case 9:
                        System.out.println("----------Edición de alta----------");
                        modificarAlta(libro);
                        break;
                    case 10:
                        System.out.println("----------Fín de edición----------");
                        break;
                    default:
                        System.out.println("¿Que rayos paso?");
                        break;
                        
                }
                } while (opc!=10);
                
            }
            
        } catch (Exception e) {
            throw e;
        }
    }
    public void modificarTodo(Libro libro) throws Exception{
        try {
        modificarTitulo(libro);
        modificarAnio(libro);
        modificarEjemplares(libro);
        modificarPrestados(libro);
        modificarDisponibles(libro);
            modificarAutor(libro);
            modificarEditorial(libro);
        modificarAlta(libro);
        } catch (Exception e) {
            throw e;
        }
        
        
    }
    public void modificarTitulo(Libro libro) throws Exception{
        try {
            System.out.println("Ingrese el título nuevo:");
        libro.setTitulo(sc.next());
        dao.modificar(libro);
        } catch (Exception e) {
            throw e;
        }
    }
    public void modificarAnio(Libro libro) throws Exception{
        try {
            System.out.println("Ingrese el año de publicación nuevo:");
        libro.setAnio(sc.nextInt());
        dao.modificar(libro);
        } catch (Exception e) {
            throw e;
        }
    }
    public void modificarEjemplares(Libro libro) throws Exception{
        try {
            System.out.println("Ingrese la cantidad de ejemplares nueva:");
        libro.setEjemplares(sc.nextInt());
        dao.modificar(libro);
        } catch (Exception e) {
            throw e;
        }
    } 
    public void modificarPrestados(Libro libro) throws Exception{
        try {
            System.out.println("Ingrese la cantidad de ejemplares prestados:");
        libro.setEjemplaresPrestados(sc.nextInt());
        dao.modificar(libro);
        } catch (Exception e) {
            throw e;
        }
    }
    public void modificarDisponibles(Libro libro) throws Exception{
        try {
        libro.setEjemplaresRestantes(libro.getEjemplares()-libro.getEjemplaresPrestados());
        dao.modificar(libro);
        } catch (Exception e) {
            throw e;
        }
    }
    public void modificarAlta(Libro libro) throws Exception{
        try {
            System.out.println("Modifique el alta/baja:");
        libro.setAlta(sc.nextBoolean());
        dao.modificar(libro);
        } catch (Exception e) {
            throw e;
        }
    }
    public void modificarAutor(Libro libro) throws Exception{
        try {
            System.out.println("Ingrese el nuevo autor:");
            String nombre = sc.next();
            Autor autor = aS.buscarPorNombre(nombre);
            if (autor!=null) {
                libro.setAutor(autor);
                dao.modificar(libro);
            }else{
               aS.crear(nombre);
               libro.setAutor(aS.buscarPorNombre(nombre));
               dao.modificar(libro);
            }
        
        } catch (Exception e) {
            throw e;
        }
    }
    public void modificarEditorial(Libro libro) throws Exception{
        try {
            System.out.println("Ingrese la nueva editorial:");
            String nombre = sc.next();
            Editorial editorial = eS.buscarPorNombre(nombre);
            if (editorial!=null) {
                libro.setEditorial(editorial);
                dao.modificar(libro);
            }else{
                eS.crear(nombre);
                libro.setEditorial(eS.buscarPorNombre(nombre));
                dao.modificar(libro);
            }
        
        } catch (Exception e) {
            throw e;
        }
    }
    
}
