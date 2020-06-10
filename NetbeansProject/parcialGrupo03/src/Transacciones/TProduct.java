package Transacciones;
import java.sql.*;
import Entities.Product;
import java.util.ArrayList;
import sqlConnection.mssqlConecction;

public class TProduct {
    
    // 1. Recupera la lista de productos para la pantalla de venta
    // Solamente recupera productos con un stock superior a 0
    public ArrayList<Product> GetProductView(){
        ArrayList<Product> LP = new ArrayList<>();
        try{
            String query = "Select * from Productos";
            Statement stm = mssqlConecction.conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                Product p = new Product();
                p.product_id = rs.getInt("product_id");
                p.product_name = rs.getString("product_name");
                p.product_brand = rs.getString("product_brand");
                p.unit_price = rs.getDouble("unit_price");
                p.units_in_stock = rs.getInt("units_in_stock");
                LP.add(p);
            }
            return LP;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
    
    // 2. Recupera la lista de productos completa para la pantalla de inventario
    public ArrayList<Product> GetProductoCompletoView(){
        ArrayList<Product> LP = new ArrayList<>();
        try{
            String query = "Select * from ProductosCompleto";
            Statement stm = mssqlConecction.conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                Product p = new Product();
                p.product_id = rs.getInt("product_id");
                p.product_brand = rs.getString("product_brand");
                p.product_name = rs.getString("product_name");
                p.unit_price = rs.getDouble("unit_price");
                p.units_in_stock = rs.getInt("units_in_stock");
                LP.add(p);
            }
            return LP;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
    
    // 3. Actualiza un producto
    public boolean UpdateProduct(int product_id,String product_name,String product_brand,double unit_price,int units_in_stock){
        try{
            String query = "exec update_product ?,?,?,?,?";
            PreparedStatement pstm = mssqlConecction.conn.prepareStatement(query);
            pstm.setInt(1, product_id);
            pstm.setString(2, product_name);
            pstm.setString(3, product_brand);
            pstm.setDouble(4, unit_price);
            pstm.setInt(5, units_in_stock);
            pstm.executeUpdate();
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    // 4. Reduce las unidades en stock para un producto [product]
    public boolean DUnits_Stock(int product_id,int quantity){
        try{
            String query ="exec decreaseUnits_stock ?,?";
            PreparedStatement pstm = mssqlConecction.conn.prepareStatement(query);
            pstm.setInt(1, product_id);
            pstm.setInt(2, quantity);
            ResultSet rs = pstm.executeQuery();
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    // 5. Aumenta las unidades en stock para un producto [product]
    public boolean IUnits_Stock(int product_id,int quantity){
        try{
            String query ="exec increaseUnits_stock ?,?";
            PreparedStatement pstm = mssqlConecction.conn.prepareStatement(query);
            pstm.setInt(1, product_id);
            pstm.setInt(2, quantity);
            ResultSet rs = pstm.executeQuery();
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    // 6. Inserta un nuevo producto en la tabla [product]
    public boolean InsertIntoProduct(String product_name,String product_brand,double unit_price, int quantity){
        try{
            String query = "exec insertIntoProduct ?,?,?,?";
            PreparedStatement pstm = mssqlConecction.conn.prepareStatement(query);
            pstm.setString(1, product_name);
            pstm.setString(2, product_brand);
            pstm.setDouble(3, unit_price);
            pstm.setInt(4, quantity);
            ResultSet rs = pstm.executeQuery();
            return true;
        }catch(Exception e){
            return false;
        }
    } 
    public boolean DeleteProduct(int product_id){
        String query = "exec delete_product ?";
        try{
            PreparedStatement pstm = mssqlConecction.conn.prepareStatement(query);
            pstm.setInt(1, product_id);
            pstm.executeUpdate();    
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
