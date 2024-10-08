/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionproyecto;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

/**
 *
 * @author jose
 */
public class Administradores extends Usuarios {
    
    public Administradores(String nombre, String contrasena) {
        super(nombre, contrasena);
    }
    
    public boolean creaUsuario(String nombre, String contrasena, String clase){
        switch (clase.toLowerCase()) {
            case "managers":
                BaseDatos.getBBDD().store(new Managers(nombre, contrasena));
                break;
            case "administradores":
                BaseDatos.getBBDD().store(new Administradores(nombre, contrasena));
                break;
            case "desarrolladores":
                BaseDatos.getBBDD().store(new Desarrolladores(nombre, contrasena));
                break;
            default:
                return false;
        }
        return true;
    }
    
    public boolean modificarUsuario(Usuarios usu, String nombre, String contrasena){
        if (usu.getId() == 1) {
            return false;
        }
        try{
            usu.cambiarValores(nombre, contrasena);
            BaseDatos.getBBDD().store(usu);
            BaseDatos.getBBDD().commit();
            return true;
        }catch(NullPointerException ex){
            return false;
        }
    }
    
    public boolean borrarUsu(Usuarios u) {
        BaseDatos.getBBDD();
        if (this.getId() == u.getId() || u.getId() == 1) {
            return false;
        }
        try {
            if (u instanceof Desarrolladores) {
                for (Tareas t : ((Desarrolladores) u).getTareasAssignadas()) {
                    t.setDesarrollador(null);
                }
            } else if (u instanceof Managers) {
                Query q = BaseDatos.getBBDD().query();
                q.constrain(Proyectos.class);
                ObjectSet oSs = q.execute();
                while (oSs.hasNext()) {
                    Proyectos p = (Proyectos)oSs.next();
                    if (p.getManager() == u) {
                        p.setManager(null);
                    }
                }
            }
            
            BaseDatos.getBBDD().delete(u);
            BaseDatos.getBBDD().commit();
            corrigeIDEliminacionUsuario();
            return true;
        } catch (Exception e) {
            BaseDatos.getBBDD().rollback();
            e.printStackTrace();
            return false;
        }
    }

}
