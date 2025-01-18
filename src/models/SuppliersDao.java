
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SuppliersDao {
    
    //Instaciomaos la conexion
    ConectionMySQL cn = new ConectionMySQL();
    //Estas variables sirve para conectarnos a la base de datos
    Connection conn;
    PreparedStatement pst;  //sirve para las consuktas
    ResultSet rs;  //sirve para obtener datos de la consulta
    
    /*//Variables que nos permitiran datos entre interfaces
    public static int id_customer = 0;
    public static String full_name_customer = "";
    public static String address_customer = "";
    public static String telephone_customer = "";
    public static String email_customer = "";*/
    
    
    //Registrar cliente
    public boolean registerSupplierQuery(Suppliers supplier){
        String query = "INSERT INTO suppliers (name,description, address, telephone, email,city, created, updated) VALUES (?,?,?,?,?,?,?,?)";
        
        Timestamp datetime = new Timestamp(new Date().getTime());
        
        try{
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.setString(1,supplier.getName());
            pst.setString(2, supplier.getDescription());
            pst.setString(3, supplier.getAddress());
            pst.setString(4, supplier.getTelephone());
            pst.setString(5, supplier.getEmail());
            pst.setString(6, supplier.getCity());
            pst.setTimestamp(7, datetime);
            pst.setTimestamp(8, datetime);
            
            pst.execute();
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al registrar al proveedor " + e);
            return false;
        }
        
    }
    
     //Listar proveedores o busqueda de proveedor
    public List listSuppliersQuery(String value){
        List<Suppliers> list_suppliers = new ArrayList();
        
        //creaos nuestra query
        String query = "SELECT * FROM suppliers ORDER BY name ASC";
        //query para buscar un empleado
        String query_search_supplier = "SELECT * FROM suppliers WHERE id LIKE '%" + value + "%'";
        
        try{
            conn = cn.getConnection();
            
            //si el usuario no ingresa nada en el cuadro de busqueda se ejcuta el if
            if(value.equalsIgnoreCase("")){
                pst = conn.prepareStatement(query);
                rs = pst.executeQuery();
            } else {  //si el usuario ingresa un dato en la busqueda se ejecuta el 2 query
                pst = conn.prepareStatement(query_search_supplier);
                rs = pst.executeQuery();
            }
             //si encuentra registros recoirre el la lista cuando se consulta
            while(rs.next()){
                
                Suppliers supplier = new Suppliers();
                supplier.setId(rs.getInt("id"));
                supplier.setName(rs.getString("name"));
                supplier.setDescription(rs.getString("description"));
                supplier.setAddress(rs.getString("address"));
                supplier.setTelephone(rs.getString("telephone"));
                supplier.setEmail(rs.getString("email"));
                supplier.setCity(rs.getString("city"));
                
                list_suppliers.add(supplier);
                
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,  e.toString());
        }
        
        return list_suppliers;
}
    
    
    //Modificar Cliente
    public boolean updateSupplierQuery(Suppliers supplier){
        //Creamos nuestra query que le vamos a pasar para agregar un empleado nuevo
        String query = "UPDATE supliers SET name = ?, description= ?, address= ?,telephone = ?, email = ?,city = ?, update = ? WHERE id = ?";
        //esta variable se utilizara para el create y el update
        Timestamp datetime = new Timestamp(new Date().getTime());
        try{
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            //accedemos a los setter del empleado para registrar los datos
            pst.setString(1, supplier.getName());
            pst.setString(2, supplier.getDescription());
            pst.setString(3, supplier.getAddress());
            pst.setString(4, supplier.getTelephone());
            pst.setString(5, supplier.getEmail());
            pst.setString(6, supplier.getCity());
            pst.setTimestamp(7, datetime);
            pst.setInt(8, supplier.getId());
            
            //ejecutamos la sentencia sql
            pst.execute();
            
            return true;
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al actualizar el proveedor " + e);
            return false;
        }
    }
    
    
    //Eliminar proveedor
    public boolean deleteSupplierQuery(int id){
        String query = "DELETE FROM suppliers WHERE ID = " + id;
        
        try{
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.execute();
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al eliminar un proveedor que tenga relacion con otra tabla ");
            return false;
        }
        }

    
}
