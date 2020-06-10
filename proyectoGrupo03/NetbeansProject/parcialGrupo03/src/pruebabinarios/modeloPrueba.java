/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebabinarios;

import java.io.Serializable;

/**
 *
 * @author bryan
 */
public class modeloPrueba implements Serializable{
    public String ServiceName,Puerto;
    public modeloPrueba(String ServiceName, String Puerto) {
        this.ServiceName = ServiceName;
        this.Puerto = Puerto;
    }
    public static modeloPrueba AquiSeGuardanLosDatosDeConexion;
}
