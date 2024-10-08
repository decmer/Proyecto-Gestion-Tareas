/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionproyecto;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

/**
 *
 * @author jose
 */
public class BaseDatos {
    private static ObjectContainer db = Db4o.openFile("baseDeDatos.db4o");;

    public static ObjectContainer abreBBDD(){
        if (db == null)
            db = Db4o.openFile("baseDeDatos.db4o");
        return db;
    }
    
    public static Usuarios encuentraUsu(int id){
        Query qu = getBBDD().query();
        qu.constrain(Usuarios.class);
        qu.descend("id").constrain(id).equal();
        ObjectSet oS = qu.execute();
        if (oS.hasNext()) {
            Usuarios usu = (Usuarios)oS.next();
            return usu;
        }
        return null;
    }
    
    public static ObjectContainer getBBDD() {
        return db;
    }

    public static boolean exitBBDD() {
        if (db == null)
            return false;
        else {
            db.close();
            db = null;
            return true;
        }
    }
}
