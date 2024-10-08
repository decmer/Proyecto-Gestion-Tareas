/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package programacionproyecto;

/**
 *
 * @author jose
 */

public enum Estados {
    EN_ESPERA("EN_ESPERA"), EN_REALIZACION("EN_REALIZACION"), TERMINADO("TERMINADO");
    
    private final String valor;
    
    public String name = this.name();
    
    Estados(String estado) {
        this.valor = estado;
    }
    
    public String getValor() {
        return valor;
    }
}
