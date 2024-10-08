package programacionproyecto;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

public abstract class Usuarios {
    private int id;
    private static Integer idAux = 0;
    private String nombre;
    private String contrasena;
    
    public static void asignaIDAux(){
        ObjectContainer tempDb = null;
        try {
            tempDb = BaseDatos.getBBDD();
            Query q = tempDb.query();
            q.constrain(Usuarios.class);
            q.descend("id").orderDescending();
            ObjectSet result = q.execute();
            if (result.hasNext())
                idAux = ((Usuarios)result.next()).id;
        }catch(Exception ex){
            System.out.println("Error al inizilisar la base de datos Usuarios");
            ex.printStackTrace();
        }
        finally {
            if (tempDb != null) {
                tempDb = null;
            }
        }
    }

    public Usuarios(String nombre, String contrasena) {
        this.id = ++idAux;
        this.nombre = nombre;
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public int getId() {
        return id;
    }
    
    public void corrigeIDEliminacionUsuario(){
        ObjectContainer tempDb = null;
        int aux = 0;
        try {
            tempDb = BaseDatos.getBBDD();
            Query q = tempDb.query();
            q.constrain(Usuarios.class);
            q.descend("id").orderAscending();
            ObjectSet result = q.execute();
            while(result.hasNext()){
                Usuarios ua = ((Usuarios)result.next());
                ua.id = ++aux;
                tempDb.store(ua);
            }
        }catch(Exception ex){
            System.out.println("Error al inizilisar la base de datos");
            ex.printStackTrace();
        }
        finally {
            idAux = aux;
            if (tempDb != null) {
                tempDb = null;
            }
        }
    }

    public void cambiarValores(String nombre, String Contrasena) {
        this.nombre = nombre;
        this.contrasena = Contrasena;
    }
    
    public static String tipoUsuario(Object o){
        if (o instanceof Administradores)
            return "Administrador";
        else if (o instanceof Desarrolladores)
            return "Desarrollador";
        else if (o instanceof Managers)
            return "Managers";
        return "NULL";
    }

    @Override
    public String toString() {
        return "Usuarios{" + "id=" + id + ", nombre=" + nombre + ", contrasena=" + contrasena + '}';
    }
}
