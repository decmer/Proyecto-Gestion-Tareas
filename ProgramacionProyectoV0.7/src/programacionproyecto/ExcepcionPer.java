/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package programacionproyecto;

/**
 *
 * @author jose
 */
public class ExcepcionPer extends Exception{

    /**
     * Creates a new instance of <code>ExcepcionPer</code> without detail
     * message.
     */
    public ExcepcionPer() {
    }

    /**
     * Constructs an instance of <code>ExcepcionPer</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public ExcepcionPer(String msg) {
        super(msg);
    }
}
