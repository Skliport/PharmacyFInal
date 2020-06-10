/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebabinarios;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bryan
 */
public class Binario {
    public int Escribir(String serviceName,String Puerto){
        File file = new File("ConfiguracionBinario.bin");
        try {
            FileOutputStream FOS = new FileOutputStream(file);
            ObjectOutputStream OOS = new ObjectOutputStream(FOS);
            OOS.writeObject(new modeloPrueba(serviceName,Puerto));
            return 0;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Binario.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        } catch (IOException ex) {
            Logger.getLogger(Binario.class.getName()).log(Level.SEVERE, null, ex);
            return 2;
        }
    }
    public int Leer(){
        File file= new File("ConfiguracionBinario.bin");
        try {
            FileInputStream FIS = new FileInputStream(file);
            ObjectInputStream OIS = new ObjectInputStream(FIS);
            modeloPrueba objeto = (modeloPrueba)OIS.readObject();
            modeloPrueba.AquiSeGuardanLosDatosDeConexion= new modeloPrueba(objeto.ServiceName, objeto.Puerto);
            return 0;
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
            return 1;
        } catch (IOException ex) {
            System.out.println(ex);
            return 2;
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
            return 3;
        }
        
    }
}
