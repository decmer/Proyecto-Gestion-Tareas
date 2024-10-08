/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionproyecto;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author jose
 */
public class Managers extends Usuarios implements Trabajadores{

    public Managers(String nombre, String contraseña) {
        super(nombre, contraseña);
    }

    @Override
    public boolean finalizarTarea(Tareas t) {
        if (t!=null || t.getEstado().equals(Estados.TERMINADO)) {
            t.setEstado(Estados.TERMINADO);
            return true;
        }
        return false;
    }

    @Override
    public boolean realizarTarea(Tareas t) {
        if (t!= null || t.getEstado().equals(Estados.EN_REALIZACION) || t.getEstado().equals(Estados.TERMINADO)) {
            t.setEstado(Estados.EN_REALIZACION);
            return true;
        }
        return false;
    }

    @Override
    public boolean crearInforme(Proyectos p) throws IOException{
        File d1 = new File("Informes_Managers");
        if(!d1.exists())
            d1.mkdir();
        File d2 = new File(d1,this.getNombre()+"-id"+this.getId());
        if(!d2.exists())
            d2.mkdir();
        File f = new File(d2, p.getNombre()+"-id"+p.getId()+".txt");
        BufferedWriter bW = new BufferedWriter(new FileWriter(f));
        StringBuilder format = new StringBuilder();
        format.append("nombre del proyecto: " + p.getNombre() + "\n\nTareas Pendientes:\n");
        for (Tareas t : p.getTareasPendientes()) {
            format.append("\n\t"+t.toString()+"\n");
        }
        
        format.append("\n\n\nTareas Realizadas:\n");
        for (Tareas t : p.getTareasRealizadas()) {
            format.append("\n\t"+t.toString()+"\n");
        }
        
        bW.write(format.toString());
        bW.close();
        return true;
    }
    
    public void crearProyecto(String nombre, Managers manager, String desccripcion, Date fL){
        BaseDatos.getBBDD().store(new Proyectos(nombre, manager, desccripcion, fL));
        BaseDatos.getBBDD().commit();
    }
    
    public boolean modificarProyecto(Proyectos p, String nombre, Managers manager, String desccripcion, Date fI, Date fL) throws ExcepcionPer{
        if (p.isCerrado()) {
            throw new ExcepcionPer("Proyecto Cerrado");
        }
        if (p != null && nombre != null && manager != null && desccripcion != null && fI != null && fL != null) {
            p.setNombre(nombre);
            p.setManager(manager);
            p.setDesccripcion(desccripcion);
            p.setFechas(new Date[]{fI, fL});
            BaseDatos.getBBDD().store(p);
            BaseDatos.getBBDD().commit();
            return true;
        }
        return false;
    }
    
    public void borrarProyecto(Proyectos p){
        ObjectContainer db = BaseDatos.getBBDD();
        for (Tareas t : p.getTareasPendientes().joinLists(p.getTareasRealizadas())) {
            if (t.getDesarrollador() != null)
                t.getDesarrollador().getTareasAssignadas().removeElement(t);
            db.delete(t);
        }
        db.delete(p);
        db.commit();
    }
    
    public void cerrarProyecto(Proyectos p){
        p.setCerrado(true);
        BaseDatos.getBBDD().store(p);
        BaseDatos.getBBDD().commit();
    }
    
    public void crearTarea(String nombre, int prioridad, Proyectos perteneProyecto, String descripcion, Estados estado, Date fechaLimite) throws ExcepcionPer{
        if (perteneProyecto.isCerrado()) {
            throw new ExcepcionPer("Proyecto Cerrado");
        }
        Tareas t = new Tareas(nombre, prioridad, perteneProyecto, descripcion, estado, fechaLimite);
        BaseDatos.getBBDD().store(t);
        BaseDatos.getBBDD().commit();
    }
    
    public void modificarTarea(Tareas t, String nombre, int prioridad, Proyectos perteneProyecto, String descripcion, Estados estado, Desarrolladores desarrollador, Date fechaLimite) throws ExcepcionPer{
        if (t.getPerteneProyecto().isCerrado()) {
            throw new ExcepcionPer("Proyecto Cerrado");
        }
        t.setNombre(nombre);
        t.setPrioridad(prioridad);
        t.setPerteneProyecto(perteneProyecto);
        t.setDescripcion(descripcion);
        t.setEstado(estado);
        t.setDesarrollador(desarrollador);
        
        t.setFechaLimite(fechaLimite);
        BaseDatos.getBBDD().store(t);
        BaseDatos.getBBDD().commit();
    }
    
    public void borrarTarea(Tareas t) throws ExcepcionPer{
        if (t.getPerteneProyecto().isCerrado()) {
            throw new ExcepcionPer("Proyecto Cerrado");
        }
        if (t.getDesarrollador() != null) {
            t.getDesarrollador().getTareasAssignadas().removeElement(t);
        }
        t.getPerteneProyecto().eliminarTarea(t);
        BaseDatos.getBBDD().delete(t);
        BaseDatos.getBBDD().commit();
    }
    
    public void asignarTarea(Tareas t, Desarrolladores d) throws ExcepcionPer{
        if (t.getPerteneProyecto().isCerrado()) {
            throw new ExcepcionPer("Proyecto Cerrado");
        }
            t.setDesarrollador(d);
            BaseDatos.getBBDD().store(t);
            BaseDatos.getBBDD().commit();
    }
}
