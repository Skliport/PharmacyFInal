
package Transacciones;
import java.sql.*;
import sqlConnection.mssqlConecction;
import Entities.User;
import java.util.ArrayList;

public class TUser {
    
    public static User user;
    
    // 1. Retorna el registro de la tabla [user] del usuario que inicia sesión
    public static boolean GetAuthUser(String username,String pass){
        try{
            String query="select * from getAuthenticatedUser(?,?)";
            PreparedStatement pstm = mssqlConecction.conn.prepareStatement(query);
            pstm.setString(1, username);
            pstm.setString(2, pass);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                TUser.user= new User();
                TUser.user.user_id=rs.getInt("user_id");
                TUser.user.admin_access=rs.getInt("admin_access");
                TUser.user.first_name=rs.getString("first_name");
                TUser.user.inventory_access=rs.getInt("inventory_access");
                TUser.user.last_name=rs.getString("last_name");
                TUser.user.order_access=rs.getInt("order_access");
                TUser.user.password=rs.getString("password");
                TUser.user.username=rs.getString("username");
                TUser.user.vendor_access=rs.getInt("vendor_access");
            }
            return true;
        }catch(Exception e){
            return false;
        }
    }
    // 2. Recupera la lista de usuarios
    public ArrayList<User> GetUsersAdminView(){
        ArrayList<User> Luser = new ArrayList<>();
        User u;
        try{
            String query =" Select * from UsuarioAdminView";
            Statement stm = mssqlConecction.conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                u = new User();
                u.user_id=rs.getInt("user_id");
                u.username = rs.getString("username");
                u.first_name = rs.getString("first_name");
                u.last_name = rs.getString("last_name");
                u.admin_access= rs.getInt("admin_access");
                u.vendor_access = rs.getInt("vendor_access");
                u.inventory_access = rs.getInt("inventory_access");
                u.order_access = rs.getInt("order_access");
                Luser.add(u);
            }
            return Luser;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
    // 3. Crea un usuario-login e inserta los datos de usuario en la tabla [user]
    public boolean createUser(String username,String login,String password,String first_name,String last_name,int admin,int vendor,int order, int inventory,String db){
        try{
            String query = "exec createUser ?,?,?,?,?,?,?,?,?,?";
            PreparedStatement pstm = mssqlConecction.conn.prepareStatement(query);
            pstm.setString(1, username);
            pstm.setString(2, login);
            pstm.setString(3, password);
            pstm.setString(4, first_name);
            pstm.setString(5, last_name);
            pstm.setInt(6, admin);
            pstm.setInt(7, vendor);
            pstm.setInt(8, inventory);
            pstm.setInt(9, order);
            pstm.setString(10, db);
            ResultSet rs = pstm.executeQuery();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
     // 3. Crea un usuario-login e inserta los datos de usuario en la tabla [user]
    public boolean createUserAdmin(String username,String login,String password,String first_name,String last_name,int admin,int vendor,int order, int inventory,String db){
        try{
            String query = "exec insertUserAdmin ?,?,?,?";
            PreparedStatement pstm = mssqlConecction.conn.prepareStatement(query);
            pstm.setString(1, username);
            pstm.setString(2, password);
            pstm.setString(3, first_name);
            pstm.setString(4, last_name);
            ResultSet rs = pstm.executeQuery();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    // 4. Actualizar usuario
    public boolean Update_user(int user_id,String first_name,String last_name,int vendor_access,int inventory_access,int order_access){
        try{
            String query="exec update_user ?,?,?,?,?,?";
            PreparedStatement pstm = mssqlConecction.conn.prepareStatement(query);
            pstm.setInt(1, user_id);
            pstm.setString(2, first_name);
            pstm.setString(3, last_name);
            pstm.setInt(4, vendor_access);
            pstm.setInt(5, inventory_access);
            pstm.setInt(6, order_access);
            pstm.executeUpdate();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    // 5. Eliminar usuario
    public boolean Delete_user(int user_id){
        try{
            String query = "exec delete_user ?";
            PreparedStatement pstm = mssqlConecction.conn.prepareStatement(query);
            pstm.setInt(1, user_id);
            pstm.executeUpdate();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
        
    }
    public boolean CheckUsername(String user_id){
        try{
            String query = "select * from checkIfUsernameExists(?)";
            PreparedStatement pstm = mssqlConecction.conn.prepareStatement(query);
            pstm.setString(1, user_id);
            ResultSet rs = pstm.executeQuery();
            boolean esta = false;
            while(rs.next()){
                if (rs.getInt("users_found")==1) {
                    esta=true;
                }else{
                    esta=false;
                }
            }
            return esta;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
        
    }
}
