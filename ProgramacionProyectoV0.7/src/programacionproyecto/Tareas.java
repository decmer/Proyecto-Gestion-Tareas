/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionproyecto;

import com.db4o.query.Query;
import java.util.Date;

/**
 *
 * @author jose
 */
public class Tareas implements Comparable<Tareas>{
    
    private int id;
    private String nombre;
    private Integer prioridad;
    private String descripcion;
    private String estado;
    private Proyectos perteneProyecto;
    private Desarrolladores desarrollador = null;
    private Date fechaLimite;

    @Override
    public int compareTo(Tareas o) {
        if (prioridad == o.prioridad) {
            return o.fechaLimite.compareTo(fechaLimite);
        }
        return o.prioridad.compareTo(prioridad);
    }

    public Tareas(String nombre, int prioridad, Proyectos perteneProyecto, String descripcion, Estados estado, Date fechaLimite) {
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.descripcion = descripcion;
        this.estado = estado.getValor();
        this.fechaLimite = fechaLimite;
        this.perteneProyecto = perteneProyecto;
        id = perteneProyecto.getidSigTare();
        perteneProyecto.ingresarTarea(this);
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
    
    public Desarrolladores getDesarrollador() {
        return desarrollador;
    }

    public Proyectos getPerteneProyecto() {
        return perteneProyecto;
    }

    public Estados getEstado() {
        return Estados.valueOf(estado);
    }

    public void setEstado(Estados e) {
        this.estado = e.getValor();
    }

    public Integer getPrioridad() {
        return prioridad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
        BaseDatos.getBBDD().store(this);
        BaseDatos.getBBDD().commit();
        if (getDesarrollador() != null) {
            getDesarrollador().getTareasAssignadas().reorder();
        }
        
        if (Estados.valueOf(estado).equals(Estados.TERMINADO)) {
            getPerteneProyecto().getTareasRealizadas().reorder();
        }else
            getPerteneProyecto().getTareasPendientes().reorder();
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPerteneProyecto(Proyectos perteneProyecto) {
        if (perteneProyecto == this.perteneProyecto)
            return;
        if (perteneProyecto != null)
            this.perteneProyecto.eliminarTarea(this);
        perteneProyecto.ingresarTarea(this);
        this.perteneProyecto = perteneProyecto;
        id = perteneProyecto.getidSigTare();
    }

    public void setDesarrollador(Desarrolladores desarrollador) {
        if (desarrollador == this.desarrollador)
            return;
        if (this.desarrollador != null)
            this.desarrollador.getTareasAssignadas().removeElement(this);
        if (desarrollador != null) {
            desarrollador.getTareasAssignadas().add(this);
        }
        this.desarrollador = desarrollador;
        BaseDatos.getBBDD().store(this.desarrollador);
        BaseDatos.getBBDD().commit();
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }
    
    @Override
    public String toString() {
        return "Tareas{" + "id=" + id + ", nombre=" + nombre + ", prioridad=" + prioridad + "\n\tID_proyecto=" + perteneProyecto.getId() +", descripcion=" + descripcion + ", estado=" + estado + ", fechaLimite=" + fechaLimite + '}';
    }

//    @Override
//    public String toString() {
//        return "Tareas{" + "id=" + id + ", prioridad=" + prioridad + '}';
//    }
    
    
}
