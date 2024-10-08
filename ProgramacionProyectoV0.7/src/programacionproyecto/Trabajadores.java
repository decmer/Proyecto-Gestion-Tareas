/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package programacionproyecto;

import java.io.IOException;

/**
 *
 * @author jose
 */
public interface Trabajadores {
    
    public boolean finalizarTarea(Tareas t);
    
    public boolean realizarTarea(Tareas t);
        
    public boolean crearInforme(Proyectos p) throws IOException;
}
