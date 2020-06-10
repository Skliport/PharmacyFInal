/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IU;

import pruebabinarios.Binario;
import sqlConnection.mssqlConecction;

/**
 *
 * @author bryan
 */
public class index {
    public static void main(String[] args) {
        Binario bin= new Binario();
        vista_login vl;
        vista_configuracion vc;
        int resp = bin.Leer();
        switch (resp) {
            case 0:
                vl = new vista_login();
                vl.setVisible(true);
                vl.show();
                break;
            case 1:
                vc = new vista_configuracion();
                vc.setVisible(true);
                vc.show();
                break;
        }
    }
}
