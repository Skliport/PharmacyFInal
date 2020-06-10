
package Transacciones;

import Entities.Log;
import java.util.ArrayList;
import sqlConnection.mssqlConecction;
import java.sql.*;

public class TLog {
    
    // 1. Recupera la lista de log general de la tabla [audit]
    public ArrayList<Log> GetLogs(){
        ArrayList<Log> LL = new ArrayList<>();
        try{
            String query = "Select * from GeneralLog";
            Statement stm = mssqlConecction.conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                Log l = new Log();
                l.action=rs.getString("action");
                l.date = rs.getDate("date");
                l.table = rs.getString("table");
                l.new_value = rs.getString("new_value");
                l.old_Value = rs.getString("old_value");
                l.username = rs.getString("username");
                LL.add(l);
            }
            return LL;
        }catch(Exception e){
            return null;
        }
    }
}
