
package libreria.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;


@Entity
public class Prestamo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaPrestamo;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaDevolucion;
    @OneToOne
    private Libro libro;
    @OneToOne
    private Cliente cliente;


    @Override
    public String toString() {
        return "libreria.entidades.Prestamo[ id=" + id + " ]";
    }
    
}
