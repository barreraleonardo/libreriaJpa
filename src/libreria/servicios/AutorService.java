
package libreria.servicios;

import java.util.UUID;
import libreria.entidades.Autor;
import persistencia.AutorDAO;


public class AutorService {
    
    private final AutorDAO dao;
    
    public AutorService(){
        this.dao = new AutorDAO();
    }
    
    public Autor buscarPorNombre(String nombre) throws Exception{
        try {
            if (nombre == null) {
                throw new Exception("Debe indicar un nombre de autor");
            }
            Autor autor = dao.buscarPorNombre(nombre);
            return autor;
        } catch (Exception e) {
            throw e;
        }
    }
    
    
      public void crear(String nombre) throws Exception {
        //Verificar existencia del autor
        try {
            Autor aux = dao.buscarPorNombre(nombre);
            if (aux == null) {
                 Autor autor = new Autor();
                autor.setId((UUID.randomUUID().toString()));
                autor.setNombre(nombre);
                dao.crear(autor);
                              
            }else{
                System.out.println("El autor ya existe en la base de datos");
            }

        } catch (Exception e) {
            throw e;
        }
    }
      
}
