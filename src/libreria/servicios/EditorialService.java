
package libreria.servicios;

import java.util.UUID;
import libreria.entidades.Editorial;
import persistencia.EditorialDAO;

public class EditorialService {
    private final EditorialDAO dao;
    
    public EditorialService(){
        this.dao = new EditorialDAO();
    }
    
    public Editorial crear(String e) throws Exception{
        Editorial editorial = new Editorial();
        editorial.setId((UUID.randomUUID().toString()));
        editorial.setNombre(e);
        dao.crear(editorial);
        return editorial;
    }
    
    public Editorial buscarPorNombre(String nombre) throws Exception{
        try {
            if (nombre == null) {
                throw new Exception("Debe indicar un nombre de editorial");
            }
            Editorial editorial = dao.buscarPorNombre(nombre);
            return editorial;
        } catch (Exception e) {
            throw e;
            
        }
        
    }
}
