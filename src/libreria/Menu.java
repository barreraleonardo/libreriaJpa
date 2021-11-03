
package libreria;

import java.util.Scanner;
import libreria.entidades.Autor;
import libreria.entidades.Libro;
import libreria.servicios.AutorService;
import libreria.servicios.EditorialService;
import libreria.servicios.LibroService;


public class Menu {
AutorService aS = new AutorService();
    EditorialService eS = new EditorialService();
LibroService lS = new LibroService();
Scanner sc = new Scanner(System.in).useDelimiter("\n");
//1) Búsqueda de un Autor por nombre.
//2) Búsqueda de un libro por ISBN.
//3) Búsqueda de un libro por Título.
//4) Búsqueda de un libro/s por nombre de Autor.
//5) Búsqueda de un libro/s por nombre de Editorial.
//6) Agregar las siguientes validaciones a todas las funcionalidades de la aplicación:
//7) Crear los métodos para dar de alta/bajo o editar dichas entidades.
//• Validar campos obligatorios.
//• No ingresar datos duplicados.
    public void menu() throws Exception{
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        int opc;
        boolean bandera = false;
        do {
            System.out.println("-----Menu-----");
        System.out.println("1-Búsqueda de un Autor por nombre."
                + "\n2-Búsqueda de un libro por ISBN."
                + "\n3-Búsqueda de un libro por Título."
                + "\n4-Búsqueda de un libro/s por nombre de Autor."
                + "\n5-Búsqueda de un libro/s por nombre de Editorial."
                + "\n6-Ingresar libro"
                + "\n7-Ingresar autor"
                + "\n8-Ingresar editorial"
                + "\n9-Editar libro");
        opc = sc.nextInt();
        switch(opc){
            case 1:
              Autor a = aS.buscarPorNombre(pedirNombre());
                break;
            case 2:
                Libro l = lS.buscarPorIsbn(pedirIsnb());
                break;
            case 3:
                Libro l1 = lS.buscarPorTitulo(pedirTitulo());
                break;
            case 4:
                System.out.println("----------Buscar libro por autor----------");
                lS.buscarPorAutor(pedirNombre());
                break;
            case 5:
                System.out.println("----------Buscar libro por editorial----------");
                lS.buscarPorEditorial(pedirEditorial());
                break;
            case 6:
                System.out.println("----------Ingresar libro----------");
                lS.crear();
                break;
            case 7:
                System.out.println("----------Ingresar autor----------");
                aS.crear(pedirNombre());
                break;
            case 8:
                System.out.println("----------Ingresar editorial----------");
                eS.crear(pedirEditorial());
                break;
            case 9:
                System.out.println("----------Edicion de libro----------");
                lS.modificarLibro(pedirTitulo());
                break;
        }
        
        } while (bandera == false);
      
    }
    
    
    
      public String pedirNombre(){
         System.out.println("Ingrese nombre del autor:");
         String nombre = sc.next();
         return nombre;
    }
      
      public String pedirTitulo(){
         System.out.println("Ingrese el título del libro:");
         String nombre = sc.next();
         return nombre;
    }
      
      public long pedirIsnb(){
          System.out.println("Ingrese ISNB");
          long isnb = sc.nextLong();
          return isnb;
      }
      
      public String pedirEditorial(){
          System.out.println("Ingrese el nombre de la editorial:");
          String nombre = sc.next();
          return nombre;
      }
  
    
    
}
