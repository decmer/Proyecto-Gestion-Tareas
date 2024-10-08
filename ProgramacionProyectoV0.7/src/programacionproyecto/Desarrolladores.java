/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionproyecto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author jose
 */
class Desarrolladores extends Usuarios implements Trabajadores{
    
    private PriorityQueue tareasAssignadas = new PriorityQueue();

    public Desarrolladores(String nombre, String contraseña) {
        super(nombre, contraseña);
    }

    @Override
    public boolean finalizarTarea(Tareas t) {
        if (t!=null && t.getEstado().equals(Estados.EN_REALIZACION)) {
            t.setEstado(Estados.TERMINADO);
            BaseDatos.getBBDD().store(t);
            BaseDatos.getBBDD().commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean realizarTarea(Tareas t) {
        if (t!= null && t.getEstado().equals(Estados.EN_ESPERA)) {
            t.setEstado(Estados.EN_REALIZACION);
            BaseDatos.getBBDD().store(t);
            BaseDatos.getBBDD().commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean crearInforme(Proyectos p) throws IOException{
        File d = new File("Informes_Desarrolladores");
        if(!d.exists())
            d.mkdir();
        File f = new File(d, this.getNombre()+"_"+p.getNombre()+"-id"+p.getId()+".txt");
        BufferedWriter bW = new BufferedWriter(new FileWriter(f));
        StringBuilder format = new StringBuilder();
        format.append("nombre del proyecto: " + p.getNombre() + "\n\nTareas Pendientes:\n");
        for (Tareas t : tareasAssignadas) {
            if (t.getPerteneProyecto().getId() == p.getId()) {
                format.append("\n\t"+t.toString()+"\n");
            }
        }
        bW.write(format.toString());
        bW.close();
        return true;
    }
    
    public boolean eliminarTarea(Tareas t){
        if(tareasAssignadas.contains(t)){
            tareasAssignadas.removeElement(t);
            BaseDatos.getBBDD().store(t);
            BaseDatos.getBBDD().commit();
            return true;
        }
        return false;
    }

    public PriorityQueue getTareasAssignadas() {
        return tareasAssignadas;
    }
    

    @Override
    public String toString() {
        return "Desarrolladores{"+ super.toString() + "tareasAssignadas=" + tareasAssignadas + '}';
    }
}
