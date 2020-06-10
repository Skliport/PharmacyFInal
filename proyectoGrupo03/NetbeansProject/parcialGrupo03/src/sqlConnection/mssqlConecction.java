
package sqlConnection;
import Transacciones.TUser;
import java.sql.*;
import sun.security.util.Debug;
import pruebabinarios.modeloPrueba;

public class mssqlConecction {
    
    private String classname="com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static Connection conn;
    
    // Establece una nueva conexión a la base de datos.
    public boolean StartConnection(String user,String pass){
        try{
            Class.forName(classname);
            String connectionURL = "jdbc:sqlserver://"+modeloPrueba.AquiSeGuardanLosDatosDeConexion.ServiceName+":"+modeloPrueba.AquiSeGuardanLosDatosDeConexion.Puerto
                +";databaseName=pharmacy;user="+ user +";password="+ pass +";";
            
            mssqlConecction.conn = DriverManager.getConnection(connectionURL);
            System.out.println("Connection is working");
            
            // Retorna el registro de la tabla [user] para el usuario autenticado.
            TUser.GetAuthUser(user, pass);
            
            return true;
        }catch(Exception e){
            System.out.println("Connection failed");
            System.out.println(e);
            return false;
        }
    }
}
