/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionproyecto;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.util.Date;

/**
 *
 * @author jose
 */
public class AnadeObjetos {
    public static void execute() throws ExcepcionPer{
        ObjectContainer bd = BaseDatos.abreBBDD();
        Usuarios.asignaIDAux();
        
        Administradores ad1 = new Administradores("joseA", "1214");
        Administradores ad2 = new Administradores("jorgeA", "1214");
        Administradores ad3 = new Administradores("MariaA", "1214");
        Administradores ad4 = new Administradores("PabloA", "1214");
        Administradores ad5 = new Administradores("LukasA", "1214");

        Desarrolladores de1 = new Desarrolladores("joseD", "1214");
        Desarrolladores de2 = new Desarrolladores("jorgeD", "1214");
        Desarrolladores de3 = new Desarrolladores("MariaD", "1214");
        Desarrolladores de4 = new Desarrolladores("PabloD", "1214");
        Desarrolladores de5 = new Desarrolladores("LukasD", "1214");
        
        Managers mn1 = new Managers("joseM", "1214");
        Managers mn2 = new Managers("jorgeM", "1214");
        Managers mn3 = new Managers("MariaM", "1214");
        Managers mn4 = new Managers("PabloM", "1214");
        Managers mn5 = new Managers("LukasM", "1214");
        
        Proyectos p1 = new Proyectos("p1", mn1, "importante", new Date(2024,12,5));
        Proyectos p2 = new Proyectos("p2", mn1, "importante", new Date(2024,11,27));
        Proyectos p3 = new Proyectos("p3", mn2, "importante", new Date(2024,8,2));
        Proyectos p4 = new Proyectos("p4", mn1, "importante", new Date(2024,7,10));
        Proyectos p5 = new Proyectos("p5", mn2, "importante", new Date(2024,6,30));
        
        Tareas t1 = new Tareas("tarea 1", 3, p1, "isdg", Estados.EN_ESPERA, new Date(2024,11,1));
        Tareas t1_1 = new Tareas("tarea 1-1", 1, p1, "isdg", Estados.EN_ESPERA, new Date(2024,0,1));
        Tareas t2 = new Tareas("tarea 2", 2, p2, "isdg", Estados.EN_ESPERA, new Date(2024,10,27));
        Tareas t3 = new Tareas("tarea 3", 3, p3,"isdg", Estados.EN_ESPERA, new Date(2024,8,2));
        Tareas t4 = new Tareas("tarea 4", 1, p4, "isdg", Estados.EN_ESPERA, new Date(2024,7,9));
        Tareas t5 = new Tareas("tarea 5", 2, p5, "isdg", Estados.TERMINADO, new Date(2024,6,20));
              

        mn1.asignarTarea(t1, de1);
        mn1.asignarTarea(t1_1, de1);
        mn1.asignarTarea(t2, de1);
        mn1.asignarTarea(t3, de1);
        
        bd.store(ad1);
        bd.store(ad2);
        bd.store(ad3);
        bd.store(ad4);
        bd.store(ad5);
        
        bd.store(de1);
        bd.store(de2);
        bd.store(de3);
        bd.store(de4);
        bd.store(de5);

        bd.store(mn1);
        bd.store(mn2);
        bd.store(mn3);
        bd.store(mn4);
        bd.store(mn5);

        bd.store(p1);
        bd.store(p2);
        bd.store(p3);
        bd.store(p4);
        bd.store(p5);

        bd.store(t1);
        bd.store(t1_1);
        bd.store(t2);
        bd.store(t3);
        bd.store(t4);
        bd.store(t5);

        bd.commit();
    }
    
    public static void main(String[] args) throws ExcepcionPer {
        execute();
        ObjectSet o = BaseDatos.getBBDD().query().execute();
        while (o.hasNext())
            System.out.println(o.next());
        BaseDatos.exitBBDD();
    }
}
