package Transacciones;
import java.sql.*;
import Entities.Order;
import Entities.Order_detail;
import Entities.Product;
import java.util.ArrayList;
import sqlConnection.mssqlConecction;

public class TOrder {
    
    // 1. Recupera la lista de ordenes.
    public ArrayList<Order_detail> GetOrders(){   
        ArrayList<Order_detail> LOD = new ArrayList<>();
        try{
            String query = "Select * from Orders";
            Statement stm = mssqlConecction.conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                Order_detail od = new Order_detail();
                od.date=rs.getDate("date");
                od.order_id=rs.getInt("order_id");
                od.total = rs.getDouble("total");
                LOD.add(od);
            }
            return LOD;
        }catch(Exception e){
            return null;
        }
    }

    // 2. Remueve un registro de venta de la tabla[order] Y [order_detail]
    public boolean DeleteOrder(int oder_id){
        try{
            String query = "exec deleteOrder ?";
            PreparedStatement pstm = mssqlConecction.conn.prepareStatement(query);
            pstm.setInt(1, oder_id);
            pstm.executeUpdate();
            return true;
        }catch(Exception e){
           // System.out.print(e);
            return false;
        }
    }
    
    // 3. Inserta un detalle de producto temporal en la tabla [temp_order_detail]
    public boolean insertIntoTempOrderDetail(int product_id,double unit_price,int quantity,double discount){
        try{
            String query ="exec insertIntoTempOrderDetail ?,?,?,?";
            PreparedStatement pstm = mssqlConecction.conn.prepareStatement(query);
            pstm.setInt(1, product_id);
            pstm.setDouble(2, unit_price);
            pstm.setInt(3, quantity);
            pstm.setDouble(4, discount);
            ResultSet rs = pstm.executeQuery();
            return true;
        }catch(Exception e){
            return false;   
        }
    }
    
    // 4. Crea una orden de venta e inserta los registros de la tabla temporal
    // [temp_order_detail] en la tabla [order_detail] final */

    public boolean InsertNewOrder(){
        try{
            String query = "exec insertNewOrder";
            Statement stm = mssqlConecction.conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            return true;
        }catch(Exception e){
            return false;
        }
    } 
    
   // 5.Filtra los detalles de la orden por ID
    public ArrayList<Product> FiltrarOrdenId(int order_find){
        ArrayList<Product> PDD = new ArrayList<>();
        try
        {
           String query = "SELECT * FROM getOrderDetailById (?)";
           PreparedStatement pstm = mssqlConecction.conn.prepareStatement(query);
           pstm.setInt(1, order_find);
           ResultSet rs = pstm.executeQuery();
           while(rs.next()){  
                Product pp = new Product();
                pp.order_id = rs.getInt("order_id");   
                pp.order_detail_id = rs.getInt("order_detail_id");
                pp.product_name = rs.getString("product_name");
                pp.product_brand = rs.getString("product_brand");
                pp.unit_price = rs.getDouble("unit_price");
                pp.quantity = rs.getInt("quantity");
                pp.discount = rs.getDouble("discount");
                PDD.add(pp);
            }
            return PDD;
        
         }catch(Exception e){
            // System.out.print(e);
         return null;
         }
    }
    
    //6.Filtro de busqueda por order_id
    public ArrayList<Order_detail> FiltrarOrder(int order_find){      
        try
        {
            ArrayList<Order_detail> ODD = new ArrayList<>();
           String query = "SELECT * FROM getOrderById (?)";
           PreparedStatement pstm = mssqlConecction.conn.prepareStatement(query);
           pstm.setInt(1, order_find);
           ResultSet rs = pstm.executeQuery();
           while(rs.next()){  
                Order_detail od = new Order_detail();
                od.date=rs.getDate("date");
                od.order_id=rs.getInt("order_id");
                od.total = rs.getDouble("total");
                ODD.add(od);
            }
            return ODD;
        
         }catch(Exception e){
             System.out.print(e);
         return null;
         }
    }
    
}
