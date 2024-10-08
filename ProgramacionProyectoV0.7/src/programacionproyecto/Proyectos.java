package programacionproyecto;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import java.util.Arrays;
import java.util.Date;


public class Proyectos {
    private static int idAux = 0;
    private int id;
    private String nombre;
    private Managers manager;
    private String desccripcion;
    private PriorityQueue tareasPendientes = new PriorityQueue();
    private PriorityQueue tareasRealizadas = new PriorityQueue();
    private int idSigTare = 0;
    private boolean cerrado = false;
    private Date[] fechas = new Date[2];
    
    public static void asignaIDAux(){
        ObjectContainer tempDb = null;
        try {
            tempDb = BaseDatos.getBBDD();
            Query q = tempDb.query();
            q.constrain(Proyectos.class);
            q.descend("id").orderDescending();
            ObjectSet result = q.execute();
            if (result.hasNext())
                idAux = ((Proyectos)result.next()).getId();
        }catch(Exception ex){
            System.out.println("Error al inizilisar la base de datos Proyectos");
            ex.printStackTrace();
        }
    }

    public Proyectos(String nombre, Managers manager, String desccripcion, Date fL) {
        this.nombre = nombre;
        this.manager = manager;
        this.desccripcion = desccripcion;
        fechas[0] = new Date();
        fechas[1] = fL;
        id = ++idAux;
    }
    
    
    public int getidSigTare(){
        ++idSigTare;
        BaseDatos.getBBDD().store(this);
        BaseDatos.getBBDD().commit();
        return idSigTare;
    }

    public String getNombre(){
        return nombre;
    }

    public String getDesccripcion() {
        return desccripcion;
    }
    
    public Date getFechaInicio(){
        return fechas[0];
    }
    
    public Date getFechaLimite(){
        return fechas[1];
    }

    public boolean isCerrado() {
        return cerrado;
    }
    
    public void ingresarTarea(Tareas t){
        if (t.getEstado().equals(Estados.TERMINADO)){
            tareasRealizadas.add(t);
            BaseDatos.getBBDD().store(tareasRealizadas);
        }else{
            tareasPendientes.add(t);
            BaseDatos.getBBDD().store(tareasPendientes);
        }
        BaseDatos.getBBDD().store(this);
        BaseDatos.getBBDD().commit();
    }

    public Managers getManager() {
        return manager;
    }
    
    public void eliminarTarea(Tareas t){
        if(!tareasPendientes.removeElement(t))
            tareasRealizadas.removeElement(t);
        BaseDatos.getBBDD().store(tareasRealizadas);
        BaseDatos.getBBDD().store(tareasPendientes);
        BaseDatos.getBBDD().store(this);
        BaseDatos.getBBDD().commit();
    }

    public PriorityQueue getTareasRealizadas() {
        return tareasRealizadas;
    }
    
    public PriorityQueue getTareasPendientes() {
        return tareasPendientes;
    }
    
    public int getId() {
        return id;
    }

    void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCerrado(boolean cerrado) {
        this.cerrado = cerrado;
    }
    
    void setManager(Managers manager) {
        this.manager = manager;
    }

    void setDesccripcion(String desccripcion) {
        this.desccripcion = desccripcion;
    }

    void setFechas(Date[] fechas) {
        this.fechas = fechas;
    }
    
    @Override
    public String toString() {
        return "Proyectos{" + "id=" + id + ", nombre=" + nombre + ", manager=" + manager + ", desccripcion=" + desccripcion + ", fechas=" + fechas + ", ProyectPendientes=" + tareasPendientes + "sigID" + idSigTare + '}';
    }
}
